package digital.school.parkingapi.presentation.controller;

import digital.school.parkingapi.business.service.IInvoiceService;
import digital.school.parkingapi.business.service.IVehicleService;
import digital.school.parkingapi.model.entity.Invoice;
import digital.school.parkingapi.model.entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/voucher")
public class InvoiceController {

    @Autowired
    private IInvoiceService iInvoiceService;
    @Autowired
    IVehicleService iVehicleService;


    @GetMapping
    public ResponseEntity<List<Invoice>> listAllVouchers() {
        List<Invoice> invoices = iInvoiceService.listAllInvoices();
        if (invoices.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(invoices);
    }

    @GetMapping(value = "/{plate}")
    public ResponseEntity<Invoice> createVoucher(@PathVariable String plate) {
        Vehicle vehicle = iVehicleService.findByPlate(plate);
        if (vehicle == null) {
            return ResponseEntity.notFound().build();
        }
        Invoice invoice = new Invoice();
        invoice.setVehiclePlate(vehicle.getVehiclePlate());
        invoice.setEntryTime(vehicle.getEntryTime());
        invoice.setExitTime(LocalDateTime.now());
        invoice.setTotalToPay(iInvoiceService.getTotalToPay(invoice.getEntryTime()));

        Invoice invoiceDB = iInvoiceService.createInvoice(invoice);

        iVehicleService.deleteVehicle(vehicle.getId());


        return ResponseEntity.ok(invoiceDB);
    }

}
