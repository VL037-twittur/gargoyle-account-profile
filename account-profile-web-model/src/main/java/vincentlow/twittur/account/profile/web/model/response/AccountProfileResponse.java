package vincentlow.twittur.account.profile.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vincentlow.twittur.base.web.model.response.BaseResponse;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountProfileResponse extends BaseResponse {

  private static final long serialVersionUID = 1538207314455383912L;

  private String username;

  private String accountName;

  private String bio;

  private int tweetsCount;

  private int followersCount;

  private int followingCount;
}
