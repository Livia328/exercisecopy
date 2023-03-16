package Part2Solution;

public class main {
    public static void main(String[] args) {
        LPObject test1 = new LPObject(3, 4, "MAX");
        constraint c1 = new constraint(1, 15, 10, 300, "<=");
        constraint c2 = new constraint(2, 2.5, 5, 110, "<=");
        test1.addConstraint(c1);
        test1.addConstraint(c2);
        Point res = test1.giveOptimumPoint();
        System.out.println("---------------optimal solution----------------");
        System.out.println("x1 = " + res.x);
        System.out.println("x2 = " + res.y);
        double op = test1.x1Coefficient * res.x + test1.x2Coefficient * res.y;
        System.out.println(test1.type + " = " +op);
    }
}
