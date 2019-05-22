package gaidadym.javaForTesters;
public class DistanceBetweenPoints {

    public static void main(String[] args) {
        Point p1 = new Point();
        p1.x = 5;
        p1.y = 4;
        Point p2 = new Point();
        p2.x = 10;
        p2.y = 11;
        System.out.println(distance (p1,p2));
        System.out.println(p1.distance (p2));
    }

        public static double distance(Point p1,Point p2){

            double dist = Math.sqrt((p2.x-p1.x)+(p2.y-p1.y));
            return dist;

        }


}

