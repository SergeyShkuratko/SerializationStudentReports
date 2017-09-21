package com.company.report;

import com.company.Lesson;
import com.company.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentAttendance implements Attendance {

    public static final String FILE_NAME = "journal_stud.txt";

    private Student student;
    private List<Attendance> attendances = new ArrayList<>();

    public StudentAttendance(Student student) {
        this.student = student;
    }

    public void addAttendance(Lesson lesson, boolean b) {
        attendances.add(new Attendance(lesson, b));
    }

    @Override
    public void print() {
        System.out.println("********************************************************************");
        System.out.println("STUDENT INFO: ");
        System.out.println("Name: " + student.getSecondName() + " " + student.getFirstName());
        System.out.println("Attendances: ");
        attendances.forEach(attendance -> {
            Lesson lesson = attendance.lesson;
            String attendanceChar = attendance.attendance ? "+" : "-";
            System.out.println("Lesson: " + lesson.getTopic() + " : " + attendanceChar);
        });
        System.out.println("********************************************************************");
    }

    @Override
    public String getFileName() {
        return FILE_NAME;
    }

    private static class Attendance implements Serializable {
        private boolean attendance;
        private Lesson lesson;

        public Attendance(Lesson lesson, boolean attendance) {
            this.attendance = attendance;
            this.lesson = lesson;
        }
    }
}
