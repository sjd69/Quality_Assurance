/**
 * Written by Stephen Dowhy
 * CS1632 Deliverable 3
 * Unit Tests for https://cs1632ex.herokuapp.com/
 */

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class D3Test {
    static WebDriver driver = new HtmlUnitDriver();


    /**
     * Gets home page before every test, as well as sets implicit wait to 2 seconds.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        driver.get("https://cs1632ex.herokuapp.com/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    /**
     * Tests that the home page message is correct.
     */
    @Test
    public void testHomeMessage() {
        WebElement e = driver.findElement(By.className("lead"));
        String eText = e.getText();
        eText = eText.replace("\n", " ").replace("\r", " ");
        assertTrue(eText.contains("Welcome, friend, to a land of pure calculation"));
    }

    /**
     * Tests that the home page includes the disclaimer/description.
     */
    @Test
    public void testDescriptionMessage() {
        try {
            WebElement row = driver.findElement(By.className("row"));
            WebElement e = row.findElement(By.className("lead"));
            String eText = e.getText();
            assertTrue(eText.contains("Used for CS1632 Software Quality Assurance, taught by Bill Laboon"));
        } catch (NoSuchElementException e) {
            fail();
        }

    }


    /**
     * Tests that the factorial page correctly reports "1!" with an invalid (empty in this case) entry
     */
    @Test
    public void testBlankFact() {
        driver.get("https://cs1632ex.herokuapp.com/fact");
        try {
            WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));

            submitButton.click();


            WebElement e = driver.findElement(By.className("jumbotron"));
            String eText = e.getText();
            assertTrue(eText.contains("1!"));
        } catch (NoSuchElementException e) {
            fail();
        }
    }

    /**
     * Tests that the factorial page correctly reports "1!" with an invalid entry
     */
    @Test
    public void testInvalidFact() {
        driver.get("https://cs1632ex.herokuapp.com/fact");
        try {
            WebElement eTextBox = driver.findElement(By.cssSelector("input[type='text']"));
            eTextBox.sendKeys("-50");

            WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));

            submitButton.click();


            WebElement e = driver.findElement(By.className("jumbotron"));
            String eText = e.getText();
            assertTrue(eText.contains("1!"));
        } catch (NoSuchElementException e) {
            fail();
        }
    }

    /**
     * Tests that the factorial page correctly reports "1" for 1!
     */
    @Test
    public void testLowFact() {
        driver.get("https://cs1632ex.herokuapp.com/fact");
        try {
            WebElement eTextBox = driver.findElement(By.cssSelector("input[type='text']"));
            eTextBox.sendKeys("1");

            WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
            submitButton.click();


            WebElement e = driver.findElement(By.className("jumbotron"));
            String eText = e.getText();
            assertTrue(eText.contains("1!"));
        } catch (NoSuchElementException e) {
            System.out.println(e.getClass().getName());
            fail();
        }
    }

    /**
     * Tests that the factorial page correctly reports 100!
     */
    @Test
    public void testMaxFact() {
        driver.get("https://cs1632ex.herokuapp.com/fact");
        try {
            WebElement eTextBox = driver.findElement(By.cssSelector("input[type='text']"));
            eTextBox.sendKeys("100");

            WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
            submitButton.click();

            BigInteger factorial = BigInteger.valueOf(1);
            for (int i = 100; i > 1; i--) {
                factorial = factorial.multiply(BigInteger.valueOf(i));
            }

            WebElement e = driver.findElement(By.className("jumbotron"));
            String eText = e.getText();
            assertTrue(eText.contains(factorial.toString()));
        } catch (NoSuchElementException e) {
            System.out.println(e.getClass().getName());
            fail();
        }
    }

    /**
     * Tests that the factorial page correctly reports the first fibonacci number
     */
    @Test
    public void testLowFib() {
        driver.get("https://cs1632ex.herokuapp.com/fib");
        try {
            WebElement eTextBox = driver.findElement(By.cssSelector("input[type='text']"));
            eTextBox.sendKeys("1");

            WebElement submitButton = driver.findElement(By.id("sub"));

            submitButton.click();

            WebElement e = driver.findElement(By.className("jumbotron"));
            String eText = e.getText();
            assertTrue(eText.contains("1!"));
        } catch (NoSuchElementException e) {
            fail();
        }
    }

    /**
     * Tests that the factorial page correctly reports the 100th fibonacci number
     */
    @Test
    public void testMaxFib() {
        driver.get("https://cs1632ex.herokuapp.com/fib");
        try {
            WebElement eTextBox = driver.findElement(By.cssSelector("input[type='text']"));
            eTextBox.sendKeys("100");

            WebElement submitButton = driver.findElement(By.id("sub"));

            submitButton.click();

            WebElement e = driver.findElement(By.className("jumbotron"));
            String eText = e.getText();
            assertTrue(eText.contains("354224848179261915075"));
        } catch (NoSuchElementException e) {
            fail();
        }
    }

    /**
     * Tests that the factorial page correctly reports "1" when an invalid entry is entered.
     */
    @Test
    public void testBlankFib() {
        driver.get("https://cs1632ex.herokuapp.com/fib");
        try {
            WebElement submitButton = driver.findElement(By.id("sub"));

            submitButton.click();

            WebElement e = driver.findElement(By.className("jumbotron"));
            String eText = e.getText();
            assertTrue(eText.contains("1!"));
        } catch (NoSuchElementException e) {
            fail();
        }

    }

    /**
     * Helper method that checks the home link exists and works correctly.
     * @return  True if working correctly and exists.
     */
    private boolean checkHomeLink() {
        try {
            driver.findElement(By.linkText("CS1632 D3 Home")).click();
            if (!driver.getCurrentUrl().contentEquals("https://cs1632ex.herokuapp.com/")) {
                return false;
            }
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Helper method that checks the fibonacci link exists and works correctly.
     * @return  True if working correctly and exists.
     */
    private boolean checkFibLink() {
        try {
            driver.findElement(By.linkText("Fibonacci")).click();
            if (!driver.getCurrentUrl().contentEquals("https://cs1632ex.herokuapp.com/fib")) {
                return false;
            }
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Helper method that checks the factorial link exists and works correctly.
     * @return  True if working correctly and exists.
     */
    private boolean checkFactLink() {
        try {
            driver.findElement(By.linkText("Factorial")).click();
            if (!driver.getCurrentUrl().contentEquals("https://cs1632ex.herokuapp.com/fact")) {
                return false;
            }
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Helper method that checks the hello link exists and works correctly.
     * @return  True if working correctly and exists.
     */
    private boolean checkHelloLink() {
        try {
            driver.findElement(By.linkText("Hello")).click();
            if (!driver.getCurrentUrl().contentEquals("https://cs1632ex.herokuapp.com/hello")) {
                return false;
            }
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Helper method that checks the cathy link exists and works correctly.
     * @return  True if working correctly and exists.
     */
    private boolean checkCathyLink() {
        try {
            driver.findElement(By.linkText("Cathedral Pics")).click();
            if (!driver.getCurrentUrl().contentEquals("https://cs1632ex.herokuapp.com/cathy")) {
                return false;
            }
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Tests all hyperlinks from the home page to ensure they are there and correctly redirect.
     */
    @Test
    public void testHomeLinks() {
        driver.get("https://cs1632ex.herokuapp.com/");
        if (!checkHomeLink()) {
            fail("NO HOME LINK");
        }

        driver.get("https://cs1632ex.herokuapp.com/");
        if (!checkFactLink()) {
            fail("NO FACT LINK");
        }

        driver.get("https://cs1632ex.herokuapp.com/");
        if (!checkFibLink()) {
            fail("NO FIB LINK");
        }

        driver.get("https://cs1632ex.herokuapp.com/");
        if (!checkHelloLink()) {
            fail("NO HELLO LINK");
        }

        driver.get("https://cs1632ex.herokuapp.com/");
        if (!checkCathyLink()) {
            fail("NO CATHY LINK");
        }

    }

    /**
     * Tests all hyperlinks from the factorial page to ensure they are there and correctly redirect.
     */
    @Test
    public void testFactLinks() {
        driver.get("https://cs1632ex.herokuapp.com/fact");
        if (!checkHomeLink()) {
            fail("NO HOME LINK");
        }

        driver.get("https://cs1632ex.herokuapp.com/fact");
        if (!checkFactLink()) {
            fail("NO FACT LINK");
        }

        driver.get("https://cs1632ex.herokuapp.com/fact");
        if (!checkFibLink()) {
            fail("NO FIB LINK");
        }

        driver.get("https://cs1632ex.herokuapp.com/fact");
        if (!checkHelloLink()) {
            fail("NO HELLO LINK");
        }

        driver.get("https://cs1632ex.herokuapp.com/fact");
        if (!checkCathyLink()) {
            fail("NO CATHY LINK");
        }

    }

    /**
     * Tests all hyperlinks from the fibonacci page to ensure they are there and correctly redirect.
     */
    @Test
    public void testFibLinks() {
        driver.get("https://cs1632ex.herokuapp.com/fib");
        if (!checkHomeLink()) {
            fail("NO HOME LINK");
        }

        driver.get("https://cs1632ex.herokuapp.com/fib");
        if (!checkFactLink()) {
            fail("NO FACT LINK");
        }

        driver.get("https://cs1632ex.herokuapp.com/fib");
        if (!checkFibLink()) {
            fail("NO FIB LINK");
        }

        driver.get("https://cs1632ex.herokuapp.com/fib");
        if (!checkHelloLink()) {
            fail("NO HELLO LINK");
        }

        driver.get("https://cs1632ex.herokuapp.com/fib");
        if (!checkCathyLink()) {
            fail("NO CATHY LINK");
        }

    }

    /**
     * Tests all hyperlinks from the hello page to ensure they are there and correctly redirect.
     */
    @Test
    public void testHelloLinks() {
        driver.get("https://cs1632ex.herokuapp.com/hello");
        if (!checkHomeLink()) {
            fail("NO HOME LINK");
        }

        driver.get("https://cs1632ex.herokuapp.com/hello");
        if (!checkFactLink()) {
            fail("NO FACT LINK");
        }

        driver.get("https://cs1632ex.herokuapp.com/hello");
        if (!checkFibLink()) {
            fail("NO FIB LINK");
        }

        driver.get("https://cs1632ex.herokuapp.com/hello");
        if (!checkHelloLink()) {
            fail("NO HELLO LINK");
        }

        driver.get("https://cs1632ex.herokuapp.com/hello");
        if (!checkCathyLink()) {
            fail("NO CATHY LINK");
        }

    }

    /**
     * Tests all hyperlinks from the cathy page to ensure they are there and correctly redirect.
     */
    @Test
    public void testCathyLinks() {
        driver.get("https://cs1632ex.herokuapp.com/cathy");
        if (!checkHomeLink()) {
            fail("NO HOME LINK");
        }

        driver.get("https://cs1632ex.herokuapp.com/cathy");
        if (!checkFactLink()) {
            fail("NO FACT LINK");
        }

        driver.get("https://cs1632ex.herokuapp.com/cathy");
        if (!checkFibLink()) {
            fail("NO FIB LINK");
        }

        driver.get("https://cs1632ex.herokuapp.com/cathy");
        if (!checkHelloLink()) {
            fail("NO HELLO LINK");
        }

        driver.get("https://cs1632ex.herokuapp.com/cathy");
        if (!checkCathyLink()) {
            fail("NO CATHY LINK");
        }

    }

    /**
     * Tests that the default message is displayed correctly when on the hello page.
     */
    @Test
    public void testBlankHello() {
        driver.get("https://cs1632ex.herokuapp.com/hello");
        try {
            WebElement e = driver.findElement(By.className("jumbotron"));
            String eText = e.getText();
            assertTrue(eText.contains("Hello CS1632, from Prof. Laboon!"));
        } catch (NoSuchElementException e) {
            fail();
        }
    }

    /**
     * Tests that the default message is displayed correctly when on the hello page and a slash is appended to the url.
     */
    @Test
    public void testSlashHello() {
        driver.get("https://cs1632ex.herokuapp.com/hello/");
        try {
            WebElement e = driver.findElement(By.className("jumbotron"));
            String eText = e.getText();
            assertTrue(eText.contains("Hello CS1632, from Prof. Laboon!"));
        } catch (NoSuchElementException e) {
            fail();
        }
    }

    /**
     * Tests that the default message is displayed correctly when on the hello page and with a very long string
     * appended to the url.
     */
    @Test
    public void testLongHello() {
        driver.get("https://cs1632ex.herokuapp.com/hello/Lorem ipsum dolor sit amet, falli tation numquam usu cu."
                + " Eirmod voluptatibus eos cu, ne nusquam civibus expetenda nec. Graeci tritani vituperata ne his,"
                + " mea cu ferri bonorum, ne putent vulputate persequeris duo. Mazim inciderint vix te. "
                + "Ut duo novum numquam eleifend.");
        try {
            WebElement e = driver.findElement(By.className("jumbotron"));
            String eText = e.getText();
            assertTrue(eText.contains("Hello CS1632, from Lorem ipsum dolor sit amet, falli tation numquam usu cu. "
                    + "Eirmod voluptatibus eos cu, ne nusquam civibus expetenda nec. Graeci tritani vituperata ne his,"
                    + " mea cu ferri bonorum, ne putent vulputate persequeris duo. Mazim inciderint vix te."
                    + " Ut duo novum numquam eleifend."));
        } catch (NoSuchElementException e) {
            fail();
        }
    }

    /**
     * Tests that the default message is displayed correctly when on the hello with a somewhat normal length name
     * appended to the url.
     */
    @Test
    public void testAlphabetHello() {
        driver.get("https://cs1632ex.herokuapp.com/hello/abcdefghijklmnopqrstuvwxyz");
        try {
            WebElement e = driver.findElement(By.className("jumbotron"));
            String eText = e.getText();
            assertTrue(eText.contains("Hello CS1632, from abcdefghijklmnopqrstuvwxyz"));
        } catch (NoSuchElementException e) {
            fail();
        }
    }

    /**
     * Tests that the default message is displayed correctly when on the hello page with special characters
     * appended to the url.
     */
    @Test
    public void testSpecialHello() {
        driver.get("https://cs1632ex.herokuapp.com/hello/!@#$%^&*()_+");
        try {
            WebElement e = driver.findElement(By.className("jumbotron"));
            String eText = e.getText();
            assertTrue(eText.contains("Hello CS1632, from !@#$%^&*()_+"));
        } catch (NoSuchElementException e) {
            fail();
        }
    }

    /**
     * Tests that the list on the cathy page correctly displays 3 images with alt text containing cathedral
     */
    @Test
    public void testCathyList() {
        driver.get("https://cs1632ex.herokuapp.com/cathy");
        try {
            WebElement div = driver.findElement(By.className("jumbotron"));
            WebElement ol = div.findElement(By.tagName("ol"));
            List<WebElement> items = ol.findElements(By.tagName("li"));
            if (items.size() == 3) {
                for (WebElement e : items) {
                    WebElement img = e.findElement(By.tagName("img"));
                    if (!img.getAttribute("alt").contains("Cathedral")) {
                        fail("Picture is not of Cathy!");
                    }
                }
            } else {
                fail("List does not have 3 items!");
            }
        } catch (NoSuchElementException e) {
            fail();
        }
    }
}
