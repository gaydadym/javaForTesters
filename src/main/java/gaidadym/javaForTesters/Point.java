package gaidadym.javaForTesters;
public class Point {
    public int x,y;
    public double distance(Point p2){
        double dist = Math.sqrt((p2.x-this.x)+(p2.y-this.y));
        return dist;
    }

}