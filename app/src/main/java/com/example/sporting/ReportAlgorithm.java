package com.example.sporting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReportAlgorithm extends AppCompatActivity {
    private TextView occultBloodTextView;
    private TextView pHTextView;
    private TextView proteinTextView;
    private TextView glucoseTextView;
    private TextView ketoneTextView;
    private Button backButton;
    private TextView userNameReportTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_report);

        // TextView 연결
        userNameReportTextView = findViewById(R.id.userNameReport);
        occultBloodTextView = findViewById(R.id.ResultOccultBlood);
        pHTextView = findViewById(R.id.ResultpH);
        proteinTextView = findViewById(R.id.ResultProtein);
        glucoseTextView = findViewById(R.id.ResultGlucose);
        ketoneTextView = findViewById(R.id.ResultKetone);

        // Intent로부터 값 받기
        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        String occultBloodStr = intent.getStringExtra("occultBlood");
        String pHStr = intent.getStringExtra("pH");
        String proteinStr = intent.getStringExtra("protein");
        String glucoseStr = intent.getStringExtra("glucose");
        String ketoneStr = intent.getStringExtra("ketone");

        // userNameReportTextView에 값 설정
        if (userName != null) {
            userNameReportTextView.setText(userName);
        }

        // 값을 "정상" 또는 "비정상"으로 설정
        if (occultBloodStr != null) {
            if (occultBloodStr.equals("음성")) {
                occultBloodTextView.setText("정상");
            } else {
                occultBloodTextView.setText("비정상");
            }
        }

        if (pHStr != null) {
            try {
                double pH = Double.parseDouble(pHStr);
                if (pH > 4.8 && pH < 7.8) {
                    pHTextView.setText("정상");
                } else {
                    pHTextView.setText("비정상");
                }
            } catch (NumberFormatException e) {
                pHTextView.setText("입력 오류");
            }
        }

        if (glucoseStr != null) {
            try {
                int glucose = Integer.parseInt(glucoseStr);
                if (glucose < 30) {
                    glucoseTextView.setText("정상");
                } else {
                    glucoseTextView.setText("비정상");
                }
            } catch (NumberFormatException e) {
                glucoseTextView.setText("입력 오류");
            }
        }

        if (proteinStr != null) {
            try {
                int protein = Integer.parseInt(proteinStr);
                if (protein > 10 && protein < 20) {
                    proteinTextView.setText("정상");
                } else {
                    proteinTextView.setText("비정상");
                }
            } catch (NumberFormatException e) {
                proteinTextView.setText("입력 오류");
            }
        }

        if (ketoneStr != null) {
            try {
                int ketone = Integer.parseInt(ketoneStr);
                if (ketone < 30) {
                    ketoneTextView.setText("정상");
                } else {
                    ketoneTextView.setText("비정상");
                }
            } catch (NumberFormatException e) {
                ketoneTextView.setText("입력 오류");
            }
        }

        // Button 연결
        backButton = findViewById(R.id.backButton);

        // 뒤로가기 버튼 클릭 리스너 설정
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 뒤로가기 동작
                finish();
            }
        });
    }
}
