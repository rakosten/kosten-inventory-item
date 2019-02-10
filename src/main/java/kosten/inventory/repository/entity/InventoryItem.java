package kosten.inventory.repository.entity;

import java.util.Date;


public class InventoryItem {

    private Long inventoryItemId;
    private String inventoryItemName;
    private Integer inventoryItemCount;
    private Date inventoryItemLastUpdatedDate;

    public Long getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(Long inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public String getInventoryItemName() {
        return inventoryItemName;
    }

    public void setInventoryItemName(String inventoryItemName) {
        this.inventoryItemName = inventoryItemName;
    }

    public Integer getInventoryItemCount() {
        return inventoryItemCount;
    }

    public void setInventoryItemCount(Integer inventoryItemCount) {
        this.inventoryItemCount = inventoryItemCount;
    }

    public Date getInventoryItemLastUpdatedDate() {
        return inventoryItemLastUpdatedDate;
    }

    public void setInventoryItemLastUpdatedDate(Date inventoryItemLastUpdatedDate) {
        this.inventoryItemLastUpdatedDate = inventoryItemLastUpdatedDate;
    }
}
