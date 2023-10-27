package controleur;

import personnages.Gaulois;
import villagegaulois.Village;

public class ControlAcheterProduit {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
			ControlTrouverEtalVendeur controlTrouverEtalVendeur,
			Village village) {
		this.village = village;
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}
	
	public boolean acheterProduitDispo(String produit) {
		return (village.rechercherVendeursProduit(produit) != null);
	}
	
	public boolean allerVoirVendeur(String nomVendeur, String produit) {
		// TODO village.
		//village.rechercherVendeursProduit(produit);
		return controlVerifierIdentite.verifierIdentite(nomVendeur);
	}
	
	public int acheterProduit(String nomVendeur, int nbProduits) {
		
		// TODO Etal etalVendeur = controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur);
		
		return nbProduitsAcheter;
	}
}
