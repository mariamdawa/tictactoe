/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictac.services;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 *
 * @author saraamer
 */
public class Dialogue {
    private Button reset;
    private Button replay;
    private Label text;
    private Stage dialogStage;
    private StackPane layout;
    private Scene scene;
    private Media media;
    private MediaPlayer mediaPlayer;

    public Dialogue(Window Parent) 
    {
    
        layout = new StackPane();
        dialogStage = new Stage();
        reset = new Button("Play again");
        replay=new Button("Replay");
        text = new Label();
        text.setStyle("-fx-text-fill:#4f2161");
        layout.getChildren().add(text);
        layout.getChildren().add(reset);
        layout.getChildren().add(replay);
        scene = new Scene(layout, 350, 100);

        Style(Parent);

        dialogStage.show();   
    }
    
    public Dialogue(Window Parent , String message) {
        layout = new StackPane();
        dialogStage = new Stage();
        reset = new Button("Play again");
        replay=new Button("Replay");
        text = new Label();
        text.setStyle("-fx-text-fill:#FFFFFF");
        if(message.equalsIgnoreCase("No winner")) 
        {
            System.err.println("media");
             media  = new Media(this.getClass().getResource("../images/tie.mp4").toString());
             text.setText(" ");
           
      
        }
        else
        {
        if(message.equalsIgnoreCase("You Lost"))
        {
          media  = new Media(this.getClass().getResource("../images/lose (1).mp4").toString());  
        }
        else
        {
       media  = new Media(this.getClass().getResource("../images/2.mp4").toString());
        }
        
        }
       // mediaView.setStyle("-fx-background-radius: 18 18 18 18;"  + "-fx-background-color: #FFFFFF");
      mediaPlayer = new MediaPlayer(media);  
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView (mediaPlayer);  
        mediaView.setFitWidth(350);
        mediaView.setFitHeight(350);
        layout.getChildren().add(mediaView); 
         layout.getChildren().add(text);
        layout.getChildren().add(reset);
        layout.getChildren().add(replay);
        scene = new Scene(layout, 350, 200);
        Style(Parent);
       dialogStage.show();
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public Button reset() {
        return reset;
    }

    public Button replay() {
        return replay;
    }

    public Stage getStage() {
        return dialogStage;
    }

    public void setlabeltext(String message) {
        text.setText(message);

    }

    public void Style(Window parent) {
        /*============================button style ===============================================================================*/

        reset.setTranslateY(25);
        reset.setTranslateX(-70);
        reset.setTextFill(Color.web("#fcfcfc"));
        reset.setMinWidth(Region.USE_COMPUTED_SIZE);
        reset.setMinHeight(Region.USE_COMPUTED_SIZE);
        reset.setPrefHeight(25);
        reset.setPrefWidth(109);
        reset.setStyle("-fx-background-radius: 10 10 10 10;" + "-fx-background-color:  #4f2161;" + "-fx-font-weight: bold");
        replay.setTranslateY(25);
        replay.setTranslateX(70);
        replay.setTextFill(Color.web("#fcfcfc"));
        replay.setMinWidth(Region.USE_COMPUTED_SIZE);
        replay.setMinHeight(Region.USE_COMPUTED_SIZE);
        replay.setPrefHeight(25);
        replay.setPrefWidth(109);
        replay.setStyle("-fx-background-radius: 10 10 10 10;" + "-fx-background-color:  #4f2161;" + "-fx-font-weight: bold");
        /*=========================label style ===================================================================================*/

        Font font = new Font("Comic Sans MS", 30);
        text.setTranslateY(-10);
        text.setFont(font);
     
        /*===========================pane style ===================================================================================*/

        layout.setStyle("-fx-background-radius: 18 18 18 18;" + "-fx-background-color: #FFFFFF");
        layout.setId("layout");
        /*=========================Stage Style =====================================================================================*/
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setScene(scene);
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        double X = (parent.getX() + 100);
        dialogStage.setX(X);
        dialogStage.setY(parent.getY() + 100);
        scene.setFill(Color.TRANSPARENT);

    }
}
