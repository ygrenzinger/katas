Meta:

Narrative:
As a software craftsman
I want to demo my capacity to do live ATDD
So that I can become a coach at SGCIB

Scenario: Any live cell with fewer than two live neighbours dies, as if caused by under-population
Given there is a cell with two live neighbours dies
When the next generation is created
Then this cell is dead
