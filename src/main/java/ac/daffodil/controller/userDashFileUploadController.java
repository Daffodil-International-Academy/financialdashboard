package ac.daffodil.controller;

import ac.daffodil.dao.ExamDao;
import ac.daffodil.dao.FileDao;
import ac.daffodil.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Muiduzzaman Lipu on 04-Apr-18.
 */
@Controller
@RequestMapping("/user")
public class userDashFileUploadController {

    @Autowired
    FileDao fileDao;

    @Autowired
    ExamDao examDao;

    @Autowired
    FileUploadService fileUploadService;

    @RequestMapping(value = { "/userDashFileUploadPage" }, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("newFile", new File());
        modelAndView.addObject("exams", examDao.getAll());
        modelAndView.setViewName("user/userDashFileUpload");
        return modelAndView;
    }

    @RequestMapping(value = "/saveFile", method = RequestMethod.POST)
    public String saveFile(@RequestParam("file") MultipartFile file, File newFile, RedirectAttributes redirectAttributes) {

        try {
            fileUploadService.store(file);
            newFile.setFile_name(file.getOriginalFilename());
            fileDao.save(newFile);
            redirectAttributes.addFlashAttribute("message", "Your File Upload Successfully...");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }

        return "redirect:/user/userDashFileUploadPage";

    }
}
