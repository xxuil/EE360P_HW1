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
		int length = end - begin + 1;
		for(int i = 1; i < length; i++) {
			int first = A[i];
			int j = i - 1;
			while((A[j] > first) && (j >= 0)) {
				A[j+1] = A[j];
				j--;
			}
			A[j+1] = first;
		}
	}
	public static int partition(int[] A, int begin, int end) {
		return 0;
	}
	protected void compute() {
		if(begin + 16 <= end) {
			Sort(A, begin, end);
		}else {
			int pivot = A[begin + ((end - begin)/2)];
			int newBegin = begin;
			int newEnd = end - 1;
			int temp = 0;

			while(newBegin <= newEnd){
				while(A[newBegin] < pivot){
					newBegin ++;
				}

				while(A[newEnd] > pivot){
					newEnd --;
				}

				if(newBegin <= newEnd){
					temp = A[newBegin];
					A[newBegin] = A[newEnd];
					A[newEnd] = temp;

					newBegin++;
					newEnd--;
				}
			}
			int partition = newBegin;

			PSort ps1 = new PSort(A, begin, partition);
			ps1.fork();
			PSort ps2 = new PSort(A, partition, end);
			ps2.fork();
			ps1.join();
		}
	}
}
