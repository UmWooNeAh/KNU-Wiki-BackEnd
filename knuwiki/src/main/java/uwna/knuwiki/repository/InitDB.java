package uwna.knuwiki.repository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import uwna.knuwiki.entity.*;

@Profile("local")
@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.init();
    }

    @ComponentScan
    static class InitService {

        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init() {
            //PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            Member memberA = new Member(null, "userA", "1234"
                    , null, Role.ROLE_USER, State.정상);

            for (int i=1;i<=100;i++) {
                Document doc = new Document(null, "문서 " + (i));
                Content content = new Content(null, memberA, doc, "r1", null, null, i + "번째 문서의 본문입니다.");
                em.persist(doc); em.persist(content);
            }

            em.persist(memberA);

        }

    }
}
