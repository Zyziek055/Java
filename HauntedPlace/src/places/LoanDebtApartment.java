package places;

import guests.Guest;

import java.util.Random;

public class LoanDebtApartment extends IndebtedApartment {
    public LoanDebtApartment() {
        Random r = new Random();
        this.setDebt(1 + r.nextInt(10000));
    }

    public void haunt(Guest guest){
        this.hauntBathroom(guest);
        hauntLaundry(guest);
    }
    @Override
    protected void hauntBathroom(Guest guest){
        Bailiff bailiff0 = new Bailiff();
        Bailiff bailiff1 = new Bailiff();
        bailiff0.scareSomeone(guest);
        bailiff1.scareSomeone(guest);
    }

}
