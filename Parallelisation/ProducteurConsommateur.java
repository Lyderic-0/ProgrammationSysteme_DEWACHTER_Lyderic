import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class ProducteurConsommateur {

    private static final int TAILLE_BUFFER = 5;
    private final Queue<Integer> buffer = new LinkedList<>();

    private final Semaphore placesLibres = new Semaphore(TAILLE_BUFFER);
    private final Semaphore elementsDisponibles = new Semaphore(0);
    private final Semaphore mutexBuffer = new Semaphore(1);

    private int totalProduit = 0;
    private int totalConsomme = 0;

    class Producteur implements Runnable {
        private final String nom;
        private final int nbArticles;

        public Producteur(String nom, int nbArticles) {
            this.nom = nom;
            this.nbArticles = nbArticles;
        }

        @Override
        public void run() {
            for (int i = 0; i < nbArticles; i++) {
                int produit = (int) (Math.random() * 100) + 1;
                try {
                    placesLibres.acquire();
                    mutexBuffer.acquire();

                    buffer.add(produit);
                    totalProduit++;
                    System.out.println(nom + " a produit : " + produit);
                    mutexBuffer.release();
                    elementsDisponibles.release();

                    Thread.sleep(100); // Simulation du temps de production
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    class Consommateur implements Runnable {
        private final String nom;
        private final int nbArticles;

        public Consommateur(String nom, int nbArticles) {
            this.nom = nom;
            this.nbArticles = nbArticles;
        }

        @Override
        public void run() {
            for (int i = 0; i < nbArticles; i++) {
                try {
                    elementsDisponibles.acquire();
                    mutexBuffer.acquire();

                    int produit = buffer.poll();
                    totalConsomme++;
                    System.out.println(nom + " a consommé : " + produit);
                    mutexBuffer.release();
                    placesLibres.release();

                    Thread.sleep(150);


                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    public Producteur creerProducteur(String nom, int nbArticles) {
        return new Producteur(nom, nbArticles);
    }

    public Consommateur creerConsommateur(String nom, int nbArticles) {
        return new Consommateur(nom, nbArticles);
    }


    public int getTotalProduit() {
        return totalProduit;
    }

    public int getTotalConsomme() {
        return totalConsomme;
    }
}