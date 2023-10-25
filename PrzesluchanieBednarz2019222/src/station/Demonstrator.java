package station;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import inerrogated.*;

public class Demonstrator {



	private static final byte numberOfInterrogatedPeople = 5;

	private List<String> names = Arrays.asList("Alicja", "Maciek", "Piotrek", "Zbych", "Adam", "Joanna", "Zuzanna");
	private List<String> surnames = Arrays.asList("Johnson", "Bryl", "Opala", "Tkaczyk", "Witkek", "Dzwon");
	private List<String> pseudonyms = Arrays.asList("Halo", "Ogrodnik", "Halina", "Kapo", "Macho");

	public static void main(String[] args) {
		PoliceStation policeStation;//Tutaj nalezy zainicjowac stworzona podklase
		List<Interrogated> interrogatedPeople;//Tutaj nalezy zainicjowac kolekcje za pomoca klasy opisanej w zadaniu
		//policeStation.reception(interrogatedPeople);//Odkomentowac, gdy komponenty zostana zainicjalizowane
	}

	private class InterrogateGeneretor {
		private String randomName() {
			Random random = new Random();
			return Demonstrator.this.names.get(random.nextInt(Demonstrator.this.names.size()));
		}

		private String randomSurname() {
			Random random = new Random();
			return Demonstrator.this.surnames.get(random.nextInt(Demonstrator.this.surnames.size()));
		}

		private String randomPseudonyms() {
			Random random = new Random();
			return Demonstrator.this.pseudonyms.get(random.nextInt(Demonstrator.this.pseudonyms.size()));
		}
	}

}
