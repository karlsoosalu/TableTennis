package TableTennis;

public class Player {
    String name;
    int totalgames;
    int wins;
    int loses;
    int points;
    int rating;
    int streak;
    int beststreak;
    int worststreak;
    int undertable;

    public Player(String name, int totalgames, int wins, int loses, int points, int rating, int streak, int beststreak, int worststreak,int undertable) {
        this.name = name;
        this.totalgames = totalgames;
        this.wins = wins;
        this.loses = loses;
        this.points = points;
        this.rating = rating;
        this.streak = streak;
        this.beststreak = beststreak;
        this.worststreak = worststreak;
        this.undertable=undertable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public int getTotalgames() {
        return totalgames;
    }

    public void setTotalgames(int totalgames) {
        this.totalgames = totalgames;
    }

    public int getBeststreak() {
        return beststreak;
    }

    public void setBeststreak(int beststreak) {
        this.beststreak = beststreak;
    }

    public int getWorststreak() {
        return worststreak;
    }

    public void setWorststreak(int worststreak) {
        this.worststreak = worststreak;
    }

    public int getUndertable() {
        return undertable;
    }

    public void setUndertable(int undertable) {
        this.undertable = undertable;
    }
}
