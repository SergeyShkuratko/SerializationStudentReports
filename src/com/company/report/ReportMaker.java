package com.company.report;

import com.company.Group;
import com.company.Journal;
import com.company.Lesson;
import com.company.Student;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReportMaker {

    public LessonAttendance lessonAttendance(Lesson lesson, List<Journal> journals) {
        Journal ourJournal = null;
        for (Journal journal : journals) {
            if (journal.getLesson().equals(lesson)) {
                ourJournal = journal;
                break;
            }
        }

        if (ourJournal == null) {
            return null;
        }

        Group group = ourJournal.getGroup();
        Set<Student> missedStudents = new HashSet<>(group.getStudents());
        missedStudents.removeAll(ourJournal.getPresentSet());
        Set<Student> notMissedStudents = new HashSet<>(group.getStudents());
        notMissedStudents.retainAll(ourJournal.getPresentSet());
        return new LessonAttendance(lesson.getTopic(), missedStudents, notMissedStudents);
    }

    public StudentAttendance studentAttendance(Student student, List<Journal> journals) {
        StudentAttendance studentAttendance = new StudentAttendance(student);
        Group studentGroup = student.getGroup();
        Set<Journal> studentGroupJournals = findStudentGroupJournals(journals, studentGroup);
        for (Journal journal : studentGroupJournals) {
            Lesson lesson = journal.getLesson();
            if (journal.getPresentSet().contains(student)) {
                studentAttendance.addAttendance(lesson, true);
            } else {
                studentAttendance.addAttendance(lesson, false);
            }
        }
        return studentAttendance;
    }

    public GroupAttendance groupAttendance(Group group, List<Journal> journals) {
        GroupAttendance groupAttendance = new GroupAttendance();
        for (Journal journal : journals) {
            if (group.equals(journal.getGroup())) {
                Set<Student> presentSet = journal.getPresentSet();
                Lesson lesson = journal.getLesson();
                for (Student student : presentSet) {
                    groupAttendance.addAttendance(student, lesson, true);
                }
                Set<Student> missedStudents = new HashSet<>(group.getStudents());
                missedStudents.removeAll(presentSet);
                for (Student student : missedStudents) {
                    groupAttendance.addAttendance(student, lesson, false);
                }
            }
        }
        return groupAttendance;
    }

    private Set<Journal> findStudentGroupJournals(List<Journal> journals, Group studentGroup) {
        Set<Journal> result = new HashSet<>();
        for (Journal journal : journals) {
            if (studentGroup.equals(journal.getGroup())) {
                result.add(journal);
            }
        }
        return result;
    }
}

