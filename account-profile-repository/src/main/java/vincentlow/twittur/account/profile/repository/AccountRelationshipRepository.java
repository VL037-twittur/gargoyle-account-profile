package vincentlow.twittur.account.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vincentlow.twittur.account.profile.model.entity.AccountRelationship;

@Repository
public interface AccountRelationshipRepository extends JpaRepository<AccountRelationship, String> {

  AccountRelationship findByFollowerIdAndFollowedId(String followerId, String followedId);
}
