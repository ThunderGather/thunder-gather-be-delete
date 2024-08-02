package thundergather.thundergatherbe.member.service.impl;

import static thundergather.thundergatherbe.global.exception.type.ErrorCode.DUPLICATE_MEETING;
import static thundergather.thundergatherbe.global.exception.type.ErrorCode.OVER_MAX_PARTICIPANTS;
import static thundergather.thundergatherbe.global.exception.type.ErrorCode.POST_NOT_FOUND;
import static thundergather.thundergatherbe.global.exception.type.ErrorCode.USER_NOT_FOUND;
import static thundergather.thundergatherbe.global.exception.type.ErrorCode.WRITE_NOT_YOURSELF;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thundergather.thundergatherbe.global.exception.GlobalException;
import thundergather.thundergatherbe.meeting.entity.Meeting;
import thundergather.thundergatherbe.meeting.repository.MeetingRepository;
import thundergather.thundergatherbe.member.entity.Member;
import thundergather.thundergatherbe.member.repository.MemberRepository;
import thundergather.thundergatherbe.member.service.MeetingService;
import thundergather.thundergatherbe.post.dto.PostListResponse;
import thundergather.thundergatherbe.post.dto.PostResponse;
import thundergather.thundergatherbe.post.entity.Post;
import thundergather.thundergatherbe.post.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {

      private final MeetingRepository meetingRepository;
      private final MemberRepository memberRepository;
      private final PostRepository postRepository;

      @Override
      public PostResponse applyMeeting(Long postId, String email) {
            Post post = getPost(postId);
            Member member = getMember(email);

            // 멤버가 이미 신청했다면 중복 에러 처리
            if (meetingRepository.existsByPostAndMember(post, member)) {
                  throw new GlobalException(DUPLICATE_MEETING);
            }
            // 모임 최대 인원수가 넘었는지 확인
            if (post.getMeetingMembers().size() >= post.getMaxParticipants()) {
                  throw new GlobalException(OVER_MAX_PARTICIPANTS);
            }
            // 모임 신청
            Meeting meeting = Meeting.builder()
                    .post(post)
                    .member(member)
                    .author(false)
                    .build();

            // 모임, 멤버, 포스트에 추가
            meeting.addPostAndMember(post, member);

            meetingRepository.save(meeting);

            return PostResponse.fromEntity(post);
      }

      @Override
      public List<PostListResponse> myMeetingList(String email) {
            Member member = getMember(email);

            // 내가 참가한 모임 리스트
            return meetingRepository.findByMemberId(member.getId()).stream()
                    .map(Meeting::getPost)
                    .map(PostListResponse::fromEntity)
                    .toList();
      }

      @Transactional
      @Override
      public void cancelMeeting(Long postId, String email) {
            Post post = getPost(postId);
            Member member = getMember(email);

            // 모임에 참가한 멤버가 아니라면 에러 처리
            if (!meetingRepository.existsByPostAndMember(post, member)) {
                  throw new GlobalException(WRITE_NOT_YOURSELF);
            }

            // 모임 신청 취소
            meetingRepository.deleteByPostAndMember(post, member);
      }



      private Post getPost(Long postId) {
            return postRepository.findById(postId)
                    .orElseThrow(() -> new GlobalException(POST_NOT_FOUND));
      }

      private Member getMember(String email) {

            return memberRepository.findByEmail(email)
                    .orElseThrow(() -> new GlobalException(USER_NOT_FOUND));
      }
}
