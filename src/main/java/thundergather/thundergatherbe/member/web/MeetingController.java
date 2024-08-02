package thundergather.thundergatherbe.member.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thundergather.thundergatherbe.auth.config.LoginUser;
import thundergather.thundergatherbe.member.service.MeetingService;
import thundergather.thundergatherbe.post.dto.PostResponse;

@RestController
@RequestMapping("/api/v1/meeting")
@RequiredArgsConstructor
public class MeetingController {

      private final MeetingService meetingService;

      @PostMapping(path = "/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<PostResponse> applyMeeting(@PathVariable Long postId, @LoginUser String email) {
            return ResponseEntity.ok(meetingService.applyMeeting(postId, email));
      }

      @GetMapping(path = "/mymeeting", produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<?> myMeetingList(@LoginUser String email) {
            return ResponseEntity.ok(meetingService.myMeetingList(email));
      }

      @DeleteMapping(path = "/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<?> cancelMeeting(@PathVariable Long postId, @LoginUser String email) {
            meetingService.cancelMeeting(postId, email);
            return ResponseEntity.ok("모임 취소 성공");
      }


}
