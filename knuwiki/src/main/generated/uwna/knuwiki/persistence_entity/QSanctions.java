package uwna.knuwiki.persistence_entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import uwna.knuwiki.entity.Sanctions;


/**
 * QSanctions is a Querydsl query type for Sanctions
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSanctions extends EntityPathBase<Sanctions> {

    private static final long serialVersionUID = -344323306L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSanctions sanctions = new QSanctions("sanctions");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath category = createString("category");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final StringPath reason = createString("reason");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QSanctions(String variable) {
        this(Sanctions.class, forVariable(variable), INITS);
    }

    public QSanctions(Path<? extends Sanctions> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSanctions(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSanctions(PathMetadata metadata, PathInits inits) {
        this(Sanctions.class, metadata, inits);
    }

    public QSanctions(Class<? extends Sanctions> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

