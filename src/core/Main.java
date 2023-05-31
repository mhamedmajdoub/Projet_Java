package core;
import java.util.Scanner;

public class Main {
		  public static void main(String[] args) {
		        GestionFilms gestionFilms = new GestionFilms();
		        Scanner scanner = new Scanner(System.in);

		        boolean quitter = false;

		        while (!quitter) {
		            System.out.println("===== Gestion de films =====");
		            System.out.println("1. Ajouter un film");
		            System.out.println("2. Supprimer un film");
		            System.out.println("3. Rechercher un film par titre");
		            System.out.println("4. Trier les films par genre");
		            System.out.println("5. Afficher la liste des films");
		            System.out.println("0. Quitter");

		            System.out.print("Choisissez une option : ");
		            int choix = scanner.nextInt();
		            scanner.nextLine(); // Pour consommer le saut de ligne après la lecture de l'entier

		            switch (choix) {
		            
		            ///// ajouter un film
		                case 1:
		                    System.out.print("Titre du film : ");
		                    String titre = scanner.nextLine();
		                    System.out.print("Genre du film : ");
		                    String genre = scanner.nextLine();
		                    System.out.print("Durée du film (en minutes) : ");
		                    int duree = scanner.nextInt();
		                    scanner.nextLine(); // Pour consommer le saut de ligne après la lecture de l'entier
		                    double rating=scanner.nextDouble();

		                    Film film = new Film(titre, genre, duree, rating);
						try {
							gestionFilms.ajouterFilm(film);
						} catch (FilmAlreadyExistsException e) {
							e.printStackTrace();
						}
		                    break;
		                    
		                    
		                ///// Supprimer un film
		                case 2:
		                    System.out.print("Titre du film à supprimer : ");
		                    String titreSupprimer = scanner.nextLine();

						Film filmASupprimer = null;
						try {
							filmASupprimer = gestionFilms.rechercherFilmParTitre(titreSupprimer);
						} catch (FilmNotFoundException e) {
							e.printStackTrace();
						}
		                    if (filmASupprimer != null) {
		                        try {
									gestionFilms.supprimerFilm(filmASupprimer);
								} catch (FilmNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		                    } else {
		                        System.out.println("Film non trouvé : " + titreSupprimer);
		                    }
		                    break;
		                    
		                //// Rechercher un film par titre
		                case 3:
		                    System.out.print("Titre du film à rechercher : ");
		                    String titreRechercher = scanner.nextLine();

						Film filmRecherche = null;
						try {
							filmRecherche = gestionFilms.rechercherFilmParTitre(titreRechercher);
						} catch (FilmNotFoundException e) {
							e.printStackTrace();
						}
		                    if (filmRecherche != null) {
		                        System.out.println("Film trouvé : " + filmRecherche.getTitre());
		                    } else {
		                        System.out.println("Film non trouvé : " + titreRechercher);
		                    }
		                    break;
		                    
		                    
		                
		                case 4:
		                    gestionFilms.trierFilmsParGenre();
		                    System.out.println("Liste des films triés par genre.");
		                    break;
		                case 5:
						try {
							gestionFilms.afficherListeFilms();
						} catch (EmptyFilmListException e) {
							e.printStackTrace();
						}
		                    break;
		                case 0:
		                    quitter = true;
		                    System.out.println("Au revoir !");
		                    break;
		                default:
		                    System.out.println("Option invalide.");
		                    break;
		            }

		            System.out.println();
		        }

		        scanner.close();

	}

}
