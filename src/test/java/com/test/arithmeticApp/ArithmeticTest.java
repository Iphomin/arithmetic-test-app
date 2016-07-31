package com.test.ArithmeticApp;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import junit.framework.Assert;        
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.annotations.Description;
import java.io.IOException;
import java.io.FileNotFoundException;


import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;


@Title("Arithmetic check")
@Description("Arifmetic check file data")
@RunWith(Parameterized.class)
public class ArithmeticTest  extends Assert
{
	private final int operand1;
	private final int operand2;
	private final String operation;
	private final float result;

	private static ArrayList<Object[]> arr;
 

	public ArithmeticTest(final int operand1, final int operand2, final String operation, final float result) {
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.operation = operation;
		this.result = result;
	}


	
/*{index}:check operand1={0}; operand2={1}; operator={2}; result={3}*/
 @Parameterized.Parameters(name = "{index}:check {0}{2}{1}={3}")
  public static Collection<Object[]> getCheckData()throws IOException, FileNotFoundException  
{          
         arr = new ArrayList<Object[]>(); 
	BufferedReader dataFile = new BufferedReader ( new FileReader("datafile.txt"));
	String str;	
 while ( (str = dataFile.readLine())!=null){

           String[] strParts = str.split(";");	   
	   Object[] arrObj = new Object[]{Integer.parseInt(strParts[0]),
					Integer.parseInt(strParts[1]),strParts[2],Float.parseFloat(strParts[3])};
	   arr.add(arrObj);
	}

       return arr; 
}


    @Test
    public void ArithmeticCheck()
    {
	
	switch (operation){
		case "+":
		checkSum( operand1,  operand2, result);		
		break;
		case "-":
		checkDiff( operand1,  operand2, result);		
		break;
		case "*":
		checkMulti( operand1,  operand2, result);		
		break;
		case "/":		
		checkDiv( operand1,  operand2, result);		
		break;
	}	
    }



  
    @Step("Sum: {0}+{1}={2}")
    public void checkSum(int operand1, int operand2, float result)
    {
	float res = operand1+operand2;
	assertThat("Result "+ (operand1+operand2) +" not equals to "+result, result, is(res));
    }

    @Step("Diff: {0}-{1}={2}")
    public void checkDiff(int operand1, int operand2, float result)
    {		float res = operand1-operand2;	
	assertThat( result, is(res));
    }
    @Step("Multi: {0}*{1}={2}")
    public void checkMulti(int operand1, int operand2, float result)
    {
	float res = operand1*operand2;
	assertThat(result, is(res));
    }

    @Step("Divide by zero")
    public void checkDivideByZero( int operand2 )
    {
	assertThat(0,not(operand2));
    }

    @Step("Div: {0}/{1}={2}")
    public void checkDiv(int operand1, int operand2, float result)
{checkDivideByZero(  operand2 );
       float res = (float)operand1/operand2;
	assertThat(result, is(res));
    }




}
