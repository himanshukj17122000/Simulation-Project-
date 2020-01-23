RPS Design
====


Names: Olga Suchankova, Kyra Chan, Himanshu Jain



### Classes:

Destructor Class

The thought behind our implementation of RPS is to create a superclass that outlines all the methods and data that
are required to implement a destructor object. These include a set of objects that the particular destroyer can destroy, methods to add and remove
objects from the can destroy set, an image which represents the destructor, and a method to set the image of the destroyer
into the gui.


Player Class

The player class contains the attribute of the player score. The methods it contains are an update score method which
updates the player score, and a get score method which returns the player score. You also need a private attribute which 
is updated after each round to represent the current destructor, a pickDestructor method, and a returnDistructor method.

Main

The main class has to ininitalize the destructor, using an input file that specifies what a Destructor destroys and what 
destroys the particular Destructor. This would be accomplished with the Destroyer class methods. There is also a method 
which creates a set of all potential destroyers from the same input file.

Then the main program initializes the players, setting their scores to 0, as well as setting the number of rounds in the
game. The game loop will have the players select a destructor and then check to see if one destroys another, and if so
the score should be updated. The game loop ends when the number of rounds or points is reached, and then the main function
declares a winner.

