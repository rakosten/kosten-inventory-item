package kosten.inventory.handler;

import kosten.inventory.dto.InventoryDto;
import kosten.inventory.dto.Request;
import kosten.inventory.service.InventoryService;
import kosten.inventory.service.InventoryServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class InventoryItemRequestHandlerTest {

    private InventoryItemRequestHandler inventoryItemRequestHandler;
    private InventoryService inventoryService;

    @Before
    public void setUp() {
        inventoryService = mock(InventoryServiceImpl.class);
        inventoryItemRequestHandler = spy(new InventoryItemRequestHandler());
        inventoryItemRequestHandler.setInventoryService(inventoryService);
    }

    @Test
    public void handleRequest() {
        Date date = new Date();
        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setInventoryItemName("testItem");
        inventoryDto.setInventoryItemLastUpdatedDate(date);
        inventoryDto.setInventoryItemId(1L);
        inventoryDto.setInventoryItemCount(7);

        when(inventoryService.getInventoryItemsById(any())).thenReturn(inventoryDto);

        Request request = new Request();
        request.setInventoryItemId(1L);

        InventoryDto returnValue = inventoryItemRequestHandler.handleRequest(request, null);

        assertEquals("testItem", returnValue.getInventoryItemName());
        assertSame(1L, inventoryDto.getInventoryItemId());
        assertEquals(date, inventoryDto.getInventoryItemLastUpdatedDate());
    }
}