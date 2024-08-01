package thundergather.thundergatherbe.auth.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import thundergather.thundergatherbe.member.entity.Member;
import thundergather.thundergatherbe.member.entity.type.MemberRoleType;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SignUpDto {

      @Email
      private String email;

      @NotBlank
      private String password;

      @NotBlank
      private String nickname;


      private String profileImageUrl;

      public static SignUpDto fromEntity(Member member) {
            return SignUpDto.builder()
                    .email(member.getEmail())
                    .password("password")
                    .nickname(member.getNickname())
                    .profileImageUrl(member.getProfileImageUrl())
                    .build();
      }

      public static Member signUpForm(SignUpDto request, String encodedPasswordEncoder) {
            return Member.builder()
                    .email(request.getEmail())
                    .password(encodedPasswordEncoder)
                    .nickname(request.getNickname())
                    .roleType(MemberRoleType.USER)
                    .profileImageUrl(request.getProfileImageUrl())
                    .build();
      }

}
