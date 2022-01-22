package com.sunag.medicinetime.medicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.sunag.medicinetime.R;

//import com.sunag.medicinetime.medicine.R;


public class MainActivity extends AppCompatActivity {
    SliderView sliderView;
    int[] images = {R.drawable.f1,
            R.drawable.f2,
            R.drawable.f3,
            R.drawable.f4,
            R.drawable.f5,
            R.drawable.f6};

//    Switch sw = (Switch)findViewById(R.id.dl);
    Button button,trial,chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sliderView = findViewById(R.id.image_slider);

        SliderAdapter sliderAdapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();


        button = (Button) findViewById(R.id.button);
        trial = (Button) findViewById(R.id.trial);
        chat = (Button) findViewById(R.id.chatBot);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });

        trial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity1();
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity2();
            }
        });
//        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(sw.isChecked())
//                {
////                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                    MainActivity.super.setTheme(R.style.Theme_AppCompat_DayNight);
//                }
////                else
////                {
////                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
////                }
//            }
//        });
//        sw.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(sw.isChecked())
//                {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                }
//                else
//                {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                }
//            }
//        });
    }
    public void openNewActivity1() {
        Intent intent = new Intent(this, quiz.class);
        startActivity(intent);

    }

    public void openNewActivity(){
        Intent intent = new Intent(this, MedicineActivity.class);
        startActivity(intent);
    }

    public void openNewActivity2(){
        Intent intent = new Intent(this, chatBot.class);
        startActivity(intent);
    }


}