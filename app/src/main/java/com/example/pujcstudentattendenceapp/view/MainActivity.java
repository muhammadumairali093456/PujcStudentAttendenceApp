package com.example.pujcstudentattendenceapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pujcstudentattendenceapp.R;

public class MainActivity extends AppCompatActivity {

    Button sub;
    TextView img2;
    ImageView img;
     Animation frombottom,fromtop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.imageView);
        img2 = (TextView) findViewById(R.id.textView27);
        sub = (Button)findViewById(R.id.button);
        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        sub.setAnimation(frombottom);
        img.setAnimation(fromtop);
        img2.setAnimation(fromtop);

        sub =(Button)findViewById(R.id.button);
        sub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent intent =new Intent(MainActivity.this,LoginMenu.class);
                startActivity(intent);
            }
        });

    }
}
