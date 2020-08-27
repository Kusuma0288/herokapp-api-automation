package au.com.woolworths.helpers.metis;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.metis.LinkResponse;
import au.com.woolworths.model.metis.LoginRequest;
import au.com.woolworths.model.metis.LoginResponse;
import au.com.woolworths.stepdefinitions.apigee.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.utils.URLResources;

import java.util.Map;
import java.util.logging.Logger;

public class MetisRewardsHelper extends BaseHelper {
  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("MetisRewardsHelper.class");

  public MetisRewardsHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public LinkResponse getLinkSession() throws Throwable {
    String endPoint = URLResources.METIS_REWARDS_LINK;
    Map<String, String> mapWebserviceResponse;

    mapWebserviceResponse = invocationUtil.invokeGetWithoutParam(endPoint, headerList);
    String responseStr = mapWebserviceResponse.get("response");
    LinkResponse response = mapper.readValue(responseStr, LinkResponse.class);
    logger.info("Link API Response: " + response);

    return response;
  }

  public LoginResponse postLoginUsingAuthCode(String DeviceID, String sessionToken) throws Throwable {
    String endPoint = URLResources.METIS_LOGIN;
    Map<String, String> mapWebserviceResponse;

    LoginRequest loginRequest = new LoginRequest();
    loginRequest.setAuthCode(TestProperties.get("authcode"));
    loginRequest.setSessionToken(sessionToken);
    loginRequest.setDeviceId(DeviceID);

    String requestStr = mapper.writeValueAsString(loginRequest);
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
    String responseStr = mapWebserviceResponse.get("response");
    LoginResponse response = mapper.readValue(responseStr, LoginResponse.class);

    return response;
  }
}
