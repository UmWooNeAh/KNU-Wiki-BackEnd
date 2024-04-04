package uwna.knuwiki.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uwna.knuwiki.exception.UserException;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmailService {

    @Autowired
    private final JavaMailSender emailSender;

    public void sendEmail(String to, String title, String body) {
        MimeMessagePreparator emailForm = createEmailForm(to, title, body);
        try {
            emailSender.send(emailForm);
        } catch (RuntimeException e) {
            throw new UserException("cannot send email");
        }

    }

    private MimeMessagePreparator createEmailForm(String to, String title, String body) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            messageHelper.setTo(to);
            messageHelper.setSubject(title);
            messageHelper.setText(body, true);
        };
        return messagePreparator;
    }


}
