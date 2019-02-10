package kosten.inventory.dto;


import java.io.Serializable;

public class Request implements Serializable {

    private Long inventoryItemId;

    public Long getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(Long inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

}
