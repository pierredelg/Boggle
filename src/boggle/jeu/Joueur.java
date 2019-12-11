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

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void updateScore(int score) {
		this.score += score;
	}

	@Override
	public String toString() {
		return "Le joueur " + name + " obtient un score de " + score + " points";
	}
}
