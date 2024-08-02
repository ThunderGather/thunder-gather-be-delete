package thundergather.thundergatherbe.post.dto;

import jakarta.validation.constraints.NotNull;
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
public class PostRequest {

      @NotNull(message = "제목을 입력해주세요.")
      private String title;

      @NotNull(message = "날짜를 입력해주세요.")
      private LocalDate desiredDate;

      @NotNull(message = "시간을 입력해주세요.")
      private LocalTime desiredTime;

      @NotNull(message = "카테고리를 입력해주세요.")
      private String category;

      @NotNull
      private Integer maxParticipants;

      @NotNull
      private String description;

      @NotNull(message = "위치를 입력해주세요.")
      private String location;

      private String openChatUrl;


      public Post toEntity() {
            return Post.builder()
                    .title(title)
                    .desiredDate(desiredDate)
                    .desiredTime(desiredTime)
                    .category(category)
                    .maxParticipants(maxParticipants)
                    .description(description)
                    .location(location)
                    .openChatUrl(openChatUrl)
                    .build();
      }


}
