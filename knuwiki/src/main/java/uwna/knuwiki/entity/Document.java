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
@Table(name = "documents")
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Document extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long id;
    private DocumentType type;

}
