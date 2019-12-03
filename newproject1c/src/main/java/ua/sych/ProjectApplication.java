package ua.sych;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.sych.entity.*;
import ua.sych.repository.*;

import java.util.ArrayList;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(ProjectApplication.class, args);

    }

    @Autowired
    private EnterpriseRepository enterpriseRepository;
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Enterprise enterprise = new Enterprise();
            enterprise.setName("enterprise" + i);
            enterprise.setAddress("street" + i);
            enterprise.setTaxNumber("tax" + i);
            enterpriseRepository.save(enterprise);
        }
        for (int i = 0; i < 3; i++) {
            Payment payment = new Payment();
            payment.setType("type " + i);
            paymentRepository.save(payment);
        }
        for (int i = 0; i < 5; i++) {
            Warehouse warehouse = new Warehouse();
            warehouse.setName("warehouse "+i);
            warehouseRepository.save(warehouse);
        }
        for (int i = 0; i < 20; i++) {
            Partner partner = new Partner();
            partner.setName("partner " + i);
            partner.setAddress("street " + i);
            partner.setPayments(new ArrayList<Payment>());
            partnerRepository.save(partner);
        }
        for (int i = 0; i < 10; i++) {
            Long id = 1L;
            Product product = new Product();
            product.setName("product "+i);
            product.setPriceForSale(i+5);
            product.setPurchasePrice(i+2);
            product.setAmount(i+1);
            product.setWarehouse(warehouseRepository.findById(id).get());
            productRepository.save(product);
        }
    }
}
