package ac.daffodil.controller;

import ac.daffodil.dao.ExamDao;
import ac.daffodil.dao.FileDao;
import ac.daffodil.dao.UserDao;
import ac.daffodil.model.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Blob;
import java.util.Optional;

/**
 * Created by Bithi on 3/25/2018.
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileDao fileDao;

    @Autowired
    ExamDao examDao;


    @Autowired
    FileUploadService fileUploadService;

    @RequestMapping(value = "/filePage", method = RequestMethod.GET)
    public ModelAndView showFilePage(HttpServletRequest request){
        fileUploadService.makeFolder();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message",  request.getParameter("message"));
        modelAndView.addObject("newFile", new File());
        modelAndView.addObject("exams", examDao.getAll());
        modelAndView.addObject("files", fileDao.getAll());
        modelAndView.setViewName("admin/adminFile");
        return modelAndView;
    }


    @RequestMapping(value = "/saveFile", method = RequestMethod.POST)
    public String saveFile(@RequestParam("file") MultipartFile file, File newFile) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            fileUploadService.store(file);
            newFile.setFile_name(file.getOriginalFilename());
            fileDao.save(newFile);
            modelAndView.addObject("message",  "Data Has Been Saved...");
        }catch (Exception e){
            modelAndView.addObject("message",  "Data Hasn`t Been Saved Properly...");
        }

        return "redirect:/file/filePage";

    }

    @GetMapping("/files/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = fileUploadService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    //get file id for report problem
    @RequestMapping(value={"/findForFile/{file_id}"}, method = RequestMethod.GET)
    public ModelAndView findForSetFileId(@PathVariable(required = true, name = "file_id") Long file_id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<File> file=fileDao.find(file_id);
        Comments comments= new Comments();
        comments.setFile(file.get());
        modelAndView.addObject("newComment", comments);
        modelAndView.setViewName("comment");
        return modelAndView;
    }

}
