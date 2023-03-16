package Part2Solution;

import java.util.*;

/**
 * class representing for constraints in a linear programming problem
 * the constraints would only consists 2 variable x1 and x2
 * the basic format would be :
 * x1Coefficient * x1 + x2Coefficient * x2 type(<=, >=, =) constantValue
 */
public class constraint {
    /**
     * the id of constraint
     */
    public int id;
    /**
     * the x1 coefficient of the constraint
     */
    public double x1Coefficient;
    /**
     * the x2 coefficient of the constraint
     */
    public double x2Coefficient;
    /**
     * the constant value of the constraint
     */
    public double constantValue;
    /**
     * the type of the constraint
     * it would be LESS_OR_EQUAL, MORE_OR_EQUAL, EQUAL
     * 
     */
    public String type;

    public static final String LESS_OR_EQUAL = "<=";
    public static final String MORE_OR_EQUAL = ">=";
    public static final String EQUAL = "=";
    public static final double DELTA = 0.00001;

    public constraint(int id, double x1Coefficient, double x2Coefficient, double constantValue, String type) {
        this.id = id;
		this.x1Coefficient = x1Coefficient;
		this.x2Coefficient = x2Coefficient;
		this.constantValue = constantValue;
		this.type = type;
	}
    /**
     * Return the value of 
     * @param p A Point instance.
     * @return double value, indicating the linear combination of the Point coordinates with this constraint coefficients.
     */
	public double evaluate(Point p) {
        return (p.x * x1Coefficient) + (p.y * x2Coefficient);
    }

    /**
     * x1Coefficient * x1 + x2Coefficient * x2 = constantValue
     * -> x2 = (constantValue - x1Coefficient * x1) / x2Coefficient
     * @param x The x1 to be evaluated in this Constraint.
     * @return The corresponding x2 of this Constraint with given x1.
     * @throws Exception Exception thrown when the x2 value of the Constraint is 0.
     */
    public double giveYof(double x)throws Exception{
        if(this.x2Coefficient == 0){
            throw new Exception("Cannot calculate x2 value of a function with x2 coefficient 0.");
        }else{
            return (this.constantValue - (this.x1Coefficient * x)) / this.x2Coefficient;
        }
    }

    /**
     * Indicates if this Constraint instance is satisfied by the Point parameter.
     * @param p Point to be evaluated.
     * @return A boolean value, true if the Point satisfies this Constraint, false otherwise.
     */
    public boolean isSatisfiedBy(Point p) {
        double evaluation = evaluate(p);
        if(this.type.equals(LESS_OR_EQUAL)) {
            return (evaluation <= this.constantValue + DELTA);
        }else if(this.type.equals(MORE_OR_EQUAL)) {
            return (evaluation >= this.constantValue - DELTA);
        }else {
            return (evaluation >= this.constantValue - DELTA && evaluation <= this.constantValue + DELTA);
        }
    }

    /**
     * Returns an ArrayList containing all intersections of this Constraint with the coordinate axes.
     * @return ArrayList of type Point.
     */
    public ArrayList<Point> pointsOfIntersectionWithAxes(){
        ArrayList<Point> cuts = new ArrayList<Point>();
        // get the intersection point with y-axis 
        double c1 = this.constantValue / this.x2Coefficient;
        if (!Double.isInfinite(c1) && !Double.isNaN(c1)){
            cuts.add(new Point(0,c1));
        }
        // get the intersection point with x-axis 
        double c2=this.constantValue/this.x1Coefficient;
        if(!Double.isInfinite(c2) && !Double.isNaN(c2)){
            cuts.add(new Point(c2,0));
        }
  
        return cuts;
    }

    /**
     * Calculate the intersection of this Constraint wtih another given parameter Constraint.
     * @param c Another Constraint instance.
     * @return the intersection point
     * 
     */
    public Point intersectionWith(constraint other) {
        double topPart = (this.constantValue * other.x2Coefficient) - (other.constantValue * this.x2Coefficient);
        double bottomPart = (this.x1Coefficient * other.x2Coefficient) - (other.x1Coefficient * this.x2Coefficient);
        double x = topPart / bottomPart;
        double y;
        if (this.x2Coefficient != 0){
            y = (this.constantValue - (this.x1Coefficient * x)) / this.x2Coefficient;
        } else{
            y = (other.constantValue - (other.x1Coefficient * x)) / other.x2Coefficient;
        }
        if (!Double.isNaN(x)  && !Double.isNaN(y)) {
            return new Point(x ,y);
        } else {
            return null;
        }
    }
}
