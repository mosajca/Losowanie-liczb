package validator;

import output.Output;

public abstract class Validator {

    protected static final String FROM_ERROR = "Niepoprawny zakres od.\n";
    protected static final String TO_ERROR = "Niepoprawny zakres do.\n";
    protected static final String AMOUNT_ERROR = "Niepoprawna ilość.\n";
    protected static final String FROM_TO_ERROR = "Zakres od powinien być minejszy lub równy zakresowi do.\n";
    protected static final String UNIQUE_ERROR = "Nie da się wylosować tylu liczb bez powtórzeń z danego zakresu.\n";
    protected static final int FROM_TO_MIN = -1000000000;
    protected static final int FROM_TO_MAX = 1000000000;
    protected static final int AMOUNT_MIN = 1;
    protected static final int AMOUNT_MAX = 1000;
    protected Validator next;

    public void add(Validator validator) {
        if (validator == this) {
            return;
        }
        if (next != null) {
            next.add(validator);
        } else {
            next = validator;
        }
    }

    public void removeLast() {
        if (next != null && next.next != null) {
            next.removeLast();
        } else {
            next = null;
        }
    }

    public abstract Output validate(int from, int to, int amount);

}
