I pledge the highest level of ethical principles in support of academic excellence.
I ensure that all of my work reflects my own abilities and not those of someone else.


Q:
Saying we want to add a cool feature - button "x" to run multiplication.
What code do we need to change/add/remove to support this feature?
Which tests can we run on the calculator? On the activity? On the app?

A:
We must update the code for simpleCalculatorImpl: 1) add a method for inserting 'x' 2) adding a
case in the method that does the calculation when '=' is pressed 3) add 'x' to the set of operands.
In addition we will need to add it to the UI and add the onClickListener in mainActivity.

After that we should add tests to all modules - the calculator logic test, the main activity test
and finally the app flow test.

----------------------------------------------------------------------------------------------------

# AndroidCalculator - Calculator exercise for Android developers

## In this project:
- Calculator screen with XML ready for portrait and landscape
- Calculator interface used by the Activity
- Unit tests for the calculator and the activity

## Your job:
- Implement `SimpleCalculatorImpl.java`
- add more unit tests to `SimpleCalculatorImpl.java`
- Implement `MainActivity.java`
- add more unit tests to `MainActivityTest.java`
- add more flow tests to `AppFlowTest.java`

Basically look for "TODO" in the code.


Good luck!