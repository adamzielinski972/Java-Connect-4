package connectfour;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TextUITest{
    private TextUI tester;

    @Before
    public void setup(){
        //set up for the test
        tester = new TextUI();

    }

    @Test
    public void check1ShouldPass(){  //checks if options 1 and 2 work
        Assert.assertTrue(tester.checkInput1(1));
        Assert.assertTrue(tester.checkInput1(2));
    }

    @Test
    public void check1ShouldFail(){  //checks if options outside of 1 and 2 fail
        Assert.assertFalse(tester.checkInput1(0));
    }

    public void check2ShouldPass(){   //checks if options 1 and 2 work
        Assert.assertTrue(tester.checkInput2(1));
        Assert.assertTrue(tester.checkInput2(2));
    }

    @Test
    public void check2ShouldFail(){ //checks if options outside of 1 and 2 fail
        Assert.assertFalse(tester.checkInput2(0));
    }

    public void check3ShouldPass(){ //checks if options within 1-7 work
        Assert.assertTrue(tester.checkInput2(1));
        Assert.assertTrue(tester.checkInput2(5));
        Assert.assertTrue(tester.checkInput2(7));
    }

    @Test
    public void check3ShouldFail(){ //checks if options outside of 1-7 fail
        Assert.assertFalse(tester.checkInput2(0));
    }

}
