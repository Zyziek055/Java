import guests.Guest;
import guests.Student;
import guests.Tourist;
import places.AbandonedHospital;
import places.LoanDebtApartment;
import places.OldCastle;
import places.WaterDebtApartment;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Guest> guests = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Student student = new Student();
            Tourist tourist = new Tourist();
            guests.add(student);
            guests.add(tourist);
        }
        OldCastle oldCastle = new OldCastle();
        AbandonedHospital hospital = new AbandonedHospital();
        WaterDebtApartment waterDebtApartment = new WaterDebtApartment();
        LoanDebtApartment mortgageDebtApartment = new LoanDebtApartment();
        for (Guest guest:guests){
            System.out.println("\nNew guest");
            oldCastle.haunt(guest);
            System.out.println(guest);
            hospital.haunt(guest);
            System.out.println(guest);
            waterDebtApartment.haunt(guest);
            System.out.println(guest);
            mortgageDebtApartment.haunt(guest);
            System.out.println(guest);
        }
    }
}