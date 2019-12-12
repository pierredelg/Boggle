package boggle.jeu;

public class Tour{

    private Joueur joueur;
    private int score;
    private Timer timer;

    public Tour(Joueur joueur){
        this.joueur =  joueur;
        this.score = 0;
        this.timer = new Timer();
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
