package uwna.knuwiki.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
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
import uwna.knuwiki.entity.QContent;
import uwna.knuwiki.paging.ArticlePage;
import uwna.knuwiki.paging.PagingDto;
import uwna.knuwiki.paging.QPagingDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static uwna.knuwiki.entity.QDocument.document;


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
                .select(document.count())
                .from(document)
                .where(document.name.contains(search))
                .fetchOne()).orElse(0L).intValue();

    }

    public Page<PagingDto> findCurrentPageDocs(ArticlePage paging, Pageable pageable) {
        List<PagingDto> content = queryFactory
                .select(new QPagingDto(document.name,
                        QContent.content.createdMember.username,
                        QContent.content.texts
                ))
                .from(QContent.content)
                .join(QContent.content.document, document)
                .where(QContent.content.createdMember.username.eq("userA"))
                .offset(paging.getOffset())
                .limit(paging.getDocsPerPage())
                .fetch();

        return new PageImpl<>(content, pageable, content.size());
    }

    public Page<PagingDto> findAllDocs(ArticlePage paging, Pageable pageable) {
        List<PagingDto> content = queryFactory
                .select(new QPagingDto(document.name,
                        QContent.content.createdMember.username,
                        QContent.content.texts
                ))
                .from(QContent.content)
                .join(QContent.content.document, document)
                .where(QContent.content.createdMember.username.eq("userA"))
                .fetch();

        return new PageImpl<>(content, pageable, content.size());

    }


}
