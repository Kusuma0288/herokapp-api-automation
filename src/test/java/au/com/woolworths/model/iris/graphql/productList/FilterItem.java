package au.com.woolworths.model.iris.graphql.productList;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class FilterItem {
  @JsonProperty("__typename")
  private String typename;
  private String key;
  private String title;
  private String label;
  private String parentKey;
  private Boolean hasChildren;
  private Boolean isApplied;
}
