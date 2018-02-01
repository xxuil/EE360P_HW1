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

	public static void insertSort(int[] A, int begin, int end) {
		int i, key, j;
		for (i = begin + 1; i < end; i++) {
			key = A[i];
			j = i - 1;

			while (j >= 0 && A[j] > key) {
				A[j + 1] = A[j];
				j = j - 1;
			}
			A[j + 1] = key;
		}
	}

	private static void swap(int [] a, int left, int right){
		int temp = a[left];
		a[left] = a[right];
		a[right] = temp;
	}

	protected void compute() {
		if((end - begin) <= 16) {
			insertSort(A,begin,end);
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
						swap(A, newBegin,newEnd);

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

				invokeAll(ps1,ps2);
			}
		}
	}
}
