package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAcheterProduitTest {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;
	private Chef abraracourcix;
	private Druide panoramix = new Druide("panoramix",2,4,6);
	private Gaulois asterix = new Gaulois("asterix",3);
	
	@BeforeEach
	public void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("le village des irréductibles", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		village.ajouterHabitant(panoramix);
		village.ajouterHabitant(asterix);
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		controlVerifierIdentite = new ControlVerifierIdentite(village);
	}
	
	@Test
	void testControlAcheterProduit() {
		ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		assertNotNull(controlAcheterProduit, "Constructeur ne renvoie pas null");
	}

	@Test
	void testAcheterProduitDispo() {
		ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		village.installerVendeur(abraracourcix, "casque", 5);
		assertTrue(controlAcheterProduit.acheterProduitDispo("casque"));
		assertFalse(controlAcheterProduit.acheterProduitDispo("intrus"));
	}

	@Test
	void testTrouverVendeurs() {
		ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		village.installerVendeur(abraracourcix, "casque", 5);
		village.installerVendeur(panoramix, "potion", 3);
		village.installerVendeur(asterix, "casque", 1);
		assertEquals(controlAcheterProduit.trouverVendeurs("casque").length, 2);
		assertEquals(controlAcheterProduit.trouverVendeurs("potion")[0],panoramix);
		assertNull(controlAcheterProduit.trouverVendeurs("intrus"));
	}

	@Test
	void testAcheterProduit() {
		assertNotNull(controlTrouverEtalVendeur);
		ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		village.installerVendeur(abraracourcix, "casque", 5);
		assertEquals(controlAcheterProduit.acheterProduit(abraracourcix.getNom(), 3),3);
		assertEquals(controlAcheterProduit.acheterProduit("Intrus", 3),0);
		assertEquals(controlAcheterProduit.acheterProduit(abraracourcix.getNom(), 45),2);
		assertEquals(controlAcheterProduit.acheterProduit(abraracourcix.getNom(), -5),0);
	}

}