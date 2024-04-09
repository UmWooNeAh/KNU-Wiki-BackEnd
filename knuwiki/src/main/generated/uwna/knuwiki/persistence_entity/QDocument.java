package uwna.knuwiki.persistence_entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import uwna.knuwiki.entity.Document;
import uwna.knuwiki.entity.DocumentType;


/**
 * QDocument is a Querydsl query type for Document
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDocument extends EntityPathBase<Document> {

    private static final long serialVersionUID = -1003271167L;

    public static final QDocument document = new QDocument("document");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<DocumentType> type = createEnum("type", DocumentType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QDocument(String variable) {
        super(Document.class, forVariable(variable));
    }

    public QDocument(Path<? extends Document> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDocument(PathMetadata metadata) {
        super(Document.class, metadata);
    }

}

