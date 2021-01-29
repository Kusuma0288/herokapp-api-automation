package au.com.woolworths.helpers.scango;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.scango.scanitems.DeleteCartResponse;
import au.com.woolworths.model.scango.scanitems.RemoveItemResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class DeleteCartHelper extends BaseHelper {

    RestInvocationUtil invocationUtil;
    private final static Logger logger = Logger.getLogger("DeleteCartHelper.class");

    public DeleteCartHelper() {
        this.invocationUtil = ServiceHooks.restInvocationUtil;

    }

    public DeleteCartResponse iClickOnDeleteCartAPI() throws IOException {
        Map<String, String> mapWebserviceResponse;
        String responseStr = null;
        Map<String, ?> queryParams = new HashMap<>();

        DeleteCartResponse response;

        String endPoint = URLResources.SCANGO_LOAD_OR_DELETE_CART;
        mapWebserviceResponse = invocationUtil.invokeDelete(endPoint,queryParams,headerListScanGo);
        responseStr = mapWebserviceResponse.get("response");
        response = mapper.readValue(responseStr, DeleteCartResponse.class);
        response.setStatusCode(mapWebserviceResponse.get("statusCode"));
        return response;
    }

}


