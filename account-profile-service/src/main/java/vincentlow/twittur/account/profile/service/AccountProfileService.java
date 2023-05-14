package vincentlow.twittur.account.profile.service;

import org.springframework.data.domain.Page;

import vincentlow.twittur.account.profile.model.entity.AccountProfile;
import vincentlow.twittur.account.profile.web.model.request.CreateAccountProfileRequest;

public interface AccountProfileService {

  Page<AccountProfile> findAccounts(int pageNumber, int pageSize);

  AccountProfile createAccountProfile(CreateAccountProfileRequest request);

  AccountProfile findAccountByUsername(String username);

  void addTweetCount(String username);
}
