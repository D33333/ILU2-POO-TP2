package controleur;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAfficherVillageTest {
	private Village village;
	private Chef abraracourcix;
	private static final Gaulois bonemine = new Gaulois("Bonemine", 6);
	private static final Druide panoramix = new Druide("Panoramix", 3, 1, 2);;
	
	@BeforeEach
	public void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("le village des irréductibles", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
	}
	
	@Test
	void testControlAfficherVillage() {
		ControlAfficherVillage controlAfficherVillage = new ControlAfficherVillage(village);
		assertNotNull(controlAfficherVillage, "Constructeur ne renvoie pas null");
	}

	@Test
	void testDonnerNomsVillageois() {
		ControlAfficherVillage controlAfficherVillage = new ControlAfficherVillage(village);
		final String[] monChefSeul = controlAfficherVillage.donnerNomsVillageois();
		assertEquals(monChefSeul[0], "Abraracourcix", "Chef appartient au village");
		assertEquals(monChefSeul.length, 1);
		village.ajouterHabitant(bonemine);
		village.ajouterHabitant(panoramix);
		final String[] mesVillageois = controlAfficherVillage.donnerNomsVillageois();
		final String[] mesTroisVillageois = new String[] {"Abraracourcix", "Bonemine", "le druide Panoramix"};
		for(int i = 0; i < mesTroisVillageois.length; i++) {
			assertEquals(mesTroisVillageois[i], mesVillageois[i]);
		}
		assertEquals(mesVillageois.length, 3);
	}

	@Test
	void testDonnerNomVillage() {
		ControlAfficherVillage controlAfficherVillage = new ControlAfficherVillage(village);
		assertEquals(controlAfficherVillage.donnerNomVillage(), "le village des irréductibles");
	}

	@Test
	void testDonnerNbEtals() {
		ControlAfficherVillage controlAfficherVillage = new ControlAfficherVillage(village);
		assertEquals(controlAfficherVillage.donnerNbEtals(), 5);
		assertNotEquals(village.installerVendeur(abraracourcix, "casques", 450), -1);
		assertEquals(controlAfficherVillage.donnerNbEtals(), 5);
		for (int i = 0; i < 3; i++) {
			village.installerVendeur(bonemine, "fleurs", 340);
		}
		assertNotEquals(village.installerVendeur(panoramix, "potions", 1), -1);
		assertEquals(controlAfficherVillage.donnerNbEtals(), 5);
		
	}

}
