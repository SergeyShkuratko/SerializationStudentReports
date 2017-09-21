package com.company;

import com.company.report.Attendance;

import java.io.*;

public class MySerializationAction {

    public static void serializeGroup(Group group) throws IOException {
        File file = new File("group.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(group);
    }

    public static Group readGroup(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
        return (Group) objectInputStream.readObject();
    }

    public static void serialize(Attendance attendance) {
        if (attendance == null) {
            System.out.println("Can not serialize null object");
            return;
        }
        try {
            String fileName = attendance.getFileName();
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            outputStream.writeObject(attendance);
        } catch (IOException e) {
            System.out.println("Serialization error");
            e.printStackTrace();
        } catch (RuntimeException e) {

        }
    }

    public static Attendance deserialize(String fileName) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            return (Attendance) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Deserialization error");
            e.printStackTrace();
        }
        return null;
    }
}
