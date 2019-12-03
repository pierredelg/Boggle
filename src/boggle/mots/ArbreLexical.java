package boggle.mots;

import java.util.Arrays;
import java.util.List ;

/** La classe ArbreLexical permet de stocker de façon compacte et
 * d'accéder rapidement à un ensemble de mots.*/
public class ArbreLexical {
    public static final int TAILLE_ALPHABET = 26 ;
    private boolean estMot ; // vrai si le noeud courant est la fin d'un mot valide
    private ArbreLexical[] fils = new ArbreLexical[TAILLE_ALPHABET] ; // les sous-arbres
    private char caractere;
    
    /** Crée un arbre vide (sans aucun mot) */
    public ArbreLexical() {
        this.caractere = ' ';
        //On initialise le tableau d'arbre fils avec toute les lettres de l'alphabet
        char c1 = 'a';
        for(int i = 0;i<fils.length;i++){
            fils[i] = new ArbreLexical(c1);
            c1++;
        }
    }
    public ArbreLexical(char caractere){
        this.caractere = caractere;
    }

    /** Indique si le noeud courant est situé à l'extrémité d'un mot
     * valide */
    public boolean estMot() { return estMot ; }
    
    
    /** Place le mot spécifié dans l'arbre
     * @return <code>true</code> si le mot a été ajouté,
     * <code>false</code> sinon*/
    public boolean ajouter(String word) {
        //Si le mot est une seule lettre on change le booleen à true
        if(word.length() == 1){
            this.estMot = true;
            return true;
        }
        //On récupere la premiere lettre du mot
        char premiereLettre = word.charAt(0);

        //On recupere l'indice de la lettre dans le tableau d'arbre fils
        // -1 si la lettre n'est pas présente
        int indiceFils = lettreContenuDansFils(premiereLettre);

        if(indiceFils == -1){
            //le tableau fils est null on crée un nouveau tableau
            if(this.fils == null){
                this.fils = new ArbreLexical[1];
            }else {
                //Sinon on agrandi le tableau existant
                this.fils = agrandiTab(this.fils);
            }
            //on ajoute la lettre au premier membre
            this.fils[0] = new ArbreLexical(premiereLettre);
            indiceFils = 0;
        }
        //On ajoute la fin du mot sur l'arbre au bon indice
        return this.fils[indiceFils].ajouter(word.substring(1));
    }


    //Méthode qui permet de retourner l'indice dans le tableau des fils
    // où se trouve la lettre donnée en parametre
    private int lettreContenuDansFils (char c){
        if(this.fils != null) {
            for (int i = 0; i < fils.length; i++) {
                if (fils[i] != null && fils[i].caractere == c) {
                    return i;
                }
            }
        }
        return -1;
    }

    //Méthode permettant d'agrandir le tableau en laissant libre le premier élément
    private ArbreLexical [] agrandiTab(ArbreLexical [] tab){
        ArbreLexical [] resultat;
        resultat = new ArbreLexical[tab.length + 1];
        for(int i = 0, j = 1 ; i < tab.length ; i++ , j++){
            resultat[j] = tab[i];
        }
        return resultat;
    }

    /** Teste si l'arbre lexical contient le mot spécifié.
        @return <code>true</code> si <code>o</code> est un mot
        (String) contenu dans l'arbre, <code>false</code> si
        <code>o</code> n'est pas une instance de String ou si le mot
        n'est pas dans l'arbre lexical. */
    public boolean contient(String word) {
        // à compléter
        return false ;
    }

    /** Ajoute à la liste <code>resultat<code> tous les mots de
     * l'arbre commençant par le préfixe spécifié. 
     * @return <code>true</code> si <code>resultat</code> a été
     * modifié, <code>false</code> sinon.*/
    public boolean motsCommencantPar(String prefixe, List<String> resultat) {
        // à compléter
        return false ;
    }
    
    /** Crée un arbre lexical qui contient tous les mots du fichier
     * spécifié. */
    public static ArbreLexical lireMots(String fichier) {
        // à compléter
        return null ;
    }
    private boolean estUneFeuille(){
        return this.fils == null || this.fils.length == 0;
    }
    public String toString(){

        if(this.estUneFeuille()){
           return "" + this.caractere;
       }

       String resultat = "";
       for(ArbreLexical arbreLexical : fils){
           if (arbreLexical != null) {
               resultat += arbreLexical.toString();
           }
       }
       return resultat;
    }

    public static void main(String[] args) {
        ArbreLexical arbreLexical = new ArbreLexical();
        System.out.println(arbreLexical);
        arbreLexical.ajouter("marche");
        System.out.println(arbreLexical);

    }
}
