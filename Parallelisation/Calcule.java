public class Calcule extends Thread{
	
	int resultat = 0;
	int valeur = 0;
	int multiplicateur = 0;
	int index = 0;
	
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
	
	int getResultat(){
		return (int) resultat;
	}
}