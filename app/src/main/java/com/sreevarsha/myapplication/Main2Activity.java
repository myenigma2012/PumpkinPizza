package com.sreevarsha.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intename =getIntent();
        int failed = (int) intename.getIntExtra("f1",0 );
        int correct = (int) intename.getIntExtra("c1",0);
        TextView tot = (TextView) findViewById(R.id.tot) ;
        TextView succ = (TextView) findViewById(R.id.succ);
        TextView fail = (TextView) findViewById(R.id.fail);
        int total= failed+correct;
        System.out.println( correct+ total+ failed);
        tot.setText("Number of Attempts:" + total );
        succ.setText("Number of successful attempts:" + correct);
        fail.setText("Number of unsuccessful attempts:" + failed);
            }
        }



