package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InfoWindow extends Stage {

    private final HBox root;

    public InfoWindow(String title, Image icon, double spacing, double padding) {
        super(StageStyle.UTILITY);
        root = new HBox(spacing);
        root.setPadding(new Insets(padding));
        setResizable(false);
        setTitle(title);
        getIcons().add(icon);
        setScene(new Scene(root));
    }

    public void addImage(Image image) {
        root.getChildren().add(new StackPane(new ImageView(image)));
    }

    public void addText(String text) {
        root.getChildren().add(new StackPane(new Label(text)));
    }

}
