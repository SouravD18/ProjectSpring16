import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 *  A set of points will be given. Number of distinct points >= 3. 
 *  Then the convex polygon connecting these points will be the Robot
 * 
 * @author Sourav
 *
 */
public class Object2D { 
    /**
     * Abstract Functions:
     *    Represent A 2D-object using a list of 2D points 
     *    OR, 4 corner points of a Rectangle that contains the object  
     * Representation Invariant:
     *    The List need at least 3 Distinct Points
     * Safety from rep Exposure:
     *    Fields are private. Copied points from list.
     */
    
    private boolean isObjectSquare = false;
    
    private List<Point2D> points = new ArrayList<Point2D>();
    
    private Point2D bottomLeft, bottomRight, topLeft, topRight;
    /**
     * Constructor - from a given list of points
     * 
     * @param givenPoints - A List of Poin2D objects
     */
    public Object2D(List<Point2D> givenPoints){
        // Coping points from given list to the points in Robot
        for(Point2D point: givenPoints){
            points.add((Point2D) point.clone());
        }
    }
    /**
     * Constructor - from corner points of the Rectangle that contains the object
     * 
     * @param givenbottomLeft
     * @param givenbottomRight
     * @param giventopLeft
     * @param giventopRight
     */
    public Object2D(Point2D givenbottomLeft, Point2D givenbottomRight
                            ,Point2D giventopLeft,Point2D giventopRight){
        
        this.bottomLeft = (Point2D) givenbottomLeft.clone();
        this.bottomRight = (Point2D) givenbottomRight.clone();
        
        this.topLeft = (Point2D) giventopLeft.clone();
        this.topRight = (Point2D) giventopRight.clone();
        
        this.isObjectSquare = true;
    }
    
    /**
     * 
     * @return A square Object that contains the given object
     */
    public Object2D squareObject(){
        // If already square, return the current Object
        if(isObjectSquare){
            return this;
        }
        
        // Find Max and Min of X-coordinates; 
        // Fing Max and Min of Y-coordinates;
        
        double xMax = points.get(0).getX();
        double xMin = points.get(0).getX();

        double yMax = points.get(0).getY();
        double yMin = points.get(0).getY();
        
        for(Point2D point: points){
            xMax = Math.max(xMax, point.getX());
            xMin = Math.min(xMin, point.getX());
            
            yMax = Math.max(yMax, point.getY());
            yMin = Math.min(yMin, point.getY());
        }
        
        Point2D topRight = new Point2D.Double(xMax, yMax);
        Point2D topLeft = new Point2D.Double(xMin, yMax);
        
        Point2D bottomRight = new Point2D.Double(xMax, yMin);
        Point2D bottomLeft = new Point2D.Double(xMin, yMin);
        
        return new Object2D(bottomLeft, bottomRight, topLeft, topRight);
    }
}
