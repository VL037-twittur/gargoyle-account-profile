package vincentlow.twittur.account.profile.web.controller;

import static vincentlow.twittur.account.profile.util.ObjectMappingHelper.toResponse;
import static vincentlow.twittur.account.profile.util.ValidatorUtil.validatePageableRequest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import vincentlow.twittur.account.profile.model.constant.ApiPath;
import vincentlow.twittur.account.profile.model.entity.AccountProfile;
import vincentlow.twittur.account.profile.service.AccountProfileService;
import vincentlow.twittur.account.profile.web.model.request.AccountRelationshipRequest;
import vincentlow.twittur.account.profile.web.model.request.CreateAccountProfileRequest;
import vincentlow.twittur.account.profile.web.model.response.AccountFollowerResponse;
import vincentlow.twittur.account.profile.web.model.response.AccountProfileResponse;
import vincentlow.twittur.base.web.controller.BaseController;
import vincentlow.twittur.base.web.model.response.PageMetaData;
import vincentlow.twittur.base.web.model.response.api.ApiListResponse;
import vincentlow.twittur.base.web.model.response.api.ApiResponse;
import vincentlow.twittur.base.web.model.response.api.ApiSingleResponse;

@Slf4j
@RestController
@RequestMapping(value = ApiPath.ACCOUNT, produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountProfileController extends BaseController {

  @Autowired
  private AccountProfileService accountProfileService;

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

  @GetMapping("/{id}")
  public ResponseEntity<ApiSingleResponse<AccountProfileResponse>> getAccountById(@PathVariable String id) {

    try {
      AccountProfile account = accountProfileService.findAccountById(id);
      AccountProfileResponse response = toResponse(account, AccountProfileResponse.class);

      return toSuccessResponseEntity(toApiSingleResponse(response));
    } catch (RuntimeException e) {
      log.error("#AccountProfileController#getAccountById ERROR! with id: {}, and error: {}", id, e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @PostMapping("/@{username}/add-tweet")
  public ResponseEntity<ApiResponse> addTweetCount(@PathVariable String username) {

    try {
      accountProfileService.addTweetCount(username);
      return toSuccessResponseEntity(successResponse);
    } catch (RuntimeException e) {
      log.error("#AccountProfileController#addTweetCount ERROR! with username: {}, and error: {}", username,
          e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @PostMapping("/@{username}/subtract-tweet")
  public ResponseEntity<ApiResponse> subtractTweetCount(@PathVariable String username) {

    try {
      accountProfileService.subtractTweetCount(username);
      return toSuccessResponseEntity(successResponse);
    } catch (RuntimeException e) {
      log.error("#AccountProfileController#subtractTweetCount ERROR! with username: {}, and error: {}", username,
          e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @PostMapping("/_follow")
  public ResponseEntity<ApiResponse> followAccount(@RequestBody AccountRelationshipRequest request) {

    try {
      accountProfileService.follow(request);
      return toSuccessResponseEntity(successResponse);
    } catch (RuntimeException e) {
      log.error("#AccountProfileController#followAccount ERROR! with request: {}, and error: {}", request,
          e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @PostMapping("/_unfollow")
  public ResponseEntity<ApiResponse> unfollowAccount(@RequestBody AccountRelationshipRequest request) {

    try {
      accountProfileService.unfollow(request);
      return toSuccessResponseEntity(successResponse);
    } catch (RuntimeException e) {
      log.error("#AccountProfileController#unfollowAccount ERROR! with request: {}, and error: {}", request,
          e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @GetMapping("/@{username}/followers")
  public ResponseEntity<ApiListResponse<AccountFollowerResponse>> getAccountFollowers(@PathVariable String username,
      @RequestParam(defaultValue = "0") int pageNumber,
      @RequestParam(defaultValue = "10") int pageSize) {

    try {
      validatePageableRequest(pageNumber, pageSize);

      Page<AccountProfile> followers = accountProfileService.getAccountFollowers(username, pageNumber, pageSize);
      List<AccountFollowerResponse> response = followers.stream()
          .map(account -> toResponse(account, AccountFollowerResponse.class))
          .collect(Collectors.toList());
      PageMetaData pageMetaData = getPageMetaData(followers, pageNumber, pageSize);

      return toSuccessResponseEntity(toApiListResponse(response, pageMetaData));
    } catch (RuntimeException e) {
      log.error("#getAccountFollowers ERROR! with username: {}, pageNumber: {}, pageSize: {}, and error: {}", username,
          pageNumber, pageSize, e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @GetMapping("/@{username}/following")
  public ResponseEntity<ApiListResponse<AccountFollowerResponse>> getAccountFollowing(@PathVariable String username,
      @RequestParam(defaultValue = "0") int pageNumber,
      @RequestParam(defaultValue = "10") int pageSize) {

    try {
      validatePageableRequest(pageNumber, pageSize);

      Page<AccountProfile> followers = accountProfileService.getAccountFollowing(username, pageNumber, pageSize);
      List<AccountFollowerResponse> response = followers.stream()
          .map(account -> toResponse(account, AccountFollowerResponse.class))
          .collect(Collectors.toList());
      PageMetaData pageMetaData = getPageMetaData(followers, pageNumber, pageSize);

      return toSuccessResponseEntity(toApiListResponse(response, pageMetaData));
    } catch (RuntimeException e) {
      log.error("#getAccountFollowing ERROR! with username: {}, pageNumber: {}, pageSize: {}, and error: {}", username,
          pageNumber, pageSize, e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @GetMapping("/{profileId}/all-followers")
  public ResponseEntity<ApiListResponse<AccountFollowerResponse>> getAllAccountFollowers(
      @PathVariable String profileId) {

    try {
      List<AccountProfile> followers = accountProfileService.getAllAccountFollowers(profileId);
      List<AccountFollowerResponse> response = followers.stream()
          .map(account -> toResponse(account, AccountFollowerResponse.class))
          .collect(Collectors.toList());

      return toSuccessResponseEntity(toApiListResponse(response, null));
    } catch (RuntimeException e) {
      log.error("#AccountProfileController#getAccountFollowers ERROR! with profileId: {}, and error: {}", profileId,
          e.getMessage(), e);
      throw new RuntimeException(e.getMessage(), e);
    }
  }

}
