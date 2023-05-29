package vincentlow.twittur.account.profile.web.model.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRelationshipRequest implements Serializable {

  private static final long serialVersionUID = -236708323986094374L;

  private String followedId; // accountProfile ID

  private String followerId; // accountProfile ID
}
