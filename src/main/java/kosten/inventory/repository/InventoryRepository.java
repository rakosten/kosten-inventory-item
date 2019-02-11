package kosten.inventory.repository;

import kosten.inventory.repository.entity.InventoryItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class InventoryRepository {

    private static final String RETRIEVE_INVENTORY_ITEM_BY_ID = "SELECT * FROM inventory_item where inventory_item_id = ?";
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryRepository.class);

    /**
     * Retrieves item by the id parameter.  Uses <class>JDBCUtil</class> to call mySQL database.
     *
     * @param inventoryItemId ... the id of the inventory item
     * @return <class>InventoryItem</class> ... essentially the entity
     */

    public InventoryItem retrieveItemById(Long inventoryItemId) {
        return JDBCUtil.execute((connection) -> {
            PreparedStatement inventoryItemStatement = connection.prepareStatement(RETRIEVE_INVENTORY_ITEM_BY_ID);
            inventoryItemStatement.setLong(1, inventoryItemId);
            ResultSet resultSet = inventoryItemStatement.executeQuery();

            LOGGER.info("Retrieved record for inventory item ID: {}", inventoryItemId);

            final InventoryItem inventoryItem = new InventoryItem();
            if (resultSet.next()) {
                inventoryItem.setInventoryItemId(resultSet.getLong("inventory_item_id"));
                inventoryItem.setInventoryItemName(resultSet.getString("inventory_item_name"));
                inventoryItem.setInventoryItemCount(resultSet.getInt("inventory_item_count"));
                inventoryItem.setInventoryItemLastUpdatedDate(resultSet.getDate("inventory_item_last_updated_date"));
            }
            return inventoryItem;
        });

    }
}
