package com.example.servicetest03;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Button button1_setStudent, button2_getStudent;
    private IStudent studentImpl;
    private boolean mBound = false;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            studentImpl = IStudent.Stub.asInterface(service);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1_setStudent = (Button) findViewById(R.id.button1_setStudent);
        button2_getStudent = (Button) findViewById(R.id.button2_getStudent);
        button2_getStudent.setOnClickListener(this);
        button1_setStudent.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent bindIntent = new Intent(this, MyService.class);
        bindService(bindIntent, connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBound){
            unbindService(connection);
            mBound = false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1_setStudent:
                try {
                    studentImpl.setStudent("shengmingyihao", "nan");
                    Toast.makeText(MainActivity.this, "success",Toast.LENGTH_LONG).show();
                } catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.button2_getStudent:
                Student s;
                try {
                    s = studentImpl.getStudent();
                    Toast.makeText(MainActivity.this, "name=" + s.getName() + ", sex=" + s.getSex(), Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}
