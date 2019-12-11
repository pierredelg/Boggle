package boggle.jeu;

public class Tour{

    private Joueur[] joueur;
    private int score;
    private Timer timer;

    public Tour(int nbJoueur){
        joueur =  new Joueur[nbJoueur];
    }
}
