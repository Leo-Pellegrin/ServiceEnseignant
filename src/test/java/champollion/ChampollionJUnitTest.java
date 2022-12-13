package champollion;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class ChampollionJUnitTest {
	Enseignant untel;
	UE uml, java, genielog;
	Intervention cours, cours2, cours3, cours4;
		
	@BeforeEach
	public void setUp() {
		untel = new Enseignant("untel", "untel@gmail.com");
		uml = new UE("UML", 60, 40, 20);
		java = new UE("Programmation en java", 10, 5, 4);		
		cours = new Intervention(new Date(), 2, 10, java, TypeIntervention.TD);
		genielog = new UE("Genie log", 80, 60, 50);
		cours2 = new Intervention(new Date(), 2, 10, genielog, TypeIntervention.CM);
		cours3 = new Intervention(new Date(), 2, 10, genielog, TypeIntervention.TP);
		cours4 = new Intervention(new Date(), 2, 10, genielog, TypeIntervention.TD);

	}
	

	@Test
	public void testNouvelEnseignantSansService() {
		assertEquals(0, untel.heuresPrevues(),
                        "Un nouvel enseignant doit avoir 0 heures prévues");
	}
	
	@Test
	public void testAjouteHeures() throws Exception {
                // 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);

		assertEquals(10, untel.heuresPrevuesPourUE(uml),
                        "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

                // 20h TD pour UML
                untel.ajouteEnseignement(uml, 0, 20, 0);
                
		assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
                         "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");		
		
	}

	@Test
	public void testAjouterInterventions(){
		untel.ajouteIntervention(cours);

		assertEquals(1, java.getNbIntervention());
		assertEquals(1, untel.getNbIntervention());
	}

	@Test
	public void testEnSousService() throws Exception{
		assertEquals(true, untel.enSousService());
		// untel.ajouteEnseignement(genielog, 70, 50, 40);
		// assertEquals(false, untel.enSousService());
	}

	@Test
	public void testResteAPlanifier() throws Exception{
		untel.ajouteEnseignement(genielog, 10, 10, 10);
		untel.ajouteIntervention(cours2);
		untel.ajouteIntervention(cours3);
		untel.ajouteIntervention(cours4);

		assertEquals(8, untel.resteAPlanifier(genielog, TypeIntervention.CM));
	}
	
}
