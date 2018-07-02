package ac.daffodil.controller;

import ac.daffodil.dao.ExamDao;
import ac.daffodil.model.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


/**
 * Created by Bithy on 3/24/2018.
 */
@Controller

public class ExamController {

    @Autowired
    ExamDao examDao;

    @RequestMapping(value = { "/exam" }, method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        Exam newExam = new Exam();
        modelAndView.addObject("newExam", newExam);
        modelAndView.addObject("exams", examDao.getAll());
        modelAndView.setViewName("admin/adminExam");
        return modelAndView;
    }

    @RequestMapping(value = { "/exam/save" }, method = RequestMethod.POST)
    public String saveExam(Exam exam) {
            examDao.save(exam);
            return "redirect:/exam";
    }


    @RequestMapping(value={"/exam/find/{exam_id}"}, method = RequestMethod.GET)
    public ModelAndView findForEditExam(@PathVariable(required = true, name = "exam_id") Long exam_id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Exam> exam= examDao.find(exam_id);
        modelAndView.addObject("newExam", exam.get());
        modelAndView.addObject("exams", examDao.getAll());
        modelAndView.setViewName("admin/adminExam");
        return modelAndView;
    }

    @RequestMapping(value="/exam/delete/{exam_id}", method = RequestMethod.GET)
    public String deleteExam(@PathVariable(required = true, name = "exam_id") Long exam_id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Exam> exam= examDao.find(exam_id);
        examDao.delete(exam.get());
        modelAndView.addObject("message", " Data Has Been Deleted...");
        return "redirect:/exam";
    }


}
