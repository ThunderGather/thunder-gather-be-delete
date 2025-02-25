package thundergather.thundergatherbe.auth.web;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import thundergather.thundergatherbe.auth.dto.LogoutDto;
import thundergather.thundergatherbe.auth.dto.ReissueDto;
import thundergather.thundergatherbe.auth.dto.SignInDto;
import thundergather.thundergatherbe.auth.dto.SignUpDto;
import thundergather.thundergatherbe.auth.jwt.dto.TokenDto;
import thundergather.thundergatherbe.auth.service.AuthService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/auth")
public class AuthController {

      private final AuthService authService;
      // private final MailService mailService;



      public ResponseEntity<SignUpDto> signUp(@RequestPart("request") SignUpDto request,
              @RequestPart(name = "image", required = false) MultipartFile image) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(authService.signUp(request, image));
      }

      @PostMapping(path = "/signin1", consumes = MediaType.APPLICATION_JSON_VALUE,
              produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<TokenDto> signIn1(@RequestBody SignInDto request) {
            return ResponseEntity.ok(authService.signIn(request));
      }

      @PostMapping(path = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE,
              produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<TokenDto> signIn(@RequestBody SignInDto request) {
            return ResponseEntity.ok(authService.signIn(request));
      }

      @PostMapping(path = "/logout", consumes = MediaType.APPLICATION_JSON_VALUE,
              produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<?> logout(@RequestBody LogoutDto request) {

            authService.logout(request);
            return ResponseEntity.status(HttpStatus.OK).body("로그아웃 성공");
      }

      @PostMapping(path = "/reissue", consumes = MediaType.APPLICATION_JSON_VALUE,
              produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<TokenDto> reissue(@Valid @RequestBody ReissueDto request) {

            return ResponseEntity.ok(authService.reissue(request));
      }

}
