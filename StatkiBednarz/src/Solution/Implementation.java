package Solution;

import generator.GeneratorStatkow;
import model.StatekNawodny;
import model.jacht.*;

import javax.swing.plaf.nimbus.State;
import java.util.*;
import java.util.stream.Collectors;

public class Implementation implements StatkiInterface{
    List<StatekNawodny> ships;

    public Implementation() {
        ships = GeneratorStatkow.generujStatkiNawodne();
    }

    public StatekNawodny getNajciezszyStatek(){
        return ships.stream().max(Comparator.comparing(StatekNawodny::getMasa)).get();
    }

    public StatekNawodny getStatekONajdluzszejNazwieProducentaNaR(){
        return ships.stream().filter(statekNawodny -> statekNawodny.getNazwaProducenta().startsWith("R"))
                .max(Comparator.comparing(StatekNawodny -> StatekNawodny.getNazwaProducenta().length())).get();
    }

    public JachtMotorowy getJachtMotorowyONajwiekszejMocySilnika() {
        return ships.stream().
                filter(statekNawodny -> statekNawodny instanceof JachtMotorowy).
                map(JachtMotorowy.class::cast).
                max(Comparator.comparing(JachtMotorowy::getMocSilnika)).get();
    }

    public JachtKabinowy getLekkiJachtKabinowyONajmniejszymOzaglowaniu (){
        return ships.stream().
                filter(statekNawodny -> statekNawodny instanceof JachtKabinowy).
                map(JachtKabinowy.class::cast).
                filter(jachtKabinowy -> jachtKabinowy.getMasa() <= 1000).
                min(Comparator.comparing(JachtKabinowy::getPowierzchniaZagla)).get();
    }

    public Set<StatekNawodny> getCoNajwyzej11DlugichICiezkichJachtow(){
        return ships.stream().
                filter(statekNawodny -> statekNawodny instanceof Jacht).
                map(Jacht.class::cast).
                filter(jacht -> jacht.getDlugosc() > 700).
                filter(jacht -> jacht.getMasa() >= 1400).
                limit(11).
                collect(Collectors.toSet());
    }

    public List<StatekNawodny> getStatkiPosortowaneWzgledemDlugosciBez2(){
        return ships.stream().
                skip(2).
                sorted(Comparator.comparing(StatekNawodny::getDlugosc).reversed()).
                collect(Collectors.toList());
    }

    public List<JachtZaglowy> getPierwsze8ZPosortowanychWzgledemOzaglowaniaBezTrzechPierwszych(){
        return ships.stream().
                filter(statekNawodny -> statekNawodny instanceof JachtZaglowy).
                map(JachtZaglowy.class::cast).
                sorted(Comparator.comparing(JachtZaglowy::getPowierzchniaZagla).reversed()).
                skip(2).
                limit(8).
                collect(Collectors.toList());
    }

    public void oznaczJachtyDobreNaPlycizny(){
        ships.stream().
                filter(statekNawodny -> statekNawodny instanceof JachtKabinowy).
                map(JachtKabinowy.class::cast).
                filter(jachtKabinowy -> jachtKabinowy.getZanurzenie() <= 30).
                filter(jachtKabinowy -> jachtKabinowy.getMasa() <= 1300).

                forEach(jachtKabinowy -> jachtKabinowy.setKomentarz("Jachtem" + jachtKabinowy.getTyp() + "wpłyniesz na kazda plycizne!"));
    }
    public String get12UnikalnychNazwTypow(){
        return ships.stream().
                filter(statekNawodny -> statekNawodny instanceof Jacht).
                map(Jacht.class::cast).
                filter(jacht -> jacht.getMasa() > 2000).
                skip(3).
                limit(12).
                map(Jacht::getTyp).
                collect(Collectors.joining("-"));

    }

    public Map<String, JachtMotorowy> getMapaJachtowMotorowych(){
        return ships.stream().
                filter(statekNawodny -> statekNawodny instanceof JachtMotorowy).
                map(JachtMotorowy.class::cast).
                sorted(Comparator.comparing(jachtMotorowy -> jachtMotorowy.getNazwaProducenta())).
                distinct().
                collect(Collectors.toMap(JachtMotorowy::getTyp, jachtMotorowy -> jachtMotorowy));
    }

    public List<Jacht> getJachtyOdkrytopokladoweIMotoroweJednePoDrugich(){
        List<JachtOdkrytopokladowy> jachtyOdkrytopokladowe = ships.stream().
                filter(statekNawodny -> statekNawodny instanceof JachtOdkrytopokladowy).
                map(JachtOdkrytopokladowy.class::cast).
                limit(10).collect(Collectors.toList());

        List <JachtMotorowy> jachtyMotorowe = ships.stream().
                filter(statekNawodny -> statekNawodny instanceof JachtMotorowy).
                map(JachtMotorowy.class::cast).
                filter(jachtMotorowy -> jachtMotorowy.getNazwaProducenta().toLowerCase() == "regal").
                skip(4).limit(4).collect(Collectors.toList());
        List<Jacht> jachty = new LinkedList<>();
        jachty.addAll(jachtyMotorowe);
        jachty.addAll(jachtyOdkrytopokladowe);
        return jachty;


    }
}