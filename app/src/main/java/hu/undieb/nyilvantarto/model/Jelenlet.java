package hu.undieb.nyilvantarto.model;

public class Jelenlet {
    public enum Status{
        PRESENT,
        RECORDEDBYTEACHER,
        MISSING
    }
    private String name;
    private Status status;

    public Jelenlet(String name) {
        this.name = name;
        this.status=Status.MISSING;
    }
    public Jelenlet(String name,Status status) {
        this.name = name;
        this.status=status;
    }
    public Jelenlet()
    {

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Jelenlet{" +
                "name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
