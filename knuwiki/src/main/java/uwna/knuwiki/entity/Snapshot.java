package uwna.knuwiki.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
//@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "snapshots")
@AllArgsConstructor
public class Snapshot extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "snapshot_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member createdMember;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    private Document document;
    private String name;
    @Transient
    private Long changeCounts;
    private String texts;
}
