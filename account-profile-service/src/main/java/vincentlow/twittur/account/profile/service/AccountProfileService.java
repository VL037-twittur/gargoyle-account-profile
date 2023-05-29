package vincentlow.twittur.account.profile.service;

import java.util.List;

import org.springframework.data.domain.Page;

import vincentlow.twittur.account.profile.model.entity.AccountProfile;
import vincentlow.twittur.account.profile.web.model.request.AccountRelationshipRequest;
import vincentlow.twittur.account.profile.web.model.request.CreateAccountProfileRequest;

public interface AccountProfileService {

  Page<AccountProfile> findAccounts(int pageNumber, int pageSize);

  AccountProfile createAccountProfile(CreateAccountProfileRequest request);

  AccountProfile findAccountByUsername(String username);

  AccountProfile findAccountById(String id);

  void addTweetCount(String username);

  void subtractTweetCount(String username);

  void follow(AccountRelationshipRequest request);

  void unfollow(AccountRelationshipRequest request);

  Page<AccountProfile> getAccountFollowers(String username, int pageNumber, int pageSize);

  Page<AccountProfile> getAccountFollowing(String username, int pageNumber, int pageSize);

  List<AccountProfile> getAllAccountFollowers(String profileId);
}
