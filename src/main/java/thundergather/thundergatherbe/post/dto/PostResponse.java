package thundergather.thundergatherbe.post.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
public class PostResponse {

      private Long postId;
      private Long memberId;
      private String memberEmail;
      private String title;
      private LocalDate desiredDate;
      private LocalTime desiredTime;
      private String category;
      private Integer maxParticipants;
      private Integer participants;
      private String description;
      private String location;
      private String openChatUrl;
      private LocalDateTime createdAt;
      private LocalDateTime updatedAt;
      private List<MemberDto> members;

      @Getter
      @NoArgsConstructor(access = AccessLevel.PROTECTED)
      @AllArgsConstructor
      @Builder
      public static class MemberDto {

            private String nickname;
            private String profileImageUrl;
            private boolean isAuthor;
      }

      public static PostResponse fromEntity(Post post) {
            List<MemberDto> members = post.getMeetingMembers().stream()
                    .map(meeting -> MemberDto.builder()
                            .nickname(meeting.getMember().getNickname())
                            .profileImageUrl(meeting.getMember().getProfileImageUrl())
                            .isAuthor(meeting.isAuthor())
                            .build())
                    .toList();

            return PostResponse.builder()
                    .postId(post.getId())
                    .memberId(post.getMember().getId())
                    .memberEmail(post.getMember().getEmail())
                    .title(post.getTitle())
                    .desiredDate(post.getDesiredDate())
                    .desiredTime(post.getDesiredTime())
                    .category(post.getCategory())
                    .maxParticipants(post.getMaxParticipants())
                    .participants(members.size())
                    .description(post.getDescription())
                    .location(post.getLocation())
                    .openChatUrl(post.getOpenChatUrl())
                    .createdAt(post.getCreatedAt())
                    .updatedAt(post.getUpdatedAt())
                    .members(members)
                    .build();
      }
}
