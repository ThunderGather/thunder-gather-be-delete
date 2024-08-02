package thundergather.thundergatherbe.meeting.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thundergather.thundergatherbe.meeting.entity.Meeting;
import thundergather.thundergatherbe.member.entity.Member;
import thundergather.thundergatherbe.post.entity.Post;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

      List<Meeting> findByPostId(Long postId);

      boolean existsByPostAndMember(Post post, Member member);

      List<Meeting> findByMemberId(Long memberId);

      void deleteByPostAndMember(Post post, Member member);
}
