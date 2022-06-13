package hiber.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @Column(nullable = true)
    private Long id;

    @Column
    private String model;

    @Column
    private  int series;

   // @OneToOne(cascade = CascadeType.DETACH, optional = false, orphanRemoval = true, fetch = FetchType.LAZY)

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    private User user;

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "model = " + model + ", " +
                "series = " + series + ")";
    }
}