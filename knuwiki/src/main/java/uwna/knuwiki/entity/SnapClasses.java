package uwna.knuwiki.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
//@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "snap_classes")
public class SnapClasses extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "snap_classes_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "snapshot_id")
    private Snapshot snapshot;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classification_id")
    private Classification classes;
}
