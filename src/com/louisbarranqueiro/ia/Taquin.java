package com.louisbarranqueiro.ia;

public class Taquin {

    TaquinState initialState;
    TaquinState solutionState;

    /**
     * Constructor
     *
     * @param initialState
     * @param solutionState
     */
    public Taquin(int[][] initialState, int[][] solutionState) {
        this.setInitialState(initialState);
        this.setSolutionState(solutionState);
    }

    /**
     * Return initialState
     *
     * @return initialState
     */
    public TaquinState getInitialState() {
        return this.initialState;
    }

    /**
     * Set initialState
     *
     * @param state
     */
    public void setInitialState(int[][] state) {
        this.initialState = new TaquinState(state);
    }

    /**
     * Return solutionState
     *
     * @return solutionState
     */
    public TaquinState getSolutionState() {
        return this.solutionState;
    }

    /**
     * Set solutionState
     *
     * @param state
     */
    public void setSolutionState(int[][] state) {
        this.solutionState = new TaquinState(state);
    }
}