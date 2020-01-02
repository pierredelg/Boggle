package boggle.mots;

import boggle.config.ChargerConfig;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GrilleLettres extends GridPane {

    private int taillePlateau;
    private String[][] etatPlateauChar;
    private GridPane gridPane;
    private List<Integer> emplacementButton;
    private Label labelMotEnCours;
    private Button buttonAjouter;
    private List<Integer> buttonListCheck = new ArrayList<>();
    private String BUTTONSTYLE = "-fx-border-color: #6a6a69; -fx-border-width: 4px;";
    private String HELPBUTTONSTYLE = "-fx-border-color: #6a6a69; -fx-border-width: 4px; -fx-background-color: orange";
    private String LABELSTYLE = "-fx-border-width: 2px; -fx-border-color: black";

    public GrilleLettres() {
        this.taillePlateau = ChargerConfig.getTaillePlateau();
        this.etatPlateauChar = new String[taillePlateau][taillePlateau];
        this.gridPane = generateGrille();
        this.labelMotEnCours = generateLabelMotEnCours();
    }

    private GridPane generateGrille() {
        emplacementButton = new ArrayList<>();
        List<De> deList = new Config().deList;
        this.gridPane = new GridPane();

        int numeroCharacter = 0;
        for (int ligne = 1; ligne <= taillePlateau; ligne++) {
            for (int colonne = 1; colonne <= taillePlateau; colonne++) {

                numeroCharacter = (numeroCharacter == deList.size()) ? 0 : numeroCharacter;
                Button button = creerButton(deList.get(numeroCharacter).getFace().toString(), ligne, colonne);
                gridPane.add(button, colonne, ligne);
                emplacementButton.add(((ligne - 1) * taillePlateau) + colonne - 1);

                etatPlateauChar[ligne - 1][colonne - 1] = deList.get(numeroCharacter).getFace().toString().toUpperCase();
                numeroCharacter++;
            }
        }
        return gridPane;
    }

    //Genere le label pour afficher le mot en cours
    private Label generateLabelMotEnCours() {

        labelMotEnCours = new Label();
        labelMotEnCours.setMinWidth(80 * taillePlateau);
        labelMotEnCours.setMinHeight(50);
        labelMotEnCours.setStyle(LABELSTYLE);
        return labelMotEnCours;
    }

    //Permet la création de boutton
    private Button creerButton(String s, int ligne, int colonne) {
        Button button = new Button(s);
        button.setMinWidth(80);
        button.setMinHeight(80);
        button.setStyle(BUTTONSTYLE);
        button.setOnMouseClicked(e -> {
            resetColorButton();
            String mot = labelMotEnCours.getText();
            mot += s;
            labelMotEnCours.setText(mot);
            labelMotEnCours.setAlignment(Pos.CENTER);
            buttonListCheck.add(((ligne - 1) * taillePlateau) + colonne - 1);
            if (labelMotEnCours.getText().length() >= ChargerConfig.getTailleMinMot() && labelMotEnCours.getText().length() >= taillePlateau - 1) {
                buttonAjouter.setDisable(false);
            }
            disableAllButton();
            ArrayList<Button> validButtons = enableSomeButton(ligne, colonne);

            //On met à jour la liste des mots possibles
            if(mot.length() >= ChargerConfig.getTailleMinMot()){
                ArrayList<String> motsPossibles = setHelpList(validButtons);
                //On colore les boutons
                colorHelpButton(validButtons, helpLetterList(motsPossibles));
            }
        });
        return button;
    }

    //Active certain boutton
    private ArrayList<Button> enableSomeButton(int ligne, int colonne) {
        ArrayList<Button> validButtons = new ArrayList<>();

        for (int l = -1; l < 2; l++) {
            for (int c = colonne == 1 ? 0 : -1; c < 2; c++) {

                if (colonne == this.taillePlateau) {
                    if (c != 1) {
                        int ligneBis = ligne + l;
                        int colonneBis = colonne + c;
                        int buttonCible = ((ligneBis - 1) * this.taillePlateau) + colonneBis - 1;
                        if (buttonCible >= 0 && buttonCible <= this.taillePlateau * this.taillePlateau - 1) {
                            Button button = (Button) gridPane.getChildren().get(buttonCible);
                            validButtons.add(button);
                            button.setDisable(false);
                        }
                    }
                } else {
                    int ligneBis = ligne + l;
                    int colonneBis = colonne + c;
                    int buttonCible = ((ligneBis - 1) * this.taillePlateau) + colonneBis - 1;
                    if (buttonCible >= 0 && buttonCible <= this.taillePlateau * this.taillePlateau - 1) {
                        Button button = (Button) gridPane.getChildren().get(buttonCible);
                        validButtons.add(button);
                        button.setDisable(false);
                    }
                }
            }
        }
        for (Integer i : buttonListCheck) {
            Button button = (Button) gridPane.getChildren().get(i);
            validButtons.remove(button);
            button.setDisable(true);

        }
        Button button = (Button) gridPane.getChildren().get((ligne - 1) * this.taillePlateau + colonne - 1);
        validButtons.remove(button);
        button.setDisable(true);
        return validButtons;
    }

    public ArrayList<String> setHelpList(ArrayList<Button> validButtons){
        //On crée la liste à renvoyer
        ArrayList<String> helpList = new ArrayList<>();

        //On récupere le mot en cours créé par le joueur
        String motEncours = labelMotEnCours.getText();
        int longueurDuMotEnCours = motEncours.length();

        //On récupere tout les mots du dictionnaire commençant par le mot en cours
        ArrayList<String> possibilitesMots = new ArrayList<>();
        ChargerConfig.getArbreLexical().motsCommencantPar(motEncours,possibilitesMots);

        ArrayList<Character> lettresPossibles = new ArrayList<>();

        //On récupere la liste des lettres possibles
        validButtons.forEach(button1 -> lettresPossibles.add(button1.getText().charAt(0)));

        //On parcourt la liste des mots possibles
        //On ajoute à la liste à renvoyer les mots possible à construire avec les lettres suivantes
        for(int i = 0 ; i < possibilitesMots.size();  i++){

            String motPossible = possibilitesMots.get(i);

            if(motPossible.equals(motEncours) || (motPossible.length() > longueurDuMotEnCours &&
                    lettresPossibles.contains(motPossible.charAt(longueurDuMotEnCours)))){
                helpList.add(motPossible);
            }
        }
        return helpList;
    }

    public List<Character> helpLetterList(List<String> helpList){

        HashSet<Character> characters = new HashSet<>();
        String motEncours = labelMotEnCours.getText();
        int longueurDuMotEnCours = motEncours.length();

        for (String mot : helpList){
            if(mot.length() > longueurDuMotEnCours) {
                characters.add(mot.charAt(longueurDuMotEnCours));
            }
        }
        return new ArrayList<>(characters);
    }

    public void colorHelpButton(ArrayList<Button> validButtons, List<Character> characters){
        //Pour chaque boutons valides on vérifie que son caractere est présent dans la liste des caractère
        for(int i = 0 ; i < validButtons.size();i++){
            Button button = validButtons.get(i);
            Character characterButton = button.getText().charAt(0);
            if(characters.contains(characterButton)){
                button.setStyle(HELPBUTTONSTYLE);
            }
        }
    }


    //Active tout les bouttons du gridpane
    public void enableAllButton() {
        for (Integer i : emplacementButton) {
            Button button = (Button) gridPane.getChildren().get(i);
            button.setDisable(false);
        }
    }

    //Permet de désactiver tout les bouttons du gridpane
    public void disableAllButton() {
        for (Integer i : emplacementButton) {
            gridPane.getChildren().get(i).setDisable(true);
        }
    }

    public void resetColorButton(){
        for (Integer i : emplacementButton) {
            gridPane.getChildren().get(i).setStyle(BUTTONSTYLE);
        }
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
