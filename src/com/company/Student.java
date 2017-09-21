package com.company;

import java.io.*;
import java.lang.reflect.Field;

public class Student implements Serializable {
    private final short num;
    private final long birthday;
    private String firstName;
    private String secondName;
    private String familyName;
    private Group group;

    public Student(short num, String firstName, String secondName, String familyName, long birthday) {
        this.num = num;
        this.firstName = firstName;
        this.secondName = secondName;
        this.familyName = familyName;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (num != student.num) return false;
        return birthday == student.birthday;
    }

    public short getNum() {
        return num;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public long getBirthday() {
        return birthday;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public int hashCode() {
        int result = (int) num;
        result = 31 * result + (int) (birthday ^ (birthday >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "num=" + num +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", familyName='" + familyName + '\'' +
                '}';
    }

    private void writeObject(ObjectOutputStream stream)
            throws IOException {

        stream.writeShort(num);
        stream.writeLong(birthday);
        stream.writeObject(firstName);
        stream.writeObject(secondName);
        stream.writeObject(familyName);
        stream.writeObject(group);
    }

    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> myClass = this.getClass();

        Field field = myClass.getDeclaredField("num");
        boolean wasAccessible = field.isAccessible();
        field.setAccessible(true);
        field.setShort(this, stream.readShort());
        field.setAccessible(wasAccessible);

        field = myClass.getDeclaredField("birthday");
        wasAccessible = field.isAccessible();
        field.setAccessible(true);
        field.setLong(this, stream.readLong());
        field.setAccessible(wasAccessible);

        firstName = (String) stream.readObject();
        secondName = (String) stream.readObject();
        familyName = (String) stream.readObject();
        group = (Group) stream.readObject();
    }


//    @Override
//    public void writeExternal(ObjectOutput out) throws IOException {
//        out.flush();
////        if(num == 3)
////        {
////            out.flush();
////        }
//    }
//
//    @Override
//    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
//
//    }
}
