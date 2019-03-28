// IStudent.aidl
package com.example.servicetest03;

import com.example.servicetest03.student;
interface IStudent {
    void setStudent(String name, String sex);
    Student getStudent();
}
