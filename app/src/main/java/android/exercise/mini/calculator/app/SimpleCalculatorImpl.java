package android.exercise.mini.calculator.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

class CalculatorTokenizer
{
    private final ArrayList<String> inputs;
    private final HashSet<String> operations;
    private int pointer = 0;

    public CalculatorTokenizer(ArrayList<String> inputs, HashSet<String> operations)
    {
        this.inputs = inputs;
        this.operations = operations;
    }

    public String getToken()
    {
        if (this.operations.contains(this.inputs.get(this.pointer)))
        {
            String ret = this.inputs.get(this.pointer);
            this.pointer++;
            return ret;
        }
        // not an operation, read until see an operation
        StringBuilder token = new StringBuilder();
        while (!this.operations.contains(this.inputs.get(this.pointer)))
        {
            token.append(this.inputs.get(this.pointer));
            this.pointer++;
        }
        return token.toString();
    }
}
public class SimpleCalculatorImpl implements SimpleCalculator {

    // todo: add fields as needed
    private ArrayList<String> inputs = new ArrayList<String>();
    private boolean equals = false;
    private static final HashSet<String> operations = new HashSet<String>(Arrays.asList("+","-","="));
    private int last_val = 0;


    private String calculate()
    {
        String operation = "+";
        int res = 0;    //TODO: is int enough?
        CalculatorTokenizer tokenizer = new CalculatorTokenizer(inputs, SimpleCalculatorImpl.operations);
        String token = tokenizer.getToken();
        while (!token.equals("="))
        {
            if (SimpleCalculatorImpl.operations.contains(token))
            {
                operation = token;
            }
            else
            {
                int intVal = Integer.parseInt(token);
                switch (operation)
                {
                    case "+":
                        res += intVal;
                        break;
                    case "-":
                        res -= intVal;
                        break;
                }
            }
            token = tokenizer.getToken();
        }
//        clear();
//        inputs.add(Integer.toString(res));
        last_val = res;
        return Integer.toString(res);
    }

    @Override
    public String output()
    {
        // todo: return output based on the current state
//        TODO: starts with 0. after answering save last answer as begin output
//        if (equals)
//        {
//            System.out.println("printing equals to view");
//            equals = false;
//            return calculate();
//        }
//        else
//        {
//            if (inputs.isEmpty())
//            {
//                return Integer.toString(last_val);
//            }
//
//            StringBuilder out_p = new StringBuilder();
//            for (String s: inputs)
//            {
//                out_p.append(s);
//            }
//            return out_p.toString();
//        }
        if (inputs.isEmpty())
        {
            return Integer.toString(last_val);
        }

        StringBuilder out_p = new StringBuilder();
        for (String s: inputs)
        {
            out_p.append(s);
        }
        return out_p.toString();
    }

    /***
     * adds toInsert to the input
     * @param toInsert String to add to input
     */
    private void insert_something(String toInsert)
    {
        inputs.add(toInsert);
    }

    @Override
    public void insertDigit(int digit) {
        // todo: insert a digit
        if (digit < 0 || digit > 9)
        {
            throw new IllegalArgumentException();
        }

        insert_something(Integer.toString(digit));
    }

    /***
     * method for inserting both + and -
     * @param op String of either + or -
     */
    private void insertOperation(String op)
    {
//        if last input was operation, do nothing
        if (inputs.size() > 0 &&
                SimpleCalculatorImpl.operations.contains(inputs.get(inputs.size()-1)))
        {
            return;
        }
        if (inputs.size() == 0)
        {
            inputs.add("0");
        }
        insert_something(op);
    }

    @Override
    public void insertPlus() {
        // todo: insert a plus
//        this.insert_something("+");
        insertOperation("+");
    }

    @Override
    public void insertMinus() {
        // todo: insert a minus
//        this.insert_something("-");
        insertOperation("-");
    }

    private void addResult(String res)
    {
//        for (String s : res)
        for (int i=0; i<res.length(); i++)
        {
            inputs.add(String.valueOf(res.charAt(i)));
        }
    }

    @Override
    public void insertEquals()
    {
        // todo: calculate the equation. after calling `insertEquals()`, the output should be the result
        //  e.g. given input "14+3", calling `insertEquals()`, and calling `output()`, output should be "17"

        // if last input was operation, we want to ignore it
        if (SimpleCalculatorImpl.operations.contains(inputs.get(inputs.size()-1)))
        {
            deleteLast();
        }
        insert_something("=");
        String res = calculate();
        inputs.clear();
//        inputs.add(res);
        addResult(res);
        last_val = Integer.parseInt(res);
//        equals = true;
    }

    @Override
    public void deleteLast()
    {
        // todo: delete the last input (digit, plus or minus)
        //  e.g.
        //  if input was "12+3" and called `deleteLast()`, then delete the "3"
        //  if input was "12+" and called `deleteLast()`, then delete the "+"
        //  if no input was given, then there is nothing to do here

        if (inputs.size() != 0)
        {
            inputs.remove(inputs.size()-1);
            if (inputs.size() == 0)
            {
                last_val = 0;
            }
        }
    }

    @Override
    public void clear() {
        // todo: clear everything (same as no-input was never given)
        inputs.clear();
        last_val = 0;
    }

    @Override
    public Serializable saveState() {
        CalculatorState state = new CalculatorState();
        // todo: insert all data to the state, so in the future we can load from this state
        state.inputs = new ArrayList<>();
        for (String i: this.inputs)
        {
            state.inputs.add(i);
        }
        state.equals = this.equals;
        state.last_val = this.last_val;
        return state;
    }

    @Override
    public void loadState(Serializable prevState) {
        if (!(prevState instanceof CalculatorState)) {
            return; // ignore
        }
        CalculatorState casted = (CalculatorState) prevState;
        // todo: use the CalculatorState to load
        this.inputs = casted.inputs;
        this.equals = casted.equals;
        this.last_val = casted.last_val;
    }

    private static class CalculatorState implements Serializable {
    /*
    TODO: add fields to this class that will store the calculator state
    all fields must only be from the types:
    - primitives (e.g. int, boolean, etc)
    - String
    - ArrayList<> where the type is a primitive or a String
    - HashMap<> where the types are primitives or a String
     */
        private ArrayList<String> inputs;
        private boolean equals;
        private int last_val;
    }
}
