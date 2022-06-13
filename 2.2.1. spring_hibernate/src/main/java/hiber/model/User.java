package hiber.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter

@Entity
@Table(name = "users")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @Column(name = "email")
   private String email;

   @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = true, fetch = FetchType.LAZY)
   private Car car;

   public void setCar(Car car) {
      if (car == null) {
         if (this.car != null) {
            this.car.setUser(null);
         }
      } else {
         car.setUser(this);
      }
      this.car = car;
   }
   
   public User(String firstName, String lastName, String email) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
   }

   public User(String firstName, String lastName, String email, Car car) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      setCar(car);
   }

   @Override
   public String toString() {
      String carDescription = this.car == null ? "no car" : this.car.toString();
      return getClass().getSimpleName() + "(" +
              "id = " + id + ", " +
              "firstName = " + firstName + ", " +
              "lastName = " + lastName + ", " +
              "email = " + email + ")" + ", " +
              "car = " + carDescription;
   }
}
