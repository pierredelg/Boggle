package boggle.mots;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe ArbreLexical permet de stocker de façon compacte et
 * d'accéder rapidement à un ensemble de mots.
 */
public class ArbreLexical {

    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int TAILLE_ALPHABET = ALPHABET.length();

    private boolean estMot; // vrai si le noeud courant est la fin d'un mot valide
    private ArbreLexical[] fils = new ArbreLexical[TAILLE_ALPHABET]; // les sous-arbres

    /**
     * Crée un arbre vide (sans aucun mot)
     */
    public ArbreLexical() {
        estMot = false;
    }

    /**
     * Indique si le noeud courant est situé à l'extrémité d'un mot
     * valide
     */
    public boolean estMot() {
        return estMot;
    }

    /**
     * Place le mot spécifié dans l'arbre
     *
     * @return <code>true</code> si le mot a été ajouté,
     * <code>false</code> sinon
     */
    public boolean ajouter(String word) {
        //Si on arrive à la fin du mot on passe le booleen à true et on retourne true
        if (word.length() == 0) {
            estMot = true;
            return true;
        } else {
            //Sinon on crée l'arbre fils avec le mot grace à la méthode suivant
            ArbreLexical fils = suivant(word, true);
            //On ajoute le mot en retirant la premiere lettre à l'arbre fils
            return fils != null && fils.ajouter(word.substring(1));
        }
    }

    /**
     * Teste si l'arbre lexical contient le mot spécifié.
     *
     * @return <code>true</code> si <code>o</code> est un mot
     * (String) contenu dans l'arbre, <code>false</code> si
     * <code>o</code> n'est pas une instance de String ou si le mot
     * n'est pas dans l'arbre lexical.
     */
    public boolean contient(String word) {
        //Si on est à la fin du mot on retourne le booleen estMot
        if (word.length() == 0) {
            return estMot;
        } else {
            //Sinon on récupere l'arbre fils à l'indice de la premiere lettre
            ArbreLexical fils = suivant(word, false);
            //On vérifie si l'arbre fils contient le mot à partir de la lettre suivante
            //Si l'arbre fils est null on retourne faux
            return fils != null && fils.contient(word.substring(1));
        }
    }

    /**
     * Ajoute à la liste <code>resultat<code> tous les mots de
     * l'arbre commençant par le préfixe spécifié.
     *
     * @return <code>true</code> si <code>resultat</code> a été
     * modifié, <code>false</code> sinon.
     */
    public boolean motsCommencantPar(String prefixe, List<String> resultat) {
        if (prefixe == null || prefixe.length() == 0) {
            return explorer("", resultat);
        } else {
            ArbreLexical suivant = suivant(prefixe, false);
            return suivant != null && suivant.motsCommencantPar(prefixe.substring(1), "" + prefixe.charAt(0), resultat);
        }
    }

    private boolean motsCommencantPar(String prefixe, String path, List<String> resultat) {
        if (prefixe.length() == 0) {
            return explorer(path, resultat);
        } else {
            ArbreLexical suivant = suivant(prefixe, false);
            return suivant != null && suivant.motsCommencantPar(prefixe.substring(1), path + prefixe.charAt(0), resultat);
        }
    }

    private boolean explorer(String prefixe, List<String> resultat) {
        boolean modif = false;
        if (estMot) {
            resultat.add(prefixe);
            modif = true;
        }
        ArbreLexical suivant;
        for (int i = 0; i < ALPHABET.length(); i++) {
            suivant = fils[i];
            if (suivant != null) {
                modif |= suivant.explorer(prefixe + ALPHABET.charAt(i), resultat);
            }
        }
        return modif;
    }

    /**
     * return le fils suivant pour le parcours de l'arbre
     *
     * @param word mots a parcourir
     * @param creer booleen pour savoir si on parcourt ou si on crée un arbre
     * @return null si pas une lettre sinon le fils suivant
     */
    private ArbreLexical suivant(String word, boolean creer) {

        //On récupere l'index de la premiere lettre du mot
        int idx = numeroAlphabetic(word);
        //Si l'index vaut -1 c'est que ce n'est pas une lettre
        //On retourne null
        if (idx < 0) {
            return null;
        }
        //Si l'arbre fils n'existe pas dans le tableau alors on le crée
        if (this.fils[idx] == null && creer) {
            this.fils[idx] = new ArbreLexical();
        }
        //On retourne l'arbre fils à l'indice de la lettre
        return this.fils[idx];
    }

    /**
     * return l'ordre alphabetique de la premiere lettre du mot
     *
     * @param word mots a parcourir
     * @return -1 si pas une lettre sinon son ordre alphabetique
     */
    private int numeroAlphabetic(String word) {
        return numeroAlphabetic(word.toUpperCase().charAt(0));
    }

    /**
     * return l'ordre alphabetique de la lettre
     *
     * @param c lettre en majuscule
     * @return -1 si pas une lettre sinon son ordre alphabetique
     */
    private int numeroAlphabetic(char c) {
        return ALPHABET.indexOf(c);
    }

    /**
     * Crée un arbre lexical qui contient tous les mots du fichier
     * spécifié.
     */
    public static ArbreLexical lireMots(String fichier) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(fichier));

        final ArbreLexical arbre = new ArbreLexical();
        String ligne;
        try {
            while((ligne = reader.readLine()) != null){
                arbre.ajouter(ligne);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return arbre;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String dictionnaire = "config/dict-fr.txt";
        ArbreLexical arbre = ArbreLexical.lireMots(dictionnaire);

        List<String> mots = new ArrayList<>();
        arbre.motsCommencantPar("", mots);
        System.out.println(mots);
        mots.clear();
        arbre.motsCommencantPar("T", mots);
        System.out.println(mots);
        mots.clear();

        arbre.motsCommencantPar("Ve", mots);
        System.out.println(mots);
        mots.clear();

        System.out.println(arbre.contient("hizavbchv"));
        System.out.println(arbre.contient("ACCUMSAN"));
        System.out.println(arbre.contient("tapote"));

    }
}

