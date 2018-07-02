package ac.daffodil.controller;

import ac.daffodil.dao.DepartmentDao;
import ac.daffodil.dao.UniversityDao;
import ac.daffodil.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentDao departmentDao;

    @Autowired
    UniversityDao universityDao;

    @RequestMapping(value = { "/departmentPage" }, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("departments", departmentDao.getAll());
        modelAndView.addObject("universities", universityDao.getAll());
        modelAndView.addObject("message",  request.getParameter("message"));
        modelAndView.addObject("newDepartment", new Department());
        modelAndView.setViewName("admin/adminDepartment");
        return modelAndView;
    }

    @RequestMapping(value="/saveDepartment", method = RequestMethod.POST)
    public String saveUniversity(Department newDepartment) {
        ModelAndView modelAndView = new ModelAndView();
        departmentDao.save(newDepartment);
        modelAndView.addObject("message", " Data Has Been Saved...");
        return "redirect:/department/departmentPage";
    }

    @RequestMapping(value={"/findForEditDepartment/{id}"}, method = RequestMethod.GET)
    public ModelAndView findForEditDepartment(@PathVariable(required = true, name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Department> department= departmentDao.find(id);
        modelAndView.addObject("newDepartment", department.get());
        modelAndView.addObject("universities", universityDao.getAll());
        modelAndView.addObject("departments", departmentDao.getAll());
        modelAndView.setViewName("admin/adminDepartment");
        return modelAndView;
    }

    @RequestMapping(value="/deleteDepartment/{id}", method = RequestMethod.GET)
    public String deleteUniversity(@PathVariable(required = true, name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Department> university= departmentDao.find(id);
        departmentDao.delete(university.get());
        modelAndView.addObject("message", " Data Has Been Deleted...");
        return "redirect:/department/departmentPage";
    }
}
