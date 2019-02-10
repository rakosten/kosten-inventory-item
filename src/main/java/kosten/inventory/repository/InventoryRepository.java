package kosten.inventory.repository;

import kosten.inventory.repository.entity.InventoryItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InventoryRepository {

    private static final String RETRIEVE_INVENTORY_ITEM_BY_NAME = "SELECT * FROM inventory_item where inventory_item_id = ?";
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryRepository.class);

    /**
     * Retrieves item by the id parameter.  Uses <class>JDBCUtil</class> to call mySQL database.
     *
     * @param inventoryItemId ... the id of the inventory item
     * @return <class>InventoryItem</class> ... essentially the entity
     */
    public InventoryItem retrieveItemById(Long inventoryItemId) {
        return JDBCUtil.execute((conn) -> {
            PreparedStatement inventoryItemStatement = conn.prepareStatement(RETRIEVE_INVENTORY_ITEM_BY_NAME);
            inventoryItemStatement.setLong(1, inventoryItemId);
            ResultSet rs = inventoryItemStatement.executeQuery();

            LOGGER.info("Retrieved record for inventory item ID: {}", inventoryItemId);

            final InventoryItem inventoryItem = new InventoryItem();
            if (rs.next()) {
                inventoryItem.setInventoryItemId(rs.getLong("inventory_item_id"));
                inventoryItem.setInventoryItemName(rs.getString("inventory_item_name"));
                inventoryItem.setInventoryItemCount(rs.getInt("inventory_item_count"));
                inventoryItem.setInventoryItemLastUpdatedDate(rs.getDate("inventory_item_last_updated_date"));
            }
            return inventoryItem;
        });

    }
}
