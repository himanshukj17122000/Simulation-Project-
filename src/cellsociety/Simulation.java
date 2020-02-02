package cellsociety;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<List<GridEntry>> SimulationGrid;
    private int status;


    public Simulation(List<List<GridEntry>> simGrid){
        setSimulationGrid(simGrid);
    }

    private void setSimulationGrid(List<List<GridEntry>> simGrid){
        SimulationGrid = simGrid;
    }

    public List<List<GridEntry>> getSimulationGrid(){
        return SimulationGrid;
    }


    public void step(){
        List<List<GridEntry>> currentGridConfig = getSimulationGrid();
        for (int r = 0; r < currentGridConfig.size(); r++) {
            for (int c = 0; c < currentGridConfig.get(r).size(); c++) {
                GridEntry currentGridEntry = currentGridConfig.get(r).get(c);
                Cell currentCell = currentGridEntry.getCell();
                currentCell.updateCell(currentGridEntry);
            }
        }

        for (int r = 0; r < currentGridConfig.size(); r++) {
            for (int c = 0; c < currentGridConfig.get(r).size(); c++) {
                GridEntry currentGridEntry = currentGridConfig.get(r).get(c);
                currentGridEntry.updateGridEntry();
            }
        }
    }

}
