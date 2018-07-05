package ac.daffodil.dao;

import ac.daffodil.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

/**
 * Created by Uzzal on 7/2/2018.
 */
@Service
public class EmailDao {
    @Autowired
    private JavaMailSender mailSender;

    @Qualifier("templateEngine")
    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    public void sendMail(Mail mail){
        try {
            MimeMessage message=mailSender.createMimeMessage();
            MimeMessageHelper messageHelper=new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            Context context=new Context();
            context.setVariables(mail.getModel());
            String html=springTemplateEngine.process("/email/email-template",context);
            messageHelper.setTo(mail.getTo());
            messageHelper.setText(html,true);
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setFrom(mail.getFrom());
            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
