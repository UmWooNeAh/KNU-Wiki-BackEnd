package uwna.knuwiki.persistence_entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import uwna.knuwiki.entity.Classification;
import uwna.knuwiki.entity.DocumentType;


/**
 * QClassification is a Querydsl query type for Classification
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClassification extends EntityPathBase<Classification> {

    private static final long serialVersionUID = 1849059916L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClassification classification = new QClassification("classification");

    public final QDocument _super = new QDocument(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final QClassification parent;

    //inherited
    public final EnumPath<DocumentType> type = _super.type;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QClassification(String variable) {
        this(Classification.class, forVariable(variable), INITS);
    }

    public QClassification(Path<? extends Classification> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClassification(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClassification(PathMetadata metadata, PathInits inits) {
        this(Classification.class, metadata, inits);
    }

    public QClassification(Class<? extends Classification> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QClassification(forProperty("parent"), inits.get("parent")) : null;
    }

}

