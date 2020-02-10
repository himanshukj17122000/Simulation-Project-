simulation
====

This project implements a cellular automata simulator.

Names: Kyra Chan, Himanshu Jain, Olga Suchankova

### Timeline

Start Date: January 25, 2020

Finish Date: February 10, 2020

Hours Spent: 120+ (40+ each)

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
* Primary classes (in charge of): The Whole Configuration Folder, XML Files and the Dialog Box
* Secondary classes (worked on): Simulation, Visualization
* Main contributions:
    * Worked on reading the XML files and setting the initial configuration of the grid by making a map of all the values inside 
    the XML file
    * Implemented the different Error checkings like checking for the right value, right value, missing values etc 
    * Returned all the paramaters required for setting up the grid and updating the values 
    * Made the XML files that contain the data that has to be read 
    * Created the XML writers for 5 simulations 

Olga Suchankova:
* Primary classes (in charge of):
* Secondary classes (worked on): 
* Main contributions:
    * Implemented all the cell types, and the data structures and algorithms to store cells and update cell states and communicate with the UI
    * created simulation loop in simulation class
    * implemented the display of cell states to the output window
    
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
* 40 classes (of which 2 are abstract and 18 are sub-classes)

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
    * Save the Configuration of the Grid in a new XML file that could later be used by the user
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
* The screen has a fixed size and is not resizable.
* The XML file requires a lot of formatted inputs, but error checking does set defaults if 
left blank.
* The program only allows certain types of simulations, with minor adjustments.
* The main rules of each type of simulation is set, but certain parameters can be altered
using sliders or changing the XML file.
* The shape of the cell can only be customized as rectangular, hexagonal, or circle.
* The color of the cells are set depending on the simulation.
* The size of the cells are calculated based on the input grid size and number of rows and
columns.
* The grid ratio (rows:columns) must be within a range of 0.833 to 1.2.
* Users cannot interact dynamically to change the state of the grid.

Interesting data files:
* Simulation class: primarily controls how the simulation updates
* Visualization class: primarily handles all UI elements and animation
* Reader class: reads input of XML file
* Predator Cell class

Known Bugs:
* The speed slider does not work
* The method that is supposed to update the cell state if the user clicks on the cell does
not work
* For the Prey simulation, sometimes a Prey does not move into a spot that the Predator just
left from; instead it displays an empty cell.

Extra credit:
* Display cell shapes as circles and hexagonals
* XML writer that can save current configuration as a new input file, and allows the user
to choose the file name
* Display chart of cell population statistics that updates in real-time
* Allow users to run two different types of simulations simultaneously and be able to control
each simulation independently

### Impressions
Overall, our group thought that the project was quite challenging. Writing the XML file was
definitely a learning curve, but having pre-written code for reading the XML file was very
helpful. Debugging simulations were really time-consuming, as it was difficult to pinpoint
the cause of the error. Visualization did not seem to be one of the most challenging aspects
of this project, but some of the features were actually very difficult to implement. Through
this experience, we were all able to get experience with handling errors, using inheritance
and polymorphism with abstract classes, as well as encapsulation (by not passing the grid 
directly). While this project consumed our lives, we really learned how to work efficiently
as a team and improved our Git and codesharing skills.
