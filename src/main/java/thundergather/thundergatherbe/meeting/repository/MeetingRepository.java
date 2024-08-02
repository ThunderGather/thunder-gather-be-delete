package thundergather.thundergatherbe.meeting.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thundergather.thundergatherbe.meeting.entity.Meeting;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

      List<Meeting> findByPostId(Long postId);
}
