package uwna.knuwiki.paging;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public class PagingDto {

    private String documentName;
    private String memberName;
    private String texts;

    @QueryProjection
    public PagingDto(String documentName, String memberName, String texts) {
        this.documentName = documentName;
        this.memberName = memberName;
        this.texts = texts;
    }
}
