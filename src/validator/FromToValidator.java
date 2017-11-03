package validator;

import output.ErrorOutput;
import output.Output;
import output.RandomOutput;

public class FromToValidator extends Validator {

    @Override
    public Output validate(int from, int to, int amount) {
        if (from > to) {
            return new ErrorOutput(FROM_TO_ERROR);
        }
        if (next != null) {
            return next.validate(from, to, amount);
        }
        return new RandomOutput(from, to, amount);
    }

}
