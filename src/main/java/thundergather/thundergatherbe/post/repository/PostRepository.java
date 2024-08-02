package thundergather.thundergatherbe.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thundergather.thundergatherbe.post.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
