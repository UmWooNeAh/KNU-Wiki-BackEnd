package uwna.knuwiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uwna.knuwiki.entity.Member;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByUsername(String username);


}
