package guests;

import java.util.Random;

public class Guest {
    protected int resistance;
    protected State state;

    public Guest() {
        Random r = new Random();
        this.resistance = 10 + r.nextInt(10);
        this.state = State.NORMAL;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "state=" + state +
                '}';
    }

    public int getResistance() {
        return resistance;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void scareMe(int power){};
}
