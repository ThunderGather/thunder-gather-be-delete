package thundergather.thundergatherbe.post.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import thundergather.thundergatherbe.post.entity.Post;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PostListResponse {

      private Long postId;
      private Long memberId;
      private String category;
      private String title;
      private LocalDateTime dateTime;
      private List<MemberDto> participants;

      @Getter
      @NoArgsConstructor(access = AccessLevel.PROTECTED)
      @AllArgsConstructor
      @Builder
      public static class MemberDto {

            private Long id;
            private String nickname;
            private String profileImageUrl;
      }


      public static PostListResponse fromEntity(Post post) {
            List<MemberDto> memberList = post.getMeetingMembers().stream()
                    .map(meeting -> MemberDto.builder()
                            .id(meeting.getMember().getId())
                            .nickname(meeting.getMember().getNickname())
                            .profileImageUrl(meeting.getMember().getProfileImageUrl())
                            .build())
                    .toList();

            return PostListResponse.builder()
                  .postId(post.getId())
                  .memberId(post.getMember().getId())
                  .category(post.getCategory())
                  .title(post.getTitle())
                  .dateTime(LocalDateTime.of(post.getDesiredDate(), post.getDesiredTime()))
                  .participants(memberList)
                  .build();
      }
}
