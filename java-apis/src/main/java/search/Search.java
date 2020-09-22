package search;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并发查找
 * 
 * @author Administrator
 *
 */
public class Search {
	static int[] arr = { 5, 52, 6, 3, 4, 10, 8, 100, 35, 78, 64, 31, 77, 90,
			45, 53, 89, 78, 1,2 };
	static ExecutorService pool = Executors.newCachedThreadPool();
	static final int Thread_Num=2;
	static AtomicInteger result=new AtomicInteger(-1);
	
	public static void createArray(){
	    arr=new int[1000*1000*100];
	    Random r=new Random();
	    for(int i=0;i<arr.length;i++){
	        arr[i]=i;
	    }
	}
	/**
	 * [beginPos,endPos)
	 * @param searchValue
	 * @param beginPos  [
	 * @param endPos    )  
	 * @return  -1: can't find
	 *          >=0:pos of find ele
	 */
	public static int search(int searchValue,int beginPos,int endPos){
		int i=0;
		for(i=beginPos;i<endPos;i++){
		    if(result.get()>=0){
		        return result.get();
		    }
			if(arr[i] == searchValue){
			    //如果设置失败，表示其它线程已经先找到了
			    if(!result.compareAndSet(-1, i)){
			        return result.get();
			    }
				return i;
			}
		}
		return -1; 
	}
	
	public static class SearchTask implements Callable<Integer>{
		int begin,end,searchValue;
		public SearchTask(int searchValue,int begin,int end){
			this.begin=begin;
			this.end=end;
			this.searchValue=searchValue;
		}
		public Integer call(){
			int re= search(searchValue,begin,end);
			return re;
		}
	}
	
	public static int pSearch(int searchValue) throws InterruptedException, ExecutionException{
		int subArrSize=arr.length/Thread_Num+1;
		List<Future<Integer>> re=new ArrayList<Future<Integer>>();
		for(int i=0;i<arr.length;i+=subArrSize){
			int end = i+subArrSize;
			if(end>=arr.length)end=arr.length;
			re.add(pool.submit(new SearchTask(searchValue,i,end)));
		}
		for(Future<Integer> fu:re){
			if(fu.get()>=0)return fu.get();
		}
		return -1;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
//	    createArray();
		int pos=pSearch(2);
		pool.shutdownNow();
		System.out.println(pos);
	}
}
