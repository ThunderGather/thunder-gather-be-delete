package thundergather.thundergatherbe.member.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import thundergather.thundergatherbe.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

      Optional<Member> findByEmail(String email);

      boolean existsByEmail(String email);

      boolean existsByNickname(String nickname);

}
