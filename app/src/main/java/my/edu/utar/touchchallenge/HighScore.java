package my.edu.utar.touchchallenge;

public class HighScore implements Comparable<HighScore> {
    private String name;
    private int score;

    public HighScore(String name, int score) {
        this.name = name;
    }

    public HighScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(HighScore other) {
        return Integer.compare(score, other.getScore());

    }
}
