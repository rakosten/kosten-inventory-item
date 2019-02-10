package kosten.inventory.service;

import kosten.inventory.dto.InventoryDto;

/**
 * Service layer for handling inventory item requests
 */
public interface InventoryService {
    /**
     * Gets inventory item for inventory item id ... invokes repository
     * @param inventoryItemId ... the id of the item
     * @return <class>InventoryDto</class>
     */
    InventoryDto getInventoryItemsById(Long inventoryItemId);
}
