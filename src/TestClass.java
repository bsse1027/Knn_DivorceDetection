import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TestClass {


    public static void main(String[] args) throws IOException {

        double kFold=10;
        TrainClass Train =new TrainClass();
        Shuffle shuffler=new Shuffle();



        shuffler.ShuffleTxt();
//        System.out.println(shuffler.getCountLine());
        int lineCount=shuffler.getCountLine();
        int startBound=1;

        for (int i=0;i<kFold;i++)
        {
            Train.Start(startBound,kFold);
            //Scanner scanner = new Scanner(new FileInputStream("iris.txt"));
            startBound= (int) (startBound+(lineCount/kFold));




        }

        System.out.println("Average Accuracy = " + Train.averageAccuracy + " %");











    }



}
