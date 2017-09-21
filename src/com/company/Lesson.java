package com.company;

import java.io.Serializable;

public class Lesson  implements Serializable{
    private final String topic;
    private final long dateTime;
    private final short classRoom;
    private final String teacherName;

    public String getTopic() {
        return topic;
    }

    public long getDateTime() {
        return dateTime;
    }

    public short getClassRoom() {
        return classRoom;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public Lesson(String topic, long dateTime, short classRoom, String teacherName) {
        this.topic = topic;
        this.dateTime = dateTime;
        this.classRoom = classRoom;
        this.teacherName = teacherName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lesson lesson = (Lesson) o;

        if (dateTime != lesson.dateTime) return false;
        if (classRoom != lesson.classRoom) return false;
        if (!topic.equals(lesson.topic)) return false;
        return teacherName.equals(lesson.teacherName);
    }

    @Override
    public int hashCode() {
        int result = topic.hashCode();
        result = 31 * result + (int) (dateTime ^ (dateTime >>> 32));
        result = 31 * result + (int) classRoom;
        result = 31 * result + teacherName.hashCode();
        return result;
    }
}
