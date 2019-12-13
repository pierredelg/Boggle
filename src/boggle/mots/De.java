package boggle.mots;

public class De {

    private Character [] possibilites;
    private Character face;

    public De(Character [] possibilite) {
        this.possibilites = possibilite;
        this.face = ' ';
    }

    public Character[] getPossibilites() {
        return possibilites;
    }

    public void setPossibilites(Character[] possibilites) {
        this.possibilites = possibilites;
    }

    public Character getFace() {
        return face;
    }

    public void setFace(Character face) {
        this.face = face;
    }

    public Character lancerDe(){
        Character characterTire = this.possibilites[(int) Math.round(Math.random() * 5)];
        this.face = characterTire;
        return characterTire;
    }
}
