package Model;

import javax.persistence.*;

@Entity
public class Supplies {

    @Id
    @Column(name = "supply_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supply_gen")
    @SequenceGenerator(name="supply_gen", sequenceName = "supply_id_seq", allocationSize = 1)
    private Integer supplyId;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "delivery")
    private String delivery;

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "user_id")
    private Users user;

    public Supplies() {
    }

    public Supplies(String number, Double price, String date, String delivery, Users user) {
        this.number = number;
        this.price = price;
        this.date = date;
        this.delivery = delivery;
        this.user = user;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public Integer getSupplyId() {
        return supplyId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }


}
