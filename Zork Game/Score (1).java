package org.uob.a1;

public class Score {
    private int startingScore;
    private int roomsVisited = 0;
    private int puzzlesSolved = 0;
    private final int PUZZLE_VALUE = 10;

    public Score(int startingScore) {
        this.startingScore = startingScore;
    }

    public void visitRoom() {
        roomsVisited++;
    }

    public void solvePuzzle() {
        puzzlesSolved++;
    }

    public double getScore() {
        return startingScore - roomsVisited + (puzzlesSolved * PUZZLE_VALUE);
    }
}
