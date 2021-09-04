import java.io.*;
import java.util.*;

public class TrainClass{

    double accuracy,averageAccuracy,accCount=0;

    public void Start(int a, Double kFold) throws IOException {

        BufferedReader input =new BufferedReader(new FileReader("iris.txt"));

        //ArrayList <ArrayList <String>> shuffle= new ArrayList<ArrayList<String>>();

        ArrayList<Double> pointDistances=new ArrayList<Double>();
        ArrayList<String> pointClass= new ArrayList<String>();
        //Map<Double,String> distanceMap =new HashMap<Double, String>();
        ArrayList<DistanceWIthClass> dWcTemp= new ArrayList<DistanceWIthClass>();
        int equalSum=0;





        Scanner scanner = new Scanner(new FileInputStream("iris.txt"));
        Scanner scanner1 = new Scanner(new FileInputStream("iris.txt"));
        int countLine=0, TestStart, readCount=0,k=5,readCountCC = 0;
        Distance dist =new Distance();
        double pointRead=-1;


        while (input.readLine()!=null)
        {
            countLine++;
        }



//       System.out.println("Total Datas: "+countLine);


        Scanner shuffleScan =new Scanner(new File("iris2.txt"));


        //int max=countLine-(countLine/kFold), min=1;

        TestStart =a;

        //System.out.println(TestStart);

        int TestBound= (int) (TestStart +(countLine/kFold)-1);

        //System.out.println(TestBound);


        double p1,p2,p3,p4;
        String className;

        ArrayList<Points> trainPoints =new ArrayList<Points>();
        ArrayList<Points> testPoints =new ArrayList<Points>();
        ArrayList<Points> testPointsCC =new ArrayList<Points>();


        shuffleScan.useDelimiter(",");

        for(int i=1;i<=countLine;i++){

            readCount++;
            /*System.out.println(readCount);*/


            p1=shuffleScan.nextDouble();
            p2=shuffleScan.nextDouble();
            p3=shuffleScan.nextDouble();
            p4=shuffleScan.nextDouble();
            className=shuffleScan.nextLine().substring(1);

            //System.out.println(p1 + p2 + p3 + p4 + className);

            Points newPoint=new Points(p1,p2,p3,p4,className);

            if (readCount>= TestStart && readCount<=TestBound)

            {
                testPoints.add(newPoint);
                continue;
            }

//            readCountCC++;
            trainPoints.add(newPoint);

        }

//        System.out.println(readCountCC);

        shuffleScan.close();
        //System.out.println("Before Clone"+testPoints.get(0));

        //System.out.println(trainPoints.size());
        for(int iiii=0;iiii < testPoints.size();iiii++)
        {
            testPointsCC.add(new Points(testPoints.get(iiii).point1,testPoints.get(iiii).point2,testPoints.get(iiii).point3,testPoints.get(iiii).point4,""));

        }

       //testPointsCC= (ArrayList<Points>) testPoints.clone();
        //System.out.println("Start Main Test Points" + testPointsCC.get(0));


        for (int i=0;i<testPoints.size();i++)
        {
            double Iset=0,Iversi=0,Ivirgin=0;
            ArrayList<DistanceWIthClass> dWc= new ArrayList<>();

            for (int j=0;j<trainPoints.size();j++)
            {
                pointRead++;
//                pointDistances.add((dist.getDistance(testPoints.get(i),trainPoints.get(j))));
//                pointClass.add(trainPoints.get(j).getClassName());

                DistanceWIthClass dc =new DistanceWIthClass((dist.getDistance(testPoints.get(i),trainPoints.get(j))), trainPoints.get(j).getClassName());
                dWc.add(dc);
                //System.out.println(dWc);

                /*distanceMap.put(dist.getDistance(testPoints.get(i),trainPoints.get(j)) , trainPoints.get(j).getClassName());*/                                                                                            //equalSum+=0.044;


                /*if(pointDistances.get((int) pointRead)==0){

                    System.out.println("TestPoints"+testPoints.get(i)+"TrainPoints"+trainPoints.get(j));

                }*/
            }
            //System.out.println("Before Sort"+dWc);
            Collections.sort(dWc);
            //System.out.println("After Sort" + dWc);

            for (int i3=0;i3<k;i3++){

                dWcTemp.add(dWc.get(i3));

            }
            //System.out.println(dWcTemp);


            for(int i2=0;i2<k;i2++)
            {

                if (dWc.get(i2).getClassName().contains("Iris-setosa") )
                {
                    Iset=Iset+1;
                }

                else if (dWc.get(i2).getClassName().contains("Iris-versicolor"))
                {
                    Iversi=Iversi+1;
                }

                else if (dWc.get(i2).getClassName().contains("Iris-virginica"))
                {
                    Ivirgin=Ivirgin+1;
                }

            }
            //System.out.println("Iset" + Iset + "I versi" + Iversi + "Ivirgin" + Ivirgin );

            //System.out.println(testPoints.get(i).getClassName());

                if(Iset > Iversi && Iset > Ivirgin)
                {
                    //System.out.println("Isetosa Paisi");
                    testPointsCC.get(i).setClassName("Iris-setosa");

                }
                else if(Iversi > Ivirgin && Iversi > Iset)
                {
                    //System.out.println("Iversi Paisi");
                    testPointsCC.get(i).setClassName("Iris-versicolor");
                }
                else if(Ivirgin>Iset && Ivirgin>Iversi)
                {
                    //System.out.println("Ivirgin Paisi");
                    testPointsCC.get(i).setClassName("Iris-virginica");
                }



            //System.out.println("Start Modified Test Points:"+testPointsCC+"\n");

                    if(testPointsCC.get(i).getClassName().contains(testPoints.get(i).getClassName()))
                    {
                        equalSum++;

                    }









            //System.out.println(equalSum);



//            System.out.println(testPoints);
//            System.out.println(testPointsCC);









            /*for(Map.Entry m:distanceMap.entrySet()){

                distanceMap.entrySet().stream().sorted(Map.Entry.comparingByKey());

            }*/



           /* for(Map.Entry m:distanceMap.entrySet()){

                System.out.println(m.getKey()+" "+m.getValue());

            }*/

            //System.out.println(testPointsCC.get(i).getClassName());
        }

        accuracy=equalSum/(Double)(countLine/kFold);
        accCount+=accuracy;


        System.out.println("Accuracy Per Iteration: "+ accuracy);

        BufferedWriter shuffledOut=new BufferedWriter(new FileWriter("irisTestCC.txt"));
        BufferedWriter shuffledOut1=new BufferedWriter(new FileWriter("irisTest.txt"));
        for(int i=0;i<testPointsCC.size();i++)
        {
            shuffledOut.write(String.valueOf(testPointsCC.get(i)));
            shuffledOut.newLine();
            shuffledOut.flush();
            shuffledOut1.write(String.valueOf(testPoints.get(i)));
            shuffledOut1.newLine();
            shuffledOut1.flush();

        }

        averageAccuracy=accCount/kFold;
    }


}
