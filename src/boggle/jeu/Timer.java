package boggle.jeu;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;

public class Timer {

    private static final Integer STARTMINUTE = 0;
    private static final Integer STARTSECONDE = 4;
    private Timeline timeline;
    private Label timerLabel = new Label();
    private Integer timeSeconds = STARTSECONDE;
    private Integer timeMinutes = STARTMINUTE;
    private boolean timerPositif;
    private boolean disable;

    private TourListener tourListener;

    public Timer(TourListener tourListener){
        this.timerPositif = true;
        this.tourListener = tourListener;
    }


    public VBox generateTimer() {

        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 1.8em;");
        if (STARTSECONDE == 0)
            timerLabel.setText(timeMinutes.toString() + " : 0" + timeSeconds.toString());
        else
            timerLabel.setText(timeMinutes.toString() + " : " + timeSeconds.toString());


        Button button = new Button();
        button.setText("Start");
        button.setOnAction(new EventHandler<ActionEvent>() {


            public void handle(ActionEvent event) {
                button.setDisable(true);
                disable = true;
                if (timeline != null) {
                    timeline.stop();
                }
                timeSeconds = STARTSECONDE;
                timeMinutes = STARTMINUTE;

                // update timerLabel

                timeline = new Timeline();
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(1),
                                new EventHandler() {

                                    // KeyFrame event handler
                                    public void handle(Event event) {
                                        timeSeconds--;

                                        if (timeMinutes == 0 && timeSeconds < 0) {
                                            timeline.stop();
                                            try {
                                                tourListener.findDuTour();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        if (timeSeconds < 0) {
                                            timeSeconds = 59;
                                            timeMinutes--;
                                        }

                                        if (timeSeconds < 10 && timeSeconds >= 0)
                                            timerLabel.setText(timeMinutes.toString() + " : 0" + timeSeconds.toString());
                                        else
                                            timerLabel.setText(timeMinutes.toString() + " : " + timeSeconds.toString());


                                    }
                                }));
                timeline.playFromStart();
            }

        });

        HBox hb = new HBox(10);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(timerLabel);
        hb.setLayoutY(30);

        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);

        vb.getChildren().addAll(button, hb);
        vb.setPadding(new Insets(5,0,0,0));
        vb.setSpacing(5);

        return vb;
    }
}