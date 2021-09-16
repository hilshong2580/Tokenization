//"C:\\Users\\tommy\\Desktop\\446\\Project 1\\tokenization-input-part-B.txt"

import java.io.*;
import java.lang.String;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.lang.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class temp {

    public static void main(String[] args) throws IOException {
        List<String> stopList = new ArrayList<>();
        List<String> ppppp = new ArrayList<>();

        try (Stream<String> stopLines = Files.lines(Paths.get("stopwords.txt"))) {
            stopLines.forEach(stopList::add);
        }

        HashMap<String, Integer> wordMap = new HashMap<>();


        List<String> plpl = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Paths.get("tokenization-input-part-B.txt"))) {
            lines.forEach(plpl::add);
        }

        int c = 0;
        int d = 0;

        for(String e: plpl){
            c++;

            //covert the word to Lower Case from reading line
                e = e.toLowerCase();
                List<String> dotList = new ArrayList<>();

                Matcher matchStringD = Pattern.compile("\\b(?:[a-zA-Z]\\.){2,}").matcher(e);
                while (matchStringD.find())
                    dotList.add(matchStringD.group());
                String tempE = e;
                for (String s : dotList) {
                    tempE = tempE.replaceAll(s, s.replace(".", ""));

                }

                //use space instead to all punctuation, then add in to the String array
                // remove "‘" from string which is not work for "\\p{Punct}"
                // remove the extra space
                //split the word by space

                String removePunct = tempE.replaceAll("\\p{Punct}", " ");
                removePunct = removePunct.replaceAll("‘", " ");
                removePunct = removePunct.replaceAll("\\s+"," ").trim();


/*
                String patternString = "\\b(" + "bed" + ")\\b";
                Pattern pattern = Pattern.compile(patternString);
                Matcher matcher = pattern.matcher(removePunct);

                while (matcher.find()) {
                    System.out.println(c+": "+" d: "+d+"  " +removePunct);
                    d++;
                }
*/





                String[] wordTemp = removePunct.split(" ");


                //do the stopword checking: if the ArrayList contains word, do not add to list
                //do the stemming check: return a word that pass from 1a to 1b to main
                for (String separated :wordTemp){
                    if(separated.length()>0){
                        /*
                            if(separated.equals("beds")){
                                System.out.println( c+" beds");
                        }

                        if(separated.equals("ships")){
                            System.out.println( c+" ships");
                        }
                         if(separated.equals("bed"))
                                System.out.println(c + " bed");

                        */

                        if(!stopList.contains(separated)){
                            separated = OneB(OneA(separated));
                            //System.out.println(separated);

                            ppppp.add(separated);
                            //System.out.println(separated);
                            if(wordMap.containsKey(separated)){
                                wordMap.put(separated, wordMap.get(separated) + 1);
                            } else
                               wordMap.put(separated, 1);
                        }
                    }
                }

        }


        //sort Value in Map
        //print the first 200 words in function
        HashMap<String, Integer> wordMapFinal = sortMap(wordMap);
        //wordMapFinal.forEach((key, value) -> System.out.println(key + " " + value));

/*
        Optional<String> firstKey = wordMapFinal.keySet().stream().findFirst();
        if (firstKey.isPresent()) {
            String key = firstKey.get();
            int ch1 = (int) key.charAt(0);
            System.out.println("  ch1 "+ch1);
        }

        for(int i = 0; i < 200; i++){
            System.out.println(ppppp.get(i));
        }
*/

        //System.out.println(OneB(OneA("whales")));
        System.out.println(" ====================================");



    }

    //sort and print most 200 frequency word the HashMap
    public static HashMap<String, Integer> sortMap(HashMap<String, Integer> rawData){

        System.out.println(rawData.get("ships"));

        return rawData.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue()))
                .limit(200)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new
                ));
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

                String temp = str;

            if(str.matches("(.*)[aeiou](.*)ed")){
                str = str.substring( 0 , str.length() - 2);
            } else if(str.matches("(.*)[aeiou](.*)edly")){
                str = str.substring( 0 , str.length() - 4);
            }
            else if(str.matches("(.*)[aeiou](.*)ingly")){
                str = str.substring( 0 , str.length() - 5);
            } else if(str.matches("(.*)[aeiou](.*)ing")){
                str = str.substring( 0 , str.length() - 3);
            }

            if(str.equals(temp)){
                return str;
            }
            else {
                if ((str.endsWith("at")) || (str.endsWith("bl")) || (str.endsWith("iz"))) {
                    return (str + "e");
                } else if (!((str.endsWith("ll")) || (str.endsWith("ss")) || (str.endsWith("zz")))
                        && str.charAt(str.length() - 1) == str.charAt(str.length() - 2)) {
                    return str.substring(0, str.length() - 1);
                } else if (str.length() < 4) {
                    return (str + "e");
                }
            }

        }
        return str;
    }

    //stemming the word for Part A 1a
    public static String OneA(String str){

/*
if(str.equals("beds")){
            System.out.println(" beds beginning");
        }
* */


        if ((str.endsWith("us")) || (str.endsWith("ss"))) {
            return str;
        } else if (str.endsWith("sses")) {
            return str.replaceAll("(sses)$", "ss");
        } else if (str.endsWith("ied") || str.endsWith("ies")) {
            if (str.length() == 4)
                return str.substring(0, str.length() - 1);
            else
                return str.substring(0, str.length() - 2);
        } else if (str.endsWith("s") && str.length()> 1){

            String temp = str;
            str = str.substring(0, str.length() - 1);

            if(str.matches("[aeiou]$"))
                str = str.substring(0, str.length() - 1);
            else
                return str;

            if(str.matches("[aeiou]"))
                return temp.substring(0, temp.length() - 1);
            else
                return str;



            //!(str.matches("(.*)[aeiou]s"))
            /*
            if(str.equals("whales")) {
                System.out.println(" whales set");
            }
            if(str.matches("(.*)[aeiou](.*)s")){
                if(str.equals("whales")) {
                    System.out.println(" whales inner");
                    System.out.println("  str  " + str.substring(0, str.length() - 1));
                }
            */

            }


        return str;
    }
}
