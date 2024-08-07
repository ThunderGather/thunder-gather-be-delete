package thundergather.thundergatherbe.auth.kakao.dto;


import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import thundergather.thundergatherbe.member.entity.Member;
import thundergather.thundergatherbe.member.entity.type.MemberRoleType;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KaKaoSignUpDto {

      @Email
      private String email;

      public static Member toEntity(KaKaoSignUpDto request) {
            return Member.builder()
                    .email(request.getEmail())
                    .roleType(MemberRoleType.USER)
                    .build();
      }

}
