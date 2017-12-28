package com.laboon;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * Test methods in ProgramExecutor class
 *
 * TODO: These methods do not use stubs/mocks/doubles.  This would
 * be a good refactoring effort in the future!
 */

public class ProgramExecutorTest {

    // Shared ProgramExecutor
    // A new one is created for each test - this is inefficient but
    // ensures a clean slate for each test

    public ProgramExecutor _e = null;

    // Create simple executor before each test
    // Consists of a program "123++@" and a stack that already has
    // the values [2, 3, 4] (4 being the "top" of the stack)
    @Before
    public void setup() {
    	ProgramStack ps = new ProgramStack();
    	ps.push(2);
    	ps.push(3);
    	ps.push(4);
    	ProgramArea pa = new ProgramArea("123++@");
    	MainPanel mp = new MainPanel();
    	_e = new ProgramExecutor(mp, ps, pa);
    }

    // Test the '+' (add) command.   3 + 4 should == 7.
    @Test
    public void testAdd() {
	_e.add();
	assertEquals(_e._ps.peek(), 7);
    }

    // * -   Subtraction: Pop two values a and b, then push the result of b-a
    @Test
    public void testSubtract() {
	_e.subtract();
	assertEquals(_e._ps.peek(), -1);
    }

    // *   Multiplication: Pop two values a and b, then push the result of a*b
    @Test
    public void testMultiply() {
	_e.multiply();
	assertEquals(_e._ps.peek(), 12);
    }

    // /   Integer division: Pop two values a and b, then push the result of b/a, rounded down. If a is zero, return 0.
    // Check for "normal" (nonzero) division
    @Test
    public void testDivide() {
	_e._ps.push(25); // b
	_e._ps.push(5);  // a
	_e.divide();
	assertEquals(5, _e._ps.peek());

    }

    // /   Integer division: Pop two values a and b, then push the result of b/a, rounded down. If a is zero, return 0.
    // Check for "normal" (nonzero) division, rounding down
    @Test
    public void testDivideRounding() {
	_e._ps.push(26); // b
	_e._ps.push(5);  // a
	_e.divide();
	assertEquals(5, _e._ps.peek());

    }


    // /   Integer division: Pop two values a and b, then push the result of b/a, rounded down. If a is zero, return 0.
    // Check that dividing by zero returns 0
    @Test
    public void testDivideZero() {
	_e._ps.push(5);
	_e._ps.push(0);
	_e.divide();
	assertEquals(0, _e._ps.peek());
    }


    // %   Modulo: Pop two values a and b, then push the remainder of the integer division of b/a.
    @Test
    public void modulo() {
	_e.modulo();
	assertEquals(3, _e._ps.peek());
    }


    // !   Logical NOT: Pop a value. If the value is zero, push 1; otherwise, push zero.
// Check for nonzero value returns 0
    @Test
    public void notNonZero() {
	_e._ps.push(1);
	_e.not();
	assertEquals(0, _e._ps.peek());

    }

    // !   Logical NOT: Pop a value. If the value is zero, push 1; otherwise, push zero.
    // Check for 0 value returns 1
    @Test
    public void testNotZero() {
	_e._ps.push(0);
	_e.not();
	assertEquals(1, _e._ps.peek());
    }

    // `   Greater than: Pop two values a and b, then push 1 if b>a, otherwise zero.
    // Check b > a returns 1
    @Test
    public void testGreaterThanBgtA() {
	_e._ps.push(5); // b
	_e._ps.push(2); // a
	_e.greaterThan();
	assertEquals(1, _e._ps.peek());
    }

    // `   Greater than: Pop two values a and b, then push 1 if b>a, otherwise zero.
    // Check b == a returns 0
    @Test
    public void testGreaterThanBeqA() {
	_e._ps.push(10);
	_e._ps.push(10);
	_e.greaterThan();
	assertEquals(0, _e._ps.peek());
    }

    // `   Greater than: Pop two values a and b, then push 1 if b>a, otherwise zero.
    // Check b < a returns 0
    @Test
    public void testGreaterThanBltA() {
	_e._ps.push(10); // b
	_e._ps.push(99); // a
	_e.greaterThan();
	assertEquals(0, _e._ps.peek());
    }

    // r    Reverse: Tests that the reverse function caller is working. Should be called once.
    @Test
    public void testReverse() {
        int[] i = {0};
        ProgramExecutor executor = mock(ProgramExecutor.class);
        doAnswer(invocation -> {
            i[0] = 1;
            return 1;
        }).when(executor).reverse();
        executor.reverse();
        assertEquals(i[0], 1);
    }

    /**
     * Test incrementing the program counter. Doubles ProgramExecuting and executes 10 times
     * _pc == 10
     */
    @Test
    public void testIncrementProgramCounter() {
        int[] val = {0};
        ProgramExecutor executor = mock(ProgramExecutor.class);
        doAnswer(invocation -> {
            val[0]++;
            return null;
        }).when(executor).incrementCounter();

        for (int i = 0; i < 10; i++) {
            executor.incrementCounter();
        }

        assertEquals(val[0], 10);
    }

    /**
     * Test decrementing te program counter. Doubles ProgramExecuting and executes 10 times
     * _pc == -10
     */
    @Test
    public void testDecrementProgramCounter() {
        int[] val = {0};
        ProgramExecutor executor = mock(ProgramExecutor.class);
        doAnswer(invocation -> {
            val[0]--;
            return null;
        }).when(executor).decrementCounter();

        for (int i = 0; i < 10; i++) {
            executor.decrementCounter();
        }

        assertEquals(val[0], -10);
    }

    /**
     * Test incrementing then decrementing the program counter. Doubles ProgramExecuting and executes 10 times
     * _pc == 0
     */
    @Test
    public void testIncrementDecrementProgramCounter() {
        final int[] val = {0};
        ProgramExecutor executor = mock(ProgramExecutor.class);
        doAnswer(invocation -> {
            val[0]--;
            return null;
        }).when(executor).decrementCounter();

        doAnswer(invocation -> {
            val[0]++;
            return null;
        }).when(executor).incrementCounter();


        for (int i = 0; i < 10; i++) {
            executor.incrementCounter();
            executor.decrementCounter();
        }
        assertEquals(val[0], 0);
    }

    /**
     * Test taking a program counter that is higher than 0. _e._ps.peek() == 10;
     */
    @Test
    public void testTakeProgramCounter() {
        for (int i = 0; i < 10; i++) {
            _e.incrementCounter();
        }
        _e.takeCounter();
        assertEquals(_e._ps.peek(), 10);
    }

    /**
     * Test taking the program counter. executor._ps.peek() == 0;
     */
    @Test
    public void testTakeZeroProgramCounter() {
        ProgramStack stack = mock(ProgramStack.class);
        ProgramArea area = mock(ProgramArea.class);
        MainPanel panel = mock(MainPanel.class);

        ProgramExecutor executor = new ProgramExecutor(panel, stack, area);

        executor.takeCounter();

        assertEquals(executor._ps.peek(), 0);
    }

    /**
     *  (   Tests that enter comment mode is working properly.
     */
    @Test
    public void testEnterCommentMode() {
        ProgramExecutor executor = mock(ProgramExecutor.class);
        executor.enterComment();

        verify(executor, times(1)).enterComment();
    }

    /**
     *  )   Tests that enter comment mode is working properly. enterComment is called multiple times.
     *  _inCommentMode = true.
     */
    @Test
    public void testEnterCommentModeMultiple() {
        for (int i = 0; i < 10; i++) {
            _e.enterComment();
        }

        assertTrue(_e._inCommentMode);
    }

    /**
     *  )   Tests that leave comment mode is working properly.
     */
    @Test
    public void testLeaveCommentMode() {
        ProgramExecutor executor = mock(ProgramExecutor.class);
        executor.leaveComment();

        verify(executor, times(1)).leaveComment();
    }

    /**
     *  )   Tests that leave comment mode is working properly. leaveComment is called multiple times.
     *  _inCommentMode = false.
     */
    @Test
    public void testLeaveCommentModeMultiple() {
        for (int i = 0; i < 10; i++) {
            _e.leaveComment();
        }

        assertFalse(_e._inCommentMode);
    }

    /**
     *  )   Tests that leave comment mode is working properly.
     */
    @Test
    public void testEnterLeaveComment() {
        ProgramExecutor executor = mock(ProgramExecutor.class);
        executor.enterComment();
        executor.leaveComment();

        verify(executor, times(1)).enterComment();
        verify(executor, times(1)).leaveComment();
    }

    /**
     *  Test that both leave and enter comment mode work properly together.
     *  _e._inCommentMode = true.
     */
    @Test
    public void testEnterLeaveCommentModeMultiple() {
        for (int i = 0; i < 10; i++) {
            _e.enterComment();
        }
        for (int i = 0; i < 10; i++) {
            _e.leaveComment();
        }
        for (int i = 0; i < 10; i++) {
            _e.enterComment();
            _e.leaveComment();
            _e.enterComment();
        }

        assertTrue(_e._inCommentMode);
    }

    /**
     *  Tests that verify comment mode is working properly. _inCommentMode is set to false.
     */
    @Test
    public void testFalseVerifyComment() {
        ProgramExecutor executor = mock(ProgramExecutor.class);
        executor.verifyComment(')');
        verify(executor).verifyComment(')');
    }

    /**
     *  Tests that verify comment mode is working properly. _inCommentMode is set to true
     *  but input char is not ')'.
     */
    @Test
    public void testTrueVerifyComment() {
        ProgramExecutor executor = mock(ProgramExecutor.class);
        executor._inCommentMode = true;

        executor.verifyComment('a');
        verify(executor).verifyComment('a');
    }

    /**
     *  Tests that verify comment mode is working properly. _inCommentMode is set to true
     *  and input char is ')'.
     */
    @Test
    public void testTrueParenthesisVerifyComment() {
        ProgramExecutor executor = mock(ProgramExecutor.class);
        executor._inCommentMode = true;

        executor.verifyComment(')');
        verify(executor).verifyComment(')');
    }

    /**
     *  Tests that verify comment mode is working properly. _inCommentMode is set to true
     *  and input char is ')'. _inStringMode = true .
     */
    @Test
    public void testTrueStringVerifyComment() {
        ProgramExecutor executor = mock(ProgramExecutor.class);
        executor._inCommentMode = true;
        executor._inStringMode = true;

        executor.verifyComment(')');
        verify(executor).verifyComment(')');
    }

    /**
     *  Tests that verify comment mode correctly sets _inCommentMode to false when it encounters
     *  ')'.
     */
    @Test
    public void testFalseInCommentMode() {
        _e.enterComment();
        _e.verifyComment(')');

        assertFalse(_e._inCommentMode);
    }

    //  *  _   Horizontal IF: pop a value; set direction to right if value=0, set to left otherwise
    // Check going right if value is 0

    public void testHorizontalIfZero() {
    	_e._ps.push(0);
	_e.horizontalIf();
	assertEquals(Direction.RIGHT, _e._d);
    }

    //  *  _   Horizontal IF: pop a value; set direction to right if value=0, set to left otherwise
    // Check going left if value is positive

    public void testHorizontalIfPositive() {
    	_e._ps.push(19);
	_e.horizontalIf();
	assertEquals(Direction.LEFT, _e._d);
    }

    //  *  _   Horizontal IF: pop a value; set direction to right if value=0, set to left otherwise
    // Check going left if value is positive

    public void testHorizontalIfNegative() {
    	_e._ps.push(-3);
	_e.horizontalIf();
	assertEquals(Direction.LEFT, _e._d);
    }


}
