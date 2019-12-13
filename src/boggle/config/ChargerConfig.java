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
                    taillePlateau = Integer.parseInt(split[1].trim());
                    break;
                case "taille-min-mot":
                    tailleMinMot = Integer.parseInt(split[1].trim());
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
                default:
                    throw new InvalidNameConfig("Paramètre de configuration non connue");
            }
        }
    }

    public static int getNombreJoueur() {
        return nombreJoueur;
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
}
