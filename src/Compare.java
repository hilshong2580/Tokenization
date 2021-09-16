import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Compare {


    public static void main(String[] args) throws IOException {

        List<String> A = new ArrayList<String>();
        try (Stream<String> stopLines = Files.lines(Paths.get("C:\\Users\\tommy\\Desktop\\446\\Project 1\\bText.txt"))) {
            stopLines.forEach((A::add));
        }

        List<String> B = new ArrayList<String>();
        try (Stream<String> stopLines = Files.lines(Paths.get("C:\\Users\\tommy\\Desktop\\446\\Project 1\\myText.txt"))) {
            stopLines.forEach(B::add);
        }

        for(String x: A){
            if(!B.contains(x)){
                System.out.println(x);
            }
        }

        boolean isEqual = A.equals(B);
        System.out.println(isEqual);

    }
}
