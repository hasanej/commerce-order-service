package hsn.commerce.order.controller;

import hsn.commerce.order.model.OrderRequest;
import hsn.commerce.order.model.OrderResponse;
import hsn.commerce.order.model.PurchaseRequest;
import hsn.commerce.order.model.WebResponse;
import hsn.commerce.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping(
            path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<OrderResponse> create(
            @RequestBody OrderRequest orderRequest,
            PurchaseRequest purchaseRequest,
            Integer inventoryId) {
        OrderResponse orderResponse = orderService.create(orderRequest, purchaseRequest, inventoryId);
        return WebResponse.<OrderResponse>builder().data(orderResponse).build();
    }
}
