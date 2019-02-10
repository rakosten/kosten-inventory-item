package kosten.inventory.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import kosten.inventory.dto.InventoryDto;
import kosten.inventory.dto.Request;
import kosten.inventory.service.InventoryService;
import kosten.inventory.service.InventoryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InventoryItemRequestHandler implements RequestHandler<Request, InventoryDto> {

    private InventoryService inventoryService = new InventoryServiceImpl();

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryItemRequestHandler.class);
    /**
     * Handles request to retrieve inventory item based on an item id
     *
     * @param request Request ... has field for inventory item id
     * @param context Context ... simple Lambda Context
     * @return <class>InventoryItemDto</class>
     */
    @Override
    public InventoryDto handleRequest(Request request, Context context) {
        LOGGER.info("Retrieving inventory item for ID: {}", request.getInventoryItemId());
        return inventoryService.getInventoryItemsById(request.getInventoryItemId());
    }

    /**
     * Old-fashioned setter for testing
     *
     * @param inventoryService ... InventoryService
     */
    protected void setInventoryService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
}
