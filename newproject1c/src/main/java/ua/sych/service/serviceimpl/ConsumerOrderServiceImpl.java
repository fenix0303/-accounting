package ua.sych.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sych.dto.request.ConsumerOrderRequest;
import ua.sych.dto.response.ConsumerOrderResponse;
import ua.sych.dto.response.ProductResponse;
import ua.sych.entity.ConsumerOrder;
import ua.sych.entity.Partner;
import ua.sych.entity.Product;
import ua.sych.exception.WrongInputDataException;
import ua.sych.repository.ConsumerOrderRepository;
import ua.sych.utils.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsumerOrderServiceImpl {
    @Autowired
    private ConsumerOrderRepository consumerOrderRepository;
    @Autowired
    private PartnerServiceImpl partnerService;
    @Autowired
    private ProductService productService;

    public void saveConsumerOrder(ConsumerOrderRequest orderRequest) {
        ConsumerOrder order = new ConsumerOrder();
        order.setNumber(orderRequest.getNumber());
        order.setDate(orderRequest.getDate());
        Partner partner = ObjectMapperUtils.map(partnerService
                .findById(orderRequest.getPartnerId()), Partner.class);
        order.setPartner(partner);
        List<Product> products = new ArrayList<>();
        for (Long id : orderRequest.getProductsId()
        ) {
            products.add(ObjectMapperUtils.map(productService.findById(id), Product.class));
        }
        order.setProducts(products);
        consumerOrderRepository.save(order);
    }

    public List<ConsumerOrderResponse> findAllConsumerOrders() {

        return ObjectMapperUtils.mapAll(consumerOrderRepository.findAll(),
                ConsumerOrderResponse.class);
    }

    public ConsumerOrderResponse findById(Long id) {
        ConsumerOrder consumerOrder = consumerOrderRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Order with id [" + id + "] not found"));

        return ObjectMapperUtils.map(consumerOrder, ConsumerOrderResponse.class);
    }


    public void updateConsumerOrder(Long id, ConsumerOrderRequest order) {
        ConsumerOrder orderByDB = consumerOrderRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Order with " + id + " not found"));
        orderByDB.setDate(order.getDate());
        orderByDB.setNumber(order.getNumber());
        Partner partner = ObjectMapperUtils.map(partnerService
                .findById(order.getPartnerId()), Partner.class);
        orderByDB.setPartner(partner);
        List<Product> products = new ArrayList<>();
        for (Long idProduct : order.getProductsId()
        ) {
            products.add(ObjectMapperUtils.map(productService.findById(idProduct), Product.class));
        }
        orderByDB.setProducts(products);
        consumerOrderRepository.save(orderByDB);
    }

    public void delete(Long id) {
        ConsumerOrder orderByDB = consumerOrderRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Order with " + id + " not found"));
        if (orderByDB.getProducts().isEmpty()) {
            consumerOrderRepository.delete(orderByDB);
        } else {
            throw new WrongInputDataException("Order with " + id + " has products");
        }
    }
}
