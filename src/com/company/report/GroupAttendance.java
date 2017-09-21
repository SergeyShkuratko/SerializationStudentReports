package com.company.report;

import com.company.Lesson;
import com.company.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAttendance implements Attendance {
    public static String FILE_NAME = "journal_gr.txt";

    HashMap<Student, List<Attendance>> attendanceHashMap = new HashMap<>();

    @Override
    public void print() {
        System.out.println("***************************************************************");
        HashMap<Student, List<Boolean>> result = new HashMap<>();
        List<String> lessonNames = new ArrayList<>();
        for (HashMap.Entry<Student, List<Attendance>> entry : attendanceHashMap.entrySet()) {
            Student student = entry.getKey();
            List<Attendance> attendanceList = entry.getValue();
            if (lessonNames.isEmpty()) {
                for (Attendance attendance : attendanceList) {
                    lessonNames.add(attendance.getLesson().getTopic());
                }
            }
            List<Boolean> currentStudentAttendance = new ArrayList<>(lessonNames.size());
            for (Attendance attendance : attendanceList) {
                String topic = attendance.getLesson().getTopic();
                int index = lessonNames.indexOf(topic);
                currentStudentAttendance.add(index, attendance.isAttendance());
            }
            result.put(student, currentStudentAttendance);
        }
        printGroup(result, lessonNames);
        System.out.println();
        System.out.println("***************************************************************");
    }

    private void printGroup(HashMap<Student, List<Boolean>> result, List<String> lessonNames) {
        int longestSecondName = 0;
        for (Map.Entry<Student, List<Boolean>> entry : result.entrySet()) {
            Student student = entry.getKey();
            String secondName = student.getSecondName();
            int length = secondName.length();
            if (length > longestSecondName) {
                longestSecondName = length;
            }
        }
        longestSecondName = longestSecondName + 2;
        makeSpace(longestSecondName);
        lessonNames.forEach(s -> {
            System.out.print(s);
            System.out.print(" ");

        });

        for (Map.Entry<Student, List<Boolean>> entry : result.entrySet()) {
            System.out.println();
            String secondName = entry.getKey().getSecondName();
            System.out.print(secondName);
            List<Boolean> value = entry.getValue();
            for (int i = 0; i < value.size(); i++) {
                int fioDifference = longestSecondName - secondName.length();
                int space;
                if (i == 0) {
                    space = lessonNames.get(i).length() / 2 + fioDifference;
                } else {
                    space = lessonNames.get(i).length() / 2;
                }
      makeSpace(space);
                String attendanceChar = value.get(i) ? "+" : "-";
                System.out.print(attendanceChar);
                if (i == 0) {
                    makeSpace(space - fioDifference);
                } else {
                    makeSpace(space);
                }
            }
        }


    }

    private void makeSpace(int space) {
        for (int sp = 0; sp < space; sp++) {
            System.out.print(" ");
        }
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

        public Lesson getLesson() {
            return lesson;
        }

        public void setLesson(Lesson lesson) {
            this.lesson = lesson;
        }

        public boolean isAttendance() {
            return attendance;
        }

        public void setAttendance(boolean attendance) {
            this.attendance = attendance;
        }

        public Attendance(Lesson lesson, boolean attendance) {
            this.lesson = lesson;
            this.attendance = attendance;
        }
    }
}
