package thundergather.thundergatherbe.auth.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import thundergather.thundergatherbe.member.entity.Member;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomUserDto {

      private Long id;
      private String email;
      private String password;
      private String roleType;

      public static CustomUserDto fromEntity(Member member) {
            return CustomUserDto.builder()
                    .id(member.getId())
                    .email(member.getEmail())
                    .password(member.getPassword())
                    .roleType(member.getRoleType().getCode())
                    .build();
      }

}
