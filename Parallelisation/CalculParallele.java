public class CalculParallele {
	private static final int[] DONNEES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private static final int MULTIPLICATEUR = 1000;   
	
	/*
	 * int cores = Runtime.getRuntime().availableProcessors();
	 * int nbThreads = 2 * cores;
	 */
	

    public static void main(String[] args) {
        System.out.println("=== Calcul Parallelle ===");		

		Calcule[] threads = new Calcule[DONNEES.length];
		int sommeTotal = 0;
		
        // TODO: Ce code sera remplacé par une version parallèle
        for (int i = 0; i < DONNEES.length; i++) {
            threads[i] = new Calcule(DONNEES[i], MULTIPLICATEUR, i);
			threads[i].start();
        }
		
		for (int i = 0; i < DONNEES.length; i++) {
			try{
				threads[i].join();
				
			}catch(InterruptedException e){}	

		
        }

		

    }
}