package places;

import guests.Guest;

import java.util.Random;

public class WaterDebtApartment extends IndebtedApartment {
    public WaterDebtApartment() {
        Random r = new Random();
        this.setDebt(1 + r.nextInt(1000));
    }

    public void haunt(Guest guest){
        if (new Random().nextBoolean()){
            this.hauntBathroom(guest);
        } else {
            this.hauntLaundry(guest);
        }
    }

    protected void hauntBathroom(Guest guest){
        SwimmerGhost swimmerGhost0 = new SwimmerGhost();
        SwimmerGhost swimmerGhost1 = new SwimmerGhost();
        swimmerGhost0.scareSomeone(guest);
        swimmerGhost1.scareSomeone(guest);
    };

    class SwimmerGhost extends Ghost{
        @Override
        public void scareSomeone(Guest guest) {
            guest.scareMe(10 + new Random().nextInt(21));
        }
    }
}
