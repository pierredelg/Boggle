package boggle.config;

import boggle.config.exceptions.InvalidNameConfig;
import boggle.mots.ArbreLexicalLudo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ChargerConfig {

    private static int nombreJoueur;
    private static int taillePlateau;
    private static int tailleMinMot;
    private static String des;
    private static String dictionnaire;
    private static ArbreLexicalLudo arbreLexicalLudo;
    private static int[] points;
    private static int timerSeconde;

    public ChargerConfig() throws IOException {
        lireConfig();
    }

    private static void lireConfig() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader("config/regles-4x4.config"));
        String ligne = "";

        while ((ligne = bufferedReader.readLine()) != null) {

            String[] split = ligne.split(":");

            switch (split[0].trim()) {

                case "nombre-joueur":
                    nombreJoueur = Integer.parseInt(split[1].trim());
                    break;
                case "taille-plateau":
                    int tailleP = Integer.parseInt(split[1].trim()) ;
                    taillePlateau = (tailleP > 10) ? 5 : tailleP;
                    break;
                case "taille-min-mot":
                    int tailleM = Integer.parseInt(split[1].trim());
                    tailleMinMot = (tailleM < 3) ? 3 : tailleM;
                    break;
                case "des":
                    des = split[1].trim();
                    break;
                case "dictionnaire":
                    dictionnaire = split[1].trim();
                    arbreLexicalLudo = ArbreLexicalLudo.lireMots("config/"+dictionnaire);
                    break;
                case "points":
                    split = split[1].split(",");
                    points = new int[split.length];
                    for (int i = 0; i < split.length; i++) {
                        points[i] = Integer.parseInt(split[i].trim());
                    }
                    break;
                case "timer-seconde":
                    int temp = Integer.parseInt(split[1].trim());
                    timerSeconde = (temp > 300) ? 180 : temp;
                    break;
                default:
                    throw new InvalidNameConfig("Paramètre de configuration non connue");
            }
        }
    }

    public static int getNombreJoueur() {
        return nombreJoueur;
    }

    public static void setNombreJoueur(int nombreJoueur) {
		ChargerConfig.nombreJoueur = nombreJoueur;
	}

	public static void setTaillePlateau(int taillePlateau) {
		ChargerConfig.taillePlateau = taillePlateau;
	}

	public static void setTailleMinMot(int tailleMinMot) {
		ChargerConfig.tailleMinMot = tailleMinMot;
	}

	public static void setDes(String des) {
		ChargerConfig.des = des;
	}

	public static void setDictionnaire(String dictionnaire) {
		ChargerConfig.dictionnaire = dictionnaire;
	}

	public static void setArbreLexicalLudo(ArbreLexicalLudo arbreLexicalLudo) {
		ChargerConfig.arbreLexicalLudo = arbreLexicalLudo;
	}

	public static void setPoints(int[] points) {
		ChargerConfig.points = points;
	}

	public static void setTimerSeconde(int timerSeconde) {
		ChargerConfig.timerSeconde = timerSeconde;
	}

	public static int getTaillePlateau() {
        return taillePlateau;
    }

    public static int getTailleMinMot() {
        return tailleMinMot;
    }

    public static String getDes() {
        return des;
    }

    public static String getDictionnaire() {
        return dictionnaire;
    }

    public static int[] getPoints() {
        return points;
    }

    public static ArbreLexicalLudo getArbreLexicalLudo() {
        return arbreLexicalLudo;
    }

    public static int getTimerSeconde() {
        return timerSeconde;
    }
}
