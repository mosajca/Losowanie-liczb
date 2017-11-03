package output;

public class ErrorOutput extends Output {

    public ErrorOutput(String result) {
        this.result = result;
    }

    @Override
    public String getDescription() {
        return "Błędy:";
    }

    @Override
    public boolean isGood() {
        return false;
    }

}
