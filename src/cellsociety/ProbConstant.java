package cellsociety;

/**
 * @Author-Kyra Chan
 * This is the class for ProbConstant. It returns the label and value attached to a slider value that can be changed.
 */

public class ProbConstant {
    private String myLabel;
    private double myProbCatch;

    /*
    Constructor for ProbConstant class
     */
    public ProbConstant(String label, double probCatch) {
        myLabel = label;
        myProbCatch = probCatch;
    }

    /*
    Returns the label of the slider
     */
    public String getLabel() { return myLabel; }

    /*
    Returns the updated value of the slider
     */
    public double getProbCatch() { return myProbCatch; }

    /*
    Sets the value of the slider to update the value being passed on
     */
    public void setProbCatch(double probCatch) { myProbCatch = probCatch; }
}
