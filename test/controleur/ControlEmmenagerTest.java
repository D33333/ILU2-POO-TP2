package controleur;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlEmmenagerTest {
	private Village village;
	private Chef abraracourcix;

	@BeforeEach
	public void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("le village des irréductibles", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
	}

	@Test
	void testControlEmmenager() {
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		assertNotNull(controlEmmenager, "Constructeur ne renvoie pas null");
	}

	@Test
	void testIsHabitant() {
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterGaulois("Bonemine", 10);
		assertTrue(controlEmmenager.isHabitant("Bonemine"));
		assertFalse(controlEmmenager.isHabitant("Existe pas"));
		controlEmmenager.ajouterDruide("Panoramix", 10, 1, 5);
		assertTrue(controlEmmenager.isHabitant("Panoramix"));
	}

	@Test
	void testAjouterDruide() {
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterDruide("Panoramix", 10, 1, 5);
		// pas de vérification
	}

	@Test
	void testAjouterGaulois() {
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterGaulois("Bonemine", 10);
		// pas de vérification
	}
	
	@Test
	void testSurLesPersonnages() {
		assertEquals(abraracourcix.getForce(),10);
		abraracourcix.toString();
		abraracourcix.parler("Bonjour!");
		//pas de vérification
		Druide panoramix = new Druide("Panoramix",2,4,6);
		Gaulois obelix = new Gaulois("Obélix",4656);
		panoramix.toString();
		panoramix.parler("Je suis le druide.");
		Druide druidix = new Druide("Druidix",3,9,45);
		druidix.preparerPotion();
		druidix.booster(obelix);
		//pas de vérification
		Gaulois asterix = new Gaulois("Astérix",15);
		asterix.toString();
		asterix.parler("Je suis le puissant "+asterix.getNom()+" avec une force de "+asterix.getForce());
		panoramix.preparerPotion();
		panoramix.booster(asterix);	
}

}