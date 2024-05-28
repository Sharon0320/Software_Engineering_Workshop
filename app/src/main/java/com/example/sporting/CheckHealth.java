package com.example.sporting;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    }
}
