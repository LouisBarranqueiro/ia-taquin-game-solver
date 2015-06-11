package com.louisbarranqueiro.ia;

import java.util.ArrayList;

public class TaquinState implements Cloneable {
    int g;
    int h;
    int f;
    int[][] state;
    TaquinState parent;
    ArrayList<TaquinState> alternatives;

    /**
     * Constructor
     *
     * @param state
     */
    public TaquinState(int[][] state) {
        this.setState(state);
        this.alternatives = new ArrayList<TaquinState>();
    }

    /**
     * Constructor
     *
     * @param stateTaquin
     */
    public TaquinState(TaquinState stateTaquin) {
        this.setState(stateTaquin.getState());
        this.setParent(stateTaquin.getParent());
        this.setG(stateTaquin.getG());
        this.alternatives = new ArrayList<TaquinState>();
    }

    /**
     * Constructor
     */
    public TaquinState() {
        this.setG(0);
        this.setH(0);
        this.setF(0);
        this.setParent(this);
        this.alternatives = new ArrayList<TaquinState>();
    }

    /**
     * Return g
     *
     * @return g
     */
    public int getG() {
        return this.g;
    }

    /**
     * Set g
     *
     * @param g
     */
    public void setG(int g) {
        this.g = g;
    }

    /**
     * Return h
     *
     * @return h
     */
    public int getH() {
        return this.h;
    }

    /**
     * Set h
     *
     * @param h
     */
    public void setH(int h) {
        this.h = h;
    }

    /**
     * Return f
     *
     * @return f
     */
    public int getF() {
        return this.f;
    }

    /**
     * Set f
     *
     * @param f
     */
    public void setF(int f) {
        this.f = f;
    }

    /**
     * Return state
     *
     * @return state
     */
    public int[][] getState() {
        return this.state;
    }

    /**
     * Set state
     *
     * @param state
     */
    public void setState(int[][] state) {
        this.state = state;
    }

    /**
     * Return parent
     *
     * @return parent
     */
    public TaquinState getParent() {
        return this.parent;
    }

    /**
     * Set parent
     *
     * @param parent
     */
    public void setParent(TaquinState parent) {
        this.parent = parent;
    }

    /**
     * Find all alternatives
     *
     * @param solution The solution of the taquin
     * @param hType    A heuristique
     * @return alternatives A list of alternatives from the current TaquinState
     */
    public ArrayList<TaquinState> findAlternatives(TaquinState solution, int hType) {
        int i = 0;
        int n = 0;

        // Search the zero in the state
        zeroloop:
        for (i = 0; i < this.state.length; i++) {
            for (n = 0; n < this.state[i].length; n++) {
                if (this.state[i][n] == 0) {
                    break zeroloop;
                }
            }
        }

        // Check if the 0 can be moved at top
        if ((i - 1) >= 0) {
            // Clone the current object
            TaquinState topAlternative = (TaquinState) this.clone();
            // Movement
            topAlternative.state[i][n] = this.state[i - 1][n];
            topAlternative.state[i - 1][n] = 0;
            topAlternative.setG(topAlternative.getG() + 1);

            // Calculate the `h` with the selected heuristique
            if (hType == 1) {
                topAlternative.setH(calcH1(topAlternative, solution));
            } else {
                topAlternative.setH(calcH2(topAlternative, solution));
            }

            topAlternative.setF(calcF(topAlternative.getG(), topAlternative.getH()));
            // Add the alternatives to the list
            alternatives.add(topAlternative);
        }

        // Check if the 0 can be moved at right
        if ((n + 1) < this.state[0].length) {
            // Clone the current object
            TaquinState rightAlternative = (TaquinState) this.clone();
            // Movement
            rightAlternative.state[i][n] = this.state[i][n + 1];
            rightAlternative.state[i][n + 1] = 0;
            rightAlternative.setG(rightAlternative.getG() + 1);

            // Calculate the `h` with the selected heuristique
            if (hType == 1) {
                rightAlternative.setH(calcH1(rightAlternative, solution));
            } else {
                rightAlternative.setH(calcH2(rightAlternative, solution));
            }

            rightAlternative.setF(calcF(rightAlternative.getG(), rightAlternative.getH()));
            // Add the alternatives to the list
            alternatives.add(rightAlternative);
        }

        // Check if the 0 can be moved at bottom
        if ((i + 1) < this.state.length) {
            // Clone the current object
            TaquinState bottomAlternative = (TaquinState) this.clone();
            // Movement
            bottomAlternative.state[i][n] = this.state[i + 1][n];
            bottomAlternative.state[i + 1][n] = 0;
            bottomAlternative.setG(bottomAlternative.getG() + 1);

            // Calculate the `h` with the selected heuristique
            if (hType == 1) {
                bottomAlternative.setH(calcH1(bottomAlternative, solution));
            } else {
                bottomAlternative.setH(calcH2(bottomAlternative, solution));
            }

            bottomAlternative.setF(calcF(bottomAlternative.getG(), bottomAlternative.getH()));
            // Add the alternatives to the list
            alternatives.add(bottomAlternative);
        }

        // Check if the 0 can be moved at left
        if ((n - 1) >= 0) {
            // Clone the current object to an alternative
            TaquinState leftAlternative = (TaquinState) this.clone();
            // Movement
            leftAlternative.state[i][n] = this.state[i][n - 1];
            leftAlternative.state[i][n - 1] = 0;
            leftAlternative.setG(leftAlternative.getG() + 1);

            // Calculate the `h` with the selected heuristique
            if (hType == 1) {
                leftAlternative.setH(calcH1(leftAlternative, solution));
            } else {
                leftAlternative.setH(calcH2(leftAlternative, solution));
            }

            leftAlternative.setF(calcF(leftAlternative.getG(), leftAlternative.getH()));
            // Add the alternatives to the list
            alternatives.add(leftAlternative);
        }

        return alternatives;
    }

    /**
     * Convert TaquinState to a string
     */
    @Override
    public String toString() {
        String string = "g:" + g + " - h: " + h + "\n";
        string += "-------------\n";

        for (int i = 0; i < this.state.length; i++) {
            string += "| ";
            for (int n = 0; n < this.state[i].length; n++) {
                string += this.state[i][n] + ((this.state[i][n] > 9) ? " | " : "  | ");
            }
            string += "\n";
        }
        string += "-------------\n";

        return string;
    }


    /**
     * Calculate f
     */
    private static int calcF(int g, int h) {
        return g + h;
    }

    /**
     * Calculate h (heuristique: number of box incorrectly placed)
     *
     * @param solution The solution of the taquin
     * @return h
     */
    public static int calcH1(TaquinState currentTaquin, TaquinState solution) {
        int h = 0;
        // Go through the array to check which box is incorrectly placed
        for (int i = 0; i < currentTaquin.getState().length; i++) {
            for (int n = 0; n < currentTaquin.getState()[i].length; n++) {
                // Increment 'h' the number of box incorrectly placed
                if (currentTaquin.getState()[i][n] != solution.getState()[i][n]) {
                    h++;
                }
            }
        }

        return h;
    }

    /**
     * Calculate h (heuristique: number of move needed to place all box correctly)
     *
     * @param solution The solution of the taquin
     * @return h
     */
    public static int calcH2(TaquinState currentTaquin, TaquinState solution) {
        int h = 0;
        // Go through the array to check which box is incorrectly placed
        for (int i = 0; i < currentTaquin.getState().length; i++) {
            for (int n = 0; n < currentTaquin.getState()[i].length; n++) {
                // Checks if the current state box is equals to the current solution box
                if (currentTaquin.getState()[i][n] != solution.getState()[i][n]) {
                    for (int j = 0; j < solution.getState().length; j++) {
                        for (int m = 0; m < solution.getState()[j].length; m++) {
                            // Checks if the current box is equals to the current solution box
                            if (currentTaquin.getState()[i][n] == solution.getState()[j][m]) {
                                // Calculate the vertical move
                                if (i > j) {
                                    h += (i - j);
                                } else {
                                    h += (j - i);
                                }
                                // Calculate the horizontal move
                                if (n > m) {
                                    h += (n - m);
                                } else {
                                    h += (m - n);
                                }
                            }
                        }
                    }
                }
            }
        }

        return h;
    }

    /**
     * Clone an object TaquinState
     *
     * @return
     */
    @Override
    public Object clone() {
        TaquinState taquinstate = new TaquinState(this);

        int[][] state = new int[this.getState().length][this.getState().length];

        // Copy manually the array
        for (int i = 0; i < this.getState().length; i++) {
            for (int n = 0; n < this.getState()[i].length; n++) {
                state[i][n] = this.getState()[i][n];
            }
        }

        taquinstate.setState(state);

        return taquinstate;
    }

    /**
     * Compare 2 TaquinState
     *
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals(Object object) {
        if (object != null && object instanceof TaquinState) {
            TaquinState stateTaquin = (TaquinState) object;

            // Compare 2 states
            for (int i = 0; i < this.state.length; i++) {
                for (int n = 0; n < this.state[i].length; n++) {
                    if (this.state[i][n] != stateTaquin.state[i][n]) {
                        return false;
                    }
                }
            }

            return true;
        }

        return false;
    }
}
