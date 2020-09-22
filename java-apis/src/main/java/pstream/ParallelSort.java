package pstream;

import java.util.Arrays;
import java.util.Random;

public class ParallelSort {
		
	public static void main(String args[]){
		Random r=new Random();
		int[] arr=new int[10000000];
		
		
		long b=System.currentTimeMillis();
		Arrays.setAll(arr, (i)->r.nextInt());
		Arrays.parallelSetAll(arr, (i)->r.nextInt());
		long e=System.currentTimeMillis();
		System.out.println("set spend time:"+(e-b)+"ms");
		
		b=System.currentTimeMillis();
	    Arrays.parallelSort(arr);
		Arrays.sort(arr);
	    e=System.currentTimeMillis();
	    
	    System.out.println("sort spend time:"+(e-b)+"ms");
	    
//	    for(int i=0;i<arr.length;i++){
//	    	System.out.print(arr[i]);
//	    	System.out.print('\t');
//		}
	}
}
