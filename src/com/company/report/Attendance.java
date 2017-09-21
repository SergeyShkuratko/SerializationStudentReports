package com.company.report;

import java.io.Serializable;

public interface Attendance extends Serializable {
    void print();

    String getFileName();
}
