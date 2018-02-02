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

    public PMergeTask(int element, int[] a){
      this.element = element;
      this.a = a;
    }

    @Override
    public Integer call() throws Exception {
      return 0;
    }
  }
}
