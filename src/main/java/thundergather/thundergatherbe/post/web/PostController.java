package thundergather.thundergatherbe.post.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thundergather.thundergatherbe.auth.config.LoginUser;
import thundergather.thundergatherbe.post.dto.PostRequest;
import thundergather.thundergatherbe.post.dto.PostResponse;
import thundergather.thundergatherbe.post.dto.PostUpdateRequest;
import thundergather.thundergatherbe.post.service.PostService;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

      private final PostService postService;

      @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
              produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<PostResponse> createPost(@Valid @RequestBody PostRequest request,
              @LoginUser String email) {
            return ResponseEntity.ok(postService.createPost(request, email));
      }


      @PatchMapping(path = "/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE,
              produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<PostResponse> updatePost(@PathVariable Long postId,
              @Valid @RequestBody PostUpdateRequest request, @LoginUser String email) {
            return ResponseEntity.ok(postService.updatePost(postId, request, email));
      }

      @DeleteMapping(path = "/{postId}",
              produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<?> deletePost(@PathVariable Long postId, @LoginUser String email) {
            postService.deletePost(postId, email);
            return ResponseEntity.ok("게시물 삭제 성공");
      }

      @GetMapping(path = "{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<PostResponse> getPost(@PathVariable Long postId) {
            return ResponseEntity.ok(postService.postDetail(postId));
      }

      @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<?> getPostList() {
            return ResponseEntity.ok(postService.postList());
      }
}
