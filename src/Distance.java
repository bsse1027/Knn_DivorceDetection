import java.awt.*;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Distance {

    public double getDistance(Points a, Points b){


        double dist = sqrt(pow((a.point1-b.point1),2) + pow((a.point2-b.point2),2) + pow((a.point3-b.point3),2) + pow((a.point4-b.point4),2));

        return dist;



    }


}
