import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConfusionMatrix {
    public void makeConfMat(int countLine) throws FileNotFoundException {
        int p1,p2,p3,p4,p5,p6,className;
        Scanner testScan =new Scanner(new File("DivorceCC.txt"));
        Scanner allScan =new Scanner(new File("divorce2.txt"));
        ArrayList<Points> finalPoints =new ArrayList<Points>();
        ArrayList<Points> allPoints =new ArrayList<Points>();

        double[] confusionMat={0,0,0,0};//TP,TN,FP,FN
        testScan.useDelimiter(",");

        for(int i=1;i<=countLine;i++){
            /*System.out.println(readCount);*/


            p1=testScan.nextInt();
            p2=testScan.nextInt();
            p3=testScan.nextInt();
            p4=testScan.nextInt();
            p5=testScan.nextInt();
            p6=testScan.nextInt();
            className= Integer.parseInt(testScan.nextLine().substring(1));

            //System.out.println(p1 + p2 + p3 + p4 + className);

            Points newPoint=new Points(p1,p2,p3,p4,p5,p6,className);
//            readCountCC++;
            finalPoints.add(newPoint);

        }

//        System.out.println(readCountCC);

        testScan.close();
        allScan.useDelimiter(",");

        for(int i=1;i<=countLine;i++){
            /*System.out.println(readCount);*/


            p1=allScan.nextInt();
            p2=allScan.nextInt();
            p3=allScan.nextInt();
            p4=allScan.nextInt();
            p5=allScan.nextInt();
            p6=allScan.nextInt();
            className= Integer.parseInt(allScan.nextLine().substring(1));

            //System.out.println(p1 + p2 + p3 + p4 + className);

            Points newPoint=new Points(p1,p2,p3,p4,p5,p6,className);
//            readCountCC++;
            allPoints.add(newPoint);

        }

//        System.out.println(readCountCC);

        allScan.close();

        for(int x=0;x<countLine;x++)
        {
            if(allPoints.get(x).getClassName()==1&&finalPoints.get(x).getClassName()==1)
            {
                confusionMat[0]++;
            }
            else if(allPoints.get(x).getClassName()==0&&finalPoints.get(x).getClassName()==0)
            {
                confusionMat[1]++;
            }
            else if(allPoints.get(x).getClassName()==0&&finalPoints.get(x).getClassName()==1)
            {
                confusionMat[2]++;
            }
            else if(allPoints.get(x).getClassName()==1&&finalPoints.get(x).getClassName()==0)
            {
                confusionMat[3]++;
            }
        }

        double accuracy = (confusionMat[0]+confusionMat[1])/countLine;
        double recall = confusionMat[0]/(confusionMat[0]+confusionMat[3]);
        double precision = confusionMat[0]/(confusionMat[0]+confusionMat[2]);
        double fMeasure = (2.0*precision*recall)/(precision+recall);

        System.out.println("------Final Stats------");
        System.out.println("Accuracy = "+ accuracy*100+"%");
        System.out.println("Recall = "+ recall*100+"%");
        System.out.println("Precision = "+ precision*100+"%");
        System.out.println("F-Measure = "+ fMeasure*100+"%");


    }
}
