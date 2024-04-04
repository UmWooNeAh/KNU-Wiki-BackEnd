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


@Controller
@RequiredArgsConstructor
@Slf4j
public class PagingController {

    @Autowired
    private final DocumentRepository documentRepository;

    @GetMapping(value = "/paging/list")
    public String getPages(@RequestParam(defaultValue="1") int page, Pageable pageable, Model model) {
        Integer totalDocs = documentRepository.getDocCounts("문서");
        if(totalDocs != 0) {
            ArticlePage paging = new ArticlePage(totalDocs, page, 8, 10);
            Page<PagingDto> documents = documentRepository.findCurrentPageDocs(paging, pageable);

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
