package com.example.sporting;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class UserInfo extends Fragment {
    private EditText NameEdit;
    private TextView NameView;
    private Button SaveName;
    private ImageButton ChangeName;
    private Button back;
    private String newName;
    private SharedPreferences sh_Pref;
    private SharedPreferences.Editor toEdit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.userinfo, container, false);


        NameEdit = view.findViewById(R.id.NameEdit);
        NameView = view.findViewById(R.id.userName);
        SaveName = view.findViewById(R.id.SaveName);
        ChangeName = view.findViewById(R.id.changeName);

        applySharedPreference(view.getContext());

        ChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NameView.setVisibility(View.GONE);
                ChangeName.setVisibility(View.GONE);
                NameEdit.setVisibility(View.VISIBLE);
                SaveName.setVisibility(View.VISIBLE);
            }
        });

        SaveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newName = NameEdit.getText().toString();
                sharedPreference(newName, view.getContext());

                NameEdit.setVisibility(View.GONE);
                SaveName.setVisibility(View.GONE);

                NameView.setText(newName);
                NameView.setVisibility(View.VISIBLE);
                ChangeName.setVisibility(View.VISIBLE);

                // PassBundleFragment는 전달하고자 하는 Fragment 클래스입니다.
                Bundle bundle = new Bundle();
                bundle.putString("key", newName);
                getParentFragmentManager().setFragmentResult("requestKey", bundle);

            }
        });

        return view;
    }

    public void sharedPreference(String userName, Context context) {
        sh_Pref = context.getSharedPreferences("USER_PREFS", Context.MODE_PRIVATE);
        toEdit = sh_Pref.edit();
        toEdit.putString("USERNAME", userName);
        toEdit.apply();
    }

    public void applySharedPreference(Context context){
        sh_Pref = context.getSharedPreferences("USER_PREFS", Context.MODE_PRIVATE);
        if (sh_Pref != null && sh_Pref.contains("USERNAME")) {
            String name = sh_Pref.getString("USERNAME", "noname");
            NameView.setText(name);
        }
    }
}
