public class TestCompteur {
	public static int NOMBRE_TEST = 50;
	public static int NOMBRE_THREAD = 9;
	public static int NOMBRE_INCREMENT = 1111111;
	

    public static void main(String[] args) {
        for (int i=0; i<NOMBRE_TEST; i++){
			CompteurDangereux.resetCompteur();
			
			Thread[] threads = new Thread[NOMBRE_THREAD];
			for (int j=0; j<NOMBRE_THREAD;j++){
				threads[j] = new Thread(new CompteurDangereux.Incrementeur("nom", NOMBRE_INCREMENT));
			}
			
			for (Thread thread : threads){
				thread.start();
			}
			
			try{
				for (Thread thread : threads){
					thread.join();
				}
				
			}catch(InterruptedException e){
				Thread.currentThread().interrupt();
			}
			
			System.out.print(CompteurDangereux.getCompteur());
			
		}
		
		
	}
}