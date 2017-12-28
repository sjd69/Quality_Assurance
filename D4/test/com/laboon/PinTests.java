package com.laboon;

import static org.junit.Assert.*;

import org.junit.*;

/**
 * Test program modifications
 */

public class PinTests
{


/*
    *//*

*/
/**
	 * Asserts that convertLocation() functions the same as the old method on the happy path
	 * and given an x,y coordinate an int is returned
	 *//*
*/
/*


    @Test
    public void convertLocationTest1()
    {
        MainPanel mpNew = new MainPanel();
        MainPanel mpOld = new MainPanel();
        ProgramArea p = new ProgramArea();

        int x = 50;
        int y = 50;

        mpNew.
        assertEquals(mpNew,MainPanel.convertLocationOLD(50,25));

    }

    *//*

*/
/**
	 * Asserts that convertLocation() functions the same as the old method on a second happy path example
	 * and given an x,y coordinate an int is returned
	 *//*
*/
/*


    @Test
    public void convertLocationTest2()
    {
        assertEquals(MainPanel.convertLocation(50,0),MainPanel.convertLocationOLD(50,0));

    }

    *//*

*/
/**
	 * Asserts that convertLocation() functions the same as the old method on a third happy path example
	 * and given an x,y coordinate an int is returned
	 *//*
*/
/*


    @Test
    public void convertLocationTest3()
    {
        assertEquals(MainPanel.convertLocation(0,50),MainPanel.convertLocationOLD(0,25));

    }

   *//*

*/
/**
	 * Asserts that convertLocation() functions the same as the old method on an edge case
	 * and given an x,y coordinate an int is returned
	 *//*
*/
/*


    @Test
    public void convertLocationEdgeTest()
    {
        assertEquals(MainPanel.convertLocation(0,0),MainPanel.convertLocationOLD(0,0));

    }

    *//*

*/
/**
	 * Asserts that convertLocation() functions the same as the old method on a second edge case
	 * and given an x,y coordinate an int is returned
	 *//*
*/
/*


     @Test
    public void convertLocationEdgeTest2()
    {
        assertEquals(MainPanel.convertLocation(80,25),MainPanel.convertLocationOLD(80,25));

    }

    *//*

*/
/**
	 * Asserts that print() functions the same as the old method on the happy path
	 * and when a character is input it is displayed in the JTextArea
	 *//*
*/
/*

*/

    @Test
    public void printTest()
    {
        MainPanel mpNew = new MainPanel();
        MainPanel mpOld = new MainPanel();
        mpNew.print('a');
        mpOld.printOLD('a');
        assertEquals(mpNew._output.getText(), mpOld._output.getText());
    }

    /**
	 * Asserts that print() functions the same as the old method on a second happy path
	 * and when a character is input it is displayed in the JTextArea
	 */

    @Test
    public void printTest2()
    {
        MainPanel mpNew = new MainPanel();
        MainPanel mpOld = new MainPanel();
        mpNew.print('a');
        mpNew.print('b');
        mpNew.print('c');
        mpNew.print('d');
        mpNew.print('e');

        mpOld.printOLD('a');
        mpOld.printOLD('b');
        mpOld.printOLD('c');
        mpOld.printOLD('d');
        mpOld.printOLD('e');
        assertEquals(mpNew._output.getText(), mpOld._output.getText());
    }

    /**
	 * Asserts that print() functions the same as the old method on an edge case
	 * and when a character is input it is displayed in the JTextArea
	 */

    @Test
    public void printEdgeTest()
    {

        MainPanel mpNew = new MainPanel();
        MainPanel mpOld = new MainPanel();
        mpNew.print(Character.MIN_VALUE);
        mpOld.printOLD(Character.MIN_VALUE);
        assertEquals(mpNew._output.getText(), mpOld._output.getText());
    }

    /**
	 * Asserts that print() functions the same as the old method on a second edge case
	 * and when a character is input it is displayed in the JTextArea
	 */

    @Test
    public void printEdgeTest2()
    {
        MainPanel mpNew = new MainPanel();
        MainPanel mpOld = new MainPanel();
        mpNew.print('\n');
        mpOld.printOLD('\n');
        assertEquals(mpNew._output.getText(), mpOld._output.getText());
    }




}