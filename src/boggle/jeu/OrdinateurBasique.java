package boggle.jeu;

import boggle.config.ChargerConfig;
import boggle.mots.GrilleLettres;
import javafx.scene.control.Button;
import util.ButtonPerso;
import util.Coord;

import java.util.ArrayList;
import java.util.List;


public class OrdinateurBasique extends Joueur{
    private String motEncours;
    private List<Coord> coordList;
    private GrilleLettres grilleLettres;

    public OrdinateurBasique(String name) {
        super(name);
    }

    public List<String> jouerTour(GrilleLettres grilleLettres){

        this.grilleLettres = grilleLettres;

        int tentativesMax = ChargerConfig.getTimerSeconde();
        int tentative = 0;
        ArrayList<String> liste = new ArrayList<>();
        while(tentative < tentativesMax){
            String mot = trouverMotEntier();
            if(mot != null && !liste.contains(mot)){
                liste.add(mot);
                super.updateScore(mot);
            }
            tentative++;
        }
        return liste;
    }

    public String trouverMotEntier(){
        motEncours = "";

        coordList = new ArrayList<>();

        //On choisi une lettre au hasard dans la grille.
        int maxLigne = grilleLettres.getEtatPlateauChar().length;
        int ligne = nombreAleatoire(0,maxLigne);

        int maxColonne = grilleLettres.getEtatPlateauChar()[0].length;
        int colonne = nombreAleatoire(0,maxColonne);

        char premiereLettre = grilleLettres.getEtatPlateauChar()[ligne][colonne].charAt(0);

        motEncours += premiereLettre;

        //On ajoute les coordonnées a la liste
        Coord coord1 = new Coord(ligne,colonne);
        coordList.add(coord1);

        //On cherche une deuxieme lettre
        Coord coord2 = choisirUneLettre(coord1);

        //On choisit une troisieme lettre
        Coord coord3 = choisirUneLettre(coord2);

        Character lettre = ' ';

        do {
            //On vérifie que la lettre ne forme pas un mot
            if (ChargerConfig.getArbreLexical().contient(motEncours)) {
                return motEncours;
            }

            lettre = chercheLettreSuivante(coordList.get(coordList.size() - 1));

            if (lettre != null) {
                motEncours += lettre;
            }

        }while(lettre != null);

        return null;
    }
    public Character chercheLettreSuivante(Coord coord){

            List<ButtonPerso> validButtons = grilleLettres.enableSomeButton(coord.getX(), coord.getY());

            //On récupere la liste des lettres possibles à partir du début du mot
            List<Character> characterList = trouverListeLettresPossible(validButtons);

            if(characterList.size() == 0){
                return null;
            }

            Character characterChoisi = null;
            //On recupere une lettre avec la possibilité de faire un mot
            if(characterList.size() != 0){
                for(int i = 0 ; i < validButtons.size();i++){
                    Button button = validButtons.get(i).getButton();
                    Character characterButton = button.getText().charAt(0);
                    if(characterList.contains(characterButton)){
                        characterChoisi = characterButton;
                        coordList.add(validButtons.get(i).getCoord());
                        break;
                    }
                }
            }
            return characterChoisi;
    }

    public List<Character> trouverListeLettresPossible(List<ButtonPerso> bouttonsValides){

        AideContextuelle aideContextuelle = new AideContextuelle(motEncours,bouttonsValides);

        //On récupere la liste des mots possibles
        List<String> motsPossibles =  aideContextuelle.setHelpList(bouttonsValides);

        return aideContextuelle.helpLetterList(motsPossibles);
    }


    public Coord choisirUneLettre(Coord coordActuelle){

        //On récupere les bouttons des cases adjacentes valides
        ArrayList<ButtonPerso> validbuttons = grilleLettres.enableSomeButton(coordActuelle.getX(),coordActuelle.getY());

        //On choisit une deuxieme lettre
        int nombreAleatoire = nombreAleatoire(0,validbuttons.size()-1);

        //On récupere le bouton aléatoirement
        ButtonPerso buttonPerso = validbuttons.get(nombreAleatoire);

        //On ajoute la lettre sélectionnée au mot
        char lettre = buttonPerso.getButton().getText().charAt(0);
        motEncours += lettre;

        //On ajoute ses coordonnées a la liste
        coordList.add(buttonPerso.getCoord());

        return buttonPerso.getCoord();

    }

    public int nombreAleatoire(int min, int max){
        return min + (int)(Math.random() * (max - min));
    }

}
