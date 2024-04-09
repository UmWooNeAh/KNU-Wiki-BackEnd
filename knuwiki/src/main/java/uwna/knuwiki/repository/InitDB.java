package uwna.knuwiki.repository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import uwna.knuwiki.entity.Document;
import uwna.knuwiki.entity.Member;
import uwna.knuwiki.entity.Snapshot;
import uwna.knuwiki.entity.DocumentType;
import uwna.knuwiki.entity.Role;
import uwna.knuwiki.entity.State;

import java.time.LocalDateTime;

@Profile("local")
@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.init();
        initService.reVersionTest();
    }

    @ComponentScan
    static class InitService {

        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init() {
            //PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            Member memberA = new Member(null, "sm1108shin@knu.ac.kr", "$2a$10$jGKW1N5biMNXRqbHylWn8eJ8FoD0JOUOW8d3KQdMXmKGDY8DlLTmS"
                    , Role.ROLE_USER, State.정상);

            for (int i=1;i<=100;i++) {
                Document doc = new Document(null, DocumentType.문서);
                Snapshot snapshot = new Snapshot(null, memberA, doc, i + " 번째 버전", null, i + "번째 문서의 본문입니다.");
                em.persist(doc); em.persist(snapshot);
            }

            em.persist(memberA);
        }

        @Transactional
        public void reVersionTest() {
            Snapshot reVersion = em.find(Snapshot.class, 13);
            reVersion.setUpdatedDate(LocalDateTime.now());
        }

    }
}
