/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import model.WarningSoundPlayer;

/**
 *
 * @author Robin Christ
 */
public class ControllerTemplate implements Initializable {
    
    private Stage stage;
    private WarningSoundPlayer sp;
    private AnimationTimer timer;
    
    @FXML
    private Button playButton;
    @FXML
    private Slider slider;
    
    public ControllerTemplate(Stage stage) {
        this.stage = stage;
        sp = new WarningSoundPlayer(50, 140, 0.4);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                sp.playWarningDebug(slider.getValue());
            }
        };
    }    
    
    public void play() {
        if(playButton.getText().equals("Play")) {
            playButton.setText("Stop");
            
            timer.start();
        } else {
            playButton.setText("Play");
            
            timer.stop();
            sp.getMediaPlayer().stop();
        }
    }
    
}
