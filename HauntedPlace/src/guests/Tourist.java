package guests;

public class Tourist extends Guest {
    @Override
    public void scareMe(int power){
        if (power > this.getResistance()){
            if (this.getState() == State.NORMAL){
                this.setState(State.SCARED);
            } else if (this.getState() == State.SCARED) {
                this.setState(State.PANIC);
            }
        } else {
            if (this.getState() == State.PANIC) {
                this.setState(State.SCARED);
            } else if (this.getState() == State.SCARED) {
                this.setState(State.NORMAL);
            }
        }
    }

    @Override
    public String toString() {
        return "Tourist{" +
                "state=" + state +
                '}';
    }
}
