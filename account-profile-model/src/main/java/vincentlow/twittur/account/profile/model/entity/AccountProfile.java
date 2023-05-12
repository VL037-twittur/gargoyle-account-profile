package vincentlow.twittur.account.profile.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "account_profile")
@Data
public class AccountProfile extends BaseEntity {

  private static final long serialVersionUID = -7495666364920304111L;

  @Column(name = "account_credential_id")
  private String accountCredentialId;

  @Column(name = "username", unique = true)
  private String username;

  @Column(name = "account_name")
  private String accountName;

  @Column(name = "bio")
  private String bio;

  @Column(name = "tweets_count")
  private int tweetsCount;

  @Column(name = "followers_count")
  private int followersCount;

  @Column(name = "following_count")
  private int followingCount;
}
