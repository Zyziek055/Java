package inerrogated;

public abstract class Interrogated {

	public static class Witness extends Interrogated {
		protected String name;
		protected String surname;

		public Witness(String name, String surname) {
			this.name = name;
			this.surname = surname;
		}

		@Override
		public String toString() {
			return "Witness{" +
					"name='" + name + '\'' +
					", surname='" + surname + '\'' +
					'}';
		}

		public void interrogateMe(){
			System.out.println("Jestem przesłuchiwany" + this.getClass() + this.toString());
		}
	}

	public class Suspect extends Interrogated {
		protected String name;
		protected String surname;

		public Suspect(String name, String surname) {
			this.name = name;
			this.surname = surname;
		}

		@Override
		public String toString() {
			return "Suspect{" +
					"name='" + name + '\'' +
					", surname='" + surname.toUpperCase().charAt(0) + "." + '\'' +
					'}';
		}

		public void interrogateMe(){
			System.out.println("Jestem przesłuchiwany" + this.getClass() + this.toString());
		}
	}

	public static class SecretAgent extends Interrogated {
		protected String pseudonym;

		public SecretAgent(String pseudonym) {
			this.pseudonym = pseudonym;
		}

		@Override
		public String toString() {
			return "SecretAgent{" +
					"pseudonym='" + pseudonym + '\'' +
					'}';
		}
		public void interrogateMe(){
			System.out.println("Jestem przesłuchiwany" + this.getClass() + this.toString());
		}
	}
	public abstract void interrogateMe();
}
