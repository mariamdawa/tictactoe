/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictac.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author M
 */
public class Sounds {
     Media media1= new Media(getClass().getResource("../sounds/player1.mp3").toExternalForm());
     Media media2= new Media(getClass().getResource("../sounds/player2.mp3").toExternalForm());
    
     public void playSound1(){
   
        //Instantiating MediaPlayer class   
        MediaPlayer mediaPlayer = new MediaPlayer(media1);  
          
        //by setting this property to true, the audio will be played   
        mediaPlayer.setAutoPlay(true); 
}
public void playSound2(){
    
        MediaPlayer mediaPlayer = new MediaPlayer(media2);  
          
        //by setting this property to true, the audio will be played   
        mediaPlayer.setAutoPlay(true); 
}
}
