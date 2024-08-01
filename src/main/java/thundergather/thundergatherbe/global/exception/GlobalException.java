package thundergather.thundergatherbe.global.exception;

import lombok.Getter;
import thundergather.thundergatherbe.global.exception.type.ErrorCode;

@Getter
public class GlobalException extends RuntimeException {

      private final ErrorCode errorCode;

      public GlobalException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
      }
}
