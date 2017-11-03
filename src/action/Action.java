package action;

import interaction.UserInteraction;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import output.Output;
import validator.FromToValidator;
import validator.RangeValidator;
import validator.UniqueValidator;
import validator.Validator;

public class Action {

    private String time = "";
    private String result = "";
    private boolean isUniquenessSet = false;
    private final UserInteraction interaction;
    private final Validator range = new RangeValidator();
    private final Validator unique = new UniqueValidator();

    public Action(UserInteraction interaction) {
        this.interaction = interaction;
        range.add(new FromToValidator());
    }

    public boolean draw() {
        int from = getInt(interaction.getFromValue());
        int to = getInt(interaction.getToValue());
        int amount = getInt(interaction.getAmountValue());
        Output output = range.validate(from, to, amount);
        result = output.getResult();
        interaction.setDescription(output.getDescription());
        interaction.setResult(result);
        time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm/ss"));
        return output.isGood();
    }

    public void saveToFile(File file) {
        if (file == null) {
            return;
        }
        try (PrintWriter writer = new PrintWriter(file.getCanonicalPath())) {
            writer.println(time);
            writer.print(result);
            interaction.setDescription("Zapis:");
            interaction.setResult("Zapisano do pliku.");
        } catch (IOException e) {
            interaction.setDescription("Błędy:");
            interaction.setResult("Wystąpił błąd. Plik nie został zapisany.\nLiczby:\n" + result);
        }

    }

    public void setUniqueness(boolean unique) {
        if (unique && !isUniquenessSet) {
            range.add(this.unique);
            isUniquenessSet = true;
        } else if (!unique && isUniquenessSet) {
            range.removeLast();
            isUniquenessSet = false;
        }
    }

    private int getInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }

}
