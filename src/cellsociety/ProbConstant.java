package cellsociety;

public class ProbConstant {
    private String myLabel;
    private double myProbCatch;

    public ProbConstant(String label, double probCatch) {
        myLabel = label;
        myProbCatch = probCatch;
    }

    public String getLabel() { return myLabel; }
    public double getProbCatch() { return myProbCatch; }
    public void setProbCatch(double probCatch) { myProbCatch = probCatch; }
}
