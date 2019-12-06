package boggle.mots;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class De {


    public static List<String> lireFichierCsv(){

        File file = new File("config/des-4x4.csv");
        List<String> characterList = new ArrayList<>();

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String ligne = "";

            while((ligne = bufferedReader.readLine()) != null){
                String [] ligneDe = ligne.split(";");
                characterList.add(ligneDe[(int) Math.round(Math.random() * 5)]);
            }

            bufferedReader.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return characterList;
    }
}
