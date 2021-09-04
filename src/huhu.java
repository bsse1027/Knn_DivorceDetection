import java.io.*;
import java.util.*;

public class huhu {

    double accuracy,averageAccuracy;

    public void Start(int a) throws IOException {

        BufferedReader input =new BufferedReader(new FileReader("iris.txt"));

        //ArrayList <ArrayList <String>> shuffle= new ArrayList<ArrayList<String>>();

        ArrayList<Double> pointDistances=new ArrayList<Double>();
        ArrayList<String> pointClass= new ArrayList<String>();
        //Map<Double,String> distanceMap =new HashMap<Double, String>();
        ArrayList<DistanceWIthClass> dWc= new ArrayList<DistanceWIthClass>();
        ArrayList<DistanceWIthClass> dWcTemp= new ArrayList<DistanceWIthClass>();




        Scanner scanner = new Scanner(new FileInputStream("iris.txt"));
        Scanner scanner1 = new Scanner(new FileInputStream("iris.txt"));
        int countLine=0, TestStart, readCount=0, kFold=10, k=5;
        Distance dist =new Distance();
        double pointRead=-1,equalSum=0;


        while (input.readLine()!=null)
        {
            countLine++;
        }

//       System.out.println("Total Datas: "+countLine);


        Scanner shuffleScan =new Scanner(new File("iris2.txt"));


        //int max=countLine-(countLine/kFold), min=1;

        TestStart =a;

        //System.out.println(TestStart);

        int TestBound= TestStart +(countLine/kFold)-1;

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
            className=shuffleScan.nextLine();

            Points newPoint=new Points(p1,p2,p3,p4,className);

            if (readCount>= TestStart && readCount<=TestBound)

            {
                testPoints.add(newPoint);
                continue;
            }


            trainPoints.add(newPoint);

        }

        scanner.close();
        //System.out.println(testPoints.size());
        //System.out.println(trainPoints.size());
        testPointsCC= (ArrayList<Points>) testPoints.clone();

        for (int i=0;i<testPoints.size();i++)
        {
            double Iset=0,Iversi=0,Ivirgin=0;

            for (int j=0;j<trainPoints.size();j++)
            {
                pointRead++;
//                pointDistances.add((dist.getDistance(testPoints.get(i),trainPoints.get(j))));
//                pointClass.add(trainPoints.get(j).getClassName());

                DistanceWIthClass dc =new DistanceWIthClass((dist.getDistance(testPoints.get(i),trainPoints.get(j)))+0.000000001 , trainPoints.get(j).getClassName());
                dWc.add(dc);

                //distanceMap.put(dist.getDistance(testPoints.get(i),trainPoints.get(j)) , trainPoints.get(j).getClassName());

                /*if(pointDistances.get((int) pointRead)==0){

                    System.out.println("TestPoints"+testPoints.get(i)+"TrainPoints"+trainPoints.get(j));

                }*/
            }

            Collections.sort(dWc);
            //System.out.println(dWc);

            for (int i3=0;i3<k;i3++){

                dWcTemp.add(dWc.get(i3));

            }


            for(int i2=0;i2<dWcTemp.size();i2++)
            {

                if (dWcTemp.get(i2).getClassName().contains("Iris-setosa"))
                {
                    Iset=Iset+(1/(dWcTemp.get(i2).getDistance()));
                }

                if (dWcTemp.get(i2).getClassName().contains("Iris-versicolor"))
                {
                    Iversi=Iversi+(1/(dWcTemp.get(i2).getDistance()));
                }

                if (dWcTemp.get(i2).getClassName().contains("Iris-virginica"))
                {
                    Ivirgin=Ivirgin+(1/(dWcTemp.get(i2).getDistance()));
                }

            }
            //System.out.println("Iset" + Iset + "I versi" + Iversi + "Ivirgin" + Ivirgin );

            //System.out.println(testPoints.get(i).getClassName());
            for (int ix=0;ix<testPointsCC.size();ix++)
            {
                if(Iset > Iversi && Iversi > Ivirgin)
                {
                    //System.out.println("Isetosa Paisi");
                    testPointsCC.get(ix).setClassName("Iris-setosa");

                }
                else if(Iversi > Ivirgin && Iversi > Iset)
                {
                    //System.out.println("Iversi Paisi");
                    testPointsCC.get(ix).setClassName("Iris-versicolor");
                }
                else if(Ivirgin>Iset && Ivirgin>Iversi)
                {
                    //System.out.println("Ivirgin Paisi");
                    testPointsCC.get(ix).setClassName("Iris-virginica");
                }
            }


            System.out.println(testPoints+"\n"+testPointsCC);


            for(int ii=0;ii<testPoints.size();ii++)
            {
                if(testPointsCC.get(ii).getClassName()==testPoints.get(ii).getClassName())
                {
                    equalSum++;

                }

            }

            System.out.println(equalSum);



//            System.out.println(testPoints);
//            System.out.println(testPointsCC);

            accuracy=equalSum/(countLine/kFold);
            System.out.println(accuracy);





            /*for(Map.Entry m:distanceMap.entrySet()){

                distanceMap.entrySet().stream().sorted(Map.Entry.comparingByKey());

            }*/



           /* for(Map.Entry m:distanceMap.entrySet()){

                System.out.println(m.getKey()+" "+m.getValue());

            }*/

            //System.out.println(testPointsCC.get(i).getClassName());
        }




    }


}
