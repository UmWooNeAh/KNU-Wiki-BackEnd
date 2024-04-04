package uwna.knuwiki.email;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class EmailContentBuilder {

    private final TemplateEngine templateEngine;
    private final String VERIFICATION_LINK = "http://localhost:8080/api/v1/auth/verify/";

    public String signupBuild(String uuid) {
        Context context = new Context();
        context.setVariable("link", VERIFICATION_LINK + uuid);
        return templateEngine.process("joinMail", context);
    }


}
