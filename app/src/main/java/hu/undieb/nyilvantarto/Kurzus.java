package hu.undieb.nyilvantarto;


public class Kurzus {
    public String getKurzusNev() {
        return kurzusNev;
    }

    public void setKurzusNev(String kurzusNev) {
        this.kurzusNev = kurzusNev;
    }

    private String kurzusNev;

    public Kurzus(String kurzusNev) {
        this.kurzusNev = kurzusNev;
    }
    @Override
    public String toString() {
        return "Kurzus{" +
                "kurzusNev='" + kurzusNev + '\'' +
                '}';
    }
}
