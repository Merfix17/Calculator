package com.example.android.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnEq, btnPlus, btnSub, btnMul, btnDiv, btnDel, btnPer, btnPow, btnPi;
    TextView resTv;
    ArrayList<String> store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        store = new ArrayList<>();

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);
        btnPlus = findViewById(R.id.btnPlus);
        btnEq = findViewById(R.id.btnEq);
        btnDiv = findViewById(R.id.btnDiv);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDel = findViewById(R.id.btnDel);
        btnPer = findViewById(R.id.btnPer);
        btnPow = findViewById(R.id.btnPow);
        btnPi = findViewById(R.id.btnPi);

        resTv = findViewById(R.id.tv);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnEq.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnPow.setOnClickListener(this);
        btnPi.setOnClickListener(this);
        btnPer.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String str;

        switch (v.getId()) {
            case R.id.btnEq:
                store.clear();
                String eq = resTv.getText().toString();

                int index = 0;

                for (int i = 0; i < eq.length(); i++){
                    char c = eq.charAt(i);
                    String p = String.valueOf(eq.charAt(i));

                    if (c == '+'){
                        str = eq.substring(index, i);
                        store.add(str);
                        store.add("+");
                        index = i + 1;
                    }

                    else if (c == '-') {
                        str = eq.substring(index, i);
                        store.add(str);
                        store.add("-");
                        index = i + 1;
                    }

                    else if (c == '*') {
                        str = eq.substring(index, i);
                        store.add(str);
                        store.add("*");
                        index = i + 1;
                    }

                    else if (c == '/') {
                        str = eq.substring(index, i);
                        store.add(str);
                        store.add("/");
                        index = i + 1;
                    }

                    else if (c == '^') {
                        str = eq.substring(index, i);
                        store.add(str);
                        store.add("^");
                        index = i + 1;
                    }

                    else if (p.equals("Pi")) {
                        str = eq.substring(index, i);
                        store.add(str);
                        store.add("Pi");
                        index = i + 1;
                    }

                    else if (c == '%') {
                        str = eq.substring(index, i);
                        store.add(str);
                        store.add("%");
                        index = i + 1;
                    }
                }

                str = eq.substring(index, eq.length());
                store.add(str);



                //[123, + , 565, +, 654]
                double res = 0;
                int t = 0;

                for (int j = 0; j < store.size(); j++) {
                    String getter2 = store.get(j);

                    if (getter2.equals("*")) {
                        int n1 = Integer.parseInt(store.get(j - 1));
                        int n2 = Integer.parseInt(store.get(j + 1));

                        int numRes = n1 * n2;

                        store.add(j - 1, "" + numRes);
                        store.remove(j);
                        store.remove(j);
                    }

                    else if (getter2.equals("/")) {
                        int n1 = Integer.parseInt(store.get(j - 1));
                        int n2 = Integer.parseInt(store.get(j + 1));

                        int numRes = n1 / n2;

                        store.add(j - 1, ""+numRes);

                        store.remove(j);
                        store.remove(j);
                    }

                    else if (getter2.equals("^")) {
                        //[5, ^, 2, 25]
                        int n1 = Integer.parseInt(store.get(j - 1));
                        int n2 = Integer.parseInt(store.get(j + 1));

                        int numRes = (int) Math.pow(n1, n2);

                        store.add(j - 1, ""+numRes);

                        store.remove(j);
                        store.remove(j);
                    }

//                    else if (getter2.equals("Pi")) {
//                        //[7*3.14]
//                        int n1 = Integer.parseInt(store.get(j - 1));
//                        double n2 = 3.14;
//
//                        int numRes = (int) (n1 * n2);
//
//                        store.add(j - 1, ""+numRes);
//
//                        store.remove(j);
//                        store.remove(j);
//                    }

                    else if (getter2.equals("%")) {
                        //["9", "%", "3", "33.0"]
                        int n1 = Integer.parseInt(store.get(j - 1));
                        int n2 = Integer.parseInt(store.get(j + 1));

                        int numRes = (n2 * 100) / n1;

                        store.add(j - 1, ""+numRes);

                        store.remove(j);
                        store.remove(j);
                    }
                }

                for (int j = store.size()-1; j >= 0; j--) {
                    String getter = store.get(j);

                    if (getter.equals("+")) {
                        res = res + t;
                    }

                    else if (getter.equals("-")) {
                        res = res - t;
                    }

                    else {
                        t = Integer.parseInt(getter);
                    }
                }



                res = res + Double.parseDouble(store.get(0));
                resTv.setText("" + res);

                Log.i("info", "" + store);

                break;
            case R.id.btn1:
                resTv.setText(resTv.getText() + "1");
                break;
            case R.id.btn2:
                resTv.setText(resTv.getText() + "2");
                break;
            case R.id.btn3:
                resTv.setText(resTv.getText() + "3");
                break;
            case R.id.btn4:
                resTv.setText(resTv.getText() + "4");
                break;
            case R.id.btn5:
                resTv.setText(resTv.getText() + "5");
                break;
            case R.id.btn6:
                resTv.setText(resTv.getText() + "6");
                break;
            case R.id.btn7:
                resTv.setText(resTv.getText() + "7");
                break;
            case R.id.btn8:
                resTv.setText(resTv.getText() + "8");
                break;
            case R.id.btn9:
                resTv.setText(resTv.getText() + "9");
                break;
            case R.id.btn0:
                resTv.setText(resTv.getText() + "0");
                break;
            case R.id.btnPlus:
                resTv.setText(resTv.getText() + "+");
                break;
            case R.id.btnSub:
                resTv.setText(resTv.getText() + "-");
                break;
            case R.id.btnMul:
                resTv.setText(resTv.getText() + "*");
                break;
            case R.id.btnDiv:
                resTv.setText(resTv.getText() + "/");
                break;
            case R.id.btnDel:
                store.clear();
                resTv.setText("");
                break;
            case R.id.btnPow:
                resTv.setText(resTv.getText() + "^");
                break;
            case R.id.btnPi:
                resTv.setText(resTv.getText() + "Pi");
                break;
            case R.id.btnPer:
                resTv.setText(resTv.getText() + "%");
                break;
        }

    }
}
