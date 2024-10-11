Insert args as: fileName numIter
- fileName is the name of the .gol file.
- numIter is the number of iterations for the simulation to run.


### Outline

1. Read in the file as per instructions in the game_of_life.pdf. Variables such as grid size, starting position and initial config are parsed.
2. Setup the initial grid according to the initial config and its starting position. 
3. Create a copy of the grid for checking, while the original grid is for updating.
3. Loop through each cell in the grid, and count the number of active neighbours in the 8 surrounding cells (the grid is padded with 1 extra row and column on each side such that every "true" cell has 8 neighbours).
4. Use the logic from the pdf to determine if each cell should be active or inactive. Update the respective cell in the original grid.
5. At the end of each iteration, update the copid grid and print the grid.

### Notes
using 

    boolean[][] copiedGrid = originalGrid;
    
does not work. Need to manually copy each cell into copiedGrid. Done here via the copyMatrix method.

