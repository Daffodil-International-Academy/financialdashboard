package ac.daffodil.controller;

import ac.daffodil.dao.DepartmentDao;
import ac.daffodil.dao.FinancialDao;
import ac.daffodil.dao.OrganizationDao;
import ac.daffodil.dao.UniversityDao;
import ac.daffodil.model.Department;
import ac.daffodil.model.Financial;
import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Created by Muiduzzaman Lipu on 19-May-18.
 */
@Controller
public class FinancialController {

    @Autowired
    FinancialDao financialDao;

    @Autowired
    OrganizationDao organizationDao;

    @RequestMapping(value = { "/admin/financial/financialPage" }, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("financials", financialDao.getAll());
        modelAndView.addObject("organizationes", organizationDao.getAll());
        modelAndView.addObject("message",  request.getParameter("message"));
        Financial financial= new Financial();
        financial.setLastUpdateDate(new Date());
        modelAndView.addObject("newFinancial", financial);
        modelAndView.setViewName("admin/adminFinancial");
        return modelAndView;
    }

    @RequestMapping(value="/admin/financial/saveFinancial", method = RequestMethod.POST)
    public String saveFinancial(Financial financial) {
        ModelAndView modelAndView = new ModelAndView();
        financialDao.save(financial);
        modelAndView.addObject("message", " Data Has Been Saved...");
        return "redirect:/admin/financial/financialPage";
    }

    @RequestMapping(value={"/admin/financial/findForEditFinancial/{id}"}, method = RequestMethod.GET)
    public ModelAndView findForEditFinancial(@PathVariable(required = true, name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Financial> financial= financialDao.find(id);
        modelAndView.addObject("newFinancial", financial.get());
        modelAndView.addObject("financials", financialDao.getAll());
        modelAndView.addObject("organizationes", organizationDao.getAll());
        modelAndView.setViewName("admin/adminFinancial");
        return modelAndView;
    }

    @RequestMapping(value="/admin/financial/deleteDepartment/{id}", method = RequestMethod.GET)
    public String deleteFinancial(@PathVariable(required = true, name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Financial> financial= financialDao.find(id);
        financialDao.delete(financial.get());
        modelAndView.addObject("message", " Data Has Been Deleted...");
        return "redirect:/admin/financial/financialPage";
    }

}
