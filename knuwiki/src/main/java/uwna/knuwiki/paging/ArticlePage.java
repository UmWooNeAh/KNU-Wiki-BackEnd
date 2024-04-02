package uwna.knuwiki.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticlePage {

    private int totalDocs; //전체 문서 수
    private int docsPerPage; //페이지 당 문서 수
    private int totalPages; //전체 페이지 수
    private int currentPage; //현재 페이지 번호
    private int startPage; //현재 페이징에서 시작 페이지 번호
    private int endPage; //현재 페이징에서 종료 페이지 번호
    private int pagingCount; //페이징 개수, 한번에 보여줄 페이지 수
    private int offset; //DB offset

    public ArticlePage(int totalDocs, int currentPage, int docsPerPage, int pagingCount) {
        this.totalDocs = totalDocs;
        this.currentPage = currentPage;
        this.docsPerPage = docsPerPage;
        this.pagingCount = pagingCount;

        //자체 계산을 해주어야 하는 것들: totalPages, startPage, endPage, offset
        if(totalDocs == 0) {
            this.totalPages = 0;
            this.startPage = 0;
            this.endPage = 0;
            this.offset = 0;
        }
        else {
            this.totalPages = isExistRest(totalDocs, docsPerPage);
            this.startPage = getStartPage(currentPage, pagingCount);
            this.endPage = getEndPage(this.startPage);
            this.offset = getOffset(currentPage);
        }

    }

    private int getOffset(int currentPage) {
        return (currentPage-1) * docsPerPage;
    }

    private int isExistRest(int totalDocs, int docsPerPage) {
        return totalDocs % docsPerPage == 0 ?  totalDocs / docsPerPage : totalDocs / docsPerPage+1;
    }

    private int getStartPage(int currentPage, int pagingCount) {
        return currentPage % pagingCount == 0 ? (currentPage / pagingCount) * pagingCount + 1 - pagingCount : (currentPage / pagingCount) * pagingCount + 1;
    }

    private int getEndPage(int startPage) {
        return Math.min((startPage + pagingCount - 1), totalPages);
    }

}
