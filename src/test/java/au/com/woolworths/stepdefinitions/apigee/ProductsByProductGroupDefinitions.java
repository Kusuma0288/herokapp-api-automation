package au.com.woolworths.stepdefinitions.apigee;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.apigee.authentication.GuestLoginRequest;
import au.com.woolworths.model.apigee.authentication.LoginReponse;
import au.com.woolworths.model.iris.graphql.productListByProductGroup.Product;
import au.com.woolworths.model.iris.graphql.productListByProductGroup.ProductsByProductGroup;
import au.com.woolworths.model.iris.graphql.productListByProductGroup.ProductsByProductGroupResponse;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.io.InputStream;

import static au.com.woolworths.graphql.parser.GraphqlParser.parseGraphql;
import static au.com.woolworths.stepdefinitions.common.ServiceHooks.restInvocationUtil;
import static au.com.woolworths.utils.URLResources.APIGEE_V2_GUEST_LOGIN;
import static au.com.woolworths.utils.URLResources.HERMES_V1_GRAPHQL;
import static au.com.woolworths.utils.Utilities.generateRandomUUIDString;
import static org.testng.Assert.assertEquals;

public class ProductsByProductGroupDefinitions extends BaseHelper {

  @Given("^I connect to apigee endpoint as a guest user$")
  public void mobileUserConnectToApigeeAPIEndpointAsGuest() throws Throwable {

    // REQUEST - set up unique deviceId
    GuestLoginRequest loginRequest = new GuestLoginRequest();
    loginRequest.setDevice_auth_token(generateRandomUUIDString());
    String requestStr = mapper.writeValueAsString(loginRequest);

    // CALL
    sharedData.recentCompleteResponse = restInvocationUtil.makeHttpRequest(APIGEE_V2_GUEST_LOGIN, requestStr, sharedData.accessToken);

    // RESPONSE - parse for sanity
    LoginReponse loginResponse = mapper.readValue(sharedData.recentCompleteResponse.get("response"), LoginReponse.class);

    // ASSERT
    assertEquals(sharedData.recentCompleteResponse.get("statusCode"), "200", "statusCode == 200 but was: " + sharedData.recentCompleteResponse.get("statusCode"));

    // Update token for subsequent calls
    sharedData.accessToken = loginResponse.getAccess_token();
  }

  @Given("I request product group {string}")
  public void userRequestsAProductGroup(String groupId) throws Throwable {

    // REQUEST - add 'groupId' inside 'variable' json object
    InputStream iStream = this.getClass().getResourceAsStream("/gqlQueries/iris/productsByProductGroup.graphql");
    ObjectNode variables = mapper.createObjectNode().put("groupId", groupId);
    String graphqlQuery = parseGraphql(iStream, variables);

    // CALL - any errors caught within
    sharedData.recentCompleteResponse = restInvocationUtil.makeHttpRequest(HERMES_V1_GRAPHQL, graphqlQuery, sharedData.accessToken);
  }

  @Then("^I can see the product group with products listed$")
  public void productGroupIsAvailable() throws Throwable {

    // Deserialize and parse product list
    ProductsByProductGroup products = mapper.readValue(sharedData.recentCompleteResponse.get("response"), ProductsByProductGroupResponse.class).getData().getProductsByProductGroup();

    // ASSERT - sort options - returns 53/54
    softAssert.assertTrue(products.getSortOptions().size() >= 53, "products.sortOptions.count >= " + 53 + " but was: " + products.getSortOptions().size());

    // ASSERT - product count - env(TEST) sometimes returns 0 products
    softAssert.assertNotEquals(products.getTotalNumberOfProducts(), 0, "totalNumberOfProducts != 0 but was: " + products.getTotalNumberOfProducts());
    softAssert.assertTrue(products.getProducts().size() > 0, "products.count > 0 but was: " + products.getProducts().size());

    // ASSERT - 1st product with "isAvailable":true - if existing
    Product firstAvailableProduct = products.getProducts().stream().filter(Product::isAvailable).findFirst().orElse(null);

    if (firstAvailableProduct != null) {
      softAssert.assertNotNull(firstAvailableProduct.getName(), "Available products[0].name != null but was: " + firstAvailableProduct.getName());
      softAssert.assertTrue(firstAvailableProduct.getProductImage().contains("cdn0.woolworths.media/content/wowproductimages/medium/"), "Available products[0].productImage contains 'https://uatcdn0.woolworths.media/content/wowproductimages/medium/' but was: " + firstAvailableProduct.getProductImage());
      softAssert.assertEquals(firstAvailableProduct.getTrolley().getButtonState(), "ADD", "Available products[0].trolley.buttonState == 'ADD' but was: " + firstAvailableProduct.getTrolley().getButtonState());
      softAssert.assertNotNull(firstAvailableProduct.getList(), "Available products[0].list != null but was: " + firstAvailableProduct.getList());
    }

    // ASSERT - 1st product with "isAvailable":false - if existing
    Product firstUnavailableProduct = products.getProducts().stream().filter((i) -> !i.isAvailable).findFirst().orElse(null);

    if (firstUnavailableProduct != null) {
      softAssert.assertNotNull(firstUnavailableProduct.getName(), "Unavailable products[0].name != null but was: " + firstUnavailableProduct.getName());
      softAssert.assertTrue(firstUnavailableProduct.getProductImage().contains("cdn0.woolworths.media/content/wowproductimages/medium/"), "Unavailable products[0].productImage contains 'https://uatcdn0.woolworths.media/content/wowproductimages/medium/' but was: " + firstUnavailableProduct.getProductImage());
      softAssert.assertTrue(firstUnavailableProduct.getInlineLabels().size() == 0, "Unavailable products[0].inlineLabels[].size == 0 but was: " + firstUnavailableProduct.getInlineLabels().size());
      softAssert.assertEquals(firstUnavailableProduct.getTrolley().getButtonState(), "DISABLED", "Unavailable products[0].trolley.buttonState == 'DISABLED' but was: " + firstUnavailableProduct.getTrolley().getButtonState());
      softAssert.assertNotNull(firstUnavailableProduct.getList(), "Unavailable products[0].list != null but was: " + firstUnavailableProduct.getList());
    }
  }
}
