package au.com.woolworths.model.iris.graphql.productList;

import lombok.EqualsAndHashCode;

@lombok.Data
@EqualsAndHashCode
public class InStoreDetails {
  private String locationText;
  private String locationType;
}