package com.company.report;

import com.company.Lesson;
import com.company.Student;

import java.io.Serializable;
import java.util.*;

public class GroupAttendance implements Attendance {
    public static String FILE_NAME = "journal_gr.txt";

    HashMap<Student, List<Attendance>> attendanceHashMap = new HashMap<>();

    @Override
    public void print() {
        System.out.println("***************************************************************");

        Set<String> lessonNames = new HashSet<>();
        for (HashMap.Entry<Student, List<Attendance>> entry : attendanceHashMap.entrySet()) {
            Student student = entry.getKey();
            List<Attendance> attendanceList = entry.getValue();

            if (lessonNames.isEmpty()) {
                for (Attendance attendance : attendanceList) {
                    lessonNames.add(attendance.lesson.getTopic());
                }
            }


        }
        System.out.println("***************************************************************");
    }

    public void addAttendance(Student student, Lesson lesson, boolean attendance) {
        List<Attendance> attendances = attendanceHashMap.get(student);
        if (attendances == null) {
            attendances = new ArrayList<>();
        }
        attendances.add(new Attendance(lesson, attendance));
        attendanceHashMap.put(student, attendances);
    }

    @Override
    public String getFileName() {
        return FILE_NAME;
    }

    private static class Attendance implements Serializable {
        private Lesson lesson;
        private boolean attendance;

        public Attendance(Lesson lesson, boolean attendance) {
            this.lesson = lesson;
            this.attendance = attendance;
        }
    }
}
