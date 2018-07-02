package ac.daffodil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Muiduzzaman Lipu on 03-Apr-18.
 */
@Controller
@RequestMapping("/admin")
public class adminDashController {
    @RequestMapping(value = { "/adminDashPage" }, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message",  request.getParameter("message"));
        modelAndView.setViewName("admin/adminDash");
        return modelAndView;
    }
}
