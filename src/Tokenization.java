import java.io.*;
import java.lang.String;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Tokenization {
    public static void main(String[] args) throws Exception {

        //read the stopWord.txt and save it into a list
        List<String> stopList = new ArrayList<>();
        readTextFile(stopList, "stopwords.txt");

        //read the part A txt file
        List<String> readPartALines = new ArrayList<>();
        readTextFile(readPartALines, "tokenization-input-part-A.txt");

        readPartALines.clear();
        wordAbbreviations(readPartALines, readPartALines);

        splitAndStopWord(readPartALines);

        /*
        for(String xx: stopList)
            System.out.println(xx);
        */



    }


    public static void splitAndStopWord(List<String> notSpilt){
        for(String str: notSpilt){
            String[] wordTemp = str.split(" ");
        }


    }

    //Consider abbreviations such as "U.S.A." as one term: "USA" --
    //remove all the punctuation
    public static void wordAbbreviations(List<String> haveDot, List<String> notDot){
        for(String rawLine:haveDot){
            String tempE = rawLine;
            Matcher matchStringD = Pattern.compile("\\b(?:[a-zA-Z]\\.){2,}").matcher(rawLine);
            while (matchStringD.find()){
                String str = tempE.replaceAll(matchStringD.group(), matchStringD.group().replace(".", ""));
                str = str.replaceAll("\\p{Punct}", " ");
                str = str.replaceAll("‘", " ");
                str = str.replaceAll("\\s+"," ").trim();
                String[] wordTemp = str.split(" ");
                for(String temp: wordTemp)
                    notDot.add(temp);
            }
        }
    }

    //read the file and conveert to lower case
    public static void readTextFile(List<String> readByLines, String path){
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            lines.forEach((e) -> readByLines.add(e.toLowerCase()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






}
