package places;

import guests.Guest;

public class OldCastle extends Place{
    protected static int whiteLadyPower = 20;

    public void haunt(Guest guest){
        WhiteLady whiteLady = new WhiteLady();
        whiteLady.scareSomeone(guest);
        CastleOwner castleOwner0 = new CastleOwner();
        CastleOwner castleOwner1 = new CastleOwner();
        castleOwner0.scareSomeone(guest);
        castleOwner1.scareSomeone(guest);
    }

    class WhiteLady extends Ghost{
        @Override
        public void scareSomeone(Guest guest) {
            guest.scareMe(whiteLadyPower);
        }
    }

    class CastleOwner extends Ghost{
        @Override
        public void scareSomeone(Guest guest) {
            guest.scareMe(0);
        }
    }
}
