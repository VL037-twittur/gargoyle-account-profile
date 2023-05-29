package vincentlow.twittur.account.profile.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "account_relationship")
@Data
public class AccountRelationship extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "follower_id", referencedColumnName = "id")
  @JsonIgnore
  private AccountProfile follower;

  @ManyToOne
  @JoinColumn(name = "followed_id", referencedColumnName = "id")
  @JsonIgnore
  private AccountProfile followed;
}
