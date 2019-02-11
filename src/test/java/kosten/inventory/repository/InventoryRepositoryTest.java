package kosten.inventory.repository;

import kosten.inventory.repository.entity.InventoryItem;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.spy;

public class InventoryRepositoryTest {

    private InventoryRepository inventoryRepository;

    @Rule
    public final EnvironmentVariables environmentVariables = new EnvironmentVariables();

    @Before
    public void setUp() {

        inventoryRepository = spy(new InventoryRepository());
        environmentVariables.set("RDS_JDBC_URL", "jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1;" +
                "INIT=create table IF NOT EXISTS INVENTORY_ITEM (inventory_item_id NUMBER, inventory_item_name VARCHAR, inventory_item_count NUMBER, inventory_item_last_updated_date DATE)\\; " +
                "insert into INVENTORY_ITEM(inventory_item_id, inventory_item_name, inventory_item_count, inventory_item_last_updated_date) VALUES (2, 'keyboard', 8, SYSDATE - 1)\\;" +
                "insert into INVENTORY_ITEM(inventory_item_id, inventory_item_name, inventory_item_count, inventory_item_last_updated_date) VALUES (1, 'monitor', 7, SYSDATE);");
        environmentVariables.set("RDS_JDBC_DRIVER", "org.h2.Driver");

    }

    @Test
    public void retrieveItemById() {
        InventoryItem inventoryItem = inventoryRepository.retrieveItemById(1L);
        assertEquals("monitor", inventoryItem.getInventoryItemName());
        assertSame(1L, inventoryItem.getInventoryItemId());
        assertSame(7, inventoryItem.getInventoryItemCount());
        assertNotNull(inventoryItem.getInventoryItemLastUpdatedDate());
    }
}