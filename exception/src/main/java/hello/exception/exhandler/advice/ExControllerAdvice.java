package hello.exception.exhandler.advice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "hello.exception.api")
public class ExControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class) //해당 오류 발생시 이 로직으로 처리됨.
    public ErrorResult illegalExHandler(IllegalArgumentException e){ //illegal의 자식까지 처리
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage()); //restcontroller이기에 그대로 json으로 반환됨
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler(UserException e){ //userexception의 자식까지 처리
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e){ //위의 예외 두개가 처리하지 못하는 예외까지 처리 (exception이 최상위 예외이기에)
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("EX", "내부 오류");
    }
}
