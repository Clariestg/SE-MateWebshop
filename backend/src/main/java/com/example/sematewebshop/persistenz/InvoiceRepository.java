//Spring Boot + Spring Data JPA erzeugen dynamisch zur Laufzeit eine Klasse zur Implementierung der Repo-Interfaces.
//Hier also, nur das Interface und die richtigen Methodennamen angeben

package com.example.sematewebshop.persistenz;

import com.example.sematewebshop.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findByCustomer_CustomerId(Long customerId);
    Optional<Invoice> findByOrderOrderId(Long orderId);
}
