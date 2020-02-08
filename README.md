simulation
====

This project implements a cellular automata simulator.

Names: Kyra Chan, Himanshu Jain, Olga Suchankova

### Timeline

Start Date: January 25, 2020

Finish Date: February 10, 2020

Hours Spent: 90+ (30+ each)

### Primary Roles
Kyra Chan:
* Primary classes (in charge of): Visualization, Splash, DialogBox, ProbConstant, Main
* Secondary classes (worked on): Configuration, Simulation
* Main contributions:
    * Implement all UI components: designed all the screens, including the sidebar, created
    all the buttons and their functions, created the chart and GUI sliders
    * Created the main method that draws the grid after each time step
    * Created the main method that initialized the grid setup (randomizing the cells)
    
Himanshu Jain:
* Primary classes (in charge of):
* Secondary classes (worked on): 
* Main contributions:
    *
    *
Olga Suchankova:
* Primary classes (in charge of):
* Secondary classes (worked on): 
* Main contributions:
    *
    *
    
### Resources Used
* Stack Overflow
* TutorialsPoint
* StackExchange
* Java Code Geeks
* Geeks for Geeks
* Design Checkup Dev - refactoring

### Running the Program

Main class: Main.java

Data files needed: 
* 7 packages (Configuration, Visualization, one for each Simulation type)
* 30 classes (of which 2 are abstract and 18 are sub-classes)

Features implemented:
* Simulation
    * Implement a grid of cells that is used to represent the simulation.
    * Cells on the edges of the grid should have smaller, partial, neighborhoods than those 
    in the middle, with full neighborhoods.
    * Allow any different arrangements of neighbors by setting an 8-bit number (rectangle)
    or the first 6 bits of the 8-bit number (hexagonal)
    * Implement the simulation algorithm where the state information of each cell is updated 
    each step based on rules applied to their state and those of their neighbors.
    * Simulations should be able to be run indefinitely.
    * Implement Java code for the rules of five different simulations: game of life, 
    percolation, segregation, predator-prey, and fire.
* Configuration
    * Read in an XML formatted file that contains the initial settings for a simulation. 
    The file contains three parts: 
        * Kind of simulation it represents, as well as a title and the author of this data 
        file.
        * Settings for global configuration parameters specific to the simulation.
        * Width and height of the grid and the initial configuration of the states for the 
        cells in the grid (border conditions, number of rows and columns).
    * Implement error checking for incorrect file data, such as:
        * Invalid or no simulation type given.
        * Invalid cell state values given and cell locations that are given outside the 
        bounds of the grid's size (users are limited to configuring the border conditions).
    * Allow simulations initial configuration to be set by:
        * Completely randomly based on a total number of locations to occupy (not including
        border conditions).
        * Randomly based on probability/concentration distributions (provided in XML file).   
* Visualization
    * Display the current states of the 2D grid and animate the simulation from its initial 
    state indefinitely until the user stops it.
    * Allow users to return back to the main upload screen.
    * Allow users to upload a new configuration file, which stops the current simulation and 
    starts the new one in a new window, or change the configuration file, which stops the
    current simulation and starts the new one in the same window.
    * Allow users to run multiple simulations at the same time so they can compare the 
    results side by side. Specific UI features:
        * The user can display different types of simulations simultaneously.
        * The user can control the buttons for each simulation (the two simulations run
        independently).
        * The user can control the arrangement of both screens.
    * The display size of an individual cell is automatically calculated each time by the 
    grid's total size and the number of rows and columns given, but the size of the 
    visualization window should not change size.
    * Allow users to pause and resume the simulation, as well as step forward through it.
    * Allow users to speed up or slow down the simulation's animation rate.
    * Allow users to interact with the simulation dynamically to change the values of its 
    parameters.
    * Display a graph of stats about the populations of all relevant cells over 
    the time of the simulation (updates in real-time).

### Notes/Assumptions

Assumptions or Simplifications:

Interesting data files:

Known Bugs:

Extra credit:

### Impressions

