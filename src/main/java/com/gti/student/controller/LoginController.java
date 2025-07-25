package com.gti.student.controller;

import com.gti.student.dto.LoginRequest;
import com.gti.student.entity.Teacher;
import com.gti.student.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes({"name", "surname", "teacherId"})
public class LoginController {

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/")
    public String showLoginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/")
    public String loginUser(@ModelAttribute LoginRequest loginRequest, RedirectAttributes redirectAttributes) {

        Teacher teacher = teacherRepository.findByEmail(loginRequest.getEmail());

        if (teacher != null && teacher.getPassword().equals(loginRequest.getPassword())) {
            redirectAttributes.addFlashAttribute("name", teacher.getFirstName().toUpperCase());
            redirectAttributes.addFlashAttribute("surname", teacher.getSurName().toUpperCase());
            redirectAttributes.addFlashAttribute("teacherId", teacher.getTeacherId());
            return "redirect:/singlestudent";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid email or password");
            return "redirect:/";
        }
    }
}
