package au.com.woolworths.apigee.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Order {
  private Object PrimaryAddress;
  private DeliveryDetails Delivery;
  private Pickup Pickup;
  private Object Fulfilment;
  private String Instructions;
  private double Savings;
  private int TeamDiscount;
  private int OrderDiscount;
  private int Subtotal;
  private int PackagingFee;
  private String PackagingFeeLabel;
  private int DeliveryFee;
  private int DeliveryFeeDiscount;
  private int DeliveryFeeBeforeDiscount;
  private int BalanceToPay;
  private int TotalIncludingGst;
  private int WowRewardsPaymentAmount;
  private int RedeemableWowRewardsDollars;
  private int DeferredWowRewardsDollars;
  private int StoreCreditTotal;
  private String EdrNumber;
  private Object Loyalty;
  private Object Discounts;
  private Object GiftCardPayments;
  private Object AvailableOrderItems;
  private Object UnavailableOrderItems;
  private Object RestrictedOrderItems;
  private Object ExceededSupplyLimitProducts;
  private Object ExceededSupplyLimitProductGroups;
  private Object RestrictedProductsByDeliveryMethod;
  private Object RestrictedProductsByDeliPlatter;
  private Object Errors;
  private boolean CanProceedToPayment;
  private Object OrderTotalsTableData;
  private LeaveUnattended LeaveUnattended;
}
