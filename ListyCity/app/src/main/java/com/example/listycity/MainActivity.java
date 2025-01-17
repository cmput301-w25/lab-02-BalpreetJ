package com.example.listycity;

import static java.util.Arrays.asList;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    String selectedCity = null;
    int selectedPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;





        });
        setContentView(R.layout.activity_main);
        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);



        Button addButton = findViewById(R.id.add_button);
        Button delButton = findViewById(R.id.del_button);
        EditText editTextCity = findViewById(R.id.editTextCity);
        Button btnConfirm = findViewById(R.id.btnConfirm);

        editTextCity.setVisibility(View.GONE);
        btnConfirm.setVisibility(View.GONE);

        addButton.setOnClickListener(v -> {
            editTextCity.setVisibility(View.VISIBLE);
            btnConfirm.setVisibility(View.VISIBLE);
        });

        btnConfirm.setOnClickListener(v -> {
            String newCity = editTextCity.getText().toString().trim();
            if (!newCity.isEmpty()) {
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged();
                //clears and hides the input field after adding the city
                editTextCity.setText("");
                editTextCity.setVisibility(View.GONE);
                btnConfirm.setVisibility(View.GONE);
            }
        });
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedCity = dataList.get(position);
            for (int i = 0; i < cityList.getChildCount(); i++) {
                cityList.getChildAt(i).setBackgroundColor(0);
            }
            view.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        });
        delButton.setOnClickListener(v -> {
            if (selectedCity != null) {
                dataList.remove(selectedCity);
                cityAdapter.notifyDataSetChanged();
                selectedCity = null;

                //resets the colours for all cities after deleeting one city
                selectedPosition = -1;

                for (int i = 0; i < cityList.getChildCount(); i++) {
                    cityList.getChildAt(i).setBackgroundColor(0);
                }
            }
        });

    }
}