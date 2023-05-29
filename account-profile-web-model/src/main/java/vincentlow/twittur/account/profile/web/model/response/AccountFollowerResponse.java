package vincentlow.twittur.account.profile.web.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import vincentlow.twittur.base.web.model.response.BaseResponse;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountFollowerResponse extends BaseResponse {

  private static final long serialVersionUID = 1508097872279203813L;

  private String username;

  private String accountName;
}
