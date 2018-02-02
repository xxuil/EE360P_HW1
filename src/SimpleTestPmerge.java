import java.util.Arrays;
import java.util.Random;

public class SimpleTestPmerge {
    private static final int size = 5;
  public static void main (String[] args) {
    int[] A1 = {1, 3, 5, 7, 9};
    int[] B1 = {2, 4, 6, 8, 10};
    verifyParallelMerge(A1, B1);
    
    int[] A2 = {13, 60, 1000, 3000, 129948};
    int[] B2 = {1, 2, 3, 5, 10};
    verifyParallelMerge(A2, B2);

    int[] A3 = {1111, 3333, 4444, 5555, 6666};
    int[] B3 = {1112, 3334, 4445, 5556, 6667};
    verifyParallelMerge(A3, B3);

      Random r = new Random();
      int[] A4 = new int[size];
      int[] B4 = new int[size];

      for(int i = 0; i < size; i++){
          int n = (int)(Math.random() * 10000 + 1);
          A4[i] = n;
          n = (int)(Math.random() * 10000 + 1);
          B4[i] = n;
      }
      Arrays.sort(A4);
      Arrays.sort(B4);
      verifyParallelMerge(A4, B4);
  }

  static void verifyParallelMerge(int[] A, int[] B) {
    int[] C = new int[A.length + B.length];
	int[] D = new int[A.length + B.length];

    System.out.println("Verify Parallel Merge for arrays: ");
    printArray(A);

	printArray(B);
    merge(A, B, C);

    PMerge.parallelMerge(A, B, D, 20);
   	
    boolean isSuccess = true;
    for (int i = 0; i < C.length; i++) {
      if (C[i] != D[i]) {
        System.out.println("Your parallel sorting algorithm is not correct");
        System.out.println("Expect:");
        printArray(C);
        System.out.println("Your results:");
        printArray(D);
        isSuccess = false;
        break;
      }
    }

    if (isSuccess) {
      System.out.println("Great, your sorting algorithm works for this test case");
    }
    System.out.println("=========================================================");
  }

  public static void merge(int[] A, int[] B, int[] C) {
  	int h = 0, i = 0, j = 0;
	while(i < A.length || j < B.length) {
		if(i == A.length) {
			C[h ++] = B[j ++];
		} else if(j == B.length) {
			C[h ++] = A[i ++];
		} else {
			if(A[i] < B[j]) C[h ++] = A[i ++];
			else C[h ++] = B[j ++];
		}
	}
  }

  public static void printArray(int[] A) {
    for (int i = 0; i < A.length; i++) {
      if (i != A.length - 1) {
        System.out.print(A[i] + " ");
      } else {
        System.out.print(A[i]);
      }
    }
    System.out.println();
  }
}
