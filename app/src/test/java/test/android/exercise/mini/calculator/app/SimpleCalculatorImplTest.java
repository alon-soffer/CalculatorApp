package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.SimpleCalculatorImpl;

import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;

public class SimpleCalculatorImplTest {

    @Test
    public void when_noInputGiven_then_outputShouldBe0(){
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        assertEquals("0", calculatorUnderTest.output());
    }

    @Test
    public void when_inputIsPlus_then_outputShouldBe0Plus(){
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertPlus();
        assertEquals("0+", calculatorUnderTest.output());
    }


    @Test
    public void when_inputIsMinus_then_outputShouldBeCorrect(){
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertMinus();
        String expected = "0-"; // TODO: decide the expected output when having a single minus
        assertEquals(expected, calculatorUnderTest.output());
    }

    @Test
    public void when_callingInsertDigitWithIllegalNumber_then_exceptionShouldBeThrown(){
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        try {
            calculatorUnderTest.insertDigit(357);
            fail("should throw an exception and not reach this line");
        } catch (RuntimeException e) {
            // good :)
        }
    }


    @Test
    public void when_callingDeleteLast_then_lastOutputShouldBeDeleted(){
        // todo: implement test
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(6);
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(4);
        calculatorUnderTest.deleteLast();
        String expected = "6-";
        assertEquals(expected, calculatorUnderTest.output());
    }

    @Test
    public void when_callingClear_then_outputShouldBeCleared(){
        // todo: implement test
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(6);
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(4);
        calculatorUnderTest.clear();
        String expected = "0";
        assertEquals(expected, calculatorUnderTest.output());
    }

    @Test
    public void when_savingState_should_loadThatStateCorrectly(){
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        // give some input
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(7);

        // save current state
        Serializable savedState = calculatorUnderTest.saveState();
        assertNotNull(savedState);

        // call `clear` and make sure calculator cleared
        calculatorUnderTest.clear();
        assertEquals("0", calculatorUnderTest.output());

        // load the saved state and make sure state was loaded correctly
        calculatorUnderTest.loadState(savedState);
        assertEquals("5+7", calculatorUnderTest.output());
    }

    @Test
    public void when_savingStateFromFirstCalculator_should_loadStateCorrectlyFromSecondCalculator(){
        SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
        SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
        // TODO: implement the test based on this method's name.
        //  you can get inspiration from the test method `when_savingState_should_loadThatStateCorrectly()`
        firstCalculator.insertDigit(1);
        firstCalculator.insertDigit(4);
        firstCalculator.insertPlus();
        firstCalculator.insertDigit(5);
        firstCalculator.insertMinus();
        firstCalculator.insertDigit(3);
        firstCalculator.insertPlus();
        Serializable savedState = firstCalculator.saveState();
        assertNotNull(savedState);

        assertEquals("0", secondCalculator.output());
        secondCalculator.loadState(savedState);
        assertEquals("14+5-3+", secondCalculator.output());
    }

    // TODO:
    //  the existing tests are not enough since they only test simple use-cases with small inputs.
    //  write at least 10 methods to test correct behavior with complicated inputs or use-cases.
    //  examples:
    //  - given input "5+7-13<DeleteLast>25", expected output is "5+17-125"
    //  - given input "9<Clear>12<Clear>8-7=", expected output is "1"
    //  - given input "8-7=+4=-1=", expected output is "4"
    //  - given input "999-888-222=-333", expected output is "-111-333"
    //  - with 2 calculators, give them different inputs, then save state on first calculator and load the state into second calculator, make sure state loaded well
    //  etc etc.
    //  feel free to be creative in your tests!
    @Test
    public void when_deletingMiddleOfInput_then_outputShouldBeCorrect()
    {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(1);
        calculatorUnderTest.insertDigit(3);
        calculatorUnderTest.deleteLast();
        calculatorUnderTest.insertDigit(2);
        calculatorUnderTest.insertDigit(5);

        assertEquals("5+7-125", calculatorUnderTest.output());
    }

    @Test
    public void when_deletingEmptyOutput_then_noExceptionAndOutputShouldBeZero()
    {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.deleteLast();

        assertEquals("0", calculatorUnderTest.output());
    }

    @Test
    public void when_usingClearAndContinuingToInsert_then_outputShouldBeCorrect()
    {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.clear();
        calculatorUnderTest.insertDigit(1);
        calculatorUnderTest.insertDigit(3);
        calculatorUnderTest.clear();
        calculatorUnderTest.insertDigit(2);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertEquals();

        assertEquals("7", calculatorUnderTest.output());
    }

    @Test
    public void when_continuingCalculationAfterEquals_then_outputShouldBeCorrect()
    {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.insertEquals();
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(1);
        calculatorUnderTest.insertDigit(3);
        calculatorUnderTest.insertEquals();
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(5);

        assertEquals("25-5", calculatorUnderTest.output());
    }

    @Test
    public void when_longCalculation_then_outputShouldBeCorrect()
    {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(2);
        calculatorUnderTest.insertDigit(2);
        calculatorUnderTest.insertDigit(2);
        calculatorUnderTest.insertEquals();
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(1);
        calculatorUnderTest.insertDigit(3);


        assertEquals("1110+13", calculatorUnderTest.output());
    }

    @Test
    public void when_savingStateFromFirstCalculator_should_loadStateCorrectlyFromSecondCalculatorEvenIfSecondHasInput(){
        SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
        SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
        // TODO: implement the test based on this method's name.
        //  you can get inspiration from the test method `when_savingState_should_loadThatStateCorrectly()`
        firstCalculator.insertDigit(1);
        firstCalculator.insertDigit(4);
        firstCalculator.insertPlus();
        firstCalculator.insertDigit(5);
        firstCalculator.insertMinus();
        firstCalculator.insertDigit(3);
        firstCalculator.insertPlus();
        Serializable savedState = firstCalculator.saveState();
        assertNotNull(savedState);

        assertEquals("0", secondCalculator.output());

        secondCalculator.insertDigit(5);
        secondCalculator.insertPlus();
        secondCalculator.insertDigit(3);

        secondCalculator.loadState(savedState);
        assertEquals("14+5-3+", secondCalculator.output());
    }

    @Test
    public void when_equalsNotYetInserted_then_outputShouldBeCorrect()
    {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(1);
        calculatorUnderTest.insertDigit(3);
        calculatorUnderTest.insertMinus();

        assertEquals("5+7+13-", calculatorUnderTest.output());
    }

    @Test
    public void when_orderComesAfterOrder_then_lastOrderShouldBeIgnored()
    {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(7);

        assertEquals("5+7", calculatorUnderTest.output());
    }

    @Test
    public void when_deletingAllInput_then_outputZero()
    {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.deleteLast();
        calculatorUnderTest.deleteLast();
        calculatorUnderTest.deleteLast();

        assertEquals("0", calculatorUnderTest.output());
    }

    @Test
    public void when_deletingMoreTimesThenInput_then_noExceptionThrow()
    {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.deleteLast();
        calculatorUnderTest.deleteLast();
        calculatorUnderTest.deleteLast();

        assertEquals("0", calculatorUnderTest.output());
    }

    @Test
    public void when_saveStateWithLongInput_then_loadingStateShouldHaveCorrectInput()
    {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(2);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(2);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(2);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(1);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(3);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(3);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(3);
        calculatorUnderTest.insertPlus();

        Serializable state = calculatorUnderTest.saveState();
        calculatorUnderTest.clear();
        assertEquals("0", calculatorUnderTest.output());

        calculatorUnderTest.loadState(state);
        assertEquals("5+5+5+7+7+7+2+2+2+1+3+3+3+", calculatorUnderTest.output());
    }

    @Test
    public void when_deletingAfterEquals_then_deleteLastDig()
    {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertEquals();

        assertEquals("10", calculatorUnderTest.output());

        calculatorUnderTest.deleteLast();
        assertEquals("1", calculatorUnderTest.output());
    }

    @Test
    public void when_pressingEqualsOnEmptyInput_then_output0()
    {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertEquals();
        assertEquals("0", calculatorUnderTest.output());
    }
}