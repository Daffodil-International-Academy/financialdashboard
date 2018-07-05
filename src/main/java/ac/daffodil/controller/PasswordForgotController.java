package ac.daffodil.controller;

import ac.daffodil.dao.EmailDao;
import ac.daffodil.dao.UserDao;
import ac.daffodil.model.Mail;
import ac.daffodil.model.PasswordForgotDto;
import ac.daffodil.model.PasswordResetToken;
import ac.daffodil.model.User;
import ac.daffodil.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * Created by Uzzal on 7/3/2018.
 */
@Controller
@RequestMapping("/forgot-password")
public class PasswordForgotController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordResetTokenRepository resetTokenRepository;
    @Autowired
    private EmailDao emailDao;


    @ModelAttribute("forgotPasswordForm")
    public PasswordForgotDto passwordForgotDto(){
        return new PasswordForgotDto();
    }

    @GetMapping
    public String displayForgotPasswordPage(){
        return "fragments/forgot-password";
    }

    @PostMapping
    public String processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDto form,
                                            BindingResult result, HttpServletRequest request){
        if(result.hasErrors()){
            return "fragments/forgot-password";
        }

        User user=userDao.findByEmail(form.getEmail());
        if(user==null){
            result.rejectValue("email",null,"Couldn't find an account for that email.");
            return "fragments/forgot-password";
        }

        PasswordResetToken resetToken=new PasswordResetToken();
        resetToken.setToken(UUID.randomUUID().toString());
        resetToken.setUser(user);
        resetToken.setExpiryDate(30);
        resetTokenRepository.save(resetToken);

        Mail mail=new Mail();
        mail.setFrom("uzzal@daffodil.ac");
        mail.setTo(user.getEmail());
        mail.setSubject("Password reset request");

        Map<String,Object> model=new HashMap<>();
        model.put("token",resetToken);
        model.put("user",user);
        model.put("signature","http://localhost:8085");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/reset-password?token=" + resetToken.getToken());
        mail.setModel(model);
        emailDao.sendMail(mail);


        return "redirect:/login";
    }

    @GetMapping(value = { "/processForgotPasswordWithButton" })
    public String processForgotPasswordWithButton(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        PasswordResetToken resetToken=new PasswordResetToken();
        resetToken.setToken(UUID.randomUUID().toString());
        resetToken.setUser(user);
        resetToken.setExpiryDate(30);
        resetTokenRepository.save(resetToken);

        return "redirect:/reset-password?token="+ resetToken.getToken();
    }
}
