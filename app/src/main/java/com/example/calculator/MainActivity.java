package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result_tv,solution_tv;
    MaterialButton button_c,button_openbracket,button_closebracket,button_equalto;
    MaterialButton button_divide,button_seven,button_eight,button_nine;
    MaterialButton button_add,button_subtract,button_four,button_five,button_six;
    MaterialButton button_one,button_two,button_three,button_multiply,button_ac,button_point,button_zero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_tv = findViewById(R.id.result_tv);
        solution_tv = findViewById(R.id.solution_tv);

        assignId(button_ac,R.id.button_ac);
        assignId(button_divide,R.id.button_divide);
        assignId(button_point,R.id.button_point);
        assignId(button_c,R.id.button_c);
        assignId(button_multiply,R.id.button_multiply);
        assignId(button_add,R.id.button_add);
        assignId(button_subtract,R.id.button_subract);
        assignId(button_equalto,R.id.button_equalto);
        assignId(button_openbracket,R.id.button_openbracket);
        assignId(button_closebracket,R.id.button_closebracket);
        assignId(button_zero,R.id.button_zero);
        assignId(button_one,R.id.button_one);
        assignId(button_two,R.id.button_two);
        assignId(button_three,R.id.button_three);
        assignId(button_four,R.id.four);
        assignId(button_five,R.id.button_five);
        assignId(button_six,R.id.button_six);
        assignId(button_seven,R.id.button_seven);
        assignId(button_eight,R.id.button_eight);
        assignId(button_nine,R.id.button_nine);




    }
    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        MaterialButton button = (MaterialButton) view;
        String buttontext = button.getText().toString();
        String datatocalculate = solution_tv.getText().toString();

        if (buttontext.equals("ac")) {
            solution_tv.setText("");
            result_tv.setText("0");
            return;
        }
        if (buttontext.equals("=")) {
            solution_tv.setText((result_tv).getText());
            return;
        }
        if (buttontext.equals("C")) {
                if (datatocalculate.length()>1){
                datatocalculate = datatocalculate.substring(0,datatocalculate.length()-1);
                }
                else{
                    datatocalculate = "0";
                }


//            datatocalculate = datatocalculate.substring(0,datatocalculate.length()-1);
        } else {
            datatocalculate = datatocalculate+buttontext;
        }
        solution_tv.setText((datatocalculate));

        String finalresult = getResult(datatocalculate);
        if (!finalresult.equals("err")){
            result_tv.setText(finalresult);
        }
    }


    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalresult = context.evaluateString(scriptable,data,"javascript",1,null).toString();

            if (finalresult.endsWith(".0")){
                finalresult = finalresult.replace(".0","");
            }return finalresult;
        }catch (Exception e){
            return "err";
        }
    }
}