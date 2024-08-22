package hsn.commerce.order.service;

import hsn.commerce.order.entity.Order;
import hsn.commerce.order.feignClient.InventoryClient;
import hsn.commerce.order.model.InventoryResponse;
import hsn.commerce.order.model.OrderRequest;
import hsn.commerce.order.model.OrderResponse;
import hsn.commerce.order.model.PurchaseRequest;
import hsn.commerce.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ValidationService validationService;

    @Autowired
    InventoryClient inventoryClient;

    @Transactional
    public OrderResponse create(OrderRequest orderRequest, PurchaseRequest purchaseRequest, Integer inventoryId) {
        validationService.validate(orderRequest);

        Order order = new Order();
        order.setInventoryId(orderRequest.getInventoryId());
        orderRepository.save(order);

        ResponseEntity<InventoryResponse> inventoryResponse
                = inventoryClient.purchaseByInventoryId(inventoryId, purchaseRequest);

        return OrderResponse.builder()
                .id(order.getId())
                .inventoryId(order.getInventoryId())
                .inventoryResponse(inventoryResponse.getBody())
                .build();
    }
}
