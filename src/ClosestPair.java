import edu.princeton.cs.algs4.Point2D;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class ClosestPair {
    private Point2D point1, point2;
    private double bestDistance = Double.POSITIVE_INFINITY;

    public ClosestPair(Point2D[] points) {
        int length = points.length;
        if(length <= 1) return;

        Point2D[] xOrderPoints = points.clone();
        Arrays.sort(xOrderPoints, Point2D.X_ORDER);

        for(int i = 0; i < length - 1; i++) {
            if(xOrderPoints[i].equals(xOrderPoints[i+1])) {
                point1 = xOrderPoints[i];
                point2 = xOrderPoints[i+1];
                return;
            }
        }

        Point2D[] yOrderPoints = points.clone();
        Arrays.sort(yOrderPoints, Point2D.Y_ORDER);

        Point2D[] aux = new Point2D[length];

        closest(xOrderPoints, yOrderPoints, aux, 0, length-1);
    }

    public double distance() {
        return this.bestDistance;
    }

    public Point2D[] closestPair() {
        return new Point2D[] { point1, point2 };
    }

    private double closest(Point2D[] xOrder,
                                         Point2D[] yOrder,
                                         Point2D[] aux,
                                         int low,
                                         int high) {
        if(high <= low) return Double.POSITIVE_INFINITY;

        int mid = low + (high - low)/2;
        Point2D middlePoint = xOrder[mid];

        double deltaLeft = closest(xOrder, yOrder, aux, low, mid);
        double deltaRight = closest(xOrder, yOrder, aux, mid+1, high);
        double delta = Math.min(deltaLeft, deltaRight);

        merge(yOrder, aux, low, mid, high);

        int m = 0;
        for(int i = low; i<= high; i++) {
            if(Math.abs(xOrder[i].x() - middlePoint.x()) < delta)
                aux[m++] = yOrder[i];
        }

        for(int i = 0; i < m; i++) {
            for(int j = i+1; (j<m) && (aux[j].y() - aux[i].y() < delta); j++){
                double distance = aux[i].distanceTo(aux[j]);

                if(distance < delta) {
                    delta = distance;
                    if(distance < bestDistance) {
                        bestDistance = delta;
                        point1 = aux[i];
                        point2 = aux[j];
                    }
                }
            }
        }

        return delta;
    }

    private boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private void merge(Comparable[] a,
                       Comparable[] aux, int low, int mid, int high) {
        for(int k = low; k <= high; k++) {
            aux[k] = a[k];
        }

        int i = low, j = mid+1;

        for(int k = low; k <= high; k++) {
            if (i>mid) a[k] = aux[j++];
            else if (j > high) a[k] = aux[i++];
            else if(less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in =
                new Scanner(new FileReader("./resources/points.txt"));
        int n = in.nextInt();
        Point2D[] points = new Point2D[n];

        for(int i = 0; i < n; i++) {
            double x = in.nextDouble();
            double y = in.nextDouble();
            points[i] = new Point2D(x,y);
        }

        ClosestPair closest = new ClosestPair(points);

        System.out.println(closest.distance());
        System.out.println("The closest pair is: "
                + Arrays.toString(closest.closestPair()));
    }
}