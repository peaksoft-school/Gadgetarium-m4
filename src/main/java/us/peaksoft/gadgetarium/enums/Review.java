package us.peaksoft.gadgetarium.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum Review {

    DELIVERY_VARIETIES("DELIVERY_VARIETIES"), PAYMENT("PAYMENT"), ORDER_REVIEW("ORDER_REVIEW");
    private String stringType;

    Review(String stringType) {
        this.stringType = stringType;
    }

    public String getStringType() {
        return stringType;
    }

    public void setStringType(String stringType) {
        this.stringType = stringType;
    }
}
