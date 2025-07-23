package com.gti.student.controller;

import com.gti.student.dto.LoginRequest;
import com.gti.student.entity.Subject;
import com.gti.student.entity.Teacher;
import com.gti.student.repository.SubjectRepository;
import com.gti.student.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SubjectRepository subjectRepository;




    @GetMapping("/")
    public String showLoginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @GetMapping("/singlestudent")
    public String showSingleStudentPage(Model model,
                                        @ModelAttribute("name") String name,
                                        @ModelAttribute("surname") String surname) {
        List<Subject> subjects = subjectRepository.findAll();
        model.addAttribute("subjects", subjects);
        model.addAttribute("name",name);
        model.addAttribute("surname",surname);
        return "singleStudent";
    }



    @PostMapping("/")
    public String loginUser(@ModelAttribute LoginRequest loginRequest,RedirectAttributes redirectAttributes) {

        Teacher teacher = teacherRepository.findByEmail(loginRequest.getEmail());

        if (teacher != null && teacher.getPassword().equals(loginRequest.getPassword())) {
            redirectAttributes.addFlashAttribute("name", teacher.getFirstName());
            redirectAttributes.addFlashAttribute("surname", teacher.getSurName());
            redirectAttributes.addFlashAttribute("teacherId", teacher.getTeacherId());
            return "redirect:/singlestudent";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid email or password");
            return "redirect:/";
        }
    }

}
