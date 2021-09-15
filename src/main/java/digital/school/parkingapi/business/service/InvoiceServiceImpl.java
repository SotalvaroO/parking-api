package digital.school.parkingapi.business.service;

import digital.school.parkingapi.data.repository.IInvoiceRepository;
import digital.school.parkingapi.model.entity.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
public class InvoiceServiceImpl implements IInvoiceService {

    @Autowired
    IInvoiceRepository iInvoiceRepository;

    @Override
    public Invoice createInvoice(Invoice invoice) {
        Invoice invoiceDB = findByPlateAndEntryTime(invoice.getVehiclePlate(), invoice.getEntryTime());
        if (invoiceDB != null) {
            return invoiceDB;
        }
        invoiceDB = iInvoiceRepository.save(invoice);
        return invoiceDB;
    }

    @Override
    public List<Invoice> listAllInvoices() {
        return iInvoiceRepository.findAll();
    }

    @Override
    public Invoice findByPlateAndEntryTime(String plate, LocalDateTime entryTime) {
        return iInvoiceRepository.findByVehiclePlateAndEntryTime(plate, entryTime);
    }

    @Override
    public Long getTotalToPay(LocalDateTime entryTime) {
        LocalDateTime realTime = LocalDateTime.now();
        Long totalTime = MINUTES.between(entryTime, realTime);
        Long totalToPay = 0L;
        if (totalTime < 1 && totalTime >= 0) {
            totalToPay = 100L;
            return totalToPay;
        }
        totalToPay = totalTime * 100;
        return totalToPay;
    }

}
