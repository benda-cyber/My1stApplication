package com.example.my1stapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    double firstNum;
    double secondNum;
    double result;
    double lastResult;
    char operator;
    boolean after_calc = false;

    private final String KEY="KEY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textViewResult);
        textView.setText("");

    }


    public void funcNumbers(View view) {

        Button b = (Button) view;
        if (!after_calc) {
            textView.append(b.getText());
        } else {
            textView.setText("");
            textView.append(b.getText());
            after_calc = false;
        }

    }

    public void funcDel(View view) {

        Button b = (Button) view;
        String str = String.valueOf(textView.getText());
        if((str!=null)&&(str.length()!=0)){
            str = str.substring(0, str.length() - 1);
            textView.setText(str);
        }
    }

    public void funcOperator(View view) {


        Animation blinkanimation = AnimationUtils.loadAnimation(this, R.anim.blinkanimation);
        view.startAnimation(blinkanimation);

        Button b = (Button) view;

        firstNum = Double.parseDouble(textView.getText().toString());
        textView.setText("");
        operator = b.getText().charAt(0);

    }

    public void funcEqual(View view) {

        secondNum = Double.parseDouble(textView.getText().toString());

        textView.setText("");

        if (Objects.equals(operator, '+')) {

            result = firstNum + secondNum;
            textView.setText(format(result));

        }

        if (Objects.equals(operator, '-')) {

            result = firstNum - secondNum;
            textView.setText(format(result));

        }
        if (Objects.equals(operator, '*')) {

            result = firstNum * secondNum;
            textView.setText(format(result));

        }
        if (Objects.equals(operator, '/')) {

            result = firstNum / secondNum;
            textView.setText(format(result));

        }
        after_calc = true;
        lastResult=result;
    }

    public void funcSci(View view) {
        Intent intent = new Intent(this, secondScreenActivity.class);
        intent.putExtra(KEY,String.valueOf(format(lastResult)));
        startActivity(intent);
    }

    public static String format(double num) {
        if (num == (int) num) {
            String result_num = String.format("%d", (int) num);
            return result_num;
        }
        return String.valueOf(num);
//        else {
//           String result_num = String.format("%.7f", num);
//            return result_num;
//        }
    }

}