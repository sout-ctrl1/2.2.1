package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String model;

    int series;

    @OneToOne(optional = false, mappedBy="car")
    User user;

    public Car() {
    }

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getID() {
        return id;
    }

    public void setID(long ID) {
        this.id = ID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

}
