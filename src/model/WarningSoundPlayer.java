/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * This class plays a warning sound file via speakers if a certain
 * measurement value drops below a threshold.<br><br>
 * 
 * When initializing, an lower bound & an upper bound value must be
 * set, according to the anticipated measurement range.<br><br>
 * 
 * Furthermore, a threshold value must be chosen, which is a double value between 0 and 1.<br>
 * If the measured value drops below the threshold between the lower and upper bound,
 * the warning sound will be played.<br><br>
 * 
 * When using this class as an initialized object, pass the measurement value periodically to the
 * <b>playWarning</b> function in order to monitor the measured process.<br><br>
 * 
 * The Volume of the warning sound will increase the closer the measured value 
 * grows to the set lower bound value.<br>
 *
 * @author Robin Christ
 */
public class WarningSoundPlayer {

    private double lowerBound, upperBound, threshold, divisor, divisor2;

    private MediaPlayer mediaPlayer;
    
    public WarningSoundPlayer(double lowerBound, double upperBound, double threshold) {
        initMediaPlayer();
        
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.threshold = threshold;
        
        this.divisor = upperBound - lowerBound;
        this.divisor2 =  divisor * threshold;
        
        if(this.threshold > 1 || this.threshold < 0) {
            System.err.print("The SoundPlayer Threshold should be a value between 0 and 1.");
        }
    }

    public void playWarning(double val) {
        
        double percentage = (val - lowerBound) / divisor;
        
        if (percentage < threshold) {
            double volume = 1 - (val - lowerBound) / divisor2;
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();
        } else {
            mediaPlayer.stop();
        }
    }
    
    public void playWarningDebug(double val) {
        System.out.println("Val: " + val);
        System.out.println("lowerBound: " + lowerBound);
        System.out.println("upperBound: " + upperBound);
        System.out.println("Threshold: " + threshold);
        System.out.println("Divisor" + divisor);
        System.out.println("Divisor2 for volume" + divisor2);
        
        double percentage = (val - lowerBound) / divisor;
        System.out.println("Percentage: " + percentage);
        
        if (percentage < threshold) {
            double volume = 1 - (val - lowerBound) / divisor2;
            System.out.println("Volume: " + volume);
            
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();
        } else {
            mediaPlayer.stop();
        }
        
        System.out.println("_________________________________________________");
    }
    
    private void initMediaPlayer() {
        String fileLoc = this.getClass().getResource("/resources/warning.mp3").toString();
        Media sound = new Media(fileLoc);
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(Integer.MAX_VALUE);
    }

    /**
     * @return the mediaPlayer
     */
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    /**
     * @return the threshold
     */
    public double getThreshold() {
        return threshold;
    }

    /**
     * @param threshold the threshold to set
     */
    public void setThreshold(double threshold) {
        this.threshold = threshold;
        this.divisor2 =  divisor * threshold;
    }

    /**
     * @return the lowerBound
     */
    public double getLowerBound() {
        return lowerBound;
    }

    /**
     * @param lowerBound the lowerBound to set
     */
    public void setLowerBound(double lowerBound) {
        this.lowerBound = lowerBound;
        this.divisor = upperBound - lowerBound;
        this.divisor2 =  divisor * threshold;
    }

    /**
     * @return the upperBound
     */
    public double getUpperBound() {
        return upperBound;
    }

    /**
     * @param upperBound the upperBound to set
     */
    public void setUpperBound(double upperBound) {
        this.upperBound = upperBound;
        this.divisor = upperBound - lowerBound;
        this.divisor2 =  divisor * threshold;
    }
}
