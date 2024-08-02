package thundergather.thundergatherbe.post.dto;


import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import thundergather.thundergatherbe.post.entity.Post;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostUpdateRequest {

      private String title;
      private LocalDate desiredDate;
      private LocalTime desiredTime;
      private String category;
      private Integer maxParticipants;
      private String description;
      private String location;
      private String openChatUrl;

      public void updatePostEntity(Post post) {
            Post.PostBuilder postBuilder = post.toBuilder();
            if (title != null) {
                  postBuilder.title(title);
            }
            if (category != null) {
                  postBuilder.category(category);
            }
            if (desiredDate != null) {
                  postBuilder.desiredDate(desiredDate);
            }
            if (desiredTime != null) {
                  postBuilder.desiredTime(desiredTime);
            }
            if (maxParticipants != null) {
                  postBuilder.maxParticipants(maxParticipants);
            }
            if (description != null) {
                  postBuilder.description(description);
            }
            if (location != null) {
                  postBuilder.location(location);
            }
            if (openChatUrl != null) {
                  postBuilder.openChatUrl(openChatUrl);
            }

            post.updatePost(postBuilder.build());
      }
}
