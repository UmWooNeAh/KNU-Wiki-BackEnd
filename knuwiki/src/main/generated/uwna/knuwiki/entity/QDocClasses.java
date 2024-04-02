package uwna.knuwiki.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDocClasses is a Querydsl query type for DocClasses
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDocClasses extends EntityPathBase<DocClasses> {

    private static final long serialVersionUID = 766367028L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDocClasses docClasses = new QDocClasses("docClasses");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QClassification classes;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final QDocument document;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QDocClasses(String variable) {
        this(DocClasses.class, forVariable(variable), INITS);
    }

    public QDocClasses(Path<? extends DocClasses> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDocClasses(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDocClasses(PathMetadata metadata, PathInits inits) {
        this(DocClasses.class, metadata, inits);
    }

    public QDocClasses(Class<? extends DocClasses> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.classes = inits.isInitialized("classes") ? new QClassification(forProperty("classes"), inits.get("classes")) : null;
        this.document = inits.isInitialized("document") ? new QDocument(forProperty("document")) : null;
    }

}

