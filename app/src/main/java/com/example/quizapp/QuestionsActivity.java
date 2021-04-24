package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionsActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    TextView tv,anscheck;
    private GestureDetector gestureDetector;
    private static int mindis=150;
    private float x1,y1,x2,y2;
    Button submitbutton, nextbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;

    String questions[] = {
            " 1. Jorge runs faster than drew . \n 2.Drew runs faster than Bernard ." +
                    " \n 3.Jorge runs faster than Bernard \n If the first two statements are true than \n third statement is",
            "When conducting an inventory of PE storage room, Coach calson found several basketballs ,tennis balls , footballs , baseballs in a locker \n" +
                    "He found 4,6,8,10 of them(a different number of each type he recorded that)\n - There are fewer tennis balls than footballs \n" +
                    "- There are 6 more baseballs than basketballs \n How many footballs are there ?",
            "Which method can be defined only once in a program?",

    };
    String answers[] = {"true","6","main method"};
    String opt[] = {
            "true","false","uncertain","none of these",
            "4","6","8","10",
            "finalize method","main method","static method","private method",

    };
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textView=(TextView)findViewById(R.id.DispName);
        Intent intent = getIntent();

        this.gestureDetector=new GestureDetector(QuestionsActivity.this,this);

        submitbutton=(Button)findViewById(R.id.button3);
        nextbutton=(Button)findViewById(R.id.buttonnext);
        nextbutton.setVisibility(View.INVISIBLE);
        tv=(TextView) findViewById(R.id.tvque);

        radio_g=(RadioGroup)findViewById(R.id.answersgrp);
        rb1=(RadioButton)findViewById(R.id.radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton2);
        rb3=(RadioButton)findViewById(R.id.radioButton3);
        rb4=(RadioButton)findViewById(R.id.radioButton4);
        anscheck=(Button)findViewById(R.id.anscheck);
        anscheck.setVisibility(View.INVISIBLE);
        tv.setText(questions[flag]);
        rb1.setText(opt[0]);
        rb2.setText(opt[1]);
        rb3.setText(opt[2]);
        rb4.setText(opt[3]);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {


                if(radio_g.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton uans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uans.getText().toString();
                if(ansText.equals(answers[flag])) {
                    anscheck.setVisibility(View.VISIBLE);
                    anscheck.setBackground((Drawable)getResources().getDrawable(R.drawable.greenn));
                    anscheck.setText("Correct");

                }
                else {
                    anscheck.setVisibility(View.VISIBLE);
                    anscheck.setBackground((Drawable)getResources().getDrawable(R.drawable.red));
                    anscheck.setText("Wrong");
                }

                flag++;

                submitbutton.setVisibility(View.INVISIBLE);
                nextbutton.setVisibility(View.VISIBLE);

            }
        });

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitbutton.setVisibility(View.VISIBLE);
                anscheck.setVisibility(View.INVISIBLE);
                radio_g.clearCheck();

                if(flag<questions.length)
                {
                    tv.setText(questions[flag]);
                    rb1.setText(opt[flag*4]);
                    rb2.setText(opt[flag*4 +1]);
                    rb3.setText(opt[flag*4 +2]);
                    rb4.setText(opt[flag*4 +3]);
                }
                else
                {

                    Intent in = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(in);
                }
                nextbutton.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1=event.getX();
                y1=event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2=event.getX();
                y2=event.getY();

                float valueX=x2-x1;
                float valueY=y2-y2;

                if(Math.abs(valueX)>mindis){
                    if(x2>x1){

                        //Toast.makeText(this, "swipe right", Toast.LENGTH_SHORT).show();
                        if (flag==0){


                        }else if (flag<questions.length){
                            flag--;
                            tv.setText(questions[flag]);
                            rb1.setText(opt[(flag)*4]);
                            rb2.setText(opt[(flag)*4 +1]);
                            rb3.setText(opt[(flag)*4 +2]);
                            rb4.setText(opt[(flag)*4 +3]);


                        }
                    }else{
                        //Toast.makeText(this, "left swipe", Toast.LENGTH_SHORT).show();
                        flag++;
                        if(flag<questions.length){
                            tv.setText(questions[flag]);
                            rb1.setText(opt[(flag)*4]);
                            rb2.setText(opt[(flag)*4 +1]);
                            rb3.setText(opt[(flag)*4 +2]);
                            rb4.setText(opt[(flag)*4 +3]);

                        }else if(flag==questions.length){
                            flag=flag-1;
                        }


                    }
                }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}