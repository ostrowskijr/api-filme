package br.com.ostrowskijr.apimovies.model;

public class Winner {

    private String producer;
    private int interval;
    private int previousWin;
    private int followingWin;

    public Winner(String producer, int interval, int previousWin, int followingWin) {
        this.producer = producer;
        this.interval = interval;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void setPreviousWin(int previousWin) {
        this.previousWin = previousWin;
    }

    public void setFollowingWin(int followingWin) {
        this.followingWin = followingWin;
    }

    public String getProducer() {
        return producer;
    }

    public int getInterval() {
        return interval;
    }

    public int getPreviousWin() {
        return previousWin;
    }

    public int getFollowingWin() {
        return followingWin;
    }

    @Override
    public String toString() {
        return "produtcer : " + getProducer() + " interval : " + getInterval() + " previousWin : " + getPreviousWin()
                + " followingWin : " + getFollowingWin();
    }

}
