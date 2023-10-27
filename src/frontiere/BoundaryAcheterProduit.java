package frontiere;

import java.util.Scanner;

import controleur.ControlAcheterProduit;
import personnages.Gaulois;

public class BoundaryAcheterProduit {
	private Scanner scan = new Scanner(System.in);
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		System.out.println("Quel produit voulez-vous acheter ?");
		String produit = scan.next();
		if(!controlAcheterProduit.acheterProduitDispo(produit)) {
			System.out.println("Désolé, personne ne vend ce produit au marché.");
		} else {
			System.out.println("Chez quel commerçant voulez-vous acheter des "+ produit + " ?");
			String nomVendeur = scan.next();
			if(!controlAcheterProduit.allerVoirVendeur(nomVendeur, produit)) {
				System.out.println("Je suis désolée " + nomVendeur + " mais il faut être un habitant de notre village pour commercer ici.");
			} else {
				System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur "+nomVendeur);
			}
			System.out.println("Combien de " + produit + "voulez-vous acheter ?");
			int nbProduits = scan.nextInt();
			controlAcheterProduit.acheterProduit(nomVendeur, nbProduits);
		}
	}
}
