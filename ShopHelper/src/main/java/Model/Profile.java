package Model;

import javax.persistence.*;


@Entity
public class Profile {

    @Id
    @Column(name = "profile_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_gen")
    @SequenceGenerator(name="profile_gen", sequenceName = "profile_id_seq", allocationSize = 1)
    private Integer profileId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phone_number;

    @OneToOne(targetEntity = Users.class)
    @JoinColumn(name = "user_id")
    private Users user;

    public Profile() {
    }

    public Profile(String name, String surname, String phone_number, Users user) {
        this.name = name;
        this.surname = surname;
        this.phone_number = phone_number;
        this.user = user;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
