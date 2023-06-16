import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;

public class WordCounter {

    public static void main(String[] args) {
        //Get txt path
        String path = System.getProperty("user.dir")+"/src/source/text/example.txt";
        //String path = System.getProperty("user.dir")+"/src/source/text/book.txt";
        System.out.println(path);

        //Necessary to read files
        FileReader file;
        BufferedReader reader;

        //Map where words will be save
        HashMap<String, Integer> mapWords = new HashMap<>();

        //Variables
        String line;
        int amount;
        int totalAmount = 0;

        try {

            file = new FileReader(path);
            if (file.ready()){
                reader = new BufferedReader(file);
                //Keep reading if the txt still have lines
                while ((line = reader.readLine()) != null){
                    //Lowercase all lines
                    line = line.toLowerCase();
                    //If the txt have . | , | ( | ) change for ""
                    line = line.replaceAll("[\\.\\,\\(\\)]", "");
                    //Save into array single words
                    String[] wordsArray = line.split(" ");
                    //Iterate into every element of wordsArray
                    for (String word : wordsArray) {
                        //If mapWords have the word, get current value of the word and add 1, else 1
                        amount = mapWords.containsKey(word) ? mapWords.get(word) + 1 : 1;
                        mapWords.put(word, amount);
                    }
                }
            }else{
                System.out.println("Error! The file wasn't ready");
            }
        }catch (Exception e){
            System.out.println("Error !"+e.getMessage());
        }
        //Sort map by words
        mapWords.entrySet().stream().sorted(Map.Entry.comparingByValue());
        //Print mapWords
        for (HashMap.Entry<String, Integer> space : mapWords.entrySet()) {
            System.out.printf("Word: '%s' | Amount: %d\n", space.getKey(), space.getValue());
            totalAmount = totalAmount + space.getValue();
        }

        //mapWords.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach();

        System.out.println();
        System.out.println("Total different words: "+mapWords.size());
        System.out.println("Total words: "+totalAmount);
    }
}