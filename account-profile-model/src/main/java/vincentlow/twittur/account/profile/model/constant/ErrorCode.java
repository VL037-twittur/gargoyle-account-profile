package vincentlow.twittur.account.profile.model.constant;

public enum ErrorCode {

  REQUEST_MUST_NOT_BE_NULL("request must not be null"),
  FOLLOWED_ID_MUST_NOT_BE_BLANK("followed id must not be blank"),
  FOLLOWER_ID_MUST_NOT_BE_BLANK("follower id must not be blank");
  
  private String message;

  ErrorCode(String message) {

    this.message = message;
  }

  public String getMessage() {

    return message;
  }
}
