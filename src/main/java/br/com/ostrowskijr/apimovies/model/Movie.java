package br.com.ostrowskijr.apimovies.model;

public class Movie {

    private int year;
    private String title;
    private String studios;
    private String producers;
    private String winner;

    public void setYear(int year) {
        this.year = year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStudios(String studios) {
        this.studios = studios;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public String getStudios() {
        return studios;
    }

    public String getProducers() {
        return producers;
    }

    public String getWinner() {
        return winner;
    }
    
    public String toString(){
        return "{year : " + getYear() + " title: " + getTitle() + " studios: " + getStudios() + " producers: " + getProducers() + " winner: " + getWinner() + "}";
    }
}
