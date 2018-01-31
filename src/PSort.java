//UT-EID=


import java.util.*;
import java.util.concurrent.*;

public class PSort extends RecursiveAction{
	private static boolean DEBUG = true;
	public int[] A;
	public int begin;
	public int end;
	PSort(int[] A, int begin, int end){
		this.A = A;
		this.begin = begin;
		this.end = end;
	}
	public static void parallelSort(int[] A, int begin, int end){
		int processors = Runtime.getRuntime().availableProcessors();
		if (DEBUG) System.out.println("Number of processors: " + processors);
		PSort temp = new PSort(A, begin, end);
		ForkJoinPool pool = new ForkJoinPool(processors);
		pool.invoke(temp);
	}
	public static void Sort(int[] A, int begin, int end) {
		
	
	}
	public static int partition(int[] A, int begin, int end) {
		return 0;
	}
	protected void compute() {
		if(begin + 16 <= end) {
			Sort(A, begin, end);
		}else {
			//partition
			//PSort foo = new PSort();
			//PSort bar = new PSort();
		}
	}
}
