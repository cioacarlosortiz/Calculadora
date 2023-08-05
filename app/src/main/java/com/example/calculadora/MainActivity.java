package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textResult;
    private StringBuilder currentNumber = new StringBuilder();
    private double result = 0;
    private char operator = ' ';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResult = findViewById(R.id.textResult);
        textResult.setText("0");

        setButtonListeners();
    }

    private void setButtonListeners() {
        Button buttonClear = findViewById(R.id.buttonClear);
        Button buttonEquals = findViewById(R.id.buttonEquals);

        buttonClear.setOnClickListener(view -> clearCalculator());
        buttonEquals.setOnClickListener(view -> performCalculation());

        int[] numberButtonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
        };

        for (int numberButtonId : numberButtonIds) {
            Button numberButton = findViewById(numberButtonId);
            numberButton.setOnClickListener(view -> appendNumber(numberButton.getText().toString()));
        }

        Button buttonDecimal = findViewById(R.id.buttonDecimal);
        buttonDecimal.setOnClickListener(view -> appendDecimal());

        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(view -> setOperator('+'));
        Button buttonSubtract = findViewById(R.id.buttonSubtract);
        buttonSubtract.setOnClickListener(view -> setOperator('-'));
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonMultiply.setOnClickListener(view -> setOperator('*'));
        Button buttonDivide = findViewById(R.id.buttonDivide);
        buttonDivide.setOnClickListener(view -> setOperator('/'));
    }

    private void appendNumber(String number) {
        if (currentNumber.toString().equals("0")) {
            currentNumber.setLength(0);
        }
        currentNumber.append(number);
        textResult.setText(currentNumber.toString());
    }

    private void appendDecimal() {
        if (!currentNumber.toString().contains(".")) {
            if (currentNumber.length() == 0) {
                currentNumber.append("0");
            }
            currentNumber.append(".");
            textResult.setText(currentNumber.toString());
        }
    }

    private void setOperator(char newOperator) {
        if (currentNumber.length() > 0) {
            result = Double.parseDouble(currentNumber.toString());
            currentNumber.setLength(0);
        }
        operator = newOperator;
    }

    private void clearCalculator() {
        currentNumber.setLength(0);
        result = 0;
        operator = ' ';
        textResult.setText("0");
    }

    private void performCalculation() {
        if (currentNumber.length() > 0) {
            double secondNumber = Double.parseDouble(currentNumber.toString());
            switch (operator) {
                case '+':
                    result += secondNumber;
                    break;
                case '-':
                    result -= secondNumber;
                    break;
                case '*':
                    result *= secondNumber;
                    break;
                case '/':
                    result /= secondNumber;
                    break;
            }
            currentNumber.setLength(0);
            currentNumber.append(result);
            textResult.setText(currentNumber.toString());
        }
    }
}

