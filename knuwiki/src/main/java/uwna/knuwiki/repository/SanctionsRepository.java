package uwna.knuwiki.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uwna.knuwiki.entity.Sanctions;

import java.util.List;

public interface SanctionsRepository extends JpaRepository<Sanctions, Long> {

    @Query("select s from Sanctions s where s.member.id = :id")
    List<Sanctions> findAllByMemberId(@Param("id") String memberId);

}
