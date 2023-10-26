package Apis;

import java.util.*;

public class Apis {
    public List<Bee> myApis = new ArrayList<>();
    public final List<String> worriorNames = Arrays.asList("Maja", "Basia", "Marta", "Amanda", "Helena");

    public Apis() {
        myApis.add(new Queen("Alicja", 20, 5));
    }

    public void addBee(Bee bee){
        myApis.add(bee);
    }

    public abstract static class Bee implements Runnable{
        protected String Name;
        protected int attackPower;
        protected int age;

        public Bee(String name, int attackPower, int age) {
            Name = name;
            this.attackPower = attackPower;
            this.age = age;
        }


    }

    public class Queen extends Bee {
        private int eggs;

        public Queen(String name, int attackPower, int age) {
            super(name, 100, age);
        }

        private void fertilization(){
            this.eggs += 1000;
        }

        @Override
        public void run() {
            System.out.println("Lot godowy...");
        }

        public String toString() {
            return "Królowa " + Name +
                    " (atak:" + attackPower + "), żyję " + age + " dni i będę matką " +
                    eggs + " młodych pszczółek :)";
        }
    }

    public static class Drone extends Bee {
        private boolean useful;

        public Drone(String name, int age) {
            super(name, 0, age);
            this.useful = true;
        }

        private void fertilization(Queen queen){
            if (this.useful){
                queen.fertilization();
                this.useful = false;
            } else {
                System.out.println("This drone is useless...");
            }
        }

        @Override
        public void run() {
            Random random = new Random();
            if (random.nextBoolean()) {

            }
        }

        @Override
        public String toString() {
            if (this.useful){
                return "Truteń" + Name +
                        " (atak:" + attackPower + "), żyję " + age + " dni";
            } else {
                return "Truteń" + Name +
                        " (atak:" + attackPower + "), spełniłem swoje zadanie :(";
            }

        }

    }

    public static class WorkerBee extends Bee {
        protected int honeyProduced;

        public WorkerBee(String name, int attackPower, int age) {
            super(name, attackPower, age);
        }

        private void collectHoney(int amountOfHoney){
            this.honeyProduced += amountOfHoney;
        }

        @Override
        public void run() {
            Random random = new Random();
            int amountOfHoney = random.nextInt(21);
            this.collectHoney(amountOfHoney);
        }

        @Override
        public String toString() {
            return "WorkerBee" + Name +
                    " (atak:" + attackPower + "), żyję " + age + " dni i zrobiłam " +
                    honeyProduced + " baryłek miodu :)";
        }
    }

    private static class AttackPowerComparation implements Comparator<Bee> {
        @Override
        public int compare(Bee bee1, Bee bee2) {
            // Najpierw porównujemy siłę
            int result = Integer.compare(bee2.attackPower, bee1.attackPower);
            if (result == 0) {
                // Jeśli siły są równe, porównujemy alfabetycznie imiona
                result = bee1.Name.compareTo(bee2.Name);
            }
            return result;
        }
    }



    public void beeLife(){

    }

    public void addWorrior(){
        Random random = new Random();
        int randomIndex = random.nextInt(worriorNames.size());
        String name = worriorNames.get(randomIndex);
        Bee worrior = new Bee(name, 99, 10) {
            @Override
            public void run() {
                System.out.println("Walka to moje życie!!!");
            }

            @Override
            public String toString() {
                return "Żołnierz " + name + " (Atak: " + attackPower + "), żyję " + age
                        + " dni i potrafię użądlić!";
            }
        };
        addBee(worrior);
    }

    public void runApis(){
        for (Bee bee : myApis){
            bee.run();
        }
    }

    public void sortByAttackPowerAndName() {
        Collections.sort(myApis, new AttackPowerComparation() {
            @Override
            public int compare(Bee bee1, Bee bee2) {
                int result = Integer.compare(bee2.attackPower, bee1.attackPower);
                if (result == 0) {
                    result = bee1.Name.compareTo(bee2.Name);
                }
                return result;
            }
        });
    }
}
