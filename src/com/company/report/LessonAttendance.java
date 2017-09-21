package com.company.report;

import com.company.Student;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.stream.Collectors;

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

    private void writeObject(ObjectOutputStream stream)
            throws IOException {
        stream.writeObject(lessonName);
        Set<Student> collectMissed = missedStudents.stream().filter(student -> student.getNum() >= 2).collect(Collectors.toSet());
        stream.writeObject(collectMissed);
        Set<Student> collectPresent = presentStudents.stream().filter(student -> student.getNum() >= 2).collect(Collectors.toSet());
        stream.writeObject(collectPresent);
    }

    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        lessonName = (String) stream.readObject();
        Set<Student> oldMissedStudents = (Set<Student>) stream.readObject();
        missedStudents = oldMissedStudents.stream().filter(student -> student.getNum() == 3).collect(Collectors.toSet());
        Set<Student> oldPresentStudents = (Set<Student>) stream.readObject();
        presentStudents = oldPresentStudents.stream().filter(student -> student.getNum() == 3).collect(Collectors.toSet());
    }
}
