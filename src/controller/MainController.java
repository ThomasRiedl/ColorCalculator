package controller;

import model.ColorCode;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.model;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    @FXML
    Button color = new Button();
    @FXML
    Label hex = new Label();
    @FXML
    TextField red = new TextField();
    @FXML
    TextField blue = new TextField();
    @FXML
    TextField green = new TextField();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {}
    public static void show(Stage stage)
    {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/sample/list.fxml"));
        Parent root = fxmlLoader.load();

        stage.setTitle("Color Calculator");
        stage.setScene(new Scene(root,350,250));
        stage.show();
        }

        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
            ex.printStackTrace(System.err);
        }
    }
    public void absoluteInput (KeyEvent event)
    {
        TextField txt = (TextField) event.getSource();
        if (event.getCode().getCode() >= 48 && event.getCode().getCode() <= 57) //Hilfe von Brettbacher
        {
            if (txt.getId().contains("red"))
            {
                model.changeColorViaAbsoluteValue(ColorCode.RED, Integer.parseInt(txt.getText()));
            }
            else if (txt.getId().contains("green"))
            {
                model.changeColorViaAbsoluteValue(ColorCode.GREEN, Integer.parseInt(txt.getText()));
            }
            else{
                model.changeColorViaAbsoluteValue(ColorCode.BLUE, Integer.parseInt(txt.getText()));
            }
        }
        else{
            txt.clear();
        }
        String hexCode = model.getHex();
        hex.setText(hexCode);
        color.setStyle("-fx-background-color: " + hexCode + ";");
    }
    public void relativeInput (Event event)
    {
        Button button = (Button) event.getSource();
        if (button.getId().contains("red"))
        {
            model.changeColorViaRelativeValue(ColorCode.RED, button.getText());
            red.setText(" " + model.getRed());
        }
        else if (button.getId().contains("green"))
        {
            model.changeColorViaRelativeValue(ColorCode.GREEN, button.getText());
            green.setText(" " + model.getGreen());
        }
        else{
            model.changeColorViaRelativeValue(ColorCode.BLUE, button.getText());
            blue.setText(" " + model.getBlue());
        }
        String hexCode = model.getHex();
        hex.setText(hexCode);
        color.setStyle("-fx-background-color: " + hexCode + ";");
    }
}