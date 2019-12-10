package boggle.mots;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

/** La classe ArbreLexical permet de stocker de façon compacte et
 * d'accéder rapidement à un ensemble de mots.*/
public class ArbreLexicalPierre {
    public static final int TAILLE_ALPHABET = 26 ;
    private boolean estMot ; // vrai si le noeud courant est la fin d'un mot valide
    private ArbreLexicalPierre[] fils = new ArbreLexicalPierre[TAILLE_ALPHABET] ; // les sous-arbres


    /** Indique si le noeud courant est situé à l'extrémité d'un mot
     * valide */
    public boolean estMot() { return estMot ; }
    
    
    /** Place le mot spécifié dans l'arbre
     * @return <code>true</code> si le mot a été ajouté,
     * <code>false</code> sinon*/
    public boolean ajouter(String word) {

        if(word.length() > 0) {
            //on récupere la premiere lettre en minuscule
            Character lettre = word.toLowerCase().charAt(0);

            // On enleve la premiere lettre du mot
            word = word.substring(1);

            //On la compare par rapport à la lettre a pour avoir l'indice dans le tableau
            int indice = lettre.compareTo('a');

            //On vérifie que l'indice ne dépasse pas le tableau
            if (indice >= 0 && indice < TAILLE_ALPHABET) {
                System.out.println("ajout lettre = " + lettre);
                this.fils[indice] = new ArbreLexicalPierre();
                if(word.length() == 1){
                    this.fils[indice].estMot = true;
                }
                this.fils[indice].ajouter(word);
                return true;
            }
        }
       return false;
    }

    /** Teste si l'arbre lexical contient le mot spécifié.
        @return <code>true</code> si <code>o</code> est un mot
        (String) contenu dans l'arbre, <code>false</code> si
        <code>o</code> n'est pas une instance de String ou si le mot
        n'est pas dans l'arbre lexical. */
    public boolean contient(String word) {

        if(word.length() > 0) {

            //on récupere la premiere lettre en minuscule
            char lettreMot = word.toLowerCase().charAt(0);

            // On enleve la premiere lettre du mot
            word = word.substring(1);

            //On parcourt le tableau
            for (int i = 0; i < this.fils.length; i++) {
                //quand l'arbre n'est pas null
                if (this.fils[i] != null) {

                    char lettre = (char) (i + 97);
                    if (lettre == lettreMot) {
                        System.out.println("lettre " +lettre + " dans mot "+ word);
                        if(word.length() == 1 && this.fils[i].estMot()){
                            System.out.println("fin");
                            return true;
                        }
                        return this.fils[i].contient(word);
                    }
                }
            }
        }
        return false;
    }

    /** Ajoute à la liste <code>resultat<code> tous les mots de
     * l'arbre commençant par le préfixe spécifié. 
     * @return <code>true</code> si <code>resultat</code> a été
     * modifié, <code>false</code> sinon.*/
    public boolean motsCommencantPar(String prefixe, List<String> resultat) {


        return false ;
    }
    
    /** Crée un arbre lexical qui contient tous les mots du fichier
     * spécifié. */
    public static ArbreLexicalPierre lireMots(String fichier) {

        File file = new File(fichier);
        ArbreLexicalPierre arbreLexical = new ArbreLexicalPierre();

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String ligne;
            while((ligne = bufferedReader.readLine()) != null) {
                arbreLexical.ajouter(ligne);
            }

            bufferedReader.close();

            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        return arbreLexical;
    }

    public String toString(){
       String resultat = "";
       //On parcourt le tableau
      for(int i = 0 ; i < this.fils.length; i++){
          //quand l'arbre n'est pas null
          if(this.fils[i] != null) {
              char lettre = (char) (i + 97);
              resultat += lettre + this.fils[i].toString();
          }
      }
       return resultat;
    }

    public static void main(String[] args) {



        ArbreLexicalPierre arbreLexical = new ArbreLexicalPierre();

        //ArbreLexicalPierre arbreLexical = lireMots("config/dict-fr.txt");
        // System.out.println("contient lombarthroses = " + arbreLexical.contient("lombarthroses"));


        System.out.println();
        System.out.println("debut ajout");
        arbreLexical.ajouter("marche");
        System.out.println("fin ajout");
        System.out.println();
        System.out.println("debut ajout");
        arbreLexical.ajouter("debut");
        System.out.println("fin ajout");
        System.out.println();
        System.out.println("debut contient");
        System.out.println("contient marche = " + arbreLexical.contient("marche"));
        System.out.println("fin contient");
        System.out.println();
        System.out.println("contient debut = " + arbreLexical.contient("debut"));
        System.out.println("contient rien = " + arbreLexical.contient("rien"));
        System.out.println("contient debuter = " + arbreLexical.contient("debuter"));

    }
}
