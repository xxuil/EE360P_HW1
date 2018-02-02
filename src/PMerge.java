//UT-EID=


import java.util.*;
import java.util.concurrent.*;


public class PMerge{
  private final static boolean DEBUG = true;
  public static void parallelMerge(int[] A, int[] B, int[]C, int numThreads){
    // TODO: Implement your parallel merge function

    if((A.length == 0) || (B.length == 0) || (C.length == 0)) {
      throw new IllegalArgumentException("Can't be empty\n");
    }

    if(numThreads <= 0)
      throw new IllegalArgumentException("Wrong numThreads\n");

    ExecutorService pool = Executors.newFixedThreadPool(numThreads);
    List<Future<Integer>> threadList = new ArrayList<Future<Integer>>();

    for(int i = 0; i < numThreads; i++){

    }
  }

  private class PMergeTask implements Callable<Integer>{
    private int element;
    private int[] a;
    private int index;

    public PMergeTask(int element, int[] a, int index){
      this.element = element;
      this.a = a;
      this.index = index;
    }
    public int binarysearch(int[] a, int left, int right, int element) {
    	if(right >= 1) {
    		int mid = left + (right - 1) / 2;
    		if(a[mid] == element) {
    			return mid;
    		}else if(a[mid] > element) {
    			return binarysearch(a, left, mid-1, element);
    		}else if(a[mid] < element) {
    			return binarysearch(a, mid+1, right, element);
    		}
    	}
    	return -1;
    }

    @Override
    public Integer call() throws Exception {
    	int index2 = binarysearch(a, 0, a.length, element);
    	if(index2 != -1) {
    		index += index2;
    	}else{
    		return -1;      //error in case the array size is less than 1
    	}
    	return index;
    }
  }
}
