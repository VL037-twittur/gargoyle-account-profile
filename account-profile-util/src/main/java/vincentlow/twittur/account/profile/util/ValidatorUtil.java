package vincentlow.twittur.account.profile.util;

import vincentlow.twittur.account.profile.model.constant.ExceptionMessage;
import vincentlow.twittur.account.profile.web.model.response.exception.BadRequestException;

public class ValidatorUtil {

  public static void validateArgument(boolean expression, String errorMessage) {

    if (!expression) {
      throw new BadRequestException(errorMessage);
    }
  }

  public static void validateState(boolean expression, String errorMessage) {

    if (!expression) {
      throw new BadRequestException(errorMessage);
    }
  }

  public static void validatePageableRequest(int pageNumber, int pageSize) {

    if (pageNumber < 0) {
      throw new BadRequestException(ExceptionMessage.PAGE_NUMBER_MUST_BE_AT_LEAST_0);
    } else if (pageSize < 1 || pageSize > 100) {
      throw new BadRequestException(ExceptionMessage.PAGE_SIZE_MUST_BE_BETWEEN_1_AND_100);
    }
  }
}
