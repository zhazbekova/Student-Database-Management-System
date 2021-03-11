package com.ensat.controllers;

import com.ensat.entities.Student;
import com.ensat.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Student controller.
 */
@Controller
public class StudentController {

    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * List all students.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("students", studentService.listAllStudents());
        System.out.println("Returning students:");
        return "students";
    }

    /**
     * View a specific student by its id.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("student/{id}")
    public String showStudent(@PathVariable Integer id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "studentshow";
    }

    // Afficher le formulaire de modification du Student
    @RequestMapping("student/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "studentform";
    }

    /**
     * New student.
     *
     * @param model
     * @return
     */
    @RequestMapping("student/new")
    public String newStudent(Model model) {
        model.addAttribute("student", new Student());
        return "studentform";
    }

    /**
     * Save student to database.
     *
     * @param student
     * @return
     */
    @RequestMapping(value = "student", method = RequestMethod.POST)
    public String saveStudent(Student student) {
        studentService.saveStudent(student);
        return "redirect:/student/" + student.getId();
    }

    /**
     * Delete student by its id.
     *
     * @param id
     * @return
     */
    @RequestMapping("student/delete/{id}")
    public String delete(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

}
