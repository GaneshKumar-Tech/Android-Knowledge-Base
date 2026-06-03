package java_playground;

import java.util.Arrays;

public class MaximumGreatness {
    static String label = "Maximum Greatness: ";

    public static void main(String[] args) {
        int[] numbers = {1, 3, 5, 2, 1, 3, 1};
        System.out.println(label + maxGreatness(numbers));
    }

    public static int maxGreatness(int[] numbers){
        Arrays.sort(numbers);
        int res = 0;
        for (int a : numbers) {
            if(a > numbers[res]){
                res++;
            }
        }
        return res;
    }

}
