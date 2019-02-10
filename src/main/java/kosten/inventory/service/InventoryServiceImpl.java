package kosten.inventory.service;

import kosten.inventory.dto.InventoryDto;
import kosten.inventory.repository.InventoryRepository;
import kosten.inventory.repository.entity.InventoryItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository inventoryRepository = new InventoryRepository();
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Override
    public InventoryDto getInventoryItemsById(Long inventoryItemId) {
        InventoryItem inventoryItem = inventoryRepository.retrieveItemById(inventoryItemId);


        return convertItem(inventoryItem);
    }

    private InventoryDto convertItem(InventoryItem inventoryItem) {
        LOGGER.info("Converting inventory item to dto for ID: {}", inventoryItem.getInventoryItemId());
        InventoryDto dto = new InventoryDto();
        dto.setInventoryItemCount(inventoryItem.getInventoryItemCount());
        dto.setInventoryItemId(inventoryItem.getInventoryItemId());
        dto.setInventoryItemLastUpdatedDate(inventoryItem.getInventoryItemLastUpdatedDate());
        dto.setInventoryItemName(inventoryItem.getInventoryItemName());

        return dto;
    }

    protected void setInventoryRepository(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
}
