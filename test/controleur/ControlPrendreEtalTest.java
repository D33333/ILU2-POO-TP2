package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlPrendreEtalTest {
	private Village village;
	private ControlVerifierIdentite controlVerifierIdentite;
	private Chef abraracourcix;
	
	@BeforeEach
	public void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("le village des irréductibles", 10, 1);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		controlVerifierIdentite = new ControlVerifierIdentite(village);
	}
	
	@Test
	void testControlPrendreEtal() {
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		assertNotNull(controlPrendreEtal, "Constructeur ne renvoie pas null");
	}

	@Test
	void testResteEtals() {
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		assertTrue(controlPrendreEtal.resteEtals());
		Village villageSansEtal = new Village("le village sans étal", 10, 0);
		ControlPrendreEtal controlPrendreEtalImpossible = new ControlPrendreEtal(controlVerifierIdentite, villageSansEtal);
		assertFalse(controlPrendreEtalImpossible.resteEtals());
	}

	@Test
	void testPrendreEtal() {
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		assertEquals(controlPrendreEtal.prendreEtal("Intrus", "chapeaux", 3), -1, "habitant invalide");
		assertEquals(controlPrendreEtal.prendreEtal(abraracourcix.getNom(), "poireaux", -77), -1, "Nombre de produits invalide");
		assertEquals(controlPrendreEtal.prendreEtal(abraracourcix.getNom(), "boucliers", 45), 0, "premier étal a l'indice 0 en interne, 1 à l'affichage");
		assertEquals(controlPrendreEtal.prendreEtal(abraracourcix.getNom(), "viande", 3), -1, "plus de place");
	}

	@Test
	void testVerifierIdentite() {
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		assertTrue(controlPrendreEtal.verifierIdentite(abraracourcix.getNom()));
		assertFalse(controlPrendreEtal.verifierIdentite("Intrus"));
	}

}
