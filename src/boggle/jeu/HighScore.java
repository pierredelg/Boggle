package boggle.jeu;

import java.io.*;

public class HighScore implements Serializable {
	private static final long serialVersionUID = 1L;
	private int score;
	private String name;

	public HighScore(int s, String n) {
		score = s;
		setName(n);
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int compareTo(HighScore h) {
		return new Integer(this.score).compareTo(h.score);
	}

	private static void initializeFile() {
		HighScore[] h = { new HighScore(0, " "), new HighScore(0,  " "), new HighScore(0,  " "),
				new HighScore(0, " "), new HighScore(0,  " "), new HighScore(0,  " "), new HighScore(0,  " "),
				new HighScore(0, " "), new HighScore(0,  " "), new HighScore(0,  " ") };
		try {
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("config/HighScores.dat"));
			o.writeObject(h);
			o.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//lecture du fichier 
	public static HighScore[] getHighScores() {
		if (!new File("config/HighScores.dat").exists())
			initializeFile();
		try {
			ObjectInputStream o = new ObjectInputStream(new FileInputStream("config/HighScores.dat"));
			HighScore[] h = (HighScore[]) o.readObject();
			return h;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Ajoute un nouveau HighScore au fichier .dat et maintient l'ordre
	public static void addHighScore(HighScore h) {
		HighScore[] highScores = getHighScores();
		highScores[highScores.length - 1] = h;
		for (int i = highScores.length - 2; i >= 0; i--) {
			if (highScores[i + 1].compareTo(highScores[i]) > 0) {
				HighScore temp = highScores[i];
				highScores[i] = highScores[i + 1];
				highScores[i + 1] = temp;
			}
		}
		try {
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("config/HighScores.dat"));
			o.writeObject(highScores);
			o.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}