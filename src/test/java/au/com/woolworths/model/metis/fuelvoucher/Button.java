package au.com.woolworths.model.metis.fuelvoucher;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Button {
  private String title;
  private String url;
}
