package au.com.woolworths.helpers.trader;

import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;
import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.trader.*;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CheckoutHelper extends BaseHelper {
  private final static Logger logger = Logger.getLogger("CheckoutHelper.class");
  RestInvocationUtil invocationUtil;

  public CheckoutHelper() {
    //this.invocationUtil = new RestInvocationUtil();
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public CheckoutV2Response getCheckoutResponseBasedOnTrolleyItems() throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr;
    Map<String, String> queryParams = new HashMap<>();
    CheckoutV2Response response;
    String endPoint = URLResources.V2_CHECKOUT;
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, CheckoutV2Response.class);
    return response;
  }

  public CheckoutV3Response getV3CheckoutResponseBasedOnTrolleyItems() throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr;
    Map<String, String> queryParams = new HashMap<>();
    CheckoutV3Response response;
    String endPoint = URLResources.V3_CHECKOUT;
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, CheckoutV3Response.class);
    return response;
  }

  public LeaveUnattended updateCanLeaveUnattended(boolean canLeaveUnattended) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String endPoint = URLResources.V3_CAN_LEAVE_UNATTENDED;
    LeaveUnattended leaveUnattended = new LeaveUnattended();
    leaveUnattended.setCanLeaveUnattended(canLeaveUnattended);
    String canLeaveUnattendedStr = mapper.writeValueAsString(leaveUnattended);
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, canLeaveUnattendedStr, headerListTrader);
    String responseStr = mapWebserviceResponse.get("response");
    return mapper.readValue(responseStr, LeaveUnattended.class);
  }

  public PackagingPreferences getDeliveryPackagingPreferenceFor() throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr;
    Map<String, String> queryParams = new HashMap<>();
    String endPoint = URLResources.V2_PACKAGING_PREFERENCE;
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);
    responseStr = mapWebserviceResponse.get("response");
    return mapper.readValue(responseStr, PackagingPreferences.class);
  }

  public PackagingPreferences updateDeliveryPackagingPreference(int packagingPreferenceId) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr;
    String endPoint = URLResources.V2_PACKAGING_PREFERENCE + "/" + packagingPreferenceId;
    StockCode stockCode = new StockCode();
    stockCode.setStockcode("4");
    String stockCodeStr = mapper.writeValueAsString(stockCode);
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, stockCodeStr, headerListTrader);
    responseStr = mapWebserviceResponse.get("response");
    return mapper.readValue(responseStr, PackagingPreferences.class);
  }

  public CheckoutV2DeliveryWindowsResponse getCheckoutDeliveryWindows(Integer deliveryAddressId) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr;
    Map<String, String> queryParams = new HashMap<>();
    queryParams.put("addressid", deliveryAddressId.toString());
    CheckoutV2DeliveryWindowsResponse response;
    String endPoint = URLResources.V2_CHECKOUTDELIVERYWINDOWS;
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, CheckoutV2DeliveryWindowsResponse.class);
    return response;
  }

  public CheckoutV3DeliveryWindows[] getCheckoutDeliveryNowWindows() throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr;
    CheckoutV3DeliveryWindows[] response;
    String endPoint = URLResources.V3_CHECKOUTDELIVERYWINDOWS;
    mapWebserviceResponse = invocationUtil.invokeGetWithoutParam(endPoint, headerListTrader);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, CheckoutV3DeliveryWindows[].class);
    return response;
  }

  public CheckoutV2DeliveryWindowsResponse getCheckoutPickupWindows(Integer storeId) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr;
    Map<String, String> queryParams = new HashMap<>();
    queryParams.put("storeid", storeId.toString());
    CheckoutV2DeliveryWindowsResponse response;
    String endPoint = URLResources.V2_CHECKOUTPICKUPWINDOWS;
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, CheckoutV2DeliveryWindowsResponse.class);
    return response;
  }

  public CheckoutSetWindowsResponseV2 setCheckoutDeliveryWindow(String windowDate, String windowID) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String requestStr;
    String responseStr;
    CheckoutSetWindowsRequestV2 checkoutSetWindowsRequestV2 = new CheckoutSetWindowsRequestV2();
    checkoutSetWindowsRequestV2.setWindowDate(windowDate);
    checkoutSetWindowsRequestV2.setWindowId(windowID);
    CheckoutSetWindowsResponseV2 responseV2;
    String endPoint = URLResources.V2_CHECKOUTWINDOWS;
    requestStr = mapper.writeValueAsString(checkoutSetWindowsRequestV2);
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListTrader);
    responseStr = mapWebserviceResponse.get("response");
    responseV2 = mapper.readValue(responseStr, CheckoutSetWindowsResponseV2.class);
    return responseV2;
  }

  public CheckoutSetWindowsResponseV3 setCheckoutDeliveryNowWindow(String windowDate, String windowID) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String requestStr;
    String responseStr;
    CheckoutSetWindowsRequestV2 checkoutSetWindowsRequestV2 = new CheckoutSetWindowsRequestV2();
    checkoutSetWindowsRequestV2.setWindowDate(windowDate);
    checkoutSetWindowsRequestV2.setWindowId(windowID);
    CheckoutSetWindowsResponseV3 responseV3;
    String endPoint = URLResources.V3_CHECKOUTWINDOWS;
    requestStr = mapper.writeValueAsString(checkoutSetWindowsRequestV2);
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListTrader);
    responseStr = mapWebserviceResponse.get("response");
    responseV3 = mapper.readValue(responseStr, CheckoutSetWindowsResponseV3.class);
    return responseV3;
  }

}
