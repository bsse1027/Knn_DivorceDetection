import java.io.*;
import java.util.*;

public class TrainClass{

    double accuracy,averageAccuracy,accCount=0;

    public void Start(int a, Double kFold) throws IOException {

        BufferedReader input =new BufferedReader(new FileReader("divorce2.txt"));

        //ArrayList <ArrayList <String>> shuffle= new ArrayList<ArrayList<String>>();

        ArrayList<Double> pointDistances=new ArrayList<Double>();
        ArrayList<String> pointClass= new ArrayList<String>();
        //Map<Double,String> distanceMap =new HashMap<Double, String>();
        ArrayList<DistanceWIthClass> dWcTemp= new ArrayList<DistanceWIthClass>();
        int equalSum=0;





        //Scanner scanner = new Scanner(new FileInputStream("iris.txt"));
        //Scanner scanner1 = new Scanner(new FileInputStream("iris.txt"));
        int countLine=0, TestStart, readCount=0,k=5,readCountCC = 0;
        Distance dist =new Distance();
        double pointRead=-1;


        while (input.readLine()!=null)
        {
            countLine++;
        }



//       System.out.println("Total Datas: "+countLine);


        Scanner shuffleScan =new Scanner(new File("divorce2.txt"));


        //int max=countLine-(countLine/kFold), min=1;

        TestStart =a;

        //System.out.println(TestStart);

        int TestBound= (int) (TestStart +(countLine/kFold)-1);

        //System.out.println(TestBound);


        int p1,p2,p3,p4,p5,p6,className;

        ArrayList<Points> trainPoints =new ArrayList<Points>();
        ArrayList<Points> allPoints =new ArrayList<Points>();
        ArrayList<Points> testPoints =new ArrayList<Points>();
        ArrayList<Points> testPointsCC =new ArrayList<Points>();


        shuffleScan.useDelimiter(",");

        for(int i=1;i<=countLine;i++){

            readCount++;
            /*System.out.println(readCount);*/


            p1=shuffleScan.nextInt();
            p2=shuffleScan.nextInt();
            p3=shuffleScan.nextInt();
            p4=shuffleScan.nextInt();
            p5=shuffleScan.nextInt();
            p6=shuffleScan.nextInt();
            className= Integer.parseInt(shuffleScan.nextLine().substring(1));

            //System.out.println(p1 + p2 + p3 + p4 + className);

            Points newPoint=new Points(p1,p2,p3,p4,p5,p6,className);

            if (readCount>= TestStart && readCount<=TestBound)

            {
                testPoints.add(newPoint);
                allPoints.add(newPoint);
                continue;
            }

//            readCountCC++;
            trainPoints.add(newPoint);
            allPoints.add(newPoint);

        }

//        System.out.println(readCountCC);

        shuffleScan.close();
        //System.out.println("Before Clone"+testPoints.get(0));

        //System.out.println(trainPoints.size());
        for(int iiii=0;iiii < testPoints.size();iiii++)
        {
            testPointsCC.add(new Points(testPoints.get(iiii).point1,testPoints.get(iiii).point2,testPoints.get(iiii).point3,testPoints.get(iiii).point4,testPoints.get(iiii).point5,testPoints.get(iiii).point6,100));

        }

       //testPointsCC= (ArrayList<Points>) testPoints.clone();
        //System.out.println("Start Main Test Points" + testPointsCC.get(0));


        for (int i=0;i<testPoints.size();i++)
        {
            int IDivorce=0,INotDivorced=0;
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

                if (dWc.get(i2).getClassName()==1 )
                {
                    IDivorce=IDivorce+1;
                }

                else if (dWc.get(i2).getClassName()==0)
                {
                    INotDivorced=INotDivorced+1;
                }

                /*else if (dWc.get(i2).getClassName().contains("Iris-virginica"))
                {
                    Ivirgin=Ivirgin+1;
                }*/

            }
            //System.out.println("IDivorce" + IDivorce + "I versi" + INotDivorced + "Ivirgin" + Ivirgin );

            //System.out.println(testPoints.get(i).getClassName());

                if(IDivorce > INotDivorced)
                {
                    //System.out.println("Isetosa Paisi");
                    testPointsCC.get(i).setClassName(1);

                }
                else
                {
                    //System.out.println("INotDivorced Paisi");
                    testPointsCC.get(i).setClassName(0);
                }




            //System.out.println("Start Modified Test Points:"+testPointsCC+"\n");

                    if(testPointsCC.get(i).getClassName()==(testPoints.get(i).getClassName()))
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

        BufferedWriter shuffledOut=new BufferedWriter(new FileWriter("DivorceCC.txt",true));
        BufferedWriter shuffledOut1=new BufferedWriter(new FileWriter("irisTest.txt"));
        for(int i=0;i<testPointsCC.size();i++)
        {
            shuffledOut.write(String.valueOf(testPointsCC.get(i)));
            shuffledOut.newLine();
            shuffledOut.flush();
        }







        averageAccuracy=(accCount/kFold)*100;
    }


}
