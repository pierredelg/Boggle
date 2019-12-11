package boggle.jeu;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Timer extends Application {

    private static final Integer STARTMINUTE = 3;
    private static final Integer STARTSECONDE = 00;
    private Timeline timeline;
    private Label timerLabel = new Label();
    private Integer timeSeconds = STARTSECONDE;
    private Integer timeMinutes = STARTMINUTE;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Timer");
        Group root = new Group();
        Scene scene = new Scene(root, 400, 350);

        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 4em;");
        if (STARTSECONDE == 0)
            timerLabel.setText(timeMinutes.toString() + " : 0" + timeSeconds.toString());
        else
            timerLabel.setText(timeMinutes.toString() + " : " + timeSeconds.toString());


        Button button = new Button();
        button.setText("Start Timer");
        button.setOnAction(new EventHandler<ActionEvent>() {


            public void handle(ActionEvent event) {
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

                                        if (timeSeconds < 0) {
                                            timeSeconds = 59;
                                            timeMinutes--;
                                        }

                                        if (timeSeconds < 10 && timeSeconds >= 0)
                                            timerLabel.setText(timeMinutes.toString() + " : 0" + timeSeconds.toString());
                                        else
                                            timerLabel.setText(timeMinutes.toString() + " : " + timeSeconds.toString());

                                        if (timeMinutes <= 0 && timeSeconds <= 0) {
                                            timeline.stop();
                                        }


                                    }
                                }));
                timeline.playFromStart();
            }

        });

        HBox hb = new HBox(10);             // gap between components is 20
        hb.setAlignment(Pos.CENTER);        // center the components within VBox

        hb.setPrefWidth(scene.getWidth());
        hb.getChildren().addAll(timerLabel);
        hb.setLayoutY(30);

        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);

        vb.setPrefWidth(scene.getWidth());
        vb.getChildren().addAll(button, hb);
        vb.setLayoutY(30);
        vb.setSpacing(50);

        root.getChildren().add(vb);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}