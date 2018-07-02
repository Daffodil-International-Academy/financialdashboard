package ac.daffodil.controller;

import ac.daffodil.dao.UniversityDao;
import ac.daffodil.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created by Muiduzzaman Lipu on 19-May-18.
 */
@Controller
@RequestMapping("/university")
public class UniversityController {

    @Autowired
    UniversityDao universityDao;

    @RequestMapping(value = { "/universityPage" }, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("universities", universityDao.getAll());
        modelAndView.addObject("message",  request.getParameter("message"));
        modelAndView.addObject("newUniversity", new University());
        modelAndView.setViewName("admin/adminUniversity");
        return modelAndView;
    }

    @RequestMapping(value="/saveUniversity", method = RequestMethod.POST)
    public String saveUniversity(University newUniversity) {
        ModelAndView modelAndView = new ModelAndView();
        universityDao.save(newUniversity);
        modelAndView.addObject("message", " Data Has Been Saved...");
        return "redirect:/university/universityPage";
    }

    @RequestMapping(value={"/findForEditUniversity/{id}"}, method = RequestMethod.GET)
    public ModelAndView findForEditUniversity(@PathVariable(required = true, name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<University> university= universityDao.find(id);
        modelAndView.addObject("newUniversity", university.get());
        modelAndView.addObject("universities", universityDao.getAll());
        modelAndView.setViewName("admin/adminUniversity");
        return modelAndView;
    }

    @RequestMapping(value="/deleteUniversity/{id}", method = RequestMethod.GET)
    public String deleteUniversity(@PathVariable(required = true, name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<University> university= universityDao.find(id);
        universityDao.delete(university.get());
        modelAndView.addObject("message", " Data Has Been Deleted...");
        return "redirect:/university/universityPage";
    }
}
