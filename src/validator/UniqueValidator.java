package validator;

import output.ErrorOutput;
import output.Output;
import output.RandomUniqueOutput;

public class UniqueValidator extends Validator {

    @Override
    public Output validate(int from, int to, int amount) {
        if (to - from + 1 < amount) {
            return new ErrorOutput(UNIQUE_ERROR);
        }
        if (next != null) {
            return next.validate(from, to, amount);
        }
        return new RandomUniqueOutput(from, to, amount);
    }

}
