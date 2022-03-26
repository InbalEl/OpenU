Here is a solution to Maman11, with extensive procedural testing.
The mock versions use the same logic to solve the problem,
but work with CLI args instead of scanners for input.

The tester files use exec to run the mock versions with all possible inputs,
and print out each board scenario with its solution.


To run tests:

Knight:
$ javac Board.java
$ javac KnightMock.java
$ javac KnightTester.java
$ java KnightTester

Chess:
$ javac Board.java
$ javac ChessMock.java
$ javac ChessTester.java
$ java ChessTester

Enjoy!
IE