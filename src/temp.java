//"C:\\Users\\tommy\\Desktop\\446\\Project 1\\tokenization-input-part-B.txt"

import java.io.*;
import java.lang.String;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://blog.softhints.com/java-regex-extract-abbriviations/
// https://stackabuse.com/how-to-sort-a-hashmap-by-value-in-java/

public class temp {

    public static void main(String[] args) throws IOException {

        List<String> stopList = new ArrayList<>();
        try (Stream<String> stopLines = Files.lines(Paths.get("C:\\Users\\tommy\\Desktop\\446\\Project 1\\stopwords.txt"))) {
            stopLines.forEach(stopList::add);
        }


        HashMap<String, Integer> wordMap = new HashMap<>();

        try {
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get("C:\\Users\\tommy\\Desktop\\446\\tokenization-input-part-B.txt")));
            for(String x: lines){
                System.out.println(x);
            }

        }
        catch (IOException e) {
            // Handle a potential exception
            System.out.println("error");
        }

        try (Stream<String> lines = Files.lines(Paths.get("C:\\Users\\tommy\\Desktop\\446\\tokenization-input-part-B.txt"))) {
            lines.forEach((e) -> {

                //covert the word to Lower Case from reading line
                //e = e.toLowerCase();
                //System.out.println(e);

/*
                String tempE2 = e;
                String[] yesWord = tempE2.split("[\\p{Punct}\\s]+");
                for (String separated :yesWord) {

                    if(separated.equals("yes"))
                        System.out.println("separated separated "+separated);

                }
*/



                Pattern reStringD = Pattern.compile("\\b(?:[a-zA-Z]\\.){2,}" );
                Matcher matchStringD = reStringD.matcher(e);
                List<String> dotList = new ArrayList<>();
                while (matchStringD.find())
                    dotList.add(matchStringD.group());
                String tempE = e;
                for (String s : dotList)
                    tempE = tempE.replaceAll(s, s.replace(".",""));



                //use space instead to all punctuation, then add in to the String array
                String[] wordTemp = tempE.split("[\\p{Punct}\\s]+");

                //do the stopword checking: if the ArrayList contains word, do not add to list
                //do the stemming check: return a word that pass from 1a to 1b to main
                for (String separated :wordTemp){

                    if(!stopList.contains(separated)){
                        separated = OneB(OneA(separated));
                        //System.out.println(separated);
                        if(wordMap.containsKey(separated)){
                            wordMap.put(separated, wordMap.get(separated) + 1);
                        } else
                            wordMap.put(separated, 1);

                    }
                }

            });
        }


        //sort Value in Map
        //print the first 200 words in function
        HashMap<String, Integer> wordMapFinal = sortMap(wordMap);



    }

    //sort and print most 200 frequency word the HashMap
    public static HashMap<String, Integer> sortMap(HashMap<String, Integer> rawData){
        int count = 0;

        HashMap<String, Integer> sortedMap = rawData.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue()))
                //.limit(250)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new
                ));


        //sortedMap.entrySet().forEach((e) -> {System.out.println(e.getKey()+ " " +e.getValue()); });

        return sortedMap;
    }

    //stemming the word for Part A 1b
    public static String OneB(String str){

        if((str.endsWith("eed")) || (str.endsWith("eedly"))){

            if((str.endsWith("eedly")) && str.substring( 0 , str.length() - 5).matches("(.*)([aeiou]+)([^aeiou])(.*)")){
                return str.replaceAll("(eedly)$", "ee");
            }
            else if((str.endsWith("eed"))  && str.substring( 0 , str.length() - 3 ).matches("(.*)([aeiou]+)([^aeiou])(.*)")){
                return str.replaceAll("(eed)$", "ee");
            }
        }
        else if((str.endsWith("ed")) || (str.endsWith("edly"))
                || (str.endsWith("ingly")) || (str.endsWith("ing"))){

            //hoping
            if(str.matches("(.*)[aeiou](.*)ed")){
                str = str.substring( 0 , str.length() - 2);
            } else if(str.matches("(.*)[aeiou](.*)edly")){
                str = str.substring( 0 , str.length() - 4);
            } else if(str.matches("(.*)[aeiou](.*)ingly")){
                str = str.substring( 0 , str.length() - 5);
            } else if(str.matches("(.*)[aeiou](.*)ing")){
                str = str.substring( 0 , str.length() - 3);
            }

            if((str.endsWith("at")) || (str.endsWith("bl")) || (str.endsWith("iz"))){
                return (str+"e");
            }
            else if(!((str.endsWith("ll")) || (str.endsWith("ss")) || (str.endsWith("zz")))
                    && str.charAt(str.length() - 1) == str.charAt(str.length() - 2)){
                    return str.substring(0, str.length() - 1);
            }else if(str.length() < 4){
                return (str+"e");
            }
        }
        return str;
    }

    //stemming the word for Part A 1a
    public static String OneA(String str){

        if ((str.endsWith("us")) || (str.endsWith("ss"))) {
            return str;
        } else if (str.endsWith("sses")) {
            return str.replaceAll("(sses)$", "ss");
        } else if (str.endsWith("ied") || str.endsWith("ies")) {
            if (str.length() == 4)
                return str.substring(0, str.length() - 1);
            else
                return str.substring(0, str.length() - 2);
        } else if (!(str.matches("(.*)[aeiou]s")) && str.length()> 1) {
            if(str.charAt(str.length()-1) == 's')
                return str.substring(0, str.length() - 1);
        }

        return str;
    }
}
