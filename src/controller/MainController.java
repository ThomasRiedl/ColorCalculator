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
import model.Model;
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
        if (event.getText().length() >= 48 && event.getText().length()  <= 57) //Hilfe von Brettbacher
        {
            if (txt.getId().contains("red"))
            {
                Model.changeColorViaAbsoluteValue(ColorCode.RED, Integer.parseInt(txt.getText()));
            }
            else if (txt.getId().contains("green"))
            {
                Model.changeColorViaAbsoluteValue(ColorCode.GREEN, Integer.parseInt(txt.getText()));
            }
            else{
                Model.changeColorViaAbsoluteValue(ColorCode.BLUE, Integer.parseInt(txt.getText()));
            }
        }
        else{
            txt.clear();
        }
        String hexCode = Model.getHex();
        hex.setText(hexCode);
        color.setStyle("-fx-background-color: " + hexCode + ";");
    }
    public void relativeInput (Event event)
    {
        Button button = (Button) event.getSource();
        if (button.getId().contains("red"))
        {
            Model.changeColorViaRelativeValue(ColorCode.RED, button.getText());
            red.setText(" " + Model.getRed());
        }
        else if (button.getId().contains("green"))
        {
            Model.changeColorViaRelativeValue(ColorCode.GREEN, button.getText());
            green.setText(" " + Model.getGreen());
        }
        else{
            Model.changeColorViaRelativeValue(ColorCode.BLUE, button.getText());
            blue.setText(" " + Model.getBlue());
        }
        String hexCode = Model.getHex();
        hex.setText(hexCode);
        color.setStyle("-fx-background-color: " + hexCode + ";");
    }
}