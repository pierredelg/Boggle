package boggle.ui;
import boggle.mots.De;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.util.List;
import java.util.Scanner;

public class plateau extends Application {

    private String[][] etatPlateau;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {

//        Scanner sc = new Scanner(System.in);
//        System.out.println("Saisissez la taille du plateau : ");
//        int taille = sc.nextInt();

        int taille  = 4;
        etatPlateau = new String[taille][taille];
        List<String> characterList = De.lireFichierCsv();

        GridPane gridpane = new GridPane();
        int numeroCharacter = 0;
        for (int ligne = 1; ligne <= taille; ligne++) {
            for (int colonne = 1; colonne <= taille; colonne++) {

                Button button = creerButton(characterList.get(numeroCharacter));
                gridpane.add(button, colonne, ligne);
                etatPlateau[ligne-1][colonne-1] = characterList.get(numeroCharacter);
                numeroCharacter++;
            }
        }

        //Utile pour verification du plateau en console :)
        for(int l = 0; l < taille; l++){
            for(int c = 0; c < taille; c++){
                System.out.print(etatPlateau[l][c]+" ");
            }
            System.out.println();
        }
        //On espace le gridpane des bords de la scène
        gridpane.setPadding(new Insets(10,10,10,10));

        //Construction de la Vbox contenant le score
        Label labelScore = new Label("//TO DO \"ici le score\"");
        labelScore.setMinWidth(150);
        labelScore.setMinHeight(taille*50);

        VBox vboxScore = new VBox();
      //  vboxScore.setStyle("-fx-border-width: 1px; -fx-border-color: black");
        vboxScore.setMinWidth(150);
        vboxScore.getChildren().addAll(labelScore);
        vboxScore.setPadding(new Insets(20,10,14,10));

        //construction de la Hbox contenant les buttons et le score
        HBox hboxButton = new HBox();
        hboxButton.getChildren().addAll(gridpane,vboxScore);

        //Construction de la Hbox contenant le mot en cours et les buttons ajouter et supprimer
        Label labelMot = new Label("//TO DO \"ici le mot en cours\"");
        labelMot.setMinWidth(50*taille);
        labelMot.setMinHeight(60);

        Button buttonAjouter = creerButton("Ajouter");
        Button buttonSupprimer = creerButton("Supprimer");

        HBox hboxMot = new HBox();
        hboxMot.setPadding(new Insets(0,10,0,10));
        hboxMot.setSpacing(10);
      //  hboxMot.setStyle("-fx-border-width: 1px; -fx-border-color: black");
        hboxMot.getChildren().addAll(labelMot,buttonAjouter,buttonSupprimer);

        //Contruction de la Vbox contenant le tout
        VBox vboxPrincipale = new VBox();
        vboxPrincipale.getChildren().addAll(hboxButton,hboxMot);



        Scene scene = new Scene(vboxPrincipale, (taille+4) * 50, (taille + 2) * 50);

        stage.setTitle("Plateau de jeu BOGGLE");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    private Button creerButton(String s) {
        Button button = new Button(s);
        button.setMinWidth(50);
        button.setMinHeight(50);
        button.setOnMouseClicked(e -> {
            System.out.print(s);
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
}
