package digital.school.parkingapi.business.service;

import digital.school.parkingapi.model.entity.Invoice;

import java.time.LocalDateTime;
import java.util.List;

public interface IInvoiceService {

    Invoice createInvoice(Invoice invoice);
    List<Invoice> listAllInvoices();
    Invoice findByPlateAndEntryTime(String plate, LocalDateTime entryTime);
    Long getTotalToPay(LocalDateTime entryTime);

}
