package ac.daffodil.controller;

import ac.daffodil.dao.FinancialDao;
import ac.daffodil.dao.OrganizationDao;
import ac.daffodil.model.Financial;
import ac.daffodil.model.Organization;
import ac.daffodil.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Muiduzzaman Lipu on 19-May-18.
 */
@Controller
public class UserDashFinancialController {

    @Autowired
    FinancialDao financialDao;

    @Autowired
    OrganizationDao organizationDao;

    @RequestMapping(value = { "/user/userDashPage" }, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/userDash");
        return modelAndView;
    }

    @RequestMapping(value = { "/user/financial/financialPage" }, method = RequestMethod.GET)
    public ModelAndView financialPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentLoggedUser = (User) authentication.getPrincipal();
        List<Financial> financialList = new ArrayList<Financial>();
        for (Financial financial:financialDao.getAll()) {
            if(financial.getOrganizationId().equals(currentLoggedUser.getOrganizationId())){
                financialList.add(financial);
            }
        }
        List<Organization> organizationList = new ArrayList<Organization>();
        for (Organization organization : organizationDao.getAll()) {
            if(organization.getOrganizationId().equals(currentLoggedUser.getOrganizationId())){
                organizationList.add(organization);
            }
        }

        modelAndView.addObject("financials", financialList);
        modelAndView.addObject("organizationes", organizationList);
        Financial financial= new Financial();
        financial.setLastUpdateDate(new Date());
        modelAndView.addObject("newFinancial", financial);
        modelAndView.setViewName("user/userFinancial");
        return modelAndView;
    }

    @RequestMapping(value="/user/financial/saveFinancial", method = RequestMethod.POST)
    public String saveFinancial(Financial financial, RedirectAttributes redirectAttributes) {
        if (financial.getOrganizationId() == null){
            redirectAttributes.addFlashAttribute("message", "Please Select Organization ID...");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/user/financial/financialPage";
        }
        financialDao.save(financial);
        redirectAttributes.addFlashAttribute("message", " Financial Data Has Been Saved Successfully...");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/user/financial/financialPage";
    }

    @RequestMapping(value={"/user/financial/findForEditFinancial/{id}"}, method = RequestMethod.GET)
    public ModelAndView findForEditFinancial(@PathVariable(required = true, name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Financial> financial= financialDao.find(id);
        modelAndView.addObject("newFinancial", financial.get());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentLoggedUser = (User) authentication.getPrincipal();
        List<Financial> financialList = new ArrayList<Financial>();
        for (Financial newFinancial : financialDao.getAll()) {
            if(newFinancial.getOrganizationId().equals(currentLoggedUser.getOrganizationId())){
                financialList.add(newFinancial);
            }
        }
        List<Organization> organizationList = new ArrayList<Organization>();
        for (Organization organization : organizationDao.getAll()) {
            if(organization.getOrganizationId().equals(currentLoggedUser.getOrganizationId())){
                organizationList.add(organization);
            }
        }

        modelAndView.addObject("financials", financialList);
        modelAndView.addObject("organizationes", organizationList);
        modelAndView.setViewName("user/userFinancial");
        return modelAndView;
    }

    @RequestMapping(value="/user/financial/deleteDepartment/{id}", method = RequestMethod.GET)
    public String deleteFinancial(@PathVariable(required = true, name = "id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Financial> financial= financialDao.find(id);
        financialDao.delete(financial.get());
        redirectAttributes.addFlashAttribute("message", "Your Financial Data Has Been Deleted...");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/user/financial/financialPage";
    }
}
