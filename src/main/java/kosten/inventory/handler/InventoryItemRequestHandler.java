package kosten.inventory.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import dagger.Module;
import dagger.Provides;
import kosten.inventory.dto.InventoryDto;
import kosten.inventory.dto.Request;
import kosten.inventory.service.InventoryService;
import kosten.inventory.service.InventoryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;


public class InventoryItemRequestHandler implements RequestHandler<Request, InventoryDto> {

    private InventoryService inventoryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryItemRequestHandler.class);

    @Inject
    public InventoryItemRequestHandler(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }


    /**
     * Handles request to retrieve inventory item based on an item id
     *
     * @param request Request ... has field for inventory item id
     * @param context Context ... simple Lambda Context
     * @return <class>InventoryItemDto</class>
     */
    @Override
    @Provides
    public InventoryDto handleRequest(Request request, Context context) {
        LOGGER.info("Retrieving inventory item for ID: {}", request.getInventoryItemId());
        return inventoryService.getInventoryItemsById(request.getInventoryItemId());
    }



}
