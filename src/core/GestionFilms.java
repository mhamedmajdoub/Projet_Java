package core;

import java.util.ArrayList;
import java.util.List;

public class GestionFilms {
	    private List<Film> listeFilms;

	    public GestionFilms() {
	        listeFilms = new ArrayList<>();
	    }

	    public void ajouterFilm (Film film) throws FilmAlreadyExistsException {
	        listeFilms.add(film);
	        System.out.println("Film ajouté : " + film.getTitre());
	    }

	    public void supprimerFilm(Film film) throws FilmNotFoundException {
	        if (listeFilms.remove(film)) {
	            System.out.println("Film supprimé : " + film.getTitre());
	        } else {
	            System.out.println("Film non trouvé : " + film.getTitre());
	        }
	    }

	    public Film rechercherFilmParTitre(String titre) throws FilmNotFoundException{
	        for (Film film : listeFilms) {
	            if (film.getTitre().equalsIgnoreCase(titre)) {
	                return film;
	            }
	        }
	        return null;
	    }

	    public void trierFilmsParGenre() {
	        String sortMethod="";
	        System.out.println(sortMethod);
	    }

	    public void afficherListeFilms() throws EmptyFilmListException{
	        if (listeFilms.isEmpty()) {
	            System.out.println("La liste des films est vide.");
	        } else {
	            System.out.println("Liste des films :");
	            for (Film film : listeFilms) {
	                System.out.println(film.getTitre() + " - " + film.getGenre() + " - Durée : " + film.getDuree() + " minutes");
	            }
	        }
	    }
}
