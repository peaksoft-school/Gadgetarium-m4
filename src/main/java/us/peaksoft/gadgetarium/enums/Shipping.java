package us.peaksoft.gadgetarium.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum Shipping {
    FROM_SHOP("Самовывоз из магазина"), COURIER_DELIVERY("Доставка курьером");

    private String Shipping;

    Shipping(String shipping) {
        Shipping = shipping;
    }

    public String getShipping() {
        return Shipping;
    }

    public void setShipping(String shipping) {
        Shipping = shipping;
    }
}
