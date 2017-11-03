package output;

import java.util.Arrays;

public class RandomUniqueOutput extends Output {

    public RandomUniqueOutput(int min, int max, int amount) {
        int[] numbers = RANDOM.ints(min, max + 1).distinct().limit(amount).toArray();
        result = Arrays.toString(numbers).replace("[", "").replace("]", "") + "\n";
    }

}
