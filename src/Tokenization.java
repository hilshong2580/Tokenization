
/*
*
*Done:  -Consider abbreviations such as "U.S.A." as one term: "USA" --
*       there are equivalents in *input-part-A.txt,* below. An abbreviation may be mixed case.
*       考慮使用縮寫詞，例如“U.S.A.” 作為一個術語：“美國”——在下面的 input-part-A.txt 中有等價物。 縮寫可能是混合情況。
*
*
* */



import java.io.*;
import java.lang.String;

public class Tokenization {

    public static void main(String[] args) throws Exception {
        System.out.println(" ");
        /*
        File file = new File ("C:\\Users\\tommy\\Desktop\\446\\Project 1\\stopwords.txt");
        BufferedReader buf = new BufferedReader(new FileReader(file));
        String str;
        while((str = buf.readLine()) != null)
        System.out.println(str);

        FileReader file = new FileReader("C:\\Users\\tommy\\Desktop\\446\\Project 1\\tokenization-input-part-A.txt");
        int i;
        while((i= file.read()) != -1){
            if((char) i == '.')
                i++;
            else if((char) i == ','){
                int temp = i;
                if()

            }
            else
                System.out.print((char) i);
        }



        FileReader file = new FileReader("C:\\Users\\tommy\\Desktop\\446\\Project 1\\tokenization-input-part-A.txt");
        BufferedReader buf = new BufferedReader(file);
        String line;
        while((line = buf.readLine()) != null){
            String[] words = line.split("[ :/,]");
            for(String x : words){
                System.out.print(x+" ");
            }
        }

        File file = new File ("C:\\\\Users\\\\tommy\\\\Desktop\\\\446\\\\Project 1\\\\tokenization-input-part-A.txt");
        BufferedReader buf = new BufferedReader(new FileReader(file));
        String str;
        while((str = buf.readLine()) != null){
            char[] ch = str.toCharArray();
        }
                    System.out.println(" ");
                    j = file.read();
                    System.out.println("(char) i = " +((char) i)+" and j = "+((char) j));
                    System.out.println(" ");
        * */

        FileReader file = new FileReader("C:\\Users\\tommy\\Desktop\\446\\Project 1\\tokenization-input-part-A.txt");
        int i;
        int j = 'a';
        while((i= file.read()) != -1){

            if((char) i == '.') {
                i++;
            }
            else if ((char) i == '\n'){
                System.out.println(" ");
            }
            else if(!Character.isLetter((char) i)){
                if((char) j != ' '){
                    System.out.print(" ");
                    j = ' ';
                }
            }
            else{
                System.out.print(Character.toLowerCase((char) i));
                j = i;
            }

        }


        //System.out.println("n");
        System.out.println(" ");
    }
}
