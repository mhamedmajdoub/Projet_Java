package core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;




public class GestionFilms {
	
	private List<Film> listeFilms;
	private static final String DATABASE_FILE="DataBase.ser";

    public GestionFilms() {
        listeFilms = new ArrayList<>();
    }

    public void ajouterFilm(Film film) throws FilmAlreadyExistsException, NotFilmException {
    	listeFilms=deserialiserFilms(DATABASE_FILE);
	   if (rechercherFilmParTitre(film.getTitre()) != null) {
	       throw new FilmAlreadyExistsException("Le film existe déjà dans la liste.");
	   }
	   if(film.getDuree()>200) {
		   throw new NotFilmException("Un film ne peut pas avoir une durée plus que 200 minutes");
	   }
	
	   listeFilms.add(film);
	   serialiserFilms(DATABASE_FILE);
	   System.out.println("Film ajouté : " + film.getTitre());
}
    
    

    public void supprimerFilm(Film film) throws FilmNotFoundException {
        listeFilms = deserialiserFilms(DATABASE_FILE);
        Iterator<Film> iterator = listeFilms.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Film currentFilm = iterator.next();
            if (currentFilm.equals(film)) {
                iterator.remove();
                found = true;
                System.out.println("Film supprimé : " + film.getTitre());
                break;
            }
        }
        if (found) {
            serialiserFilms(DATABASE_FILE);
        } else {
            throw new FilmNotFoundException(film.getTitre()+ " n'existe pas dans votre base de données :(");
        }
    }

    
    public Film rechercherFilmParTitre(String titre){
    	listeFilms=deserialiserFilms(DATABASE_FILE);
        for (Film film : listeFilms) {
            if (film.getTitre().equalsIgnoreCase(titre)) {
                return film;
            }
        }
        serialiserFilms(DATABASE_FILE);
        return null;
    }

    public void afficherListeFilms() throws EmptyFilmListException {
    	listeFilms=deserialiserFilms(DATABASE_FILE);
        if (listeFilms.isEmpty()) {
            System.out.println("La liste des films est vide.");
        } else {
            System.out.println("Liste des films :" + "  ================================");
            for (Film film : listeFilms) {
                System.out.println("Titre du film : "+ film.getTitre());
                System.out.println("Genre du film : "+ film.getGenre());
                System.out.println("Durée du film : " + film.getDuree() + " minutes");
                System.out.println("Rating : "+ film.getRating());
                System.out.println("Date d'ajout : "+ film.getDateAjout());
                System.out.println("================================================");
            }
        }
    }
    
    
    // Méthode pour sérialiser les films
    public void serialiserFilms(String DATABASE_FILE) {
        try {
            FileOutputStream fichier = new FileOutputStream(DATABASE_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fichier);
            oos.writeObject(listeFilms);
            oos.close();
            fichier.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
 // Méthode pour désérialiser les films
    public List<Film> deserialiserFilms(String DATABASE_FILE) {
        try {
            FileInputStream fichier = new FileInputStream(DATABASE_FILE);
            ObjectInputStream ois = new ObjectInputStream(fichier);
            listeFilms = (List<Film>) ois.readObject();
            ois.close();
            fichier.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
		return listeFilms;
    }
    
    // Trier les films par Genre
    public void trierFilmsParGenre() throws EmptyFilmListException{
    	listeFilms=deserialiserFilms(DATABASE_FILE);
    	if(!listeFilms.isEmpty()) {
	        Collections.sort(listeFilms, new Comparator<Film>() {
	            public int compare(Film film1, Film film2) {
	                return film1.getGenre().compareTo(film2.getGenre());
	            }
	        });
	        System.out.println("Liste des films triés par Genre :");
	        serialiserFilms(DATABASE_FILE);
    }
	else throw new EmptyFilmListException("La liste des films est vide !");
    }

    // Trier les films par leurs durées
    public void trierFilmsParDuree() throws EmptyFilmListException{
    	listeFilms=deserialiserFilms(DATABASE_FILE);
    	if(!listeFilms.isEmpty()) {
        Collections.sort(listeFilms, new Comparator<Film>() {
            public int compare(Film film1, Film film2) {
                return Integer.compare(film1.getDuree(), film2.getDuree());
            }
        });
        System.out.println("Liste des films triés par durée :");
        serialiserFilms(DATABASE_FILE);
    	}
		else throw new EmptyFilmListException("La liste des films est vide !");
    }

    // Trier les films par leurs titres
	public void trierFilmsParTitre() throws EmptyFilmListException{
		listeFilms=deserialiserFilms(DATABASE_FILE);
		if(!listeFilms.isEmpty()) {
			Collections.sort(listeFilms, new Comparator<Film>() {
			    public int compare(Film film1, Film film2) {
			        return film1.getTitre().compareTo(film2.getTitre());
			    }
			});
	        System.out.println("Liste des films triés par Titre :");
	        serialiserFilms(DATABASE_FILE);
	}
		else throw new EmptyFilmListException("La liste des films est vide !");
	}
	
    public void trierFilmsParDateAjoutDescendant() throws EmptyFilmListException {
    	listeFilms=deserialiserFilms(DATABASE_FILE);
    	if(!listeFilms.isEmpty()) {
	        Collections.sort(listeFilms, new Comparator<Film>() {
	            public int compare(Film film1, Film film2) {
	                return film1.getDateAjout().compareTo(film2.getDateAjout());
	            }
	        });
	        System.out.println("Liste des films triés par date d'ajout :");
	        serialiserFilms(DATABASE_FILE);
    	}
    	else throw new EmptyFilmListException("La liste des films est vide !");
    }

    public void trierFilmsParDateAjoutAscendant() throws EmptyFilmListException {
    	listeFilms=deserialiserFilms(DATABASE_FILE);
    	if(!listeFilms.isEmpty()) {
	        Collections.sort(listeFilms, new Comparator<Film>() {
	            public int compare(Film film1, Film film2) {
	                return film1.getDateAjout().compareTo(film2.getDateAjout());
	            }
	        });
	        Collections.reverse(listeFilms);
	        System.out.println("Liste des films triés par date d'ajout :");
	        serialiserFilms(DATABASE_FILE);
    	}
    	else throw new EmptyFilmListException("La liste des films est vide !");
    }
}
