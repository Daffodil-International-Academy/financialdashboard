package ac.daffodil.controller;

import ac.daffodil.dao.ChildCommentDao;
import ac.daffodil.dao.CommentDao;
import ac.daffodil.dao.FileDao;
import ac.daffodil.model.ChildComments;
import ac.daffodil.model.Comments;
import ac.daffodil.model.File;
import ac.daffodil.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.xml.stream.events.Comment;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Bithi on 4/2/2018.
 */
@Controller
public class CommentController {

    Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    CommentDao commentDao;

    @Autowired
    FileDao fileDao;

    @Autowired
    ChildCommentDao childCommentDao;

    Comments comments= new Comments();
    List<Comments> comments1= new LinkedList<>();

    ChildComments childComments=new ChildComments();



    @RequestMapping(value = { "/comment" }, method = RequestMethod.GET)
    public ModelAndView commentPage() {
        ModelAndView modelAndView = new ModelAndView();
        Comments newComment=new Comments();

        comments1= new LinkedList<>();
        for (Comments cmt : commentDao.getAll()) {
            if (cmt.getFile().getFile_id() == comments.getFile().getFile_id()){
                comments1.add(cmt);
            }
        }

        modelAndView.addObject("newComment", comments);
        modelAndView.addObject("commentList", comments1);
        modelAndView.setViewName("user/userDashComment");
        return modelAndView;
    }


    //get file id for review problem
    @RequestMapping(value={"/comment/findForFile/{file_id}"}, method = RequestMethod.GET)
    public ModelAndView findForSetFileId(@PathVariable(required = true, name = "file_id") Long file_id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<File> file=fileDao.find(file_id);
        comments.setFile(file.get());

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            String email = ((User)principal).getEmail();
            comments.setUser_email(email);
        }

        comments1= new LinkedList<>();
        for (Comments cmt : commentDao.getAll()) {
            if (cmt.getFile().getFile_id() == file_id){
                comments1.add(cmt);
            }
        }

        modelAndView.addObject("commentList", comments1);
        modelAndView.addObject("newComment", comments);
        modelAndView.setViewName("user/userDashComment");
        return modelAndView;
    }


    @RequestMapping(value = { "/comment/saveComment" }, method = RequestMethod.POST)
    public String saveComment(Comments comments, RedirectAttributes redirectAttributes) {
                commentDao.save(comments);
                redirectAttributes.addFlashAttribute("message", "You Comment is= "+comments.getComment_text());
                redirectAttributes.addFlashAttribute("alertClass", "alert-success");
                return "redirect:/comment";
    }


    @RequestMapping(value={"/comment/find/{comment_id}"}, method = RequestMethod.GET)
    public ModelAndView findForEditComment(@PathVariable(required = true, name = "comment_id") Long comment_id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Comments> comments= commentDao.find(comment_id);
        modelAndView.addObject("newComment", comments.get());
        modelAndView.addObject("commentList", comments1);
        modelAndView.setViewName("user/userDashComment");
        return modelAndView;
    }


    @RequestMapping(value="/comment/delete/{comment_id}", method = RequestMethod.GET)
    public String deleteExam(@PathVariable(required = true, name = "comment_id") Long comment_id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Comments> comments= commentDao.find(comment_id);
        commentDao.delete(comments.get());
        modelAndView.addObject("message", " Data Has Been Deleted...");
        return "redirect:/comment";
    }

    //    child Comment section


    //comment id for reporting problem
    @RequestMapping(value={"/findForComment/{comment_id}"}, method = RequestMethod.GET)
    public ModelAndView findForSetCommentId(@PathVariable(required = true, name = "comment_id") Long comment_id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Comments> comment=commentDao.find(comment_id);
        ChildComments childComments= new ChildComments();
        childComments.setComments(comment.get());
        modelAndView.addObject("newComment", childComments);
        System.out.println(childComments.getComments().getComment_id());
        modelAndView.setViewName("user/childComment");
        return modelAndView;
    }




    //for saving childComment
    @RequestMapping(value = { "/comment/saveChildComment" }, method = RequestMethod.POST)
    public String saveChildComment(ChildComments childComments,RedirectAttributes redirectAttributes) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            String name = ((User)principal).getFirstName();
            childComments.setUser_name(name);
            childCommentDao.save(childComments);
            redirectAttributes.addFlashAttribute("message", "Your Comment is= "+comments.getComment_text());
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/comment";
        }
        return "redirect:/comment";
    }


}
