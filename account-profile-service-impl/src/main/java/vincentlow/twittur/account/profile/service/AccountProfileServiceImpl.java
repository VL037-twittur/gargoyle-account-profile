package vincentlow.twittur.account.profile.service;

import static vincentlow.twittur.account.profile.util.ValidatorUtil.validateAccount;
import static vincentlow.twittur.account.profile.util.ValidatorUtil.validateArgument;
import static vincentlow.twittur.account.profile.util.ValidatorUtil.validateRelationship;
import static vincentlow.twittur.account.profile.util.ValidatorUtil.validateState;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.micrometer.common.util.StringUtils;
import vincentlow.twittur.account.profile.model.constant.ErrorCode;
import vincentlow.twittur.account.profile.model.constant.ExceptionMessage;
import vincentlow.twittur.account.profile.model.entity.AccountProfile;
import vincentlow.twittur.account.profile.model.entity.AccountRelationship;
import vincentlow.twittur.account.profile.repository.AccountProfileRepository;
import vincentlow.twittur.account.profile.repository.AccountRelationshipRepository;
import vincentlow.twittur.account.profile.web.model.request.AccountRelationshipRequest;
import vincentlow.twittur.account.profile.web.model.request.CreateAccountProfileRequest;

@Service
public class AccountProfileServiceImpl implements AccountProfileService {

  @Autowired
  private AccountProfileRepository accountProfileRepository;

  @Autowired
  private AccountRelationshipRepository accountRelationshipRepository;

  @Override
  public Page<AccountProfile> findAccounts(int pageNumber, int pageSize) {

    return accountProfileRepository.findAll(PageRequest.of(pageNumber, pageSize));
  }

  @Override
  public AccountProfile createAccountProfile(CreateAccountProfileRequest request) {

    AccountProfile accountProfile = new AccountProfile();
    BeanUtils.copyProperties(request, accountProfile);
    accountProfile.setCreatedBy(request.getAccountCredentialId());
    accountProfile.setUpdatedBy(request.getAccountCredentialId());

    return accountProfileRepository.save(accountProfile);
  }

  @Override
  public AccountProfile findAccountByUsername(String username) {

    AccountProfile account = accountProfileRepository.findByUsernameAndMarkForDeleteFalse(username);
    return validateAccount(account, ExceptionMessage.ACCOUNT_NOT_FOUND);
  }

  @Override
  public AccountProfile findAccountById(String id) {

    AccountProfile account = accountProfileRepository.findByIdAndMarkForDeleteFalse(id);
    return validateAccount(account, ExceptionMessage.ACCOUNT_NOT_FOUND);
  }

  @Override
  public void addTweetCount(String username) {

    AccountProfile account = accountProfileRepository.findByUsernameAndMarkForDeleteFalse(username);
    account.setTweetsCount(account.getTweetsCount() + 1);
    accountProfileRepository.save(account);
  }

  @Override
  public void subtractTweetCount(String username) {

    AccountProfile account = accountProfileRepository.findByUsernameAndMarkForDeleteFalse(username);
    account.setTweetsCount(account.getTweetsCount() - 1);
    accountProfileRepository.save(account);
  }

  @Override
  public void follow(AccountRelationshipRequest request) {

    validateState(Objects.nonNull(request), ErrorCode.REQUEST_MUST_NOT_BE_NULL.getMessage());
    validateArgument(StringUtils.isNotBlank(request.getFollowedId()),
        ErrorCode.FOLLOWED_ID_MUST_NOT_BE_BLANK.getMessage());
    validateArgument(StringUtils.isNotBlank(request.getFollowerId()),
        ErrorCode.FOLLOWER_ID_MUST_NOT_BE_BLANK.getMessage());

    AccountProfile followerAccount = accountProfileRepository.findByIdAndMarkForDeleteFalse(request.getFollowerId());
    validateAccount(followerAccount, ExceptionMessage.FOLLOWER_ACCOUNT_NOT_FOUND);

    AccountProfile followedAccount = accountProfileRepository.findByIdAndMarkForDeleteFalse(request.getFollowedId());
    validateAccount(followedAccount, ExceptionMessage.FOLLOWED_ACCOUNT_NOT_FOUND);

    AccountRelationship relationship =
        accountRelationshipRepository.findByFollowerIdAndFollowedId(request.getFollowerId(), request.getFollowedId());
    validateRelationship(relationship, ExceptionMessage.ALREADY_FOLLOWED_THE_ACCOUNT);

    relationship = new AccountRelationship();
    relationship.setFollower(followerAccount);
    relationship.setFollowed(followedAccount);

    LocalDateTime now = LocalDateTime.now();
    relationship.setCreatedBy(followerAccount.getId());
    relationship.setCreatedDate(now);
    relationship.setUpdatedBy(followerAccount.getId());
    relationship.setUpdatedDate(now);

    followerAccount.setFollowingCount(followerAccount.getFollowingCount() + 1);
    followedAccount.setFollowersCount(followedAccount.getFollowersCount() + 1);

    accountRelationshipRepository.save(relationship);
    accountProfileRepository.saveAll(List.of(followerAccount, followedAccount));
  }

  @Override
  public void unfollow(AccountRelationshipRequest request) {

    validateState(Objects.nonNull(request), ErrorCode.REQUEST_MUST_NOT_BE_NULL.getMessage());
    validateArgument(StringUtils.isNotBlank(request.getFollowedId()),
        ErrorCode.FOLLOWED_ID_MUST_NOT_BE_BLANK.getMessage());
    validateArgument(StringUtils.isNotBlank(request.getFollowerId()),
        ErrorCode.FOLLOWER_ID_MUST_NOT_BE_BLANK.getMessage());

    AccountProfile followerAccount = accountProfileRepository.findByIdAndMarkForDeleteFalse(request.getFollowerId());
    validateAccount(followerAccount, ExceptionMessage.FOLLOWER_ACCOUNT_NOT_FOUND);

    AccountProfile followedAccount = accountProfileRepository.findByIdAndMarkForDeleteFalse(request.getFollowedId());
    validateAccount(followedAccount, ExceptionMessage.FOLLOWED_ACCOUNT_NOT_FOUND);

    AccountRelationship relationship =
        accountRelationshipRepository.findByFollowerIdAndFollowedId(request.getFollowerId(), request.getFollowedId());

    if (Objects.nonNull(relationship)) {
      accountRelationshipRepository.deleteById(relationship.getId());

      followerAccount.setFollowingCount(followerAccount.getFollowingCount() - 1);
      followedAccount.setFollowersCount(followedAccount.getFollowersCount() - 1);

      accountProfileRepository.saveAll(List.of(followerAccount, followedAccount));
    }
  }

  @Override
  public Page<AccountProfile> getAccountFollowers(String username, int pageNumber, int pageSize) {

    AccountProfile account = accountProfileRepository.findByUsernameAndMarkForDeleteFalse(username);
    validateAccount(account, ExceptionMessage.ACCOUNT_NOT_FOUND);

    return accountProfileRepository.findFollowers(account.getId(), PageRequest.of(pageNumber, pageSize));
  }

  @Override
  public Page<AccountProfile> getAccountFollowing(String username, int pageNumber, int pageSize) {

    AccountProfile account = accountProfileRepository.findByUsernameAndMarkForDeleteFalse(username);
    validateAccount(account, ExceptionMessage.ACCOUNT_NOT_FOUND);

    return accountProfileRepository.findFollowing(account.getId(), PageRequest.of(pageNumber, pageSize));
  }

  @Override
  public List<AccountProfile> getAllAccountFollowers(String profileId) {

    AccountProfile account = accountProfileRepository.findByIdAndMarkForDeleteFalse(profileId);
    validateAccount(account, ExceptionMessage.ACCOUNT_NOT_FOUND);

    return accountProfileRepository.findAllFollowers(profileId);
  }
}
