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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
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
        Financial financial= new Financial();
        financial.setLastUpdateDate(new Date());
        modelAndView.addObject("newFinancial", financial);
        modelAndView.setViewName("admin/adminFinancial");
        return modelAndView;
    }

    @RequestMapping(value="/admin/financial/saveFinancial", method = RequestMethod.POST)
    public String saveFinancial(Financial financial, RedirectAttributes redirectAttributes) {
        if (financial.getOrganizationId() == null){
            redirectAttributes.addFlashAttribute("message", "Please Select Organization ID...");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/admin/financial/financialPage";
        }
        financialDao.save(financial);
        redirectAttributes.addFlashAttribute("message", " Financial Data Has Been Saved S" +
                "" +
                "uccessfully...");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
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
    public String deleteFinancial(@PathVariable(required = true, name = "id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Financial> financial= financialDao.find(id);
        financialDao.delete(financial.get());
        redirectAttributes.addFlashAttribute("message", "Your Financial Data Has Been Deleted...");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/admin/financial/financialPage";
    }

}
