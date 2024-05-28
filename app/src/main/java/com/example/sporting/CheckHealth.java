package com.example.sporting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

public class CheckHealth extends Fragment {
    private TextView userName1;
    private TextView userName2;
    private TextView userName3;
    private Button getResult;

    private EditText inputOccultBlood;
    private EditText inputpH;
    private EditText inputProtein;
    private EditText inputGlucose;
    private EditText inputKetone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.checkhealth, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // TextView 연결
        userName1 = view.findViewById(R.id.userName1);
        userName2 = view.findViewById(R.id.userName2);
        userName3 = view.findViewById(R.id.userName3);
        getResult = view.findViewById(R.id.getResult);

        // EditText 연결
        inputOccultBlood = view.findViewById(R.id.inputOccultBlood);
        inputpH = view.findViewById(R.id.inputpH);
        inputProtein = view.findViewById(R.id.inputProtein);
        inputGlucose = view.findViewById(R.id.inputGlucose);
        inputKetone = view.findViewById(R.id.inputKetone);

        // SharedPreferences에서 값을 가져와서 TextView에 설정
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String savedName = sharedPreferences.getString("savedName", ""); // 기본값은 빈 문자열
        userName1.setText(savedName);
        userName2.setText(savedName);
        userName3.setText(savedName);

        // FragmentResultListener 설정
        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                // 번들에서 값 가져오기
                String result = bundle.getString("key");

                if (result != null) {
                    // 값이 null이 아닌 경우 TextView에 설정
                    userName1.setText(result);
                    userName2.setText(result);
                    userName3.setText(result);
                    // SharedPreferences에 값 저장
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("savedName", result);
                    editor.apply();
                    // Toast 메시지로 값 표시
                    Toast.makeText(getActivity(), result + " 으로 이름이 변경되었습니다!", Toast.LENGTH_LONG).show();
                }
            }
        });

        getResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText 값 가져오기
                String occultBlood = inputOccultBlood.getText().toString();
                String pH = inputpH.getText().toString();
                String protein = inputProtein.getText().toString();
                String glucose = inputGlucose.getText().toString();
                String ketone = inputKetone.getText().toString();

                // ReportAlgorithm 액티비티 시작
                Intent intent = new Intent(getActivity(), ReportAlgorithm.class);
                intent.putExtra("occultBlood", occultBlood);
                intent.putExtra("pH", pH);
                intent.putExtra("protein", protein);
                intent.putExtra("glucose", glucose);
                intent.putExtra("ketone", ketone);
                startActivity(intent);
            }
        });
    }
}
