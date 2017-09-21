package com.company;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Group implements Serializable {
    private final short groupNum;
    private final String groupName;
    private Set<Student> students = new HashSet<>();

    public Group(short groupNum, String groupName, Set<Student> students) {
        this.groupNum = groupNum;
        this.students = students;
        this.groupName = groupName;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public short getGroupNum() {
        return groupNum;
    }

    public String getGroupName() {
        return groupName;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (groupNum != group.groupNum) return false;
        return groupName.equals(group.groupName);
    }

    @Override
    public int hashCode() {
        int result = groupNum;
        result = 31 * result + groupName.hashCode();
        return result;
    }
}
