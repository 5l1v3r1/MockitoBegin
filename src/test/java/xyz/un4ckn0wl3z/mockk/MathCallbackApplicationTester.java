package xyz.un4ckn0wl3z.mockk;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import xyz.un4ckn0wl3z.mockk.service.CalculatorService;

// @RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)
public class MathCallbackApplicationTester {
	
   private MathApplication mathApplication;
   private CalculatorService calcService;

   @Before
   public void setUp(){
      mathApplication = new MathApplication();
      calcService = mock(CalculatorService.class);
      mathApplication.setCalculatorService(calcService);
   }

   @Test
   public void testAdd(){

      //add the behavior to add numbers
      when(calcService.add(20.0,10.0)).thenAnswer(new Answer<Double>() {

         
         public Double answer(InvocationOnMock invocation) throws Throwable {
            //get the arguments passed to mock
            Object[] args = invocation.getArguments();
            System.out.println(Arrays.toString(args));
			
            //get the mock 
            Object mock = invocation.getMock();	
            
            System.out.println(mock.toString());
			
            //return the result
            return 30.0;
         }
      });

      //test the add functionality
      Assert.assertEquals(mathApplication.add(20.0, 10.0),30.0,0);
   }
}