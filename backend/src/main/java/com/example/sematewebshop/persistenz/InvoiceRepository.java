//Spring Boot + Spring Data JPA erzeugen dynamisch zur Laufzeit eine Klasse zur Implementierung der Repo-Interfaces.
//Hier also, nur das Interface und die richtigen Methodennamen angeben

package com.example.sematewebshop.persistenz;

import com.example.sematewebshop.domain.Customer;
import com.example.sematewebshop.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findByCustomer(Customer customer); //Billing History anzeigen lassen

}
