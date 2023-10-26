import Apis.*;

public class Main {
    public static void main(String[] args) {
        Apis apis = new Apis();
        apis.addBee(new Apis.WorkerBee("Kasia", 10, 3));
        apis.addBee(new Apis.WorkerBee("Julia", 12, 5));
        apis.addBee(new Apis.WorkerBee("Kasia", 5, 7));
        apis.addBee(new Apis.Drone("Grzegorz", 8));
        apis.addBee(new Apis.Drone("Garry", 1));
        apis.addBee(new Apis.Drone("Szymon", 4));
        apis.addWorrior();
        apis.addWorrior();
        apis.addWorrior();

        System.out.println(apis.myApis);

        apis.sortByAttackPowerAndName();

        System.out.println(apis.myApis);

        apis.runApis();

        System.out.println(apis.myApis);
    }


}