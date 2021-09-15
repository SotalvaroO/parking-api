package digital.school.parkingapi.data.repository;

import digital.school.parkingapi.model.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IInvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findByVehiclePlateAndEntryTime(String vehiclePlate, LocalDateTime entryTime);
}
