package output;

import java.util.Random;

public abstract class Output {

    protected static final Random RANDOM = new Random();
    protected String result = "";

    public String getResult() {
        return result;
    }

    public String getDescription() {
        return "Wylosowane liczby:";
    }

    public boolean isGood() {
        return true;
    }

}
