package interaction;

public interface UserInteraction {

    String getFromValue();

    String getToValue();

    String getAmountValue();

    void setResult(String text);

    void setDescription(String text);
}
