package com.gti.student.controller;

import com.gti.student.dto.ClassGroupDTO;
import com.gti.student.dto.StudentGradeInfoDTO;
import com.gti.student.entity.*;
import com.gti.student.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes({"name", "surname", "teacherId","teacherSubjects"})
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ClassGroupRepository classGroupRepository;

    @GetMapping("/singlestudent")
    public String showSingleStudentPage(Model model,
                                        @ModelAttribute("name") String name,
                                        @ModelAttribute("surname") String surname,
                                        @ModelAttribute("teacherId") Integer teacherId) {

        List<Subject> subjects = subjectRepository.findAll();
        List<Subject> teacherSubjects = subjectRepository.findByTeacher_TeacherId(teacherId);
        model.addAttribute("subjects", subjects);
        model.addAttribute("name",name);
        model.addAttribute("surname",surname);
        model.addAttribute("teacherSubjects",teacherSubjects);
        return "singleStudent";
    }

    @PostMapping("/searchStudent")
    public String handleStudentForm(
            @RequestParam Integer studentId,
            @RequestParam String subjectId,
            @RequestParam String action,
            @RequestParam(required = false, name = "first-grade") Double firstGrade,
            @RequestParam(required = false, name = "second-grade") Double secondGrade,
            @RequestParam(required = false, name = "third-grade") Double thirdGrade,
            @RequestParam(required = false, name = "final-exam") Double finalExam,
            @ModelAttribute("name") String name,
            @ModelAttribute("surname") String surname,
            @ModelAttribute("teacherId") Integer teacherId,
            Model model) {

        model.addAttribute("name", name);
        model.addAttribute("surname", surname);
        model.addAttribute("teacherId", teacherId);
        model.addAttribute("selectedStudentId", studentId);
        model.addAttribute("selectedSubjectId", subjectId);

        if ("search".equals(action)) {
            handleSearch(studentId, subjectId, model);
        } else if ("info".equals(action)) {
            return "redirect:/studentDetails?studentId=" + studentId;
        } else if ("classGroup".equals(action)) {
            return "redirect:/classGroup";
        }else if ("addUpdate".equals(action)) {
            handleGradeUpdate(studentId, subjectId, firstGrade, secondGrade, thirdGrade, finalExam, teacherId, model);
        } else if ("delete".equals(action)) {
            handleGradeDelete(studentId, subjectId, teacherId, model);
        }

        // Always reload subjects
        List<Subject> subjects = subjectRepository.findAll();
        model.addAttribute("subjects", subjects);
        List<Subject> teacherSubjects = subjectRepository.findByTeacher_TeacherId(teacherId);
        model.addAttribute("teacherSubjects", teacherSubjects);
        return "singleStudent";
    }

    @GetMapping("/studentDetails")
    public String showStudentDetailsPage(
            @RequestParam Integer studentId,
            @ModelAttribute("name") String name,
            @ModelAttribute("surname") String surname,
            Model model) {

        model.addAttribute("name", name);
        model.addAttribute("surname", surname);
        model.addAttribute("studentId", studentId);

        // Check if student exists and fetch write all student data
        handleStudentInfoSearch(studentId, model);
        // Fetch all grades for student
        List<Grade> grades = gradeRepository.findByStudentId(studentId);
        if (grades != null) {
        model.addAttribute("grades", grades);
        } else{
            model.addAttribute("errorMessage", "Grade not found with ID: " + studentId);
        }
        return "studentDetails";
    }

    @PostMapping("/searchStudentInfo")
    public String searchStudentInfo(@RequestParam Integer studentId,
                                    @ModelAttribute("name") String name,
                                    @ModelAttribute("surname") String surname,
                                    Model model) {

        model.addAttribute("name", name);
        model.addAttribute("surname", surname);
        model.addAttribute("studentId", studentId);

            handleStudentInfoSearch(studentId, model);
            List<Grade> grades = gradeRepository.findByStudentId(studentId);
            model.addAttribute("grades", grades);
            return "studentDetails";
    }

    @PutMapping({"/updateStudentInfo"})
    public String updateStudentInfo(@RequestParam Integer studentId,
                                    @ModelAttribute("name") String name,
                                    @ModelAttribute("surname") String surname,
                                    @RequestParam(required = false) String firstName,
                                    @RequestParam(required = false) String lastName,
                                    @RequestParam(required = false) String classCode,
                                    @RequestParam(required = false) String PPSN,
                                    @RequestParam(required = false) String gender,
                                    @RequestParam(required = false) String dob,
                                    @RequestParam(required = false) String email,
                                    @RequestParam(required = false) String phone,
                                    @RequestParam(required = false) String addressLineOne,
                                    @RequestParam(required = false) String addressLineTwo,
                                    @RequestParam(required = false) String city,
                                    @RequestParam(required = false) String eircode,
                                    Model model) {

        model.addAttribute("name", name);
        model.addAttribute("surname", surname);
        model.addAttribute("studentId", studentId);

        handleStudentInfoUpdate(studentId, firstName, lastName, classCode, PPSN, gender, dob, email, phone,
                addressLineOne, addressLineTwo, city, eircode, model);
        List<Grade> grades = gradeRepository.findByStudentId(studentId);
        model.addAttribute("grades", grades);
        return "studentDetails";
    }

    @GetMapping("/classGroup")
    public String showClassGroupPage(@ModelAttribute("name") String name,
                                     @ModelAttribute("surname") String surname,
                                     @ModelAttribute("teacherId") Integer teacherId,
                                     Model model) {

        model.addAttribute("name", name);
        model.addAttribute("surname", surname);
        List<Subject> teacherSubjects = subjectRepository.findByTeacher_TeacherId(teacherId);
        model.addAttribute("teacherSubjects",teacherSubjects);
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses",courses);

        return "classGroup";
    }

    @GetMapping("/getClasses")
    @ResponseBody
    public List<ClassGroup> getClassesByCourse(@RequestParam String courseId) {
        return classGroupRepository.findByCourse_CourseId(courseId);
    }


    @GetMapping("/getSubjects")
    @ResponseBody
    public List<Subject> getSubjectsByCourse(@RequestParam String courseId) {
        return subjectRepository.findByCourse_CourseId(courseId);
    }

    @PostMapping("/searchClassGroup")
    public String searchClassGroupData(@RequestParam String courseId,
                                       @RequestParam String classId,
                                       @RequestParam String subjectId,
                                       @RequestParam String action,
                                       @ModelAttribute("name") String name,
                                       @ModelAttribute("surname") String surname,
                                       @ModelAttribute("teacherId") Integer teacherId,
                                       Model model) {

        model.addAttribute("name", name);
        model.addAttribute("surname", surname);
        model.addAttribute("teacherId", teacherId);
        model.addAttribute("selectedCourseId", courseId);
        model.addAttribute("selectedClassId", classId);
        model.addAttribute("selectedSubjectId", subjectId);

        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);

        if ("search".equals(action)) {
            List<StudentGradeInfoDTO> results = gradeRepository.findStudentGradesByClassAndSubject(classId, subjectId);
            model.addAttribute("searchResults", results);
        }

        return "classGroup";
    }

    @PostMapping("/studentTableModify")
    public String studentTableModify(@RequestParam Integer studentId,
                                     @RequestParam String action,
//                                     @RequestParam String subjectId,
//                                     @RequestParam String courseId,
//                                     @RequestParam String classId,
                                     @ModelAttribute("name") String name,
                                     @ModelAttribute("surname") String surname,
                                     @ModelAttribute("teacherId") Integer teacherId,
                                     Model model){

        model.addAttribute("name", name);
        model.addAttribute("surname", surname);
        model.addAttribute("teacherId", teacherId);
//        model.addAttribute("selectedCourseId", courseId);
//        model.addAttribute("selectedClassId", classId);
//        model.addAttribute("selectedSubjectId", subjectId);



        if ("info".equals(action)) {
            return "redirect:/studentDetails?studentId=" + studentId;
        } else if ("update".equals(action)) {
            //update comes here
        } else if ("delete".equals(action)) {
            //delete comes here
        }
        return "classGroup";
    }




    // Methods for mappings
    private void handleSearch(Integer studentId, String subjectId, Model model) {
        Student student = studentRepository.findById(studentId).orElse(null);

        if (student != null) {
            model.addAttribute("fullName", student.getFirstName().toUpperCase() + " " + student.getSurName().toUpperCase());
            Grade record = gradeRepository.findByStudentIdAndSubjectId(studentId, subjectId);
            if (record != null) {
                if (hasAnyGrade(record)) {
                    model.addAttribute("firstGrade", record.getFirstGrade());
                    model.addAttribute("secondGrade", record.getSecondGrade());
                    model.addAttribute("thirdGrade", record.getThirdGrade());
                    model.addAttribute("finalExam", record.getFinalExam());
                    if (record.getOverAll() >= 80) {
                        model.addAttribute("overAll", "A Distinction");
                    } else if (record.getOverAll() >= 50 && record.getOverAll() <= 80) {
                        model.addAttribute("overAll", "B Not Bad");
                    } else {
                        model.addAttribute("overAll", "You are failed!");
                    }
                } else {
                    model.addAttribute("noGradesFound", true);
                }
            } else {
                model.addAttribute("studentNotEnrolledInSubject", true);
            }
        } else {
            model.addAttribute("studentNotFound", true);
        }
    }

    private String handleStudentInfoSearch(Integer studentId, Model model) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student != null) {
            model.addAttribute("firstName", student.getFirstName().toUpperCase());
            model.addAttribute("lastName", student.getSurName().toUpperCase());
            model.addAttribute("classCode", student.getClassCode().toUpperCase());
            model.addAttribute("PPSN", student.getPpsn().toUpperCase());
            model.addAttribute("gender", student.getGender().toUpperCase());
            model.addAttribute("dob", student.getDateOfBirth().toUpperCase());
            model.addAttribute("email", student.getEmail().toUpperCase());
            model.addAttribute("phone", student.getPhoneNumber().toUpperCase());
            model.addAttribute("addressLineOne", student.getAddressLineOne().toUpperCase());
            model.addAttribute("addressLineTwo", student.getAddressLineTwo().toUpperCase());
            model.addAttribute("city", student.getCity().toUpperCase());
            model.addAttribute("eircode", student.getEircode().toUpperCase());
        } else {
            model.addAttribute("errorMessage", "Student not found with ID: " + studentId);
            return "studentDetails";
        }
        return null;
    }

    private void handleStudentInfoUpdate(Integer studentId, String firstName, String surName, String classCode,
                                         String PPSN, String gender, String dob, String email, String phone, String addressLineOne,
                                         String addressLineTwo, String city, String eircode, Model model) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student != null) {
            try {
            student.setFirstName(firstName);
            student.setSurName(surName);
            student.setClassCode(classCode);
            student.setPpsn(PPSN);
            student.setGender(gender);
            student.setDateOfBirth(dob);
            student.setEmail(email);
            student.setPhoneNumber(phone);
            student.setAddressLineOne(addressLineOne);
            student.setAddressLineTwo(addressLineTwo);
            student.setCity(city);
            student.setEircode(eircode);
            studentRepository.save(student);
            model.addAttribute("message", "Student info updated successfully.");
            } catch (Exception e) {
                logger.error("Student update failed", e);
                model.addAttribute("errorMessage", "Failed to update student info: " + e.getMessage());
            }
        } else  {
            model.addAttribute("errorMessage", "Student not found with ID: " + studentId);
        }
    }

    private void handleGradeUpdate(Integer studentId, String subjectId, Double firstGrade, Double secondGrade,
                                   Double thirdGrade, Double finalExam, Integer teacherId, Model model) {
        Grade record = gradeRepository.findByStudentIdAndSubjectId(studentId, subjectId);
        if (record != null) {
            Subject subject = subjectRepository.findBySubjectId(subjectId).orElse(null);
            if (subject != null && subject.getTeacher().getTeacherId().equals(teacherId)) {
                try {
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
            model.addAttribute("message", "No grade record found to update.");
        }
    }

    private void handleGradeDelete(Integer studentId, String subjectId, Integer teacherId, Model model) {
        Grade record = gradeRepository.findByStudentIdAndSubjectId(studentId, subjectId);
        if (record != null) {
            Subject subject = subjectRepository.findBySubjectId(subjectId).orElse(null);
            if (subject != null && subject.getTeacher().getTeacherId().equals(teacherId)) {
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
                model.addAttribute("message", "You are not authorized to delete this grade.");
            }
        } else {
            model.addAttribute("message", "No grade record found to delete.");
        }
    }

    private boolean hasAnyGrade(Grade record) {
        return record.getFirstGrade() != null ||
                record.getSecondGrade() != null ||
                record.getThirdGrade() != null ||
                record.getFinalExam() != null;
    }
}
