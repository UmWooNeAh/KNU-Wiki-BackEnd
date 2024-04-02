package uwna.knuwiki.paging;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class PagingForm {

    private int page; //현재 페이지 번호
    @JsonProperty(value = "record_size")
    private int recordSize; //페이지 당 보여줄 데이터 수
    @JsonProperty(value = "page_size")
    private int pageSize; //하단에 출력할 페이지 수(1..최대 10개)
    private String keyword; //검색 키워드
    @JsonProperty(value = "search_type")
    private String searchType; //검색 유형

    public PagingForm(int recordSize) {
        this.page = 1;
        this.recordSize = recordSize;
        this.pageSize = 10;
    }

    //offset: k번째 페이지에서 첫번째 데이터가 db에서 몇번째인지?
    public int getOffset() {
        return (page - 1) * recordSize;
    }

}
