package co.uk.sainsburys.domain.exception;

public class InvalidMoneyOperationException extends IllegalStateException {

  public InvalidMoneyOperationException(String message) {
    super(message);
  }

}
