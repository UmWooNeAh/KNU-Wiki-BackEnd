package uwna.knuwiki.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClassification is a Querydsl query type for Classification
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClassification extends EntityPathBase<Classification> {

    private static final long serialVersionUID = 1849059916L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClassification classification = new QClassification("classification");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QClassification parent;

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

