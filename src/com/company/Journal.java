package com.company;

import java.util.Set;

public class Journal {
    private Lesson lesson;
    private Group group;
    private Set<Student> presentSet;

    public Journal(Lesson lesson, Group group, Set<Student> presentSet) {
        this.lesson = lesson;
        this.group = group;
        this.presentSet = presentSet;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Set<Student> getPresentSet() {
        return presentSet;
    }

    public void setPresentSet(Set<Student> presentSet) {
        this.presentSet = presentSet;
    }
}
