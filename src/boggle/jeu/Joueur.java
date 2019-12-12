package boggle.jeu;

public class Joueur {

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

		switch (taille){
			case 3 :
				this.score += 1;
				break;
			case 4:
				this.score += 1;
				break;
			case 5:
				this.score += 2;
				break;
			case 6:
				this.score += 3;
				break;
			case 7:
				this.score += 5;
				break;
			default:
				if(taille > 7)
					this.score += 11;
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
