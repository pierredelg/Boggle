package boggle.mots;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GrilleLettres extends GridPane {

    private int taillePlateau;
    private String[][] etatPlateauChar;
    private GridPane gridPane;
    private List<Integer> emplacementButton;
    private Label labelMotEnCours;
    private Button buttonAjouter;
    private List<Integer> buttonListCheck = new ArrayList<>();

    public GrilleLettres() throws IOException {
        lireFichierConfig();
        this.gridPane = generateGrille();
        this.labelMotEnCours = generateLabelMotEnCours();
    }

    private GridPane generateGrille(){
        emplacementButton = new ArrayList<>();
        Config config = new Config();
        config.chargerConfigDe();
        List<De2> de2List = config.de2List;
        this.gridPane = new GridPane();

        int numeroCharacter = 0;
        for (int ligne = 1; ligne <= taillePlateau; ligne++) {
            for (int colonne = 1; colonne <= taillePlateau; colonne++) {

                Button button = creerButton(de2List.get(numeroCharacter).getFace().toString(),ligne,colonne);
                gridPane.add(button, colonne, ligne);
                emplacementButton.add(((ligne-1)*taillePlateau)+colonne-1);

                etatPlateauChar[ligne - 1][colonne - 1] = de2List.get(numeroCharacter).getFace().toString().toUpperCase();
                numeroCharacter++;
            }
        }
        return gridPane;
    }

    //Genere le label pour afficher le mot en cours
    private Label generateLabelMotEnCours(){

        labelMotEnCours = new Label();
        labelMotEnCours.setMinWidth(80 * taillePlateau);
        labelMotEnCours.setMinHeight(50);
        labelMotEnCours.setStyle("-fx-border-width: 2px; -fx-border-color: black");
        return labelMotEnCours;
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
    public void disableAllButton(){
        for(Integer i : emplacementButton){
            gridPane.getChildren().get(i).setDisable(true);
        }
    }

    //Active certain boutton
    private void enableSomeButton(int ligne, int colonne){

        for(int l = -1 ; l < 2; l++){
            for(int c = colonne==1?0:-1; c < 2; c++){

                if(colonne == this.taillePlateau){
                    if(c != 1) {
                        int ligneBis = ligne + l;
                        int colonneBis = colonne + c;
                        int buttonCible = ((ligneBis - 1) * this.taillePlateau) + colonneBis - 1;
                        if (buttonCible >= 0 && buttonCible <= this.taillePlateau * this.taillePlateau - 1)
                            gridPane.getChildren().get(buttonCible).setDisable(false);
                    }
                }else {
                    int ligneBis = ligne + l;
                    int colonneBis = colonne + c;
                    int buttonCible = ((ligneBis - 1) * this.taillePlateau) + colonneBis - 1;
                    if (buttonCible >= 0 && buttonCible <= this.taillePlateau * this.taillePlateau - 1)
                        gridPane.getChildren().get(buttonCible).setDisable(false);
                }
            }
        }
        for(Integer i: buttonListCheck){
            gridPane.getChildren().get(i).setDisable(true);
        }
        gridPane.getChildren().get((ligne - 1) * this.taillePlateau + colonne - 1).setDisable(true);
    }



    //Active tout les bouttons du gridpane
    public void enableAllButton(){
        for(Integer i : emplacementButton){
            gridPane.getChildren().get(i).setDisable(false);
        }
    }

    private void lireFichierConfig() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("config/parametres.txt"));

        String ligne = "";
        while ((ligne = bufferedReader.readLine()) != null) {
            String[] split = ligne.split("=");
            if (split[0].equalsIgnoreCase("taille_du_plateau")) {
                this.taillePlateau = Integer.parseInt(split[1]);
                etatPlateauChar = new String[taillePlateau][taillePlateau];
            }
        }
        bufferedReader.close();
    }

    public int getTaillePlateau() {
        return taillePlateau;
    }


    public GridPane getGridPane() {
        return gridPane;
    }

    public Label getLabelMotEnCours() {
        return labelMotEnCours;
    }

    public Button getButtonAjouter() {
        return buttonAjouter;
    }

    public void setButtonAjouter(Button buttonAjouter) {
        this.buttonAjouter = buttonAjouter;
    }

    public List<Integer> getButtonListCheck() {
        return buttonListCheck;
    }

}
