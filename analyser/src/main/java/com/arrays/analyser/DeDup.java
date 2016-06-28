package com.arrays.analyser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;



public class DeDup 
{
	private static final Logger logger = Logger.getLogger(DeDup.class);
	
	//The randonIntegers has been turned to static as the non-static member cannot be accessed by a static method (main method).
	public static int[] randomIntegers = {1,2,34,34,25,1,45,3,26,85,4,34,86,25,43,2,1,10000,11,16,19,1,18,4,9,3,
            20,17,8,15,6,2,5,10,14,12,13,7,8,9,1,2,15,12,18,10,14,20,17,16,3,6,19,
            13,5,11,4,7,19,16,5,9,12,3,20,7,15,17,10,6,1,8,18,4,14,13,2,11}; 

    public static void main( String[] args )
    {
    	DeDup obj=new DeDup();
		System.out.println("Initial Size of Array :"+randomIntegers.length);
		int[] dupArrOfNumbers=obj.findDuplicates(randomIntegers);
		
		obj.printArrayElements(dupArrOfNumbers, "findDuplicates");
		
		int[] uniqueNumGenBySet=obj.findDuplicatesUsingSet(randomIntegers);
		obj.printArrayElements(uniqueNumGenBySet,"findDuplicatesUsingSet");
		
		int[] uniqueNumGenByMap=obj.findDupsUsingMap(randomIntegers);
		obj.printArrayElements(uniqueNumGenByMap, "findDupsUsingMap");
    }
    
    /**
     * The below method identifies the duplicates and constructs a new array
     * with unique/non-duplicate elements.
     * Advantage: This is a simple traditional and straight forward approach.
     * Disadvantage: Since this compares each element with all the elements in an array, the complexity this will increase by time which will be O(n^2)
     * @param numbers
     * @return uniqueNumbers
     * 
     */
    public int[] findDuplicates(int[] numbers){
		//Local variable to load and return the duplicates
		int[] uniqueNumbers = new int[numbers.length];
		try {
			logger.info("Entering findDuplicates() method.....");
			
			for (int i = 0; i < numbers.length; i++) 
			{
				for (int k = i + 1; k < numbers.length; k++) 
				{
					if (!(numbers[i] == numbers[k])) 
					{
						uniqueNumbers[i] = numbers[k];
						logger.info("Numbers printed in the loop on findDuplicates method :"+uniqueNumbers[i]);
						
					}
					else{
						logger.info("Duplicate Element :"+numbers[i]);
					}
				}
			}
			
		} catch (Exception e) 
		{
			logger.info("Error caught in findDuplicates() method :"+e.getMessage()+". Please turn the logger level to ERROR to see the complete trace");
			logger.error(e.getStackTrace());
		}

		return uniqueNumbers;
		
		
		
	}
	
	public int[] findDuplicatesUsingSet(int[] numbers){
		Set<Integer> unqNumberSet=new HashSet<Integer>();
		int[] uniqueNumberArr = null;//new int[numbers.length];
		int arrIndex=0;
		logger.info("Entering findDuplicates() method.....");
		try{
			for(int i=0;i<numbers.length;i++)
			{
				if(unqNumberSet.add(numbers[i])==true)
				{
					//uniqueNumberArr[i]=numbers[i]; This commented code was used initially but the issue with this was the size of the array was not shrinking as per the unique elements.
					
					logger.debug("Numbers printed in the loop on findDuplicatesUsingSet method :"+numbers[i]);
					
					}else{
					logger.info("This is a duplicate element :"+numbers[i]);
				}
			}
			uniqueNumberArr = new int[unqNumberSet.size()];
			Iterator itr=unqNumberSet.iterator();
			while(itr.hasNext()){
				uniqueNumberArr[arrIndex]=(Integer) itr.next();
				arrIndex++;
			}
			
			
								
		}catch(Exception e)
		{
			logger.info("Error caught in findDuplicatesUsingSet() method :"+e.getMessage()+". Please turn the logger level to ERROR to see the complete trace");
			logger.error(e.getStackTrace());
		}
		
		return uniqueNumberArr;
		
	}
	
	public int[] findDupsUsingMap(int[] numbers)
	{
		int[] uniqueElements = null;
		int index=0; //this is declared and initialized to construct Array.
		logger.info("Entering findDupsUsingMap() method.....");
		try{
			Map<Integer, Integer> numMap=new HashMap<Integer, Integer>();
			for(int i=0;i<numbers.length;i++)
			{
				Integer count=numMap.get(numbers[i]);
				if(logger.isDebugEnabled()){
					logger.debug("Count is: "+count);
				}
				if(count == null)
				{
					numMap.put(numbers[i], 1);
				}else{
					numMap.put(numbers[i], ++count);
				}
			}
			
			uniqueElements=new int[numMap.size()];
			Iterator<Map.Entry<Integer, Integer>> mapEntries=numMap.entrySet().iterator();
			while(mapEntries.hasNext())
			{	
				
				Map.Entry<Integer, Integer> entries= mapEntries.next();
				Integer key=entries.getKey();
				Integer value=entries.getValue();
				//By this time all the keys will be unique because of the above check ()if count == null
				uniqueElements[index]=key;
				if(logger.isDebugEnabled()){
					logger.debug("Values Inserting to new array :"+key);
				}
				index++;
			
				if(value>1){
					
					logger.info("This is a Duplicate element of the Array :"+key);
				}
				
			}
			
		}catch(Exception e)
		{
			logger.info("Error caught in findDupsUsingMap() method :"+e.getMessage()+". Please turn the logger level to ERROR to see the complete trace");
			logger.error(e.getStackTrace());
		}
		
		return uniqueElements;
		
	}
	
	public void printArrayElements(int[] arrElements, String methodName)
	{
		System.out.println("Array Size: "+arrElements.length);
		for (int j = 0; j < arrElements.length; j++) 
		{			
			logger.info("Unique Numbers from "+methodName+" :" + arrElements[j]);
		}
	}
}
