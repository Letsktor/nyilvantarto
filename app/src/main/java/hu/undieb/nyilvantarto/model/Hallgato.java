package hu.undieb.nyilvantarto.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Hallgato {
    private String name;
    private String cardNumber;
    private String cardId;
    public Hallgato(){}
    public Hallgato(String name, String cardNumber, String cardId) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.cardId = cardId;
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

    @Override
    public String toString() {
        return "Hallgato{" +
                "name='" + name + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardId='" + cardId + '\'' +
                '}';
    }
}
