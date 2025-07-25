package com.gti.student.controller;

import com.gti.student.dto.LoginRequest;
import com.gti.student.entity.Grade;
import com.gti.student.entity.Student;
import com.gti.student.entity.Subject;
import com.gti.student.entity.Teacher;
import com.gti.student.repository.GradeRepository;
import com.gti.student.repository.StudentRepository;
import com.gti.student.repository.SubjectRepository;
import com.gti.student.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@SessionAttributes({"name", "surname", "teacherId"})
@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private StudentRepository studentRepository;




    @GetMapping("/")
    public String showLoginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/")
    public String loginUser(@ModelAttribute LoginRequest loginRequest,RedirectAttributes redirectAttributes) {

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

    @PostMapping("/searchStudent")
    public String handleStudentForm(
            @RequestParam Integer studentId,
            @RequestParam String subjectId,
            @RequestParam String action,
            @RequestParam(required = false, name = "first-grade") Double  firstGrade,
            @RequestParam(required = false, name = "second-grade") Double  secondGrade,
            @RequestParam(required = false, name = "third-grade") Double  thirdGrade,
            @RequestParam(required = false, name = "final-exam") Double  finalExam,
            @ModelAttribute("name") String name,
            @ModelAttribute("surname") String surname,
            @ModelAttribute("teacherId") Integer teacherId,
            Model model) {

        // setting teacher name/surname for header
        model.addAttribute("name", name);
        model.addAttribute("surname", surname);
        model.addAttribute("teacherId", teacherId);
        // setting subject and student with posted values after submit
        model.addAttribute("selectedStudentId", studentId);
        model.addAttribute("selectedSubjectId", subjectId);

        if ("search".equals(action)) {
            Student student = studentRepository.findById(studentId).orElse(null);

            if (student != null) {
                model.addAttribute("fullName", student.getFirstName().toUpperCase() + " " + student.getSurName().toUpperCase());

                // Check if student is enrolled in the selected subject
                boolean isEnrolled = gradeRepository.existsByStudentIdAndSubjectId(studentId, subjectId);

                if (isEnrolled) {
                    // Retrieve the grade for this subject
                    Grade record = gradeRepository.findByStudentIdAndSubjectId(studentId, subjectId);
                    if (record != null) {
                        boolean recordExist = (record.getFirstGrade() != null)
                                || (record.getSecondGrade() != null)
                                || (record.getThirdGrade() != null)
                                || (record.getFinalExam() != null);
                        if (recordExist) {
                            model.addAttribute("firstGrade", record.getFirstGrade());
                            model.addAttribute("secondGrade", record.getSecondGrade());
                            model.addAttribute("thirdGrade", record.getThirdGrade());
                            model.addAttribute("finalExam", record.getFinalExam());
                        } else {
                            // Student is enrolled but no grade found
                            model.addAttribute("noGradesFound", true);
                        }
                    }
                } else {
                    // Student is NOT enrolled in this subject
                    model.addAttribute("studentNotEnrolledInSubject", true);
                }

            } else {
                // Student not found
                model.addAttribute("studentNotFound", true);
            }

        } else if ("info".equals(action)) {
            return "redirect:/studentInfoPage?studentId=" + studentId;
        } else if ("classGroup".equals(action)) {

        } else if ("addUpdate".equals(action)) {
            Grade record = gradeRepository.findByStudentIdAndSubjectId(studentId, subjectId);
            if (record != null) {
                Subject subject = subjectRepository.findBySubjectId(subjectId).orElse(null);
                if (subject != null) {
                    if (subject.getTeacher().getTeacherId().equals(teacherId)) {
                        try{
                        // this part I don't know how to do
                            record.setFirstGrade(firstGrade);
                            record.setSecondGrade(secondGrade);
                            record.setThirdGrade(thirdGrade);
                            record.setFinalExam(finalExam);
                            gradeRepository.save(record);
                            model.addAttribute("message", "Grades updated successfully.");
                        } catch (Exception e) {
                            logger.error("Grade update failed", e);
                            model.addAttribute("message", "Failed to update grades: " + e.getMessage());
                        }
                    } else {
                        model.addAttribute("message", "You are not authorized to update this grade.");
                    }
                } else {
                    model.addAttribute("message", "Subject not found.");
                }
            } else {
                model.addAttribute("message", "No grade record found to update. Please, use add / update to add.");
            }
        } else if ("delete".equals(action)) {
            Grade record = gradeRepository.findByStudentIdAndSubjectId(studentId, subjectId);
            if (record != null) {
                Subject subject = subjectRepository.findBySubjectId(subjectId).orElse(null);
                if (subject != null) {
                    if (subject.getTeacher().getTeacherId().equals(teacherId)) {
                        try {
                            record.setFirstGrade(null);
                            record.setSecondGrade(null);
                            record.setThirdGrade(null);
                            record.setFinalExam(null);
                            gradeRepository.save(record);
                            model.addAttribute("message", "Grades deleted successfully.");
                        } catch (Exception e) {
                            logger.error("Grade deletion failed", e);
                            model.addAttribute("message", "Failed to delete grades: " + e.getMessage());
                        }
                    } else {
                        model.addAttribute("message", "You are not authorized to delete grades for this subject.");
                    }
                } else {
                    model.addAttribute("message", "Subject not found.");
                }
            } else {
                model.addAttribute("message", "No grade record found to delete.");
            }
        }

        // Always reload subjects
        List<Subject> subjects = subjectRepository.findAll();
        model.addAttribute("subjects", subjects);

        return "singleStudent";
    }

}
