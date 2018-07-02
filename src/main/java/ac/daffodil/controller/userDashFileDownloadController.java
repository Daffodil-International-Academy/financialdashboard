package ac.daffodil.controller;

import ac.daffodil.dao.CommentDao;
import ac.daffodil.dao.ExamDao;
import ac.daffodil.dao.FileDao;
import ac.daffodil.model.Comments;
import ac.daffodil.model.File;
import ac.daffodil.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Muiduzzaman Lipu on 04-Apr-18.
 */
@Controller
@RequestMapping("/user")
public class userDashFileDownloadController {

    @Autowired
    FileDao fileDao;

    @Autowired
    CommentDao commentDao;

    @Autowired
    ExamDao examDao;

    @Autowired
    FileUploadService fileUploadService;

    @RequestMapping(value = { "/userDashFileDownloadPage" }, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("newFile", new File());
        modelAndView.addObject("files", fileDao.getAll());
        modelAndView.addObject("exams", examDao.getAll());
        modelAndView.setViewName("user/userDashFileDownload");
        return modelAndView;
    }

    @GetMapping("/files/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = fileUploadService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }



}
