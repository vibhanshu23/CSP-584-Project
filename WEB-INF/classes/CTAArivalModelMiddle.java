import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CTAArivalModelMiddle {
    private String tmst;
    private String errCd;
    private Object errNm;
    public List<CTAArrivalItem> eta = new ArrayList<CTAArrivalItem>();
    public ArrayList<CTAArrivalItem> arrDueTrains = new ArrayList<CTAArrivalItem>();
    public ArrayList<CTAArrivalItem> arrScheduledTrains = new ArrayList<CTAArrivalItem>();
    public String getTmst() {
        return tmst;
    }
    public void setTmst(String tmst) {
        this.tmst = tmst;
    }
    public String getErrCd() {
        return errCd;
    }
    public void setErrCd(String errCd) {
        this.errCd = errCd;
    }
    public Object getErrNm() {
        return errNm;
    }
    public void setErrNm(Object errNm) {
        this.errNm = errNm;
    }
    public List<CTAArrivalItem> getEta() {
        return eta;
    }
    public void setEta(List<CTAArrivalItem> eta) {
        this.eta = eta;
    }
    public ArrayList<CTAArrivalItem> getArrDueTrains() {
        return arrDueTrains;
    }
    public void setArrDueTrains(ArrayList<CTAArrivalItem> arrDueTrains) {
        this.arrDueTrains = arrDueTrains;
    }
    public ArrayList<CTAArrivalItem> getArrScheduledTrains() {
        return arrScheduledTrains;
    }
    public void setArrScheduledTrains(ArrayList<CTAArrivalItem> arrScheduledTrains) {
        this.arrScheduledTrains = arrScheduledTrains;
    }
}