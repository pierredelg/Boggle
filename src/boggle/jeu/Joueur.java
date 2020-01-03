package boggle.jeu;

import boggle.config.ChargerConfig;

public abstract class Joueur {

    private String name;
    private int score = 0;

    public Joueur(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void updateScore(String mot) {

        int taille = mot.length();
        int[] points = ChargerConfig.getPoints();

        switch (taille){
            case 3 :
                this.score += points[0];
                break;
            case 4:
                this.score += points[1];
                break;
            case 5:
                this.score += points[2];
                break;
            case 6:
                this.score += points[3];
                break;
            case 7:
                this.score += points[4];
                break;
            default:
                if(taille > 7)
                    this.score += points[5];
        }
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return name + " obtient un score de " + score + " points";
    }
}
