package kosten.inventory.service;

import kosten.inventory.dto.InventoryDto;
import kosten.inventory.repository.InventoryRepository;
import kosten.inventory.repository.entity.InventoryItem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class InventoryServiceImplTest {

    private InventoryService inventoryService;
    private InventoryRepository inventoryRepository;

    @Before
    public void setUp() {
        inventoryRepository = mock(InventoryRepository.class);
        inventoryService = spy(new InventoryServiceImpl(inventoryRepository));
    }

    @Test
    public void getInventoryItemsById() {
        Date date = new Date();
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setInventoryItemLastUpdatedDate(date);
        inventoryItem.setInventoryItemCount(7);
        inventoryItem.setInventoryItemName("testItem");
        inventoryItem.setInventoryItemId(1L);

        when(inventoryRepository.retrieveItemById(any())).thenReturn(inventoryItem);

        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setInventoryItemCount(7);
        inventoryDto.setInventoryItemId(1L);
        inventoryDto.setInventoryItemLastUpdatedDate(date);
        inventoryDto.setInventoryItemName("testItem");

        InventoryDto returnValue = inventoryService.getInventoryItemsById(1L);

        assertEquals("testItem", returnValue.getInventoryItemName());
        assertSame(7, returnValue.getInventoryItemCount());
        assertEquals(date, returnValue.getInventoryItemLastUpdatedDate());
        assertSame(1L, returnValue.getInventoryItemId());
    }
}