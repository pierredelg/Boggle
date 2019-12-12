package boggle.mots;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.List;

public class GrilleLettres extends GridPane {

    //doit construire la grille aleatoire et la renvoyer à startbienvenue.
    //charger les configs aussi

    private final int taillePlateau = 5 ;
    private String[][] etatPlateauChar = new String[taillePlateau][taillePlateau];
    private GridPane gridPane;
    private List<Integer> emplacementButton;
    private Label labelMotEnCours;

    private Button buttonAjouter;
    private List<Integer> buttonListCheck = new ArrayList<>();

    public GrilleLettres(){
        this.gridPane = generateGrille();
        this.labelMotEnCours = generateLabelMotEnCours();
    }

    private GridPane generateGrille(){
        emplacementButton = new ArrayList<>();
        Config config = new Config();
        config.chargerConfigDe();
        List<De2> de2List = config.de2List;
        this.gridPane = new GridPane();
        //On espace le gridpane des bords de la scène
        int numeroCharacter = 0;
        for (int ligne = 1; ligne <= taillePlateau; ligne++) {
            for (int colonne = 1; colonne <= taillePlateau; colonne++) {

                // TODO
                Button button = creerButton(de2List.get(numeroCharacter).getFace().toString(),ligne,colonne);
                gridPane.add(button, colonne, ligne);
                emplacementButton.add(((ligne-1)*taillePlateau)+colonne-1);

                // TODO
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

        return gridPane;
    }

    private Label generateLabelMotEnCours(){
        //Construction du label pour afficher le mot en cours
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
    private void disableAllButton(){
        for(Integer i : emplacementButton){
            gridPane.getChildren().get(i).setDisable(true);
        }
    }

    //Permet d'activer certains boutton
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



    //Permet d'activer tout les bouttons du gridpane
    public void enableAllButton(){
        for(Integer i : emplacementButton){
            gridPane.getChildren().get(i).setDisable(false);
        }
    }

    public int getTaillePlateau() {
        return taillePlateau;
    }

    public String[][] getEtatPlateauChar() {
        return etatPlateauChar;
    }

    public void setEtatPlateauChar(String[][] etatPlateauChar) {
        this.etatPlateauChar = etatPlateauChar;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public List<Integer> getEmplacementButton() {
        return emplacementButton;
    }

    public void setEmplacementButton(List<Integer> emplacementButton) {
        this.emplacementButton = emplacementButton;
    }

    public Label getLabelMotEnCours() {
        return labelMotEnCours;
    }

    public void setLabelMotEnCours(Label labelMotEnCours) {
        this.labelMotEnCours = labelMotEnCours;
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

    public void setButtonListCheck(List<Integer> buttonListCheck) {
        this.buttonListCheck = buttonListCheck;
    }
}
