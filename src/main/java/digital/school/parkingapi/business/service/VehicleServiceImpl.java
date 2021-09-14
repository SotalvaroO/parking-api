package digital.school.parkingapi.business.service;

import digital.school.parkingapi.data.repository.IVehicleRepository;
import digital.school.parkingapi.model.entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements IVehicleService{

    @Autowired
    private IVehicleRepository iVehicleRepository;

    @Override
    public Vehicle registerVehicle(Vehicle vehicle) {
        Vehicle vehicleDB = iVehicleRepository.findByVehiclePlate(vehicle.getVehiclePlate());
        if (vehicleDB != null){
            return vehicleDB;
        }
        vehicleDB = iVehicleRepository.save(vehicle);
        return vehicleDB;
    }

    @Override
    public Vehicle findById(Long id) {
        return iVehicleRepository.findById(id).orElse(null);
    }

    @Override
    public Vehicle findByPlate(String plate) {
        return iVehicleRepository.findByVehiclePlate(plate);
    }

    @Override
    public List<Vehicle> listAllVehicles() {
        return iVehicleRepository.findAll();
    }

    @Override
    public Vehicle deleteVehicle(Long id) {
        Vehicle vehicle = findById(id);
        if (vehicle == null){
            return vehicle;
        }
        iVehicleRepository.deleteById(id);
        return  vehicle;
    }

    @Override
    public List<Vehicle> listByVehiclePlate(String plate) {
        return iVehicleRepository.findAllByVehiclePlate(plate);
    }
}
