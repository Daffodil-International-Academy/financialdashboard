package ac.daffodil.controller;

import ac.daffodil.dao.DepartmentDao;
import ac.daffodil.dao.UniversityDao;
import ac.daffodil.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Muiduzzaman Lipu on 04-Apr-18.
 */
@Controller
@RequestMapping("/user")
public class userDashUniversityController {

    @Autowired
    UniversityDao universityDao;

    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping(value = { "/userDashUniversityPage" }, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("universities", universityDao.getAll());
        modelAndView.addObject("departments", departmentDao.getAll());
        modelAndView.setViewName("user/userDashUniversity");
        return modelAndView;
    }

    @RequestMapping(value={"/findForDepartment/{universityId}"}, method = RequestMethod.GET)
    public ModelAndView findForDepartment(@PathVariable(required = true, name = "universityId") Long universityId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("universities", universityDao.getAll());

        List<Department> departments1 = new LinkedList<>();
        for (Department dept : departmentDao.getAll()) {
            if (dept.getUniversityId() == universityId){
                departments1.add(dept);
            }
        }

        modelAndView.addObject("departmentList", departments1);
        modelAndView.setViewName("user/userDashDepartment");
        return modelAndView;
    }
}
