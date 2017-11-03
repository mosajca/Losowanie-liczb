package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Rows extends VBox {

    public Rows(double spacing, double padding) {
        super(spacing);
        setPadding(new Insets(padding));
    }

    public void addRow(double spacing, Pos alignment, Node... children) {
        HBox hBox = new HBox(spacing);
        hBox.getChildren().addAll(children);
        hBox.setAlignment(alignment);
        getChildren().add(hBox);
    }

    public void addAll(Node... children) {
        getChildren().addAll(children);
    }

}
