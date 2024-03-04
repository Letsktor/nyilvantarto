package hu.undieb.nyilvantarto;

public class Student {
    private String name;
    private String cardNumber;
    private String cardId;
    private String neptunCode;

    public Student(String name, String cardNumber, String cardId, String neptunCode) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.cardId = cardId;
        this.neptunCode = neptunCode;
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

    public String getNeptunCode() {
        return neptunCode;
    }

    public void setNeptunCode(String neptunCode) {
        this.neptunCode = neptunCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
