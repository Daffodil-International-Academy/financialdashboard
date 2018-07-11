package ac.daffodil.controller;

import ac.daffodil.dao.OrganizationDao;
import ac.daffodil.dao.RoleDao;
import ac.daffodil.dao.UserDao;
import ac.daffodil.model.Comments;
import ac.daffodil.model.Role;
import ac.daffodil.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by codin on 3/20/2018.
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    OrganizationDao organizationDao;

    @RequestMapping(value = { "/userPage" }, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userDao.getAll());
        modelAndView.addObject("roles", roleDao.getAll());
        modelAndView.addObject("organizationes", organizationDao.getAll());
        modelAndView.addObject("newUser", new User());
        modelAndView.addObject("newRole", new Role());
        modelAndView.setViewName("admin/adminUser");
        return modelAndView;
    }

    @RequestMapping(value="/saveUser", method = RequestMethod.POST)
    public String saveUser(User user, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();

        for (User checkEmailUser : userDao.getAll()) {
            if(user.getEmail().equals(checkEmailUser.getEmail())){
                redirectAttributes.addFlashAttribute("message", " Your Email is Already Registered. Please Enter a New Email...");
                redirectAttributes.addFlashAttribute("alertClass", "alert-warning");
                return "redirect:/user/userPage";
            }
        }

        Optional<Role> role= roleDao.find(user.getRoleId());
        Set<Role> roles= new HashSet<Role>();
        roles.add(role.get());
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        redirectAttributes.addFlashAttribute("message", "User Save Successfully...");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/user/userPage";
    }

    @RequestMapping(value="/updateUser", method = RequestMethod.POST)
    public String updateUser(User user, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Role> role= roleDao.find(user.getRoleId());
        Set<Role> roles= new HashSet<Role>();
        roles.add(role.get());
        user.setRoles(roles);
        userDao.save(user);
        redirectAttributes.addFlashAttribute("message", "User Update Successfully...");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/user/userPage";
    }

    @RequestMapping(value={"/findForEditUser/{id}"}, method = RequestMethod.GET)
    public ModelAndView findForEditUser(@PathVariable(required = true, name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<User> user= userDao.find(id);
        modelAndView.addObject("newUser", user.get());
        modelAndView.addObject("users", userDao.getAll());
        modelAndView.addObject("roles", roleDao.getAll());
        modelAndView.addObject("organizationes", organizationDao.getAll());
        modelAndView.setViewName("admin/adminUser");
        return modelAndView;
    }

    @RequestMapping(value="/deleteUser/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable(required = true, name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<User> user= userDao.find(id);
        userDao.delete(user.get());
        modelAndView.addObject("message", " Data Has Been Deleted...");
        return "redirect:/user/userPage";
    }




}
