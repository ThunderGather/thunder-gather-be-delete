package thundergather.thundergatherbe.post.service;

import java.util.List;
import thundergather.thundergatherbe.post.dto.PostListResponse;
import thundergather.thundergatherbe.post.dto.PostRequest;
import thundergather.thundergatherbe.post.dto.PostResponse;
import thundergather.thundergatherbe.post.dto.PostUpdateRequest;

public interface PostService {


      // 게시물 생성
      PostResponse createPost(PostRequest request, String email);

      // 게시물 수정
      PostResponse updatePost(Long postId, PostUpdateRequest request, String email);

      // 게시물 삭제
      void deletePost(Long postId, String email);

      //게시물 상세 조회
      PostResponse postDetail(Long postId);

      //게시물 전체 리스트 조회
      List<PostListResponse> postList();

}
