package com.example.my1stapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class secondScreenActivity extends AppCompatActivity {

    TextView textView;
    double firstNum;
    double secondNum;
    double result;
    CharSequence operator;
    boolean after_calc = false;

    private final String KEY="KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        String lastResult = getIntent().getStringExtra(KEY);
        textView = findViewById(R.id.textViewResult);
        textView.setText("");
        Toast.makeText(this,"You are now in scientific calculator mode and the last result is : " + lastResult ,Toast.LENGTH_LONG).show();
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

        Button b = (Button) view;

        firstNum = Double.parseDouble(textView.getText().toString());
        textView.setText("");
        operator = b.getText();

    }

    public void funcEqual(View view) {

        secondNum = Double.parseDouble(textView.getText().toString());

        textView.setText("");

        if (Objects.equals(operator, "+")) {

            result = firstNum + secondNum;
            textView.setText(format(result));

        }

        if (Objects.equals(operator, "-")) {

            result = firstNum - secondNum;
            textView.setText(format(result));

        }
        if (Objects.equals(operator, "*")) {

            result = firstNum * secondNum;
            textView.setText(format(result));

        }
        if (Objects.equals(operator, "/")) {

            result = firstNum / secondNum;
            textView.setText(format(result));

        }
        if (Objects.equals(operator, "^")) {

            result = Math.pow(firstNum,secondNum);
            textView.setText(format(result));

        }

        if (Objects.equals(operator, "root")) {

            if(firstNum>0){
                result = Math.pow(firstNum,(1/secondNum));
                textView.setText(format(result));

            }
            else{
                Toast.makeText(this,"The second number is not a positive number so a root is not possible at the moment.",Toast.LENGTH_LONG).show();
            }
        }

        if (Objects.equals(operator, "%")) {
            result = (firstNum * secondNum)/100;
            textView.setText(format(result));

        }
        after_calc = true;
    }

    public void funcFactorial(View view) {
        firstNum = Double.parseDouble(textView.getText().toString());

        if((firstNum % 2 == 0)||((firstNum % 2) - 1 == 0)) {
            long fact = 1;
            for (int i = 2; i <= firstNum; i++) {
                fact = fact * i;
            }
            result = fact;
            textView.setText(format(result));
            after_calc = true;

        }
        else{
            Toast.makeText(this,"This number is not a positive integer so a factorial is not possible at the moment.",Toast.LENGTH_LONG).show();
        }
    }
    public void funcPI(View view){
        result = Math.PI;
        textView.setText(format(result));
        after_calc = true;

    }
    public void funcReg(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public static String format(double num) {
        if (num == (int) num) {
            String result_num = String.format("%d", (int) num);
            return result_num;
        } else {
            String result_num = String.format("%.7f", num);
            return result_num;
        }
    }

    public void funcSin(View view) {
        firstNum = Double.parseDouble(textView.getText().toString());
        double rad = Math.toRadians(firstNum);
        result = Math.sin(rad);
        textView.setText(format(result));
        after_calc = true;

    }

    public void funcCos(View view) {
        firstNum = Double.parseDouble(textView.getText().toString());
        double rad = Math.toRadians(firstNum);
        result = Math.cos(rad);
        textView.setText(format(result));
        after_calc = true;

    }

    public void funcTan(View view) {
        firstNum = Double.parseDouble(textView.getText().toString());
        double rad = Math.toRadians(firstNum);
        result = Math.tan(rad);
        textView.setText(format(result));
        after_calc = true;

    }

    public void funcSqrt(View view) {
        firstNum = Double.parseDouble(textView.getText().toString());
        if(firstNum>0) {
            result = Math.sqrt(firstNum);
            textView.setText(format(result));
            after_calc = true;
        }
        else{
            Toast.makeText(this,"Your number is not a positive number so a root is not possible at the moment.",Toast.LENGTH_LONG).show();
        }
    }
}