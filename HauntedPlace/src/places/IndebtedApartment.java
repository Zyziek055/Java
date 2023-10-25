package places;

import guests.Guest;

public class IndebtedApartment extends Place{
    protected int debt;
    public void setDebt(int debt) {
        this.debt = debt;
    }

    protected void hauntBathroom(Guest guest){
    }

    protected void hauntLaundry(Guest guest){
        Bailiff bailiff = new Bailiff();
        bailiff.scareSomeone(guest);
    }

    class Bailiff extends Ghost{
        protected int power = debt/500;

        @Override
        public void scareSomeone(Guest guest) {
            guest.scareMe(power);
        }
    }
}
