package us.peaksoft.gadgetarium.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum OrderStatus {
    CREATED, CONFIRMED, SHIPPED, CANCELLED, REFUNDED
}