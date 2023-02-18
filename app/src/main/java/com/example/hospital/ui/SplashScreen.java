package com.example.hospital.ui;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hospital.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {
    ActivitySplashScreenBinding binding;
    int currentProgress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        CountDownTimer countDownTimer = new CountDownTimer(11*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                currentProgress+=10;
                binding.progressBar.setProgress(currentProgress);
                binding.progressBar.setMax(100);

            }

            @Override
            public void onFinish() {
                Toast.makeText(SplashScreen.this, "finished", Toast.LENGTH_SHORT).show();
            }
        };
        countDownTimer.start();

    }



}