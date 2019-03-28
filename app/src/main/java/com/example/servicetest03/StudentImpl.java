package com.example.servicetest03;

import android.os.RemoteException;

public class StudentImpl extends IStudent.Stub {

    private Student student;

    public StudentImpl(){
        student = new Student();
    }

    @Override
    public void setStudent(String name, String sex) throws RemoteException {
        student.setName(name);
        student.setSex(sex);
    }

    @Override
    public Student getStudent() throws RemoteException {
        return student;
    }
}
