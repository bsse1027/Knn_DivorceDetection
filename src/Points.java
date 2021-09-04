public class Points {

    int point1,point2,point3,point4,point5,point6;
    private int ClassName;

    public Points(int p1,int p2,int p3,int p4,int p5,int p6 ,int a)
    {

        this.point1=p1;
        this.point2=p2;
        this.point3=p3;
        this.point4=p4;
        this.point5=p5;
        this.point6=p6;
        this.ClassName=a;

    }

    public int getClassName() {
        return ClassName;
    }

    public void setClassName(int className) {
        ClassName = className;
    }

    @Override
    public String toString() {
        return "Points{" +
                "point1=" + point1 +
                ", point2=" + point2 +
                ", point3=" + point3 +
                ", point4=" + point4 +
                ", ClassName=" + ClassName +
                '}';
    }
}
