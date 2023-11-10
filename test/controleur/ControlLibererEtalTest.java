package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import villagegaulois.Etal;
import villagegaulois.Village;

class ControlLibererEtalTest {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private Chef abraracourcix;
	private Druide panoramix;
	
	@BeforeEach
	public void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("le village des irréductibles", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		panoramix = new Druide("panoramix",2,4,6);
	}

	@Test
	void testControlLibererEtal() {
		ControlLibererEtal controlLibererEtal = new ControlLibererEtal(controlTrouverEtalVendeur);
		assertNotNull(controlLibererEtal, "Constructeur ne renvoie pas null");		
	}

	@Test
	void testIsVendeur() {
		ControlLibererEtal controlLibererEtal = new ControlLibererEtal(controlTrouverEtalVendeur);
		village.installerVendeur(abraracourcix, "casque", 5);
		assertTrue(controlLibererEtal.isVendeur(abraracourcix.getNom()));
		village.installerVendeur(panoramix, "potion", 3);
		//Panoramix n'appartient pas au village
		assertFalse(controlLibererEtal.isVendeur(panoramix.getNom()));
		assertFalse(controlLibererEtal.isVendeur("Intrus"));
	}

	@Test
	void testLibererEtal() {
		ControlLibererEtal controlLibererEtal = new ControlLibererEtal(controlTrouverEtalVendeur);
		village.ajouterHabitant(panoramix);
		int ind_Etal = village.installerVendeur(panoramix, "potion", 3);
		System.out.println(ind_Etal);
		Etal etal = village.rechercherEtal(panoramix);
		assertNotNull(etal);
		assertTrue(village.rechercherEtal(panoramix).isEtalOccupe());
		assertEquals(controlLibererEtal.libererEtal(panoramix.getNom())[0],"true");
		assertEquals(controlLibererEtal.libererEtal(abraracourcix.getNom())[0],"false");
		assertEquals(controlLibererEtal.libererEtal("Intrus")[0],"false");
	}

}