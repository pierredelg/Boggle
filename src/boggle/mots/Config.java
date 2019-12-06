package boggle.mots;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Config {
    public String nomFichierCondfig = "config/des-4x4.csv";

    public List<De2> de2List = new ArrayList<>();

    public Config() {
        chargerConfigDe();
    }

    //On charge les config de chaques dés avec laquelle on va créer le dé
    //et le lancer
    public void chargerConfigDe(){
        File file = new File(nomFichierCondfig);

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String ligne = "";

            while((ligne = bufferedReader.readLine()) != null){

                String[] split = ligne.split(";");

                Character [] characters = new Character[split.length];

                for (int i = 0 ; i < split.length ; i++){
                    characters[i] = split[i].charAt(0);
                }
                De2 de2 = new De2(characters);
                de2.lancerDe();
                this.de2List.add(de2);
            }

            bufferedReader.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
