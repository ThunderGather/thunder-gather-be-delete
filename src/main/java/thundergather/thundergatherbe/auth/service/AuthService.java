package thundergather.thundergatherbe.auth.service;


import org.springframework.web.multipart.MultipartFile;
import thundergather.thundergatherbe.auth.dto.LogoutDto;
import thundergather.thundergatherbe.auth.dto.ReissueDto;
import thundergather.thundergatherbe.auth.dto.SignInDto;
import thundergather.thundergatherbe.auth.dto.SignUpDto;
import thundergather.thundergatherbe.auth.jwt.dto.TokenDto;

public interface AuthService {

      SignUpDto signUp(SignUpDto request, MultipartFile multipartFile);

      TokenDto signIn(SignInDto request);

      void logout(LogoutDto request);

      TokenDto reissue(ReissueDto request);


}
