package digital.school.parkingapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "tbl_vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vehicle_plate")
    private String vehiclePlate;

    @Column(name = "entry_time")
    private LocalDateTime entryTime = LocalDateTime.now();


}
