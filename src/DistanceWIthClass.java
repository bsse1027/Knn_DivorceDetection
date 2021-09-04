import java.util.Comparator;

public class DistanceWIthClass implements Comparable<DistanceWIthClass> {

    double distance;
    String className;

    public DistanceWIthClass(double d,String a){

        this.distance=d;
        this.className=a;
    }

    public String getClassName() {
        return className;
    }

    public double getDistance() {
        return distance;
    }


    @Override
    public int compareTo(DistanceWIthClass o) {
        if(distance == o.distance)
        {
            return 0;
        }
        else if(distance > o.distance)
            return 1;
        else
            return -1;
    }

    @Override
    public String toString() {
        return "DistanceWIthClass{" +
                "distance=" + distance +
                ", className='" + className + "\n" + '\'' +
                '}';
    }
}
