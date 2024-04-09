package uwna.knuwiki.controller;


import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uwna.knuwiki.paging.ArticlePage;
import uwna.knuwiki.paging.PagingDto;
import uwna.knuwiki.repository.DocumentRepository;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
public class PagingController {

    @Autowired
    private final DocumentRepository documentRepository;

    @GetMapping(value = "/paging/list")
    public String getPages(@RequestParam(defaultValue="1") int page, Model model) {
        Integer totalDocs = documentRepository.getDocCounts("버전");
        log.info("총 문서수 = {}", totalDocs);
        if(totalDocs != 0) {
            ArticlePage paging = new ArticlePage(totalDocs, page, 8, 10);
            List<PagingDto> documents = documentRepository.findCurrentPageDocs(paging, "sm1108shin@knu.ac.kr");

            //log.info("현재 페이지 첫번째 문서={}", documents.stream().findFirst().get().getTexts());
            model.addAttribute("pagingInfo", paging);
            model.addAttribute("documents", documents);
        }
        else {
            model.addAttribute("documents", new ArrayList<>());
        }
        return "paging";
    }
}
