package us.peaksoft.gadgetarium.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import us.peaksoft.gadgetarium.enums.OrderStatus;
import us.peaksoft.gadgetarium.enums.PaymentType;
import us.peaksoft.gadgetarium.enums.Shipping;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private Shipping shipping;
    private PaymentType typePayment;
    private OrderStatus orderStatus;
    private Long userId;
    private String countryName;
    private String cityName;
    private String streetName;
    private String stateName;
    private String postalCode;
}
