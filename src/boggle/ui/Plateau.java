package boggle.ui;

import boggle.jeu.EtatJeuListener;
import boggle.jeu.GestionTour;
import boggle.jeu.Tour;
import boggle.mots.ArbreLexicalLudo;
import boggle.mots.GrilleLettres;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Plateau extends Application implements EtatJeuListener {

    private List<String> listMotValider = new ArrayList<>();
    private GridPane gridPane;
    private Label labelMotValider;
    private Label labelTextInformation;
    private Label labelJoueur;
    private Button buttonSupprimer;
    private GrilleLettres grilleLettres;
    private Stage stage;

    private GestionTour gestionTour;

    public static void main(String[] args) {
        launch(args);
    }

    //Page de bienvenue
    public void start(Stage stage) {
        this.stage = stage;
        Text text = new Text("bonjour");
        Button buttonAccesConfig = creerButtonBienvenue("Config");

        //Contruction de la Vbox contenant le text et le boutton
        VBox vboxPrincipale = new VBox();
        vboxPrincipale.getChildren().addAll(text, buttonAccesConfig);
        Scene scene = new Scene(vboxPrincipale, 500, 500);

        //Modification du titre de la scène
        stage.setTitle("Bienvenue sur boggle");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    public void startBienvenue() throws IOException {
        new GestionTour(this).start();
    }

    @Override
    public void setTour(Tour tour) {
        this.grilleLettres = new GrilleLettres();
        gridPane = grilleLettres.getGridPane();

        labelTextInformation = new Label();
        labelTextInformation.setMinWidth((grilleLettres.getTaillePlateau() - 1) * 80 - 20);
        labelTextInformation.setMinHeight(50);

        //Création du label pour le joueur courant
        labelJoueur = new Label(tour.getJoueur().getName());
        labelJoueur.setMinWidth(170);
        labelJoueur.setMinHeight(50);
        labelJoueur.setAlignment(Pos.CENTER);
        labelJoueur.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        VBox vBoxTimer = tour.getTimer().generateTimer();
        vBoxTimer.setMinWidth(80);
        vBoxTimer.setMinHeight(50);

        //Construction de la Hbox contenant l'information (partie et joueur)
        HBox hboxInformation = new HBox();
        hboxInformation.getChildren().addAll(vBoxTimer, labelTextInformation, labelJoueur);
        hboxInformation.setPadding(new Insets(0, 20, 0, 20));
        hboxInformation.setSpacing(20);

        //Construction du label pour afficher les mots validés
        labelMotValider = new Label();
        labelMotValider.setMinWidth(168);

        //Construction de la Vbox contenant le score
        VBox vboxScore = new VBox();
        vboxScore.getChildren().addAll(labelMotValider);
        vboxScore.setStyle("-fx-border-width: 2px; -fx-border-color: black");

        //construction de la Hbox contenant les buttons et le score
        HBox hboxButton = new HBox();
        hboxButton.getChildren().addAll(gridPane, vboxScore);
        hboxButton.setPadding(new Insets(10, 20, 20, 20));
        hboxButton.setSpacing(20);


        grilleLettres.setButtonAjouter(creerButtonAjouter("Ajouter"));
        grilleLettres.getButtonAjouter().setDisable(true);
        buttonSupprimer = creerButtonSupprimer("Supprimer");

        //Contruction d'une hbox contenant les bouttons ajout et suppression
        HBox hBoxButtonAction = new HBox();
        hBoxButtonAction.setSpacing(8);
        hBoxButtonAction.getChildren().addAll(grilleLettres.getButtonAjouter(), buttonSupprimer);

        //Construction de la Hbox contenant le mot en cours et les buttons ajouter et supprimer
        HBox hboxMot = new HBox();
        hboxMot.getChildren().addAll(grilleLettres.getLabelMotEnCours(), hBoxButtonAction);
        hboxMot.setPadding(new Insets(0, 20, 0, 20));
        hboxMot.setSpacing(20);

        //Contruction de la Vbox contenant le tout
        VBox vboxPrincipale = new VBox();
        vboxPrincipale.getChildren().addAll(hboxInformation, hboxButton, hboxMot);

        //Construction de la scéne
        Scene scene = new Scene(vboxPrincipale, (grilleLettres.getTaillePlateau() * 80) + 230, (grilleLettres.getTaillePlateau() * 80) + 160);

        //Modification du titre de la scène
        stage.setTitle("Plateau de jeu BOGGLE");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void finDuJeu(List<Tour> tourList) {

        stage.close();

        Label labelScore = new Label();
        labelScore.setMinWidth(520);
        labelScore.setMinHeight(tourList.size() * 100);
        labelScore.setAlignment(Pos.CENTER);
        labelScore.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        String result = "";
        //Récupération des scores
        for (Tour tour : tourList)
            result += tour.getJoueur().toString() + " \n\n";

        labelScore.setText(result);

        Button buttonRecommencer = creerButtonRecommencer("Recommencer");
        buttonRecommencer.setMinWidth(520);
        buttonRecommencer.setMinHeight(50);

        //Contruction de la Vbox contenant le score et le boutton recommencer
        VBox vboxPrincipale = new VBox();
        vboxPrincipale.getChildren().addAll(labelScore, buttonRecommencer);
        Scene scene = new Scene(vboxPrincipale, 520, tourList.size() * 100 + 50);

        //Modification du titre de la scène
        stage.setTitle("Fin du jeu");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    //Permet la création de boutton
    private Button creerButtonBienvenue(String s) {
        Button button = new Button(s);
        button.setMinWidth(80);
        button.setMinHeight(80);
        button.setStyle("-fx-border-color: #6a6a69; -fx-border-width: 4px");
        button.setOnMouseClicked(e -> {
            try {
                startBienvenue();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        return button;
    }

    //Permet la création de boutton
    private Button creerButtonRecommencer(String s) {
        Button button = new Button(s);
        button.setStyle("-fx-border-color: #6a6a69; -fx-border-width: 4px");
        button.setOnMouseClicked(e -> {
            start(stage);
        });
        return button;
    }


    //Permet la création du boutton ajouter ainsi q'un traitement particulier
    private Button creerButtonAjouter(String s) {
        Button button = new Button(s);
        button.setMinWidth(50);
        button.setMinHeight(50);
        button.setStyle("-fx-border-color: #6a6a69; -fx-border-width: 3px");
        button.setOnMouseClicked(e -> {
            if (!listMotValider.contains(grilleLettres.getLabelMotEnCours().getText())) {
                try {
                    if (estDansDictionnaire(grilleLettres.getLabelMotEnCours().getText())) {
                        String toutLesMotsValider = labelMotValider.getText();
                        toutLesMotsValider += grilleLettres.getLabelMotEnCours().getText() + "\n";
                        labelMotValider.setText(toutLesMotsValider);
                        labelMotValider.setAlignment(Pos.CENTER);
                        labelMotValider.setStyle("-fx-font-weight: bold");
                        listMotValider.add(grilleLettres.getLabelMotEnCours().getText());
                        grilleLettres.getLabelMotEnCours().setText("");
                        grilleLettres.getButtonListCheck().clear();
                        grilleLettres.enableAllButton();
                        grilleLettres.getButtonAjouter().setDisable(true);
                    } else {
                        labelTextInformation.setText("Ce mot n'existe pas !");
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                labelTextInformation.setText("Ce mot est déjà pris !");
            }
            labelTextInformation.setAlignment(Pos.CENTER);
            labelTextInformation.setStyle("-fx-font-size: 18; -fx-font-weight: bold");
            labelTextInformation.setTextFill(Color.RED);
        });
        return button;
    }

    //Permet la création du boutton supprimer ainsi qu'un traitement particulier
    private Button creerButtonSupprimer(String s) {
        Button button = new Button(s);
        button.setMinWidth(50);
        button.setMinHeight(50);
        button.setStyle("-fx-border-color: #ff0000; -fx-border-width: 3px");
        button.setOnMouseClicked(e -> {
            grilleLettres.getLabelMotEnCours().setText("");
            labelTextInformation.setText("");
            grilleLettres.getButtonAjouter().setDisable(true);
            grilleLettres.getButtonListCheck().clear();
            grilleLettres.enableAllButton();
        });
        return button;
    }

    public boolean estDansDictionnaire(String motEnCours) throws FileNotFoundException {
        ArbreLexicalLudo arbre = ArbreLexicalLudo.lireMots("./config/dict-fr.txt");
        return arbre.contient(motEnCours);
    }
}
