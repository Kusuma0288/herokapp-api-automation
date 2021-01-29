package au.com.woolworths.model.scango.kiosk;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class KioskPaymentRequest {
    private String cartbarcode;
    private Paymentinfo paymentinfo;
}
