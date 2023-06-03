package core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;




public class GestionFilms {
	
	private List<Film> listeFilms;
	private static final String DATABASE_FILE="DataBase.ser";

    public GestionFilms() {
        listeFilms = new ArrayList<>();
    }

    public void ajouterFilm(Film film) throws FilmAlreadyExistsException, FilmNotFoundException {
    	listeFilms=deserialiserFilms(DATABASE_FILE);
	   if (rechercherFilmParTitre(film.getTitre()) != null) {
	       throw new FilmAlreadyExistsException("Le film existe déjà dans la liste.");
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
            throw new FilmNotFoundException("Film non trouvé : " + film.getTitre());
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

    public void afficherListeFilms() throws EmptyFilmListException, FilmNotFoundException{
    	listeFilms=deserialiserFilms(DATABASE_FILE);
        if (listeFilms.isEmpty()) {
            System.out.println("La liste des films est vide.");
        } else {
            System.out.println("Liste des films :" + "  ================================");
            for (Film film : listeFilms) {
                System.out.println("Titre du film : "+ film.getTitre());
                System.out.println("Genre du film : "+ film.getGenre());
                System.out.println("Durée du film : " + film.getDuree() + " minutes");
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

}
