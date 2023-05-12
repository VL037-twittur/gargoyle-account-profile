package vincentlow.twittur.account.profile.web.controller;

import static vincentlow.twittur.account.profile.util.ObjectMappingHelper.toResponse;
import static vincentlow.twittur.account.profile.util.ValidatorUtil.validatePageableRequest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import vincentlow.twittur.account.profile.model.constant.ApiPath;
import vincentlow.twittur.account.profile.model.entity.AccountProfile;
import vincentlow.twittur.account.profile.service.AccountProfileService;
import vincentlow.twittur.account.profile.web.model.request.CreateAccountProfileRequest;
import vincentlow.twittur.account.profile.web.model.response.AccountProfileResponse;
import vincentlow.twittur.base.web.controller.BaseController;
import vincentlow.twittur.base.web.model.response.PageMetaData;
import vincentlow.twittur.base.web.model.response.api.ApiListResponse;
import vincentlow.twittur.base.web.model.response.api.ApiSingleResponse;

@Slf4j
@RestController
@RequestMapping(value = ApiPath.ACCOUNT, produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountProfileController extends BaseController {

  @Autowired
  private AccountProfileService accountProfileService;

  @GetMapping("/greet")
  ResponseEntity<ApiSingleResponse<String>> greet() {

    return toSuccessResponseEntity(toApiSingleResponse("Hello From GARGOYLE-ACCOUNT-PROFILE service"));
  }

  @GetMapping
  public ResponseEntity<ApiListResponse<AccountProfileResponse>> getAccounts(
      @RequestParam(defaultValue = "0") int pageNumber,
      @RequestParam(defaultValue = "10") int pageSize) {

    try {
      validatePageableRequest(pageNumber, pageSize);

      Page<AccountProfile> accounts = accountProfileService.findAccounts(pageNumber, pageSize);
      List<AccountProfileResponse> response = accounts.stream()
          .map(account -> toResponse(account, AccountProfileResponse.class))
          .collect(Collectors.toList());
      PageMetaData pageMetaData = getPageMetaData(accounts, pageNumber, pageSize);

      return toSuccessResponseEntity(toApiListResponse(response, pageMetaData));
    } catch (Exception e) {
      log.error("#AccountProfileController#getAccounts ERROR! with pageNumber: {}, pageSize: {}, and error: {}",
          pageNumber, pageSize,
          e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @PostMapping
  public ResponseEntity<ApiSingleResponse<AccountProfileResponse>> createAccountProfile(
      @RequestBody CreateAccountProfileRequest request) {

    try {
      AccountProfile account = accountProfileService.createAccountProfile(request);
      AccountProfileResponse response = toResponse(account, AccountProfileResponse.class);

      return toSuccessResponseEntity(toApiSingleResponse(response));
    } catch (RuntimeException e) {
      log.error("#AccountProfileController#createAccountProfile ERROR! with request: {}, and error: {}", request,
          e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @GetMapping("/@{username}")
  public ResponseEntity<ApiSingleResponse<AccountProfileResponse>> getAccountByUsername(@PathVariable String username) {

    try {
      AccountProfile account = accountProfileService.findAccountByUsername(username);
      AccountProfileResponse response = toResponse(account, AccountProfileResponse.class);

      return toSuccessResponseEntity(toApiSingleResponse(response));
    } catch (RuntimeException e) {
      log.error("#AccountProfileController#getAccountByUsername ERROR! with username: {}, and error: {}", username,
          e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

}
