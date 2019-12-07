package boggle.ui;

import boggle.mots.Config;
import boggle.mots.De2;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.List;

public class Plateau extends Application {

    private String[][] etatPlateau;
    private Label labelMotEnCours;
    private Label labelMotValider;
    private Label labelTextInformation;
    private Label labelJoueur;


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {

        int taille = 4;
        etatPlateau = new String[taille][taille];

        // TODO
        //List<String> characterList = De.lireFichierCsv();
        Config config = new Config();
        config.chargerConfigDe();
        List<De2> de2List = config.de2List;

        GridPane gridpane = new GridPane();
        //On espace le gridpane des bords de la scène
        int numeroCharacter = 0;
        for (int ligne = 1; ligne <= taille; ligne++) {
            for (int colonne = 1; colonne <= taille; colonne++) {

                // TODO
                //Button button = creerButton(characterList.get(numeroCharacter));
                Button button = creerButton(de2List.get(numeroCharacter).getFace().toString());
                gridpane.add(button, colonne, ligne);
                // TODO
                //etatPlateau[ligne-1][colonne-1] = characterList.get(numeroCharacter);
                etatPlateau[ligne - 1][colonne - 1] = de2List.get(numeroCharacter).getFace().toString().toUpperCase();
                numeroCharacter++;
            }
        }

        //Utile pour verification du plateau en console :)
        for (int l = 0; l < taille; l++) {
            for (int c = 0; c < taille; c++) {
                System.out.print(etatPlateau[l][c] + " ");
            }
            System.out.println();
        }

        labelTextInformation = new Label();
        labelTextInformation.setMinWidth(taille*80);
        labelTextInformation.setMinHeight(50);

        //label pour nom du joueur
        labelJoueur = new Label("Joueur 1");
        labelJoueur.setMinWidth(150);
        labelJoueur.setMinHeight(50);
        labelJoueur.setAlignment(Pos.CENTER);
        labelJoueur.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");


        //Construction de la Hbox contenant l'information (partie et joueur)
        HBox hboxInformation = new HBox();
        hboxInformation.getChildren().addAll(labelTextInformation,labelJoueur);
        hboxInformation.setPadding(new Insets(0,20,0,20));
        hboxInformation.setSpacing(20);

        labelMotValider = new Label(" ");
        labelMotValider.setMinWidth(168);

        //Construction de la Vbox contenant le score
        VBox vboxScore = new VBox();
        vboxScore.getChildren().addAll(labelMotValider);
        vboxScore.setStyle("-fx-border-width: 2px; -fx-border-color: black");

        //construction de la Hbox contenant les buttons et le score
        HBox hboxButton = new HBox();
        hboxButton.getChildren().addAll(gridpane, vboxScore);
        hboxButton.setPadding(new Insets(10,20,20,20));
        hboxButton.setSpacing(20);

        labelMotEnCours = new Label();
        labelMotEnCours.setMinWidth(80 * taille);
        labelMotEnCours.setMinHeight(50);
        labelMotEnCours.setStyle("-fx-border-width: 2px; -fx-border-color: black");

        Button buttonAjouter = creerButtonAjouter("Ajouter");
        Button buttonSupprimer = creerButtonSupprimer("Supprimer");

        HBox hBoxButtonAction = new HBox();
        hBoxButtonAction.setSpacing(8);
        hBoxButtonAction.getChildren().addAll(buttonAjouter,buttonSupprimer);

        //Construction de la Hbox contenant le mot en cours et les buttons ajouter et supprimer
        HBox hboxMot = new HBox();
        hboxMot.getChildren().addAll(labelMotEnCours, hBoxButtonAction);
        hboxMot.setPadding(new Insets(0,20,0,20));
        hboxMot.setSpacing(20);

        //Contruction de la Vbox contenant le tout
        VBox vboxPrincipale = new VBox();
        vboxPrincipale.getChildren().addAll(hboxInformation,hboxButton, hboxMot);

        Scene scene = new Scene(vboxPrincipale, (taille * 80) + 230, (taille * 80) + 160);


        stage.setTitle("Plateau de jeu BOGGLE");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    private Button creerButton(String s) {
        Button button = new Button(s);
        button.setMinWidth(80);
        button.setMinHeight(80);
        button.setStyle("-fx-border-color: #6a6a69; -fx-border-width: 4px");
        button.setOnMouseClicked(e -> {
            System.out.print(s);
            String mot = labelMotEnCours.getText();
            mot += s;
            labelMotEnCours.setText(mot);
            labelMotEnCours.setAlignment(Pos.CENTER);
        });
        return button;
    }

    private Button creerButtonAjouter(String s) {
        Button button = new Button(s);
        button.setMinWidth(50);
        button.setMinHeight(50);
        button.setStyle("-fx-border-color: #6a6a69; -fx-border-width: 3px");
        button.setOnMouseClicked(e -> {

            if(estDansDictionnaire(labelMotEnCours.getText())){
                String toutLesMotsValider = labelMotValider.getText();
                toutLesMotsValider += labelMotEnCours.getText()+"\n";
                labelMotValider.setText(toutLesMotsValider);
                labelMotValider.setAlignment(Pos.CENTER);
                labelMotValider.setStyle("-fx-font-weight: bold");
                labelMotEnCours.setText(" ");
            }else{
                labelTextInformation.setText("Ce mot n'existe pas !");
                labelTextInformation.setAlignment(Pos.CENTER);
                labelTextInformation.setStyle("-fx-font-size: 18; -fx-font-weight: bold");
                labelTextInformation.setTextFill(Color.RED);
            }

        });
        return button;
    }

    private Button creerButtonSupprimer(String s) {
        Button button = new Button(s);
        button.setMinWidth(50);
        button.setMinHeight(50);
        button.setStyle("-fx-border-color: #ff0000; -fx-border-width: 3px");
        button.setOnMouseClicked(e -> {
            labelMotEnCours.setText(" ");
        });
        return button;
    }

    private void textDisplay(GridPane grid, String theText, int ligne, int col) {
        Text text = new Text();
        text.setWrappingWidth(50);
        text.setText(theText);
        text.setTextAlignment(TextAlignment.CENTER);
        GridPane.setRowIndex(text, ligne);
        GridPane.setColumnIndex(text, col);
        grid.getChildren().addAll(text);
    }

    //TODO
    //verifier si le mot en cours est dans le dictionnaire.
    //retourne vrai si présent. Faux sinon.
    //J'ai mis return true par defaut pour tester l'affichage lorsqu'un mot existe
    // mettre à false pour tester le message d'erreur à titre informatif
    public boolean estDansDictionnaire(String motEnCours){
        return false;
    }
}
