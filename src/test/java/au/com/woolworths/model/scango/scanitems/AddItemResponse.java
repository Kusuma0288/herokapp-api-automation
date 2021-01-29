package au.com.woolworths.model.scango.scanitems;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AddItemResponse {
    private String cartid;
    private String lastupdated;
    private String status;
    private String reason;
    private String storeid;
    private Double merchanttotal;
    private Double totalprice;
    private Double totalgst;
    private Integer savings;
    private Integer totalquantity;
    private String rewardcardnumber;
    private Integer rewardsredeemed;
    private Integer rewardsaccrued;
    private Integer intervtnprice;
    private Integer intervtnquantity;
    private Integer totalsavings;
    private List<Item> items = null;
    private List<Object> notifications = null;
    private List<Object> coupons = null;
    private List<Object> offers = null;
    private List<Object> pendingpromotions = null;
    private Discounts discounts;
    private List<Object> totaldiscounts = null;
    private String statusCode;
}
