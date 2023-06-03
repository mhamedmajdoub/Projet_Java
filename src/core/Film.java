package core;
import java.io.*;
import java.util.Date;

public class Film implements Serializable {
	
	private String titre;
    private String genre;
    private int duree;
    private double rating;
    private Date dateAjout;

    public Film(String titre, String genre, int duree, double rating) {
        this.titre = titre;
        this.genre = genre;
        this.duree = duree;
        this.rating=rating;
        this.dateAjout = new Date();
    }

    // Getters et Setters
    public double getRating() {
		return rating;
	}
    public void setRating(double rating) {
		this.rating = rating;
	}

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
    public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}
    public Date getDateAjout() {
		return dateAjout;
	}
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Film otherFilm = (Film) obj;
        return getTitre().equals(otherFilm.getTitre());
    }


}
