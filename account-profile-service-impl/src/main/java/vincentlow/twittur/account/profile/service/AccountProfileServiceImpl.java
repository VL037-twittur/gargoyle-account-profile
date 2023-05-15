package vincentlow.twittur.account.profile.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import vincentlow.twittur.account.profile.model.entity.AccountProfile;
import vincentlow.twittur.account.profile.repository.AccountProfileRepository;
import vincentlow.twittur.account.profile.web.model.request.CreateAccountProfileRequest;

@Service
public class AccountProfileServiceImpl implements AccountProfileService {

  @Autowired
  private AccountProfileRepository accountProfileRepository;

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

    return accountProfileRepository.findByUsernameAndMarkForDeleteFalse(username);
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
}
