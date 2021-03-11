package com.ensat.services;

import com.ensat.entities.Student;

public interface StudentService {

    Iterable<Student> listAllStudents();

    Student getStudentById(Integer id);

    Student saveStudent(Student student);

    void deleteStudent(Integer id);

}
