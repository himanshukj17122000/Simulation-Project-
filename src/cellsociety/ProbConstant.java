package cellsociety;

public class ProbConstant {
    private String myLabel;
    private double myProbCatch;

    public ProbConstant(String label, double probCatch) {
        myLabel = label;
        myProbCatch = probCatch;
    }

    public String getMyLabel() { return myLabel; }
    public double getMyProbCatch() { return myProbCatch; }
    public void setMyProbCatch(double probCatch) { myProbCatch = probCatch; }
}
