package uwna.knuwiki.persistence_entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import uwna.knuwiki.entity.Snapshot;


/**
 * QSnapshot is a Querydsl query type for Snapshot
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSnapshot extends EntityPathBase<Snapshot> {

    private static final long serialVersionUID = -1580117846L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSnapshot snapshot = new QSnapshot("snapshot");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final QMember createdMember;

    public final QDocument document;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath texts = createString("texts");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QSnapshot(String variable) {
        this(Snapshot.class, forVariable(variable), INITS);
    }

    public QSnapshot(Path<? extends Snapshot> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSnapshot(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSnapshot(PathMetadata metadata, PathInits inits) {
        this(Snapshot.class, metadata, inits);
    }

    public QSnapshot(Class<? extends Snapshot> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdMember = inits.isInitialized("createdMember") ? new QMember(forProperty("createdMember")) : null;
        this.document = inits.isInitialized("document") ? new QDocument(forProperty("document")) : null;
    }

}

