package digital.school.parkingapi.data.repository;

import digital.school.parkingapi.model.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findByVehiclePlate(String plate);
    List<Vehicle> findAllByVehiclePlate(String vehiclePlate);

}
