package boggle.mots;

import boggle.config.ChargerConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Config {
    public String nomFichierDes = "config/"+ ChargerConfig.getDes();

    public List<De> deList = new ArrayList<>();

    public Config() {
        chargerConfigDe();
    }

    //On charge les config de chaques dés avec laquelle on va créer le dé
    //et le lancer
    public List<De> chargerConfigDe(){
        System.out.println(nomFichierDes);
        File file = new File(nomFichierDes);

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String ligne = "";

            while((ligne = bufferedReader.readLine()) != null){

                String[] split = ligne.split(";");

                Character [] characters = new Character[split.length];

                for (int i = 0 ; i < split.length ; i++){
                    characters[i] = split[i].charAt(0);
                }
                De de2 = new De(characters);
                de2.lancerDe();
                this.deList.add(de2);
            }


            bufferedReader.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return this.deList;
    }

}
