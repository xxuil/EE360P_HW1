import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class PMergeTask implements Callable<Map<Integer,Integer>> {
    private int element;
    private int[] a;
    private int index;

    public PMergeTask(int element, int[] a, int index){
        this.element = element;
        this.a = a;
        this.index = index;
    }
    public static int search(int[] a, int element) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] > element) {
                return i;
            }
            else if((a[i] < element) && (i == a.length - 1)){
                return i + 1;
            }
        }
        return -1;
    }

        /*
        if(right >= left) {
            int mid = left + (right - left) / 2;
            if(a[mid] >= element) {
                return mid;
            }else if(a[mid] > element) {
                return binarysearch(a, left, mid-1, element);
            }else if(a[mid] < element) {
                return binarysearch(a, mid+1, right, element);
            }
        }
        return -1;
        */

    @Override
    public Map<Integer,Integer> call() throws Exception {
        int index2 = search(a, element);
        if(index2 != -1) {
            index += index2;
        }else{
            return null;      //error in case the array size is less than 1
        }
        Map<Integer,Integer> temp = new HashMap<>();
        temp.put(index, element);
        return temp;
    }

    public static void main(String[] args){
    }
}
