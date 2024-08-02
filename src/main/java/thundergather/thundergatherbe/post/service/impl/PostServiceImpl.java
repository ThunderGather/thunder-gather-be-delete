package thundergather.thundergatherbe.post.service.impl;

import static thundergather.thundergatherbe.global.exception.type.ErrorCode.*;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thundergather.thundergatherbe.global.exception.GlobalException;
import thundergather.thundergatherbe.meeting.entity.Meeting;
import thundergather.thundergatherbe.meeting.repository.MeetingRepository;
import thundergather.thundergatherbe.member.entity.Member;
import thundergather.thundergatherbe.member.repository.MemberRepository;
import thundergather.thundergatherbe.post.dto.PostListResponse;
import thundergather.thundergatherbe.post.dto.PostRequest;
import thundergather.thundergatherbe.post.dto.PostResponse;
import thundergather.thundergatherbe.post.dto.PostUpdateRequest;
import thundergather.thundergatherbe.post.entity.Post;
import thundergather.thundergatherbe.post.repository.PostRepository;
import thundergather.thundergatherbe.post.service.PostService;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

      private final PostRepository postRepository;
      private final MemberRepository memberRepository;
      private final MeetingRepository meetingRepository;

      @Override
      public PostResponse postDetail(Long postId) {
            return PostResponse.fromEntity(getPost(postId));
      }

      @Override
      public List<PostListResponse> postList() {
            return postRepository.findAll().stream()
                    .map(PostListResponse::fromEntity)
                    .toList();
      }

      @Override
      public PostResponse createPost(PostRequest request, String email) {

            Member member = getMember(email);
            Post post = request.toEntity();

            member.addPost(post);

            Post savedPost = postRepository.save(post);

            Meeting meeting = Meeting.builder()
                    .post(savedPost)
                    .member(member)
                    .author(true)
                    .build();

            savedPost.addMeetingMember(meeting);
            member.addMeetingMemberList(meeting);

            meetingRepository.save(meeting);

            return PostResponse.fromEntity(savedPost);

      }

      @Override
      public PostResponse updatePost(Long postId, PostUpdateRequest request, String email) {
            Member member = getMember(email);
            Post savedPost = getPost(postId);

            validationPost(savedPost, member);

            request.updatePostEntity(savedPost);

            postRepository.save(savedPost);

            return PostResponse.fromEntity(savedPost);

      }

      @Override
      @Transactional
      public void deletePost(Long postId, String email) {
            Member member = getMember(email);
            Post post = getPost(postId);

            validationPost(post, member);

            // 스트림을 사용하여 모든 Meeting 엔티티를 삭제
            meetingRepository.findByPostId(postId).forEach(meeting -> {
                  member.removeMeetingMemberList(meeting);
                  meetingRepository.delete(meeting);
            });

            // 게시물 삭제
            member.removePost(post);
            postRepository.delete(post);
      }

      private Post getPost(Long postId) {
            return postRepository.findById(postId)
                    .orElseThrow(() -> new GlobalException(POST_NOT_FOUND));
      }

      private Member getMember(String email) {

            return memberRepository.findByEmail(email)
                    .orElseThrow(() -> new GlobalException(USER_NOT_FOUND));
      }

      private void validationPost(Post post, Member member) {
            if (!post.getMember().getEmail().equals(member.getEmail())) {
                  throw new GlobalException(WRITE_NOT_YOURSELF);
            }
      }

}
