package au.com.woolworths.model.iris.graphql.productList;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@lombok.Data
@EqualsAndHashCode
public class SortOption {
  @JsonProperty("__typename")
  private String typename;
  private String key;
  private String title;
  private String subtitle;
  private Boolean isApplied;
}
