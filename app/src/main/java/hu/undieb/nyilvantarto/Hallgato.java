package hu.undieb.nyilvantarto;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Hallgato {
    private String name;
    private String cardNumber;
    private String cardId;

    private boolean jelenvan;
    private boolean hianyzik;
    private boolean oktatoaltalrogzitve;
    private boolean cardSet;
    public boolean isCardSet() {
        return cardSet;
    }

    public void setCardSet(boolean cardSet) {
        this.cardSet = cardSet;
    }


    public Hallgato(){}
    public Hallgato(String name, String cardNumber, String cardId) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.cardId = cardId;
        this.jelenvan=false;
        this.hianyzik=false;
        this.oktatoaltalrogzitve=false;
        this.cardSet=false;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public boolean isJelenvan() {
        return jelenvan;
    }

    public void setJelenvan(boolean jelenvan) {
        this.jelenvan = jelenvan;
    }

    public boolean isHianyzik() {
        return hianyzik;
    }

    public void setHianyzik(boolean hianyzik) {
        this.hianyzik = hianyzik;
    }

    public boolean isOktatoaltalrogzitve() {
        return oktatoaltalrogzitve;
    }

    public void setOktatoaltalrogzitve(boolean oktatoaltalrogzitve) {
        this.oktatoaltalrogzitve = oktatoaltalrogzitve;
    }
}
