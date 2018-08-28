package xyz.un4ckn0wl3z.mockk;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import xyz.un4ckn0wl3z.mockk.service.CalculatorService;

// @RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)
public class MathApplicationTester {
	
   //@InjectMocks annotation is used to create and inject the mock object
   @InjectMocks 
   MathApplication mathApplication = new MathApplication();

   //@Mock annotation is used to create the mock object to be injected
   @Mock
   CalculatorService calcService;

   @Test
   public void testAdd(){
      //add the behavior of calc service to add two numbers
      when(calcService.add(3.0,2.0)).thenReturn(5.0);
		
      //test the add functionality
      Assert.assertEquals(mathApplication.add(3.0, 2.0),5.0,0);
      
      //verify the behavior
      verify(calcService).add(3.0, 2.0);
   }
   
   
   @Test(expected = RuntimeException.class)
   public void testAddExc(){
      //add the behavior to throw exception
      doThrow(new RuntimeException("Add operation not implemented"))
         .when(calcService).add(10.0,20.0);

      //test the add functionality
      Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0); 
   }
}