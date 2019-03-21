
This is a simple TicTacToe game created using Java, Gradle, JUnit, JaCoCo, Mockito, based on TDD.  

**Part1**  
**Requirement No.1**
- whenXOutsideBoardThenRuntimeException
- whenYOutsideBoardThenRuntimeException
- whenOccupiedThenRuntimeException

**Requirement No.2**
- givenFirstTurnWhenNextPlayerThenX
- givenLastTurnWasXWhenNextPlayerThen0

**Requirement No.3**
- whenPlayThenNoWinner
- whenPlayAndWholeHorizontalLineThenWinner
- whenPlayAndWholeVerticalLineThenWinner
- whenPlayAndTopBottomDiagonalLineThenWinner
- whenPlayAndBottomTopDiagonalLineThenWinner

**Requirement No.4**
- whenAllBoxesAreFilledThenDraw

**Part2**  
**Additional requirements**  
Users will be able to interrupt the game at any stage, 
and the results will be saved in the MongoDB database.

The tests will be carried out using JUnit together with 
dummy and validation mechanisms from the Mockito platform.





