package vincentlow.twittur.account.profile.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vincentlow.twittur.account.profile.model.entity.AccountProfile;

@Repository
public interface AccountProfileRepository extends JpaRepository<AccountProfile, String> {

  AccountProfile findByUsernameAndMarkForDeleteFalse(String username);

  AccountProfile findByIdAndMarkForDeleteFalse(String accountProfileId);

  @Query("SELECT ap FROM AccountProfile ap JOIN AccountRelationship ar ON ap.id = ar.follower.id WHERE ar.followed.id = :profileId")
  Page<AccountProfile> findFollowers(String profileId, Pageable pageable);

  @Query("SELECT ap FROM AccountProfile ap JOIN AccountRelationship ar ON ap.id = ar.followed.id WHERE ar.follower.id = :profileId")
  Page<AccountProfile> findFollowing(String profileId, Pageable pageable);

  @Query("SELECT ap FROM AccountProfile ap JOIN AccountRelationship ar ON ap.id = ar.follower.id WHERE ar.followed.id = :id")
  List<AccountProfile> findAllFollowers(String id);
}
