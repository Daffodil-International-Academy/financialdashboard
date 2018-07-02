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
//        for (Role role : roleDao.getAll()) {
//            System.out.println(role.getRoleName());
//        }
        modelAndView.addObject("message",  request.getParameter("message"));
        modelAndView.addObject("newUser", new User());
        modelAndView.addObject("newRole", new Role());
        modelAndView.setViewName("admin/adminUser");
        return modelAndView;
    }

    @RequestMapping(value="/saveUser", method = RequestMethod.POST)
    public String saveUser(User user) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Role> role= roleDao.find(user.getRoleId());
        Set<Role> roles= new HashSet<Role>();
        roles.add(role.get());
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        modelAndView.addObject("message", " Data Has Been Saved...");
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
