package com.arrays.analyser;


import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;



public class DeDupTest
{
	public static int[] randomIntegers = {1,2,34,34,25,1,45,3,26,85,4,34,86,25,43,2,1,10000,11,16,19,1,18,4,9,3,
            20,17,8,15,6,2,5,10,14,12,13,7,8,9,1,2,15,12,18,10,14,20,17,16,3,6,19,
            13,5,11,4,7,19,16,5,9,12,3,20,7,15,17,10,6,1,8,18,4,14,13,2,11}; 
	
	public static int[] expectednumbers={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,85,17,16,19,86,18,20,25,26,34,43,10000,45};
	
	private static DeDup deDupObj;
    
    @BeforeClass
    public static void initAnalyser(){
    	deDupObj=new DeDup();
    }

   @Test
    public void testMethodUsingJustArrays()
    {
	   assertArrayEquals(expectednumbers,deDupObj.findDuplicates(randomIntegers));
	}
   	
   	@Test
   	public void testMethodUsingSet()
   	{
   		assertArrayEquals(expectednumbers,deDupObj.findDuplicatesUsingSet(randomIntegers));
   	}
   
   	@Test
   	public void testMethodUsingMaps()
   	{
   		assertArrayEquals(expectednumbers,deDupObj.findDupsUsingMap(randomIntegers));
   	}
}
