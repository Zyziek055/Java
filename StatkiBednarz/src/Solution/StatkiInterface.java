package Solution;

import model.StatekNawodny;
import model.jacht.Jacht;
import model.jacht.JachtKabinowy;
import model.jacht.JachtMotorowy;
import model.jacht.JachtZaglowy;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface StatkiInterface {

    StatekNawodny getNajciezszyStatek();
    StatekNawodny getStatekONajdluzszejNazwieProducentaNaR();
    JachtMotorowy getJachtMotorowyONajwiekszejMocySilnika();
    JachtKabinowy getLekkiJachtKabinowyONajmniejszymOzaglowaniu();
    Set<StatekNawodny> getCoNajwyzej11DlugichICiezkichJachtow();
    List<StatekNawodny> getStatkiPosortowaneWzgledemDlugosciBez2();
    List<JachtZaglowy> getPierwsze8ZPosortowanychWzgledemOzaglowaniaBezTrzechPierwszych();
    void oznaczJachtyDobreNaPlycizny();
    String get12UnikalnychNazwTypow();
    Map<String, JachtMotorowy> getMapaJachtowMotorowych();
    List<Jacht> getJachtyOdkrytopokladoweIMotoroweJednePoDrugich();

}
