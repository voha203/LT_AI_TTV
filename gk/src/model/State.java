package model;

public class State {
    private Chess[][] state;
    private int score;

    public State() {
        
    }

    public Chess[][] getState() {
        return state;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setState(Chess[][] state) {
        this.state = state;
    }
}
