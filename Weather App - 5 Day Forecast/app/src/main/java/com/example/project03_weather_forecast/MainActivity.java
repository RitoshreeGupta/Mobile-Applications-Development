package com.example.project03_weather_forecast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {

    private Spinner drop_menu;
    int curr_pos = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drop_menu = (Spinner)findViewById(R.id.spinner_menu);

        drop_menu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == curr_pos){
                    return;
                }else{
                    Intent intent = new Intent(MainActivity.this, activity_two.class);
                    if(position == 1){
                        intent.putExtra("city_name","Detroit");
                        startActivity(intent);
                    }else if (position == 2){
                        intent.putExtra("city_name","Dearborn");
                        startActivity(intent);

                    }else if(position == 3){
                        intent.putExtra("city_name","Warren");
                        startActivity(intent);
                    }else if(position == 4){
                        intent.putExtra("city_name","New York");
                        startActivity(intent);
                    } else{

                        intent.putExtra("city_name","Monroe");
                        startActivity(intent);

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}