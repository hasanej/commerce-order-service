package hsn.commerce.order.feignClient;

import hsn.commerce.order.model.InventoryResponse;
import hsn.commerce.order.model.PurchaseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service", url = "http://localhost:8080", path = "/api/inventory")
public interface InventoryClient {
    @PatchMapping(
            name = "/purchase/{inventoryId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<InventoryResponse> purchaseByInventoryId(@PathVariable("inventoryId") int id,
                                                                   @RequestBody PurchaseRequest request);
}