package core;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	private static final String DATABASE_FILE = "DataBase.ser";

	public static void main(String[] args) throws FilmNotFoundException {
	
	        GestionFilms gestionFilms = new GestionFilms();
	        Scanner scanner = new Scanner(System.in);

	        boolean quitter = false;

	        while (!quitter) {
	            System.out.println("===== Gestion de films =====");
	            System.out.println("1. Ajouter un film");
	            System.out.println("2. Supprimer un film");
	            System.out.println("3. Rechercher un film par titre");
	            System.out.println("4. Trier les films par titre");
	            System.out.println("5. Trier les films par genre");
	            System.out.println("6. Trier les films par Duree");
	            System.out.println("7. Afficher la liste des films");
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
	                    System.out.print("What's the rating of this film? : ");
	                    double rating=scanner.nextDouble();
	                    scanner.nextLine(); 

	                    Film film = new Film(titre, genre, duree, rating);
						try {
							if(gestionFilms.rechercherFilmParTitre(film.getTitre())==null)
								gestionFilms.ajouterFilm(film);
							else throw new FilmAlreadyExistsException("Ce film exist déja dans votre base de données :( ");
						} catch (FilmAlreadyExistsException e) {
							e.printStackTrace();
						}
						catch(NotFilmException e) {
							e.printStackTrace();
						}
		                break;
	                    
	                    
	                ///// Supprimer un film
	                case 2:
	                    System.out.print("Titre du film à supprimer : ");
	                    String titreSupprimer = scanner.nextLine();
	                    Film filmASupprimer = null;
						filmASupprimer = gestionFilms.rechercherFilmParTitre(titreSupprimer);
				        if (filmASupprimer != null) {
				        	try {
								gestionFilms.supprimerFilm(filmASupprimer);
								System.out.println("Film supprimé avec succès !");
				            }catch (FilmNotFoundException e) {
	                    		System.out.println(e.getMessage());
	                    	}
				        }
				        else System.out.println("Le film n'existe pas dans la base de données.");
		                break;
	                    
	                //// Rechercher un film par titre
	                case 3:
	                    System.out.print("Titre du film à rechercher : ");
	                    String titreRechercher = scanner.nextLine();
	                    if(titreRechercher=="0") break;
	                    else {
							Film filmRecherche = null;
							filmRecherche = gestionFilms.rechercherFilmParTitre(titreRechercher);
			                    if (filmRecherche != null) {
			                        System.out.println("Film trouvé : ===================");
			                        System.out.println("Titre : "+ filmRecherche.getTitre());
			                        System.out.println("Genre : "+ filmRecherche.getGenre());
			                        System.out.println("Duree : "+ filmRecherche.getDuree());
			                        System.out.println("Rating : "+ filmRecherche.getRating());
			                    } else {
			                    	throw new FilmNotFoundException(titreRechercher+" n'existe pas dans votre base de données :(");
			                    }
		                    break;
	                    }
	                case 4:
	                    try{
	                    	gestionFilms.trierFilmsParTitre();
							gestionFilms.afficherListeFilms();
	                    } catch(EmptyFilmListException e) {
	                    	e.printStackTrace();
							System.out.println("Choisissez 1 pour ajouter des films à votre base de données :)");
	                    }
	                    break;	
	                    
	                case 5:
					try {
						gestionFilms.trierFilmsParGenre();
						gestionFilms.afficherListeFilms();
					} catch (EmptyFilmListException e) {
						e.printStackTrace();
						System.out.println("Choisissez 1 pour ajouter des films à votre base de données :)");
					}
	                    break;

	                case 6:
					try {
						gestionFilms.trierFilmsParDuree();
						gestionFilms.afficherListeFilms();
					} catch (EmptyFilmListException e) {
						e.printStackTrace();
						System.out.println("Choisissez 1 pour ajouter des films à votre base de données :)");
					}
	                    break;
	                case 7:
						try {
							gestionFilms.afficherListeFilms();
						} catch (EmptyFilmListException e) {
							e.printStackTrace();
							System.out.println("Choisissez 1 pour ajouter des films à votre base de données :)");
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
