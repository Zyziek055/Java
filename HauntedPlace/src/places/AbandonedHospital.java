package places;

import guests.Guest;
import guests.State;

public class AbandonedHospital extends Place{
    @Override
    public void haunt(Guest guest){
        Ghost oneTimeGhost = new Ghost() {
            @Override
            public void scareSomeone(Guest guest) {
                if (guest.getState()== State.NORMAL){
                    guest.scareMe(15);
                }
            }
        };
        oneTimeGhost.scareSomeone(guest);
    }
}



