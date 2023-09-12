package ru.mirea.feofanov.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Constraints constraints	=	new	Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresCharging(true)
                .setRequiresBatteryNotLow(true) //уровень батареи не ниже критического
                .setRequiresDeviceIdle(false) //девайс не используется какое-то время и ушел в спячку
                .build();
        WorkRequest	uploadWorkRequest	=
                new	OneTimeWorkRequest.Builder(UploadWorker.class)
                        .setConstraints(constraints)
                        .build();
        WorkManager
                .getInstance(this)
                .enqueue(uploadWorkRequest);
    }
}