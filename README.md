# Brickwork
**Java program for brick layering.** Assignment 2 from MentorMate application.
Created in a way that no brick in the generated second layer lies exactly on a brick from the first layer.

## Usage
* Input **N M** (dimensions of the area, both numbers must be even)

```2 4```

* Add a single value separated by a space for each line N and following 
column M describing the bricks layout in the first layer. 
Each brick is marked with two equal numbers written in the squares of the area that are covered by this brick. 
All bricks are marked with whole numbers ranging from 1 to the total number of the bricks. 
M and N are even numbers not exceeding 100.

```1 1 2 2```

```3 3 4 4```

* Generates second layer if possible and prints it. In case the algorithm cannot create a solution gives: ```Error -1```