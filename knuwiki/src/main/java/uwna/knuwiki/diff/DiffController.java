package uwna.knuwiki.diff;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uwna.knuwiki.diff.diff_match_patch;
import uwna.knuwiki.diff.diff_match_patch.Diff;

import java.util.LinkedList;

@Controller
@Slf4j
@RequiredArgsConstructor
public class DiffController {
    @Autowired
    private final diff_match_patch dmp;

    @GetMapping("/diff")
    public String getDifference(Model model) {

        String text1 = "Member memberA = new Member(null, \"userA\", \"1234\", null, Role.ROLE_USER, State.정상);";
        String text2 = "Member memberA = new Member(null, \"sm1108shin@knu.ac.kr\", \"$2a$10$jGKW1N5biMNXRqbHylWn8eJ8FoD0JOUOW8d3KQdMXmKGDY8DlLTmS\", Role.ROLE_USER, State.정상);";

        LinkedList<Diff> diffs = dmp.diff_main(text1, text2);
        dmp.Diff_EditCost = 10; //성능과 연관된 수치 설정
        dmp.diff_cleanupSemantic(diffs); //유저가 보기 편하게 diffs 구성

        for (Diff diff : diffs) {
            log.info("{}", diff);
        }
        model.addAttribute("diffs", diffs);
        return "diff";
    }
}
