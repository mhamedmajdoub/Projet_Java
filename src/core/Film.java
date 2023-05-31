package core;

public class Film {
	    private String titre;
	    private String genre;
	    private int duree;
	    private double rating;

	    public Film(String titre, String genre, int duree, double rating) {
	        this.titre = titre;
	        this.genre = genre;
	        this.duree = duree;
	        this.rating=rating;
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
	}

