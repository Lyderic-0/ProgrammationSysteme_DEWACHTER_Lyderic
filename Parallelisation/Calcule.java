public class Calcule extends Thread{
	
	private long resultat = 0;
	private int valeur = 0;
	private int multiplicateur = 0;
	private int index = 0;
	
	public Calcule(int valeur, int multiplicateur, int index){
		this.valeur = valeur;
		this.multiplicateur = multiplicateur;
		this.index = index;
	}
	
	@Override
	public void run() {
		// TODO: Implémenter le calcul intensif
		// Même logique que dans la boucle interne de CalculSequentiel
		
		// Calcul intensif simulé par des opérations mathématiques
		for (int j = 0; j < multiplicateur; j++) {
			resultat += valeur * valeur + valeur;
							// on pourrais aussi mettre un sleep
		}
		
		

	}
	
	public long getResultat(){
		return resultat;
	}
}