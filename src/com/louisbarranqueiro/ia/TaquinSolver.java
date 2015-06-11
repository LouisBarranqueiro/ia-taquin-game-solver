package com.louisbarranqueiro.ia;

import java.util.ArrayList;

public class TaquinSolver {

    /**
     * Solve a taquin
     *
     * @param taquin A taquin
     * @param hType  A heuristique
     * @param silent Silent mode (true : display alternatives, false : hide alternatives)
     */
    public static void solve(Taquin taquin, int hType, boolean silent) {
        // Contains possible solution not explored
        ArrayList<TaquinState> open = new ArrayList<TaquinState>();
        // Contains solution explored
        ArrayList<TaquinState> close = new ArrayList<TaquinState>();
        // Contains alternatives of taquin state
        ArrayList<TaquinState> alternatives = new ArrayList<TaquinState>();
        final long startTime = System.currentTimeMillis();
        final long endTime;

        // Init `open` Collection with initial state of a taquin
        open.add(taquin.getInitialState());
        // A* algorithm
        while (!open.isEmpty()) {
            for (int i = 0; i < open.size(); i++) {
                TaquinState state = new TaquinState(open.get(i));

                alternatives = state.findAlternatives(taquin.getSolutionState(), hType);

                // Display alternatives
                if (silent) {
                    for (TaquinState alternative : alternatives) {
                        System.out.println(alternative.toString());
                    }
                }

                // Checks all alternatives found
                for (TaquinState alternative : alternatives) {
                    // Checks if the current alternative is the solution
                    if (alternative.getH() == 0) {
                        endTime = System.currentTimeMillis();
                        System.out.println("Solution found in " + (endTime - startTime) + "ms with hType : " + hType);
                        System.out.println(alternative.toString());
                        return;
                    } else {
                        if (!open.contains(alternative) && !close.contains(alternative)) {
                            open.add(alternative);
                        } else if ((open.contains(alternative)) && (open.get(open.indexOf(alternative)).getF() < alternative.getF())) {
                            open.remove(open.indexOf(alternative));
                            open.add(alternative);
                        } else if ((close.contains(alternative)) && (close.get(close.indexOf(alternative)).getF() < alternative.getF())) {
                            close.remove(close.indexOf(alternative));
                            open.add(alternative);
                        }
                    }
                }
            }
        }
    }
}