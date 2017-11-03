package output;

import java.util.Arrays;

public class RandomOutput extends Output {

    public RandomOutput(int min, int max, int amount) {
        int[] numbers = RANDOM.ints(amount, min, max + 1).toArray();
        result = Arrays.toString(numbers).replace("[", "").replace("]", "") + "\n";
    }

}
