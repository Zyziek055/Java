package Lotnisko;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class Lotnisko {
    private ArrayList<Samolot> list = new ArrayList<>();
    Random random = new Random();
    public Lotnisko(int n){
        String x = "abcdefghijklmnoprstuwxyz";
        for (int i = 0; i < n; i++){
            GeneratorImion nazwa = (y) -> {
                int p = random.nextInt(1 ,21);
                StringBuilder str = new StringBuilder();
                for (int k = 0; k < p; k++){
                    str.append(y.charAt(random.nextInt(0,x.length())));
                }
                return str.toString();
            };
            int probability = random.nextInt(0,3);
            if (probability == 0){
                SamolotPasazerski samolotPasazerski = new SamolotPasazerski(nazwa.losuj(x), random.nextInt(500,1001), 0, random.nextInt(100,301), 0 );
                list.add(samolotPasazerski);
            } else if (probability == 1) {
                SamolotTowarowy samolotTowarowy = new SamolotTowarowy(nazwa.losuj(x), random.nextInt(300,701), 0, random.nextInt(10,101), 0 );
                list.add(samolotTowarowy);
            } else {
                Mysliwiec mysliwiec = new Mysliwiec(nazwa.losuj(x), random.nextInt(900,3001), 0 );
                list.add(mysliwiec);
            }
        }
    }

    public void OdprawaSamolotow() {

        for (Samolot samolot:list) {

            if (samolot instanceof SamolotPasazerski){
                ((SamolotPasazerski) samolot).setAktualnaLiczbaPasazerow(random.nextInt(0,400));
                try {
                    samolot.Odprawa();
                } catch (WyjatekLotniczy e) {
                    System.out.println(e.getMessage());
                }
            } else if (samolot instanceof SamolotTowarowy) {
                ((SamolotTowarowy) samolot).setAktualnyLadunek(random.nextInt(0,201));
                try {
                    samolot.Odprawa();
                } catch (WyjatekLotniczy e) {
                    System.out.println(e.getMessage());
                }
            } else if (samolot instanceof Mysliwiec) {
                ((Mysliwiec) samolot).setIloscRakiet(random.nextInt(0,11));
                try {
                    samolot.Odprawa();
                } catch (WyjatekLotniczy e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void dzialaniaLotniskowe(){
        Consumer<List<Samolot>> var = (list)-> {
            list.forEach(System.out::println);
            list.forEach(Samolot::laduj);
            list.forEach(samolot -> {
                try {
                    samolot.Odprawa();
                } catch (WyjatekLotniczy e) {
                    System.out.println(e.getMessage());
                }
            });
            list.forEach(a -> a.lec(10));
            list.forEach(samolot1 -> {if(samolot1 instanceof Mysliwiec) ((Mysliwiec) samolot1).Atak();


            });

        };
        var.accept(list);
    }
    public void sortowanieSamolotow(){
        Comparator<Samolot> cmp = Comparator.comparingInt(Samolot::getPredkosc_max);
        list.sort(cmp);
        System.out.println(list);
        Comparator<Samolot> tmp = Comparator.comparing(Samolot::getNazwa)
                .thenComparing(Samolot::getNazwa, Comparator.comparingInt(String::length));
        list.sort(tmp);
        System.out.println(list);
    }
    public void sortowanieLosowe(){
        SortowanieLosowe srt = () -> {
            int prob = random.nextInt(0,2);
            Comparator<Samolot> x;
            if (prob == 0){
                x =Comparator.comparing(Samolot::getPredkosc_max);
            } else {
                x = Comparator.comparing(Samolot::getNazwa)
                        .thenComparing(Samolot::getNazwa, Comparator.comparingInt(String::length));
            }
            list.sort(x);
            System.out.println(list);
        };
        srt.losowanie();

    }


    private static abstract class Samolot {
        private String nazwa;
        private int predkosc_max, ilosc_w_pow;
        private boolean leci = false;

        public Samolot(String nazwa, int predkosc_max, int ilosc_w_pow) {
            this.nazwa = nazwa;
            this.predkosc_max = predkosc_max;
            this.ilosc_w_pow = ilosc_w_pow;
        }

        public void lec(int ilosc_godzin){
            boolean z = true;
            try {
                this.Odprawa();
            } catch (WyjatekLotniczy e) {
                System.out.println("Problem z odprawa");
                System.out.println(e.getMessage());
                z = false;
            }
            if (z) {
                if (leci) {
                    System.out.println("Lecimy");
                    this.ilosc_w_pow += ilosc_godzin;
                } else {
                    System.out.println("Startujemy...");
                    this.ilosc_w_pow += ilosc_godzin;
                    this.leci = true;
                }
            }
        }
        public void laduj(){
            if (leci){
                this.leci = false;
                System.out.println("Ladujemy...");
            } else {
                System.out.println("I tak jestesmy na ziemi");
            }
        }
        public void Odprawa() throws WyjatekLotniczy{

        }
        public boolean getLeci(){
            return this.leci;
        }

        public int getPredkosc_max() {
            return predkosc_max;
        }

        public String getNazwa() {
            return nazwa;
        }

        @Override
        public String toString() {
            return " o nazwie: " + this.nazwa + ". Predkosc maksymalna: " + this.predkosc_max + ", w powietrzu spedzil laczne: " + this.ilosc_w_pow;
        }
    }
    public static class SamolotPasazerski extends Samolot {
        private int maxLiczbaPasazerow;

        public void setMaxLiczbaPasazerow(int maxLiczbaPasazerow) {
            this.maxLiczbaPasazerow = maxLiczbaPasazerow;
        }

        public void setAktualnaLiczbaPasazerow(int aktualnaLiczbaPasazerow) {
            this.aktualnaLiczbaPasazerow = aktualnaLiczbaPasazerow;
        }

        private int aktualnaLiczbaPasazerow;

        public SamolotPasazerski(String nazwa, int predkoscMax, int iloscGodzinWPowietrzu, int maxLiczbaPasazerow, int aktualnaLiczbaPasazerow) {
            super(nazwa, predkoscMax, iloscGodzinWPowietrzu);
            this.maxLiczbaPasazerow = maxLiczbaPasazerow;
            this.aktualnaLiczbaPasazerow = aktualnaLiczbaPasazerow;
        }

        @Override
        public void Odprawa() throws WyjatekEKonomiczny {
            if (this.aktualnaLiczbaPasazerow < this.maxLiczbaPasazerow/2) {
                throw new WyjatekEKonomiczny("Za mało pasażerów.");
            } else if (this.aktualnaLiczbaPasazerow > this.maxLiczbaPasazerow) {
                throw new WyjatekEKonomiczny("Przeladowano - " + (aktualnaLiczbaPasazerow - maxLiczbaPasazerow));
            }
        }

        @Override
        public String toString() {
            String str = "Samolot pasażerski" + super.toString() + " moze zabrac: " + this.maxLiczbaPasazerow;
            if (this.getLeci()){
                return str + "Samolot leci z: " + this.aktualnaLiczbaPasazerow + " pasazerami.";
            }
            return str + " uziemiony";
        }
    }

    public static class SamolotTowarowy extends Samolot {
        private int maxLadunek;
        private int aktualnyLadunek;

        public void setAktualnyLadunek(int aktualnyLadunek) {
            this.aktualnyLadunek = aktualnyLadunek;
        }

        public SamolotTowarowy(String nazwa, int predkoscMax, int iloscGodzinWPowietrzu, int maxLadunek, int aktualnyLadunek) {
            super(nazwa, predkoscMax, iloscGodzinWPowietrzu);
            this.maxLadunek = maxLadunek;
            this.aktualnyLadunek = aktualnyLadunek;
        }

        @Override
        public void Odprawa() throws WyjatekLotniczy {
            if (aktualnyLadunek < maxLadunek / 2){
                throw new WyjatekEKonomiczny("Za mały ladunek nie lecimy");

            } else if (aktualnyLadunek > maxLadunek) {
                throw  new WyjatekEKonomiczny("Przeladowano o " + (aktualnyLadunek - maxLadunek) + " ton");
            }
        }

        @Override
        public String toString() {
            String str = "Samolot towarowy" + super.toString() + " moze zabrac: " + this.maxLadunek;
            if (this.getLeci()){
                return str + "Samolot leci z: " + this.aktualnyLadunek + " tonami ladunku";
            }
            return str + " uziemiony";
        }
    }

    public static class Mysliwiec extends Samolot {
        private int iloscRakiet;

        public Mysliwiec(String nazwa, int predkoscMax, int iloscGodzinWPowietrzu) {
            super(nazwa, predkoscMax, iloscGodzinWPowietrzu);
        }

        public void setIloscRakiet(int iloscRakiet) {
            this.iloscRakiet = iloscRakiet;
        }

        @Override
        public void Odprawa() {}
        public void Atak(){
            if (!this.getLeci()){
                return;
            } else if (this.iloscRakiet == 0) {
                this.laduj();
            } else {
                this.iloscRakiet--;
                System.out.println("Ataaaaak");
            }
        }
        @Override
        public String toString() {
            String str = "Samolot pasażerski" + super.toString();
            if (this.getLeci()){
                return str + "Samolot leci z: " + this.iloscRakiet + " rakietami na pokladzie";
            }
            return str + " uziemiony";
        }

    }

    @FunctionalInterface
    private interface SortowanieLosowe {
        void losowanie();
    }

    @FunctionalInterface
    private interface GeneratorImion {
        String losuj(String var);
    }
    private static class WyjatekLotniczy extends Exception {
        WyjatekLotniczy(String msg){super(msg);}
    }
    public static class WyjatekEKonomiczny extends WyjatekLotniczy {
        WyjatekEKonomiczny(String msg) {
            super(msg);
        }
    }

}
