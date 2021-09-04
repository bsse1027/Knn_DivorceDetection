import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Shuffle {

    int countLine=0;

    public void ShuffleTxt() throws IOException {

        BufferedReader input =new BufferedReader(new FileReader("iris.txt"));
        BufferedWriter shuffledOut=new BufferedWriter(new FileWriter("iris2.txt"));
        Scanner scanner1 = new Scanner(new FileInputStream("iris.txt"));
        ArrayList<String> inputTake=new ArrayList<String>();


        while (input.readLine()!=null)
        {
            countLine++;
        }

        for (int i=0;i<countLine;i++)
        {
            String s= scanner1.next();
            inputTake.add(s);


        }

        Collections.shuffle(inputTake);

        for(int i=0;i<inputTake.size();i++)
        {
            shuffledOut.write(inputTake.get(i));
            shuffledOut.newLine();
            shuffledOut.flush();

        }


    }

    public int getCountLine() {
        return countLine;
    }
}
