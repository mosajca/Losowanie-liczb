package validator;

import output.ErrorOutput;
import output.Output;
import output.RandomOutput;

public class RangeValidator extends Validator {

    @Override
    public Output validate(int from, int to, int amount) {
        StringBuilder errors = new StringBuilder();
        String[] errorDescriptions = {FROM_ERROR, TO_ERROR, AMOUNT_ERROR};
        int[] min = {FROM_TO_MIN, FROM_TO_MIN, AMOUNT_MIN};
        int[] max = {FROM_TO_MAX, FROM_TO_MAX, AMOUNT_MAX};
        int[] values = {from, to, amount};

        for (int i = 0; i < 3; ++i) {
            if (!isInRange(values[i], min[i], max[i])) {
                errors.append(errorDescriptions[i]);
            }
        }

        if (errors.length() == 0) {
            if (next != null) {
                return next.validate(from, to, amount);
            } else {
                return new RandomOutput(from, to, amount);
            }
        } else {
            return new ErrorOutput(errors.toString());
        }
    }

    private boolean isInRange(int value, int min, int max) {
        return value >= min && value <= max;
    }
}
