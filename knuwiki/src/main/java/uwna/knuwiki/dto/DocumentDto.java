package uwna.knuwiki.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uwna.knuwiki.entity.DocumentType;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class DocumentDto {

    private Long id;
    private DocumentType type;
    private String name;
    private String texts;
    private String version;
    private String createdMemberId;
    private List<String> classes;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;


}
