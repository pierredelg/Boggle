package boggle.mots;

//https://www.enseignement.polytechnique.fr/informatique/profs/Philippe.Chassignet/00-01/TD/td_6.html

public class ArbreLexical2 {

        private char c;                 // le caractére
        private ArbreLexical2 suffixes;  // les suites possibles de ce caractére
        private ArbreLexical2 suivants;  // les autres dictionnaires de méme niveau
        private boolean estMot;

        // constructeur
        ArbreLexical2(char c, ArbreLexical2 suffixes, ArbreLexical2 suivants) {
            // recopie les paramétres dans les champs de l'objet
            this.c = c;
            this.suffixes = suffixes;
            this.suivants = suivants;
        }

        // pseudo-constructeur
        public ArbreLexical2 d(char c, ArbreLexical2 suffixes, ArbreLexical2 suivants) {
            return new ArbreLexical2(c, suffixes, suivants);
        }

        public ArbreLexical2(char c){
            this.c = c;
        }

        public ArbreLexical2(){}

        // construit explicitement un dictionnaire
        public void ajouter(String mot) {

            if(this.c != 0 && mot.charAt(0) != this.c){
                ajoutNiveau(mot);
            }else{
                ajoutLigne(mot);
            }
        }

        private void ajoutNiveau(String mot){

            if(!mot.isEmpty()){
                this.suffixes = new ArbreLexical2();
                this.suffixes.ajoutLigne(mot);
            }
        }

        private void ajoutLigne(String mot){
            if(!mot.isEmpty()) {
                estMot = mot.length() == 1;
                char c = mot.charAt(0);
                this.c = c;
                this.suivants = new ArbreLexical2();
                this.suivants.ajoutLigne(mot.substring(1));
            }
        }

        public boolean charExist(char c){

            while(this.suffixes != null) {
                return this.suffixes.c == c?true: this.suffixes.suffixes.charExist(c);
            }
            return false;
        }

        public String toString(){

            String result = "";
            result +=  this.suivants != null?""+this.c+this.suivants:"";
            result += this.suffixes != null?""+this.suffixes.c+this.suffixes:"";
            return result;

        }

        public static void main(String args[]){

            ArbreLexical2 arbre = new ArbreLexical2();
            arbre.ajouter("mot");
            arbre.ajouter("orage");
            //arbre.ajouter("");
            System.out.println(arbre);
        }

}
