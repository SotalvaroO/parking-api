package digital.school.parkingapi.business.service;

import digital.school.parkingapi.model.entity.Vehicle;

import java.time.LocalDateTime;
import java.util.List;

public interface IVehicleService {

    Vehicle registerVehicle(Vehicle vehicle);
    Vehicle findById(Long id);
    Vehicle findByPlate(String plate);
    List<Vehicle> listAllVehicles();
    Vehicle deleteVehicle(Long id);
    List<Vehicle> listByVehiclePlate(String plate);

}
