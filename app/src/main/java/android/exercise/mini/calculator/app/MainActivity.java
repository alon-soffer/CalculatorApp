package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @VisibleForTesting
    public SimpleCalculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (calculator == null) {
            calculator = new SimpleCalculatorImpl();
        }

    /*
    TODO:
    - find all views
    - initial update main text-view based on calculator's output
    - set click listeners on all buttons to operate on the calculator and refresh main text-view
     */
        TextView buttonEquals = findViewById(R.id.buttonEquals);
        TextView buttonPlus = findViewById(R.id.buttonPlus);
        TextView buttonMinus = findViewById(R.id.buttonMinus);
        TextView buttonClear = findViewById(R.id.buttonClear);
        TextView button0 = findViewById(R.id.button0);
        TextView button2 = findViewById(R.id.button2);
        TextView button5 = findViewById(R.id.button5);
        TextView button8 = findViewById(R.id.button8);
        TextView button1 = findViewById(R.id.button1);
        TextView button4 = findViewById(R.id.button4);
        TextView button7 = findViewById(R.id.button7);
        View buttonBackSpace = findViewById(R.id.buttonBackSpace);
        TextView button3 = findViewById(R.id.button3);
        TextView button6 = findViewById(R.id.button6);
        TextView button9 = findViewById(R.id.button9);
        TextView calcOutput = findViewById(R.id.textViewCalculatorOutput);

        calcOutput.setText(calculator.output());
        TextView[] digitButtons =  {button0, button1, button2, button3, button4, button5, button6, button7, button8, button9};
        for (int i = 0; i < 10; i++)
        {
            int digit = i;
            digitButtons[i].setOnClickListener(v -> {
                calculator.insertDigit(digit);
                calcOutput.setText(calculator.output());
            });
        }
        buttonPlus.setOnClickListener(v -> {
            calculator.insertPlus();
            calcOutput.setText(calculator.output());
        });
        buttonMinus.setOnClickListener(v -> {
            calculator.insertMinus();
            calcOutput.setText(calculator.output());
        });
        buttonBackSpace.setOnClickListener(v -> {
            calculator.deleteLast();
            calcOutput.setText(calculator.output());
        });
        buttonClear.setOnClickListener(v -> {
            calculator.clear();
            calcOutput.setText(calculator.output());
        });
        buttonEquals.setOnClickListener(v -> {
            calculator.insertEquals();
            calcOutput.setText(calculator.output());
        });



    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState)
    {
        super.onSaveInstanceState(outState);
        // todo: save calculator state into the bundle
        outState.putSerializable("calculator_saved_state", calculator.saveState());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // todo: restore calculator state from the bundle, refresh main text-view from calculator's output
        calculator.loadState(savedInstanceState.getSerializable("calculator_saved_state"));
        TextView calcOutput = findViewById(R.id.textViewCalculatorOutput);
        calcOutput.setText(calculator.output());
    }
}