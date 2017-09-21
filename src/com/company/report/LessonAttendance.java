package com.company.report;

import com.company.Student;

import java.util.Set;

public class LessonAttendance implements Attendance {

    public static final String FILE_NAME = "journal_les.txt";

    private String lessonName;
    private Set<Student> missedStudents;
    private Set<Student> presentStudents;

    public LessonAttendance(String lessonName, Set<Student> missedStudents, Set<Student> presentStudents) {
        this.lessonName = lessonName;
        this.missedStudents = missedStudents;
        this.presentStudents = presentStudents;
    }

    @Override
    public String getFileName() {
        return FILE_NAME;
    }

    @Override
    public void print() {
        System.out.println("********************************************************************");
        System.out.println("LESSON INFO:");
        System.out.println("Lesson name: " + lessonName);
        System.out.println("Present students: ");
        presentStudents.forEach(this::printStudent);
        System.out.println("Missed students: ");
        missedStudents.forEach(this::printStudent);
        System.out.println("********************************************************************");
    }

    public void printStudent(Student student) {
        short num = student.getNum();
        String firstName = student.getFirstName();
        String secondName = student.getSecondName();
        System.out.println(num + " " + firstName + " " + secondName);
    }
}
