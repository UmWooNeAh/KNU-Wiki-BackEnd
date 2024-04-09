package uwna.knuwiki.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uwna.knuwiki.entity.QSnapshot;
import uwna.knuwiki.paging.ArticlePage;
import uwna.knuwiki.paging.PagingDto;
import uwna.knuwiki.paging.QPagingDto;

import java.util.List;
import java.util.Optional;

import static uwna.knuwiki.entity.QSnapshot.*;
import static uwna.knuwiki.entity.QDocument.*;


@Repository
@Transactional
@Slf4j
@RequiredArgsConstructor
public class DocumentRepository {

    @Autowired
    private final JPAQueryFactory queryFactory;

    @Autowired
    private final EntityManager em;

    public Integer getDocCounts(String search) {
        return Optional.ofNullable(queryFactory
                .select(snapshot.count())
                .from(snapshot)
                .join(snapshot.document, document)
                .where(snapshot.name.contains(search))
                .fetchOne()).orElse(0L).intValue();

    }

    public List<PagingDto> findCurrentPageDocs(ArticlePage paging, String username) {
        return queryFactory
                .select(new QPagingDto(QSnapshot.snapshot.name,
                        QSnapshot.snapshot.createdMember.username,
                        QSnapshot.snapshot.texts
                ))
                .from(QSnapshot.snapshot)
                .join(QSnapshot.snapshot.document, document)
                .where(QSnapshot.snapshot.createdMember.username.eq(username))
                .offset(paging.getOffset())
                .limit(paging.getDocsPerPage())
                .fetch();
        //return new PageImpl<>(snapshots, pageable, snapshots.size());
    }

    public Page<PagingDto> findAllDocs(ArticlePage paging, Pageable pageable, String username) {
        List<PagingDto> snapshots = queryFactory
                .select(new QPagingDto(snapshot.name,
                        snapshot.createdMember.username,
                        QSnapshot.snapshot.texts
                ))
                .from(QSnapshot.snapshot)
                .join(QSnapshot.snapshot.document, document)
                .where(QSnapshot.snapshot.createdMember.username.eq(username))
                .fetch();

        return new PageImpl<>(snapshots, pageable, snapshots.size());

    }


}
