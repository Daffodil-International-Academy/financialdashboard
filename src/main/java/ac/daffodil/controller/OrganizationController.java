package ac.daffodil.controller;

import ac.daffodil.dao.OrganizationDao;
import ac.daffodil.model.Organization;
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
public class OrganizationController {

    @Autowired
    OrganizationDao organizationDao;

    @RequestMapping(value = { "/admin/organization/organizationPage" }, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("organizationes", organizationDao.getAll());
        modelAndView.addObject("message",  request.getParameter("message"));
        modelAndView.addObject("newOrganization", new Organization());
        modelAndView.setViewName("admin/adminOrganization");
        return modelAndView;
    }

    @RequestMapping(value="/admin/organization/saveOrganization", method = RequestMethod.POST)
    public String saveOrganization(Organization organization) {
        ModelAndView modelAndView = new ModelAndView();
        organizationDao.save(organization);
        modelAndView.addObject("message", " Data Has Been Saved...");
        return "redirect:/admin/organization/organizationPage";
    }

    @RequestMapping(value={"/admin/organization/findForEditOrganization/{id}"}, method = RequestMethod.GET)
    public ModelAndView findForEditOrganization(@PathVariable(required = true, name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Organization> organization= organizationDao.find(id);
        modelAndView.addObject("newOrganization", organization.get());
        modelAndView.addObject("organizationes", organizationDao.getAll());
        modelAndView.setViewName("admin/adminOrganization");
        return modelAndView;
    }

    @RequestMapping(value="/admin/organization/deleteOrganization/{id}", method = RequestMethod.GET)
    public String deleteOrganization(@PathVariable(required = true, name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Organization> organization= organizationDao.find(id);
        organizationDao.delete(organization.get());
        modelAndView.addObject("message", " Data Has Been Deleted...");
        return "redirect:/admin/organization/organizationPage";
    }
}
