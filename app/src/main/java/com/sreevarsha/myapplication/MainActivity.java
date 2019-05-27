package com.sreevarsha.myapplication;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.Color;
import java.util.Random;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.support.constraint.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    EditText eText;
    Button btn;
    int r;
    int c=10;
    int failed=0;
    int correct=0;



    public int generateRandomInt( ) {
        Random ran = new Random();
        int random= ran.nextInt(100);
        random = random +1;
        return random;
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //gen num
        r = generateRandomInt();

        //assigning the ids
        final EditText eText = (EditText) findViewById(R.id.n1);
        final Button btn1 =(Button) findViewById(R.id.b1);
        Button btn2 = (Button) findViewById(R.id.b2);
        Button btn3 = (Button) findViewById(R.id.b3);
        final TextView info = (TextView) findViewById(R.id.info);
        final ConstraintLayout myLayout = (ConstraintLayout) findViewById(R.id.consLay);

        //guess button
        btn1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                //hiding the keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(myLayout.getWindowToken(),0);
                String str = eText.getText().toString();
                eText.setText("");
                int num;

                if (str.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Please enter a number.", Toast.LENGTH_LONG).show();
                } else {
                    num = Integer.parseInt(str);
                    Toast.makeText(getBaseContext(), "Checking", Toast.LENGTH_SHORT).show();

                    // change bg
                    int diff = r - num;
                    if (diff < 0) {
                        diff = -1 * diff;
                    }
                    if(diff <= 10)
                        myLayout.setBackgroundColor(Color.rgb(0,255- 20*diff, 0));
                    else {
                        if (diff * 3 > 255)
                            myLayout.setBackgroundColor(Color.rgb(255, 0, 0));
                        else {
                            myLayout.setBackgroundColor(Color.rgb(diff * 3, 0, 0));
                            }
                    }


                    if (num == r) {
                        Toast.makeText(getApplicationContext(), "Answer is Correct! Play another game.", Toast.LENGTH_LONG).show();
                        System.out.println(r);
                        r = generateRandomInt();
                        c = 10;
                        correct++;
                        System.out.println(correct);
                    }
                     else if (num > r) {
                        Toast.makeText(getApplicationContext(), "Guess a lower answer!", Toast.LENGTH_LONG).show();
                        System.out.println(r);
                        c-- ;
                    } else {
                        Toast.makeText(getApplicationContext(), "Guess a higher answer!", Toast.LENGTH_LONG).show();
                        System.out.println(r);
                        c --;
                    }

                    {
                    info.setText("Number of Attempts Left:" + c);
                    if (c == 0) {
                        Toast.makeText(getBaseContext(), "You've completed 10 tries and lost the game. Play again!", Toast.LENGTH_LONG).show();
                        r = generateRandomInt();
                        c = 10;
                        info.setText("Number of Attempts Left:" + c);
                        failed++;
                        System.out.println(failed);}}
                    }
                }



        });

        //return button accepts answer
        eText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    btn1.performClick();
                }
                return false;
            }
        });

        //score button
        btn2.setOnClickListener(new OnClickListener() {
                public void onClick (View view1){
                    Intent i = new Intent(MainActivity.this, Main2Activity.class);
                    i.putExtra("f1",failed);
                    i.putExtra("c1",correct);
                    startActivity(i);
                }
            });
       // reset button
       btn3.setOnClickListener( new OnClickListener() {
            public void onClick ( View view2){
                failed= 0;
                correct = 0;
                c=10;
                info.setText("Number of Attempts Left:" + c );
            }
       }) ;
    }

}
