package hu.undieb.nyilvantarto.model;

public class Jelenlet {
    public enum Status{
        PRESENT,
        RECORDEDBYTEACHER,
        MISSING
    }
    private String name;
    private Status status=Status.MISSING;

    public Jelenlet(String name) {
        this.name = name;
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
}
