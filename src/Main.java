import com.company.*;
import com.company.report.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Journal> allJournals = new ArrayList<>();


        Student student0 = new Student((short) 1, "Иван", "Иванович", "Иванов", Date.parse("1995/07/07"));
        Student student1 = new Student((short) 2, "Петр", "Петрович", "Петров", Date.parse("1995/06/06"));
        Student student2 = new Student((short) 3, "Федор", "Федорович", "Федеров", Date.parse("1995/05/05"));

        Set<Student> students = new HashSet<>();
        students.add(student0);
        students.add(student1);
        students.add(student2);

        Group group = new Group((short) 1, "Группа доп подготовки", students);
        students.forEach(student -> {
            student.setGroup(group);
        });

        Lesson lesson0 = new Lesson("Типизация", Date.parse("2017/09/21"), (short) 1, "Артем");
        Lesson lesson1 = new Lesson("Потоки", Date.parse("2017/09/21"), (short) 1, "Артем");
        Lesson lesson2 = new Lesson("Массивы", Date.parse("2017/09/21"), (short) 1, "Артем");
        Lesson lesson3 = new Lesson("Фреймворки", Date.parse("2017/09/21"), (short) 1, "Артем");

        Set<Student> presentStudents0 = new HashSet<>();
        presentStudents0.add(student1);
        Set<Student> presentStudents1 = new HashSet<>();
        presentStudents1.add(student1);
        presentStudents1.add(student2);
        Set<Student> presentStudents2 = new HashSet<>();
        presentStudents2.add(student1);
        presentStudents2.add(student0);
        Set<Student> presentStudents3 = new HashSet<>();
        presentStudents3.add(student0);
        presentStudents3.add(student1);
        presentStudents3.add(student2);


        Journal journal0 = new Journal(lesson0, group, presentStudents0);
        Journal journal1 = new Journal(lesson1, group, presentStudents1);
        Journal journal2 = new Journal(lesson2, group, presentStudents2);
        Journal journal3 = new Journal(lesson3, group, presentStudents3);

        allJournals.add(journal0);
        allJournals.add(journal1);
        allJournals.add(journal2);
        allJournals.add(journal3);

        ReportMaker reportMaker = new ReportMaker();
        LessonAttendance lessonAttendance = reportMaker.lessonAttendance(lesson3, allJournals);
        MySerializationAction.serialize(lessonAttendance);

        Attendance deserialize = MySerializationAction.deserialize(LessonAttendance.FILE_NAME);
        if (deserialize != null) {
            deserialize.print();
        }

        StudentAttendance studentAttendances = reportMaker.studentAttendance(student2, allJournals);
        MySerializationAction.serialize(studentAttendances);
        deserialize = MySerializationAction.deserialize(StudentAttendance.FILE_NAME);
        if (deserialize != null) {
            deserialize.print();
        }

        GroupAttendance groupAttendance = reportMaker.groupAttendance(group, allJournals);
        MySerializationAction.serialize(groupAttendance);
        deserialize = MySerializationAction.deserialize(GroupAttendance.FILE_NAME);
        if (deserialize != null) {
            deserialize.print();
        }

    }
}
