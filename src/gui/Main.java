package gui;

import action.Action;
import interaction.UserInteraction;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application implements UserInteraction {

    private TextField from;
    private TextField to;
    private TextField amount;
    private TextArea output;
    private Label description;

    @Override
    public void start(Stage primaryStage) {
        Rows root = new Rows(10, 10);
        Image icon = new Image(getClass().getResourceAsStream("icon.png"));

        Label hintRange = new Label("Zakres:");
        from = getTextField(105);
        Label dash = new Label("-");
        to = getTextField(105);
        Label hintMinMax = new Label("min: -10^9, max: 10^9");
        root.addRow(10, Pos.CENTER_LEFT, hintRange, from, dash, to, hintMinMax);

        Label hintAmount = new Label("Ile liczb:");
        amount = getTextField(51);
        Label hintMax = new Label("max: 1000");
        CheckBox unique = new CheckBox("bez powtórzeń");
        root.addRow(10, Pos.CENTER_LEFT, hintAmount, amount, hintMax, unique);

        description = new Label("");
        output = getTextArea(150, true, false);
        root.addAll(description, output);

        Button draw = new Button("Losuj");
        Button save = new Button("Zapisz do pliku");
        Button about = new Button("O programie");
        Button exit = new Button("Koniec");
        root.addRow(10, Pos.CENTER, draw, save, about, exit);

        InfoWindow info = new InfoWindow("O programie", icon, 10, 10);
        info.addImage(icon);
        info.addText("Losowanie liczb\nlistopad 2017");

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt", "*.txt"));

        Action action = new Action(this);

        save.setDisable(true);
        about.disableProperty().bind(info.showingProperty());

        draw.setOnAction(event -> save.setDisable(!action.draw()));
        save.setOnAction(event -> action.saveToFile(fileChooser.showSaveDialog(primaryStage)));
        about.setOnAction(event -> info.show());
        exit.setOnAction(event -> Platform.exit());
        unique.setOnAction(event -> action.setUniqueness(unique.isSelected()));

        primaryStage.setTitle("Losowanie liczb");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> Platform.exit());
        primaryStage.show();
    }

    private TextField getTextField(double prefWidth) {
        TextField textField = new TextField();
        textField.setPrefWidth(prefWidth);
        return textField;
    }

    private TextArea getTextArea(double maxHeight, boolean wrapText, boolean editable) {
        TextArea textArea = new TextArea();
        textArea.setMaxHeight(maxHeight);
        textArea.setWrapText(wrapText);
        textArea.setEditable(editable);
        return textArea;
    }

    @Override
    public String getFromValue() {
        return from.getText();
    }

    @Override
    public String getToValue() {
        return to.getText();
    }

    @Override
    public String getAmountValue() {
        return amount.getText();
    }

    @Override
    public void setResult(String text) {
        output.setText(text);
    }

    @Override
    public void setDescription(String text) {
        description.setText(text);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
