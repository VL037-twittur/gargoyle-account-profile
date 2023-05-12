package vincentlow.twittur.account.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vincentlow.twittur.account.profile.model.entity.AccountProfile;

@Repository
public interface AccountProfileRepository extends JpaRepository<AccountProfile, String> {

  AccountProfile findByAccountCredentialIdAndMarkForDeleteFalse(String accountCredentialId);

  AccountProfile findByUsernameAndMarkForDeleteFalse(String username);
}
