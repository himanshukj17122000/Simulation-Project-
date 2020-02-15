# Simulation Design Final
### Names
Kyra Chan, Himanshu Jain, Olga Suchankova

## Team Roles and Responsibilities

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
 * Primary classes (in charge of): All the cell type folders, the Simulation Class, GridEntry for both regular and ants
 * Secondary classes (worked on): Visualization, Configuration
 * Main contributions:
     * Implemented all the cell types, and the data structures and algorithms to store cells and update cell states and communicate with the UI
     * created simulation loop in simulation class
     * implemented the display of cell states to the output window
    

## Design goals

#### What Features are Easy to Add
* Simulation:
     * Simulations are easy to Add - make the cells that consist of the simulation class and then add to the GridEntry
        methods in order to select (once GridEntry ants bug is fixed, it will be easy to add the more complicated simulations)
     * Changing the cell neighborhood is also easy, just adjust the one method in GridEntry class
     * it is easy to add additional sliders to change the interactivity with the cells
* Configuration:
    * Easy to add any XML Writer for a Simulation 
    * It is also easy to add a new simulation as we just need to make the XML file for it and a separate class although 
    all the setter and getter methods have already been made 
    * Add any new variable in the XMl file and read it from it and pass it on to other classes 
    * Add a new exception checking algorithm 
* Visualization:
    * Easy to add any GUI components to the screen, whether that be an input text field,
    button, slider, or chart (just use the methods created in Layout)
    * Easy to create multiple screens for different simulations / animations
    * Overall structure makes it easy to add scrolling features

## High-level Design

#### Core Classes
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

## Assumptions that Affect the Design

#### Features Affected by Assumptions
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

## New Features HowTo
* Visualization:
    * Chart and Sliders:
        * To add new components, there are single-purpose methods in the Layout class that can create any of
        the already existing GUI components with the same consistent style. Simple call createButton(),
        createSlider(), or createChart(), etc. to make the method. Then, to add the functionality to the GUI
        component, make a separate method on whichever class the component will be in that implements that
        functionality and reference this method in implementButtons() or implementSlider(), whichever one is
        more suitable.
        * Due to how elements are grouped, it is also easy to just convert any type of Group, e.g. a VBox,
         into containing a ScrollPane as well.
    * Ability to View Two Simulations:
        * Due to the separation of the Visualization class and Splash class, it is very easy to implement
        the feature that allows the user to display two simulations simultaneously. Simply add a button
        that creates a new Stage, which opens up a new window that calls another instance of the Visualization
        class. This is why the "Upload Simulation" button was separated into "Change Simulation" (which would
        just change the current simulation into a different one or start the same simulation over again in the
        same window) and the "Upload New Simulation" button (which would create a new window for the second
        simulation).    
* Configuration 
    * New Simulations:
        * To add new simulations, the XML file has to be made and the individual class has to be made that is 
        going to extend the Configuration class. The class of the new simulation will be using the methods in 
        the Configuration class to set and get its properties and for passing around the information. The Reader
        will still be the same although some more cases will be added to the files for the new simulation. Even 
        a new case will be added to the DialogBox class to read the new simulation and throw an error if a wrong
        file has been chosen. 
    * New Writer:
        * The new writer would require the developer to make a new class for reading the properties of that particular
        simulation and writing those to a XML file. It would also require a conditional in Visualization class so that 
        the XML Writer for that particular simulation is invoked. 
    * New properties:
        * The new properties would require the developer to add a setter and a getter method for that particular variable
        and it would require him/her to make a new variable in the Configuration class and the class of that particular     
        simulation. It would also require the developer to add default values for all those properties. 
    * New Exception:
        * The new exception can be added by extending that particular class with the new exception and then adding that 
        to the catch statement. 
    * Simulation:
        * To run any of the new simulations, simply submit a correctly formatted XML file and it runs!
        * To select the neighbor patterns for the simulation, make sure to include the tag in the XML file, the default is finite, but one can select toroidal boundaries
        * To change the neighbor pattern, go to the simulation specific configuration file and change the binary string in there
        * To run a hexagonal grid, make sure to adjust the shape tag in the XML file, the default is rectangular
        * New sliders were added by adding getter and setter methods in the cell classes and calling the setter method for simulation parameters inside of each cell's public update method
        
        All of the features dealing with the grid layout and Neighbors were made by adding and adjusting to the methods in the Grid Entry Class. There are decision trees which decide what other
        grid entry objects should be considered as neighbors.
        
        New Simulations are added by adding Cell classes, adding to the Grid Entry createCell method, and making children of GridEntry if so needed

#### Easy to Add Features
* Simulation:
    * Simulations are easy to Add - make the cells that consist of the simulation class and then add to the GridEntry
    methods in order to select (once GridEntry ants bug is fixed, it will be easy to add the more complicated simulations)
    * Changing the cell neighborhood is also easy, just adjust the one method in GridEntry class
    * it is easy to add additional sliders to change the interactivity with the cells
* Configuration:
    * Easy to add any XML Writer for a Simulation 
    * It is also easy to add a new simulation as we just need to make the XML file for it and a separate class although 
    all the setter and getter methods have already been made 
    * Add any new variable in the XMl file and read it from it and pass it on to other classes 
    * Add a new exception checking algorithm 
* Visualization:
    * Easy to add any GUI components to the screen, whether that be an input text field,
    button, slider, or chart (just use the methods created in Layout)
    * Easy to create multiple screens for different simulations / animations
    * Overall structure makes it easy to add scrolling features

#### Other Features not yet Done
* Simulation:
    * Some of the simulations (Ant is almost implemented except for 1 bug)
   
* Configuration:
    * Change cell colors as part of initial configuration set up
* Visualization:
    * Dynamic interaction to change grid states
    * Speed slider not fully functioning
