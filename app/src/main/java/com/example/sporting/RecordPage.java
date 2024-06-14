package com.example.sporting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class RecordPage extends Fragment {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> recordList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recordpage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.listView);
        recordList = new ArrayList<>();

        // Get the data from the arguments
        if (getArguments() != null) {
            String occultBlood = getArguments().getString("occultBlood");
            String pH = getArguments().getString("pH");
            String protein = getArguments().getString("protein");
            String glucose = getArguments().getString("glucose");
            String ketone = getArguments().getString("ketone");
            String userName = getArguments().getString("userName");

            // Add data to the record list
            recordList.add("Name: " + userName);
            recordList.add("Occult Blood: " + occultBlood);
            recordList.add("pH: " + pH);
            recordList.add("Protein: " + protein);
            recordList.add("Glucose: " + glucose);
            recordList.add("Ketone: " + ketone);
        }

        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, recordList);
        listView.setAdapter(adapter);
    }
}
