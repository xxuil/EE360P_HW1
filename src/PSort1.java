//UT-EID=


import java.util.*;
import java.util.concurrent.*;

public class PSort1 extends RecursiveAction{
	private static boolean DEBUG = true;
	public int[] A;
	public int begin;
	public int end;
	PSort1(int[] A, int begin, int end){
		this.A = A;
		this.begin = begin;
		this.end = end;
	}
	public static void parallelSort(int[] A, int begin, int end){
		int processors = Runtime.getRuntime().availableProcessors();
		if (DEBUG) System.out.println("Number of processors: " + processors);
		PSort1 temp = new PSort1(A, begin, end);
		ForkJoinPool pool = new ForkJoinPool(processors);
		pool.invoke(temp);
		pool.shutdown();
	}

	public static void Sort(int[] A, int begin, int end) {
		int length = A.length;
		int i, key, j;
		for (i = 1; i < length; i++) {
			key = A[i];
			j = i - 1;

			while (j >= 0 && A[j] > key) {
				A[j + 1] = A[j];
				j = j - 1;
			}
			A[j + 1] = key;
		}
	}


	public static int partition(int[] A, int begin, int end) {
		return 0;
	}
	protected void compute() {
		if((end - begin) <= 16) {
				Sort(A,begin,end);
		}else {
			if(begin < end) {
				//partition
				//PSort foo = new PSort();
				//PSort bar = new PSort();
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

				if(false){
					System.out.println(partition);
					SortTest.printArray(A);
				}

				PSort1 ps1 = new PSort1(A, begin, partition);
				PSort1 ps2 = new PSort1(A, partition, end);

				ps1.fork();
				ps2.compute();
			}
		}
	}
}
