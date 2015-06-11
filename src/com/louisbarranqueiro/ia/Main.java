package com.louisbarranqueiro.ia;

public class Main {

    public static void main(String[] args) {
        // Taquin 3x3
        int[][] initialState3x3  = new int[][]{{2, 8, 3},{1, 6, 4},{7, 0, 5}};
        int[][] solutionState3x3 = new int[][]{{1, 2, 3},{8, 0, 4},{7, 6, 5}};

        // Taquin 4x4
        int[][] initialState4x4  = new int[][]{{1, 3, 6, 4}, {5, 2, 7, 8}, {9, 10, 0, 11}, {13, 14, 15, 12}};
        int[][] solutionState4x4 = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};

        // Create 2 taquin, 1 3x3, 1 4x4
        Taquin taquin3x3 = new Taquin(initialState3x3, solutionState3x3);
        Taquin taquin4x4 = new Taquin(initialState4x4, solutionState4x4);

        // Solve a taquin 3x3 and 4x4 with first heuristique (calculate the number of box incorrectly placed)
        TaquinSolver.solve(taquin3x3, 1, false);
        TaquinSolver.solve(taquin4x4, 1, false);

        // Solve a taquin 3x3 and 4x4 with second heuristique (calculate the number of move needed to solve the taquin
        TaquinSolver.solve(taquin3x3, 2, false);
        TaquinSolver.solve(taquin4x4, 2, false);
    }
}
