package boggle.jeu;

import boggle.config.ChargerConfig;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AideContextuelle {

    private String motEnCours;
    private ArrayList<Button> validButtons;
    private ArrayList<String> motsPossibles;
    private List<Character> characters;
    private String HELPBUTTONSTYLE = "-fx-border-color: #6a6a69; -fx-border-width: 4px; -fx-background-color: orange";


    public AideContextuelle(String motEnCours, ArrayList<Button> validButtons) {
        this.motEnCours = motEnCours;
        this.validButtons = validButtons;
        this.motsPossibles = setHelpList(validButtons);
        this.characters = helpLetterList(motsPossibles);
    }

    public ArrayList<String> setHelpList(ArrayList<Button> validButtons){
        //On crée la liste à renvoyer
        ArrayList<String> helpList = new ArrayList<>();

        //On récupere le mot en cours créé par le joueur
        int longueurDuMotEnCours = motEnCours.length();

        //On récupere tout les mots du dictionnaire commençant par le mot en cours
        ArrayList<String> possibilitesMots = new ArrayList<>();
        ChargerConfig.getArbreLexical().motsCommencantPar(motEnCours,possibilitesMots);

        ArrayList<Character> lettresPossibles = new ArrayList<>();

        //On récupere la liste des lettres possibles
        validButtons.forEach(button1 -> lettresPossibles.add(button1.getText().charAt(0)));

        //On parcourt la liste des mots possibles
        //On ajoute à la liste à renvoyer les mots possible à construire avec les lettres suivantes
        for(int i = 0 ; i < possibilitesMots.size();  i++){

            String motPossible = possibilitesMots.get(i);

            if(motPossible.equals(motEnCours) || (motPossible.length() > longueurDuMotEnCours &&
                    lettresPossibles.contains(motPossible.charAt(longueurDuMotEnCours)))){
                helpList.add(motPossible);
            }
        }
        return helpList;
    }

    //On crée une liste contenant les prochaines lettres possible pour construire un mot
    public List<Character> helpLetterList(List<String> helpList){

        HashSet<Character> characters = new HashSet<>();

        int longueurDuMotEnCours = motEnCours.length();

        for (String mot : helpList){
            if(mot.length() > longueurDuMotEnCours) {
                characters.add(mot.charAt(longueurDuMotEnCours));
            }
        }
        return new ArrayList<>(characters);
    }

    //On ajoute une coloration sur les boutons des prochaines lettres suceptibles de créer un mot
    public void colorHelpButton(){
        //Pour chaque boutons valides on vérifie que son caractere est présent dans la liste des caractère
        for(int i = 0 ; i < validButtons.size();i++){
            Button button = validButtons.get(i);
            Character characterButton = button.getText().charAt(0);
            if(characters.contains(characterButton)){
                button.setStyle(HELPBUTTONSTYLE);
            }
        }
    }
}
