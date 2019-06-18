package it.uniroma3.domain;

import javax.persistence.Embeddable;

@Embeddable
public class LineItem {
    private String lineItemId;
    private String menuItemId;
    private int quantity;

    public LineItem(){}

    public LineItem(String lineItemId, String menuItemId, int quantity) {
        this.lineItemId = lineItemId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
    }

    public String getLineItemId() {
        return lineItemId;
    }

    public void setLineItemId(String lineItemId) {
        this.lineItemId = lineItemId;
    }

    public String getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(String menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
