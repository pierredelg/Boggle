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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Plateau extends Application {

    private String[][] etatPlateauChar;
    private List<Integer> emplacementButton;
    private List<String> listMotValider = new ArrayList<>();
    private List<Integer> buttonListCheck = new ArrayList<>();
    private GridPane gridPane;
    private Label labelMotEnCours;
    private Label labelMotValider;
    private Label labelTextInformation;
    private Label labelJoueur;
    private Button buttonAjouter;
    private Button buttonSupprimer;
    private int taillePlateau;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {

        taillePlateau = 5;
        etatPlateauChar = new String[taillePlateau][taillePlateau];
        emplacementButton = new ArrayList<>();

        // TODO
        //List<String> characterList = De.lireFichierCsv();
        Config config = new Config();
        config.chargerConfigDe();
        List<De2> de2List = config.de2List;

        gridPane = new GridPane();
        //On espace le gridpane des bords de la scène
        int numeroCharacter = 0;
        for (int ligne = 1; ligne <= taillePlateau; ligne++) {
            for (int colonne = 1; colonne <= taillePlateau; colonne++) {

                // TODO
                //Button button = creerButton(characterList.get(numeroCharacter));
                Button button = creerButton(de2List.get(numeroCharacter).getFace().toString(),ligne,colonne);
                gridPane.add(button, colonne, ligne);
                emplacementButton.add(((ligne-1)*taillePlateau)+colonne-1);

                // TODO
                //etatPlateau[ligne-1][colonne-1] = characterList.get(numeroCharacter);
                etatPlateauChar[ligne - 1][colonne - 1] = de2List.get(numeroCharacter).getFace().toString().toUpperCase();
                numeroCharacter++;
            }
        }

        //Utile pour verification du plateau en console :)
        for (int l = 0; l < taillePlateau; l++) {
            for (int c = 0; c < taillePlateau; c++) {
                System.out.print(etatPlateauChar[l][c] + " ");
            }
            System.out.println();
        }

        labelTextInformation = new Label();
        labelTextInformation.setMinWidth(taillePlateau*80);
        labelTextInformation.setMinHeight(50);

        //label pour nom du joueur (pour l'emplacement) => donc test
        labelJoueur = new Label("Joueur 1");
        labelJoueur.setMinWidth(170);
        labelJoueur.setMinHeight(50);
        labelJoueur.setAlignment(Pos.CENTER);
        labelJoueur.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        //Construction de la Hbox contenant l'information (partie et joueur)
        HBox hboxInformation = new HBox();
        hboxInformation.getChildren().addAll(labelTextInformation,labelJoueur);
        hboxInformation.setPadding(new Insets(0,20,0,20));
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
        hboxButton.setPadding(new Insets(10,20,20,20));
        hboxButton.setSpacing(20);

        //Construction du label pour afficher le mot en cours
        labelMotEnCours = new Label();
        labelMotEnCours.setMinWidth(80 * taillePlateau);
        labelMotEnCours.setMinHeight(50);
        labelMotEnCours.setStyle("-fx-border-width: 2px; -fx-border-color: black");

        //Création du boutton ajouter
        buttonAjouter = creerButtonAjouter("Ajouter");
        buttonAjouter.setDisable(true);
        buttonSupprimer = creerButtonSupprimer("Supprimer");

        //Contruction d'une hbox contenant les bouttons ajout et suppression
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

        //Construction de la scéne
        Scene scene = new Scene(vboxPrincipale, (taillePlateau * 80) + 230, (taillePlateau * 80) + 160);

        //Modification du titre de la scène
        stage.setTitle("Plateau de jeu BOGGLE");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    //Permet la création de boutton
    private Button creerButton(String s,int ligne, int colonne) {
        Button button = new Button(s);
        button.setMinWidth(80);
        button.setMinHeight(80);
        button.setStyle("-fx-border-color: #6a6a69; -fx-border-width: 4px");
        button.setOnMouseClicked(e -> {
            String mot = labelMotEnCours.getText();
            mot += s;
            labelMotEnCours.setText(mot);
            labelMotEnCours.setAlignment(Pos.CENTER);
            buttonListCheck.add(((ligne-1)*taillePlateau)+colonne-1);
            if(labelMotEnCours.getText().length() >= taillePlateau - 1 ){
                buttonAjouter.setDisable(false);
            }
            disableAllButton();
            enableSomeButton(ligne,colonne);
        });
        return button;
    }

    //Permet de désactiver tout les bouttons du gridpane
    private void disableAllButton(){
        for(Integer i : emplacementButton){
            gridPane.getChildren().get(i).setDisable(true);
        }
    }

    //Permet d'activer certains boutton
    private void enableSomeButton(int ligne, int colonne){

        for(int l = -1 ; l < 2; l++){
            for(int c = colonne==1?0:-1; c < 2; c++){

                if(colonne == taillePlateau){
                    if(c != 1) {
                        int ligneBis = ligne + l;
                        int colonneBis = colonne + c;
                        int buttonCible = ((ligneBis - 1) * taillePlateau) + colonneBis - 1;
                        if (buttonCible >= 0 && buttonCible <= taillePlateau*taillePlateau - 1)
                            gridPane.getChildren().get(buttonCible).setDisable(false);
                    }
                }else {
                    int ligneBis = ligne + l;
                    int colonneBis = colonne + c;
                    int buttonCible = ((ligneBis - 1) * taillePlateau) + colonneBis - 1;
                    if (buttonCible >= 0 && buttonCible <= taillePlateau*taillePlateau - 1)
                        gridPane.getChildren().get(buttonCible).setDisable(false);
                }
            }
        }
        for(Integer i: buttonListCheck){
            gridPane.getChildren().get(i).setDisable(true);
        }
        gridPane.getChildren().get((ligne - 1) * taillePlateau + colonne - 1).setDisable(true);
    }


    //Permet d'activer tout les bouttons du gridpane
    private void enableAllButton(){
        for(Integer i : emplacementButton){
            gridPane.getChildren().get(i).setDisable(false);
        }
    }

    //Permet la création du boutton ajouter ainsi q'un traitement particulier
    private Button creerButtonAjouter(String s) {
        Button button = new Button(s);
        button.setMinWidth(50);
        button.setMinHeight(50);
        button.setStyle("-fx-border-color: #6a6a69; -fx-border-width: 3px");
        button.setOnMouseClicked(e -> {
            if(!listMotValider.contains(labelMotEnCours.getText())) {
                if (estDansDictionnaire(labelMotEnCours.getText())) {
                    String toutLesMotsValider = labelMotValider.getText();
                    toutLesMotsValider += labelMotEnCours.getText() + "\n";
                    labelMotValider.setText(toutLesMotsValider);
                    labelMotValider.setAlignment(Pos.CENTER);
                    labelMotValider.setStyle("-fx-font-weight: bold");
                    listMotValider.add(labelMotEnCours.getText());
                    labelMotEnCours.setText("");
                    buttonListCheck.clear();
                    enableAllButton();
                    buttonAjouter.setDisable(true);
                } else {
                    labelTextInformation.setText("Ce mot n'existe pas !");
                }
            }else{
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
            labelMotEnCours.setText("");
            labelTextInformation.setText("");
            buttonAjouter.setDisable(true);
            buttonListCheck.clear();
            enableAllButton();
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
        return true;
    }
}
