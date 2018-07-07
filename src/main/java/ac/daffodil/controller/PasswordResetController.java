package ac.daffodil.controller;

import ac.daffodil.config.WebMvcConfig;
import ac.daffodil.dao.UserDao;
import ac.daffodil.model.PasswordResetDto;
import ac.daffodil.model.PasswordResetToken;
import ac.daffodil.model.User;
import ac.daffodil.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Uzzal on 7/3/2018.
 */
@Controller
@RequestMapping("/reset-password")
public class PasswordResetController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    @Autowired
    private WebMvcConfig mvcConfig;

    @ModelAttribute("passwordResetForm")
    public PasswordResetDto resetDto(){
        return new PasswordResetDto();
    }

    @GetMapping
    public String displayResetPasswordPage(@RequestParam(required = false) String token, Model model , HttpServletRequest request) {

        PasswordResetToken resetToken = tokenRepository.findByToken(token);
        if (resetToken == null){
            model.addAttribute("error", "Could not find password reset token.");
        } else if (resetToken.isExpired()){
            model.addAttribute("error", "Token has expired, please request a new password reset token.");
        } else {
            model.addAttribute("token", resetToken.getToken());
        }

        if(request.isUserInRole("authority")){
            return "authority/authorityResetPassword";
        }else if(request.isUserInRole("centerhead")){
            return "centerHead/centerHeadResetPassword";
        }else if(request.isUserInRole("user")){
             return "user/userResetPassword";
         }

        return "fragments/reset-password";
    }

    @PostMapping
    @Transactional
    public String handlePasswordReset(@ModelAttribute("passwordResetForm") @Valid PasswordResetDto form,
                                      BindingResult result,
                                      RedirectAttributes redirectAttributes, HttpServletRequest request) {

        if (result.hasErrors()){
            redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetForm", result);
            redirectAttributes.addFlashAttribute("passwordResetForm", form);
            return "redirect:/reset-password?token=" + form.getToken();
        }

        PasswordResetToken token = tokenRepository.findByToken(form.getToken());
        User user = token.getUser();
        String updatedPassword = mvcConfig.passwordEncoder().encode(form.getPassword());

        userDao.updateUserPassword(updatedPassword,user.getId());

        redirectAttributes.addFlashAttribute("message", "Password Change Successfully");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        if(request.isUserInRole("authority")){
            return "redirect:/authority/authorityDashPage";
        }else if(request.isUserInRole("centerhead")){
            return "redirect:/centerHead/centerHeadDashPage";
        }else if(request.isUserInRole("user")){
            return "redirect:/user/userDashPage";
        }

        tokenRepository.delete(token);

        return "redirect:/login";
    }


}
