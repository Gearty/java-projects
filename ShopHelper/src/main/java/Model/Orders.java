package Model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Orders {

    @Id
    @Column(name = "order_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_gen")
    @SequenceGenerator(name="orders_gen", sequenceName = "order_id_seq", allocationSize = 1)
    private Integer orderId;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "delivering_place", nullable = false)
    private String delivering_place;

    @Column(name = "sending_date", nullable = false)
    private String sending_date;

    @Column(name = "items_price", nullable = false)
    private Double items_price;

    @Column(name = "payment")
    private String payment;

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "user_id")
    private Users user;

    public Orders() {
    }

    public Orders(Integer number, String delivering_place, String sending_date, Double items_price, String payment, Users user) {
        this.number = number;
        this.delivering_place = delivering_place;
        this.sending_date = sending_date;
        this.items_price = items_price;
        this.payment = payment;
        this.user = user;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDelivering_place() {
        return delivering_place;
    }

    public void setDelivering_place(String delivering_place) {
        this.delivering_place = delivering_place;
    }

    public String getSending_date() {
        return sending_date;
    }

    public void setSending_date(String sending_date) {
        this.sending_date = sending_date;
    }

    public Double getItems_price() {
        return items_price;
    }

    public void setItems_price(Double items_price) {
        this.items_price = items_price;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

}
