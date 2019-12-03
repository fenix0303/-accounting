package ua.sych.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sych.dto.request.ProviderOrderRequest;
import ua.sych.dto.response.ProviderOrderResponse;
import ua.sych.entity.Partner;
import ua.sych.entity.Product;
import ua.sych.entity.ProviderOrder;
import ua.sych.exception.WrongInputDataException;
import ua.sych.repository.ProviderOrderRepository;
import ua.sych.utils.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderOrderImpl {
    @Autowired
    private ProviderOrderRepository providerOrderRepository;
    @Autowired
    private PartnerServiceImpl partnerService;
    @Autowired
    private ProductService productService;

    public void save(ProviderOrderRequest orderRequest) {
        ProviderOrder order = new ProviderOrder();
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
        providerOrderRepository.save(order);
    }

    public List<ProviderOrderResponse> findAllProviderOrders() {
        return ObjectMapperUtils.mapAll(providerOrderRepository.findAll(),
                ProviderOrderResponse.class);
    }

    public ProviderOrderResponse findById(Long id) {
        ProviderOrder providerOrder = providerOrderRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Order with id [" + id + "] not found"));

        return ObjectMapperUtils.map(providerOrder, ProviderOrderResponse.class);
    }

    public void update(Long id, ProviderOrderRequest order) {
        ProviderOrder orderByDB = providerOrderRepository.findById(id)
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
        providerOrderRepository.save(orderByDB);
    }

     public void delete(Long id) {
        ProviderOrder orderByDB = providerOrderRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("Order with " + id + " not found"));
        if (orderByDB.getProducts().isEmpty()) {
            providerOrderRepository.delete(orderByDB);
        } else {
            throw new WrongInputDataException("Order with " + id + " has products");
        }
    }
}
