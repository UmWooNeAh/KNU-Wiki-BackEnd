package uwna.knuwiki.persistence_entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import uwna.knuwiki.entity.SnapClasses;


/**
 * QSnapClasses is a Querydsl query type for SnapClasses
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSnapClasses extends EntityPathBase<SnapClasses> {

    private static final long serialVersionUID = 1981526L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSnapClasses snapClasses = new QSnapClasses("snapClasses");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QClassification classes;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSnapshot snapshot;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QSnapClasses(String variable) {
        this(SnapClasses.class, forVariable(variable), INITS);
    }

    public QSnapClasses(Path<? extends SnapClasses> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSnapClasses(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSnapClasses(PathMetadata metadata, PathInits inits) {
        this(SnapClasses.class, metadata, inits);
    }

    public QSnapClasses(Class<? extends SnapClasses> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.classes = inits.isInitialized("classes") ? new QClassification(forProperty("classes"), inits.get("classes")) : null;
        this.snapshot = inits.isInitialized("snapshot") ? new QSnapshot(forProperty("snapshot"), inits.get("snapshot")) : null;
    }

}

