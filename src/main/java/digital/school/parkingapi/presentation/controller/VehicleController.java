package digital.school.parkingapi.presentation.controller;

import digital.school.parkingapi.business.service.IVehicleService;
import digital.school.parkingapi.model.entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/vehicle")
public class VehicleController {

    @Autowired
    private IVehicleService iVehicleService;

    @GetMapping
    public ResponseEntity<List<Vehicle>> listVehicles(@RequestParam(name = "plate", required = false) String plate) {
        List<Vehicle> vehicles = new ArrayList<>();
        if (plate != null) {
            vehicles = iVehicleService.listByVehiclePlate(plate);
            if (vehicles.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        } else {
            vehicles = iVehicleService.listAllVehicles();
            if (vehicles.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Vehicle> findVehicleById(@PathVariable("id") Long id) {
        Vehicle vehicle = iVehicleService.findById(id);
        if (vehicle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehicle);
    }

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        Vehicle vehicleDB = iVehicleService.registerVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleDB);
    }

}
