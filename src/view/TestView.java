/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerTemplate;
import java.awt.Dimension;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Robin Christ
 */
public class TestView extends Application {

    private Image icon;

    @Override
    public void start(Stage stage) throws Exception {
        //Create FXMLLoader and load .fxml file from another package.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FXML_Template.fxml"));

        //Initialize main controller and hand over the stage if you need to change mouse styles etc.
        ControllerTemplate controller = new ControllerTemplate(stage);

        //Then link the controller with the .fxml.
        loader.setController(controller);

        //Initialize root, Parent as Pointer is fine, can also use Pane etc.
        Parent root = loader.load();

        //Initialize scene and pass the root
        Scene scene = new Scene(root);

        //load a CSS file from another package.
        scene.getStylesheets().add("/css/Style.css");

        stage.setScene(scene);

        //----------------------------------------------------------------------
        //---------------------------- Utility ---------------------------------
        //----------------------------------------------------------------------
        
        //Set window size relative to current screen resolution.
        setStageSize(stage, 0.75, 0.75);
        //Changes Window Style.
        stage.initStyle(StageStyle.DECORATED);
        //Set an icon for Window and Taskbar.
        if (loadIcon()) {
            stage.getIcons().add(icon);
        }
        //Set Window Title.
        stage.setTitle("Title");
        //Set shown hint when entering full screen.
        stage.setFullScreenExitHint("");
        //Tell program to exit upon closing the main window; useful for multithreading.
        stage.setOnCloseRequest(e -> {
            System.exit(0);
        });
        //----------------------------------------------------------------------
        //----------------------------------------------------------------------
        //----------------------------------------------------------------------
        
        //Make the window appear!
        stage.show();
    }

    /**
     * Loads this program's main icon, called "icon.png", from the resources package.
     * @return 
     */
    private boolean loadIcon() {
        String imageLoc = this.getClass().getResource("/resources/icon.png").toString();
        
        if (imageLoc != null && !imageLoc.isEmpty()) {
            icon = new Image(imageLoc);
        }
        return icon != null;
    }

    private void setStageSize(Stage stage, double width, double height) {
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        stage.setWidth(screenSize.getWidth() * width);
        stage.setHeight(screenSize.getHeight() * height);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
