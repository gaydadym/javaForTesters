package gaidadym.javaForTesters;
public class Point {
    public int x,y;
    public double distance(Point p2){
        double dist = Math.sqrt(Math.pow((p2.x-this.x),2)+Math.pow((p2.y-this.y),2));
        return dist;
    }

}