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
			Gaulois[] commercants = controlAcheterProduit.trouverVendeurs(produit);
			if(commercants.length == 0) {
				System.out.println("Je suis désolée " + nomAcheteur + " mais il faut être un habitant de notre village pour commercer ici.");
			} else {
				int i = 0;
				while (i < commercants.length) {
					System.out.println((++i) + " - " + commercants[(--i)].getNom());
					i++;
				}
				int commercantChoisi = scan.nextInt()-1;
				while (commercantChoisi < 0 || commercantChoisi >= commercants.length) {
					System.out.println("Entrez un nombre valide");
					commercantChoisi = scan.nextInt();
				}
				String nomVendeur = commercants[commercantChoisi].getNom();
				System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " + nomVendeur);
				System.out.println("Bonjour " + nomAcheteur);
				System.out.println("Combien de " + produit + " voulez-vous acheter ?");
				int nbProduits = scan.nextInt();
				int nbProduitsAcheter = controlAcheterProduit.acheterProduit(nomVendeur, nbProduits);
				if (nbProduitsAcheter == 0) {
					System.out.println(nomAcheteur + " veut acheter " + nbProduits + " " + produit + ", malheureusement il n'y en a plus !");
				} else if (nbProduits > nbProduitsAcheter) {
					System.out.println(nomAcheteur + " veut acheter " + nbProduits + " " + produit + ", malheureusement " + nomVendeur + " n'en a plus que " + nbProduitsAcheter + ". " + nomAcheteur + " achète tout le stock de " + nomVendeur + ".");
				} else {
					System.out.println(nomAcheteur + " achète " + nbProduitsAcheter + " " + produit + " à " + nomVendeur + ".");
				}
			}
		}
	}
}
