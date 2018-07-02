package ac.daffodil.controller;

import ac.daffodil.dao.RoleDao;
import ac.daffodil.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * Created by Lipu on 21-Mar-18.
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleDao roleDao;

    @RequestMapping(value = { "/rolePage" }, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roles", roleDao.getAll());
        modelAndView.addObject("message",  request.getParameter("message"));
        modelAndView.addObject("newRole", new Role());
        modelAndView.setViewName("admin/adminRole");
        return modelAndView;
    }

    @RequestMapping(value="/saveRole", method = RequestMethod.POST)
    public String saveRole(Role newRole) {
        ModelAndView modelAndView = new ModelAndView();
        roleDao.save(newRole);
        modelAndView.addObject("message", " Data Has Been Saved...");
        return "redirect:/role/rolePage";
    }

    @RequestMapping(value={"/findForEditRole/{id}"}, method = RequestMethod.GET)
    public ModelAndView findForEditRole(@PathVariable(required = true, name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Role> role= roleDao.find(id);
        modelAndView.addObject("newRole", role.get());
        modelAndView.addObject("roles", roleDao.getAll());
        modelAndView.setViewName("admin/adminRole");
        return modelAndView;
    }

    @RequestMapping(value="/deleteRole/{id}", method = RequestMethod.GET)
    public String deleteRole(@PathVariable(required = true, name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Role> role= roleDao.find(id);
        roleDao.delete(role.get());
        modelAndView.addObject("message", " Data Has Been Deleted...");
        return "redirect:/role/rolePage";
    }


}
