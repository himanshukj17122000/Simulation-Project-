package cellsociety;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Simulation {
    private List<List<GridEntry>> SimulationGrid;
    private int status;
    private Set<GridEntry> emptyCellSet = new HashSet<GridEntry>();


    public Simulation(List<List<GridEntry>> simGrid) {
        setSimulationGrid(simGrid);
    }

    private void setSimulationGrid(List<List<GridEntry>> simGrid) {
        SimulationGrid = simGrid;
    }

    public List<List<GridEntry>> getSimulationGrid() {
        return SimulationGrid;
    }

    private void setEmptyCellSet(Set<GridEntry> emptyCells) {
        emptyCellSet = emptyCells;
    }

    public Set<GridEntry> getEmptyCellSet() {
        return emptyCellSet;
    }


    public void step() {
        Set<GridEntry> newEmptyCellSet = new HashSet<GridEntry>();
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
                if (currentGridEntry.getCellType() == 1) { // update to not hard code
                    newEmptyCellSet.add(currentGridEntry);
                }
            }
            setEmptyCellSet(newEmptyCellSet);
        }
    }
}
