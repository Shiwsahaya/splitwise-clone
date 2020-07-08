package split.wise.web.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SplitWiseExceptionController {

    Logger logger = LoggerFactory.getLogger(SplitWiseExceptionController.class);

    @ExceptionHandler
    public ResponseEntity<SplitWiseErrorResponse>handleException(UserNotFoundException exc){
        SplitWiseErrorResponse splitWiseErrorResponse = new SplitWiseErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis()
        );
        logger.error(exc.getMessage());
        return new ResponseEntity<>(splitWiseErrorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<SplitWiseErrorResponse>handleException(Exception exc){
        SplitWiseErrorResponse splitWiseErrorResponse=new SplitWiseErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis()
        );
        logger.error(exc.getMessage());
        return new ResponseEntity<>(splitWiseErrorResponse,HttpStatus.BAD_REQUEST);
    }
}