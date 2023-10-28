package ItAcademy521.Sprint521.Controllers;

import ItAcademy521.Sprint521.Model.Exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandlerImp implements IGlobalControllerExceptionHandler {


    @ExceptionHandler
    @ResponseStatus
    @Override
    public ResponseEntity<?> handleGameListIsEmptyException(GameListIsEmptyException gliee) {
        return new ResponseEntity<>("Error P-401 : Game list is Empty", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    @ResponseStatus
    @Override
    public ResponseEntity<?> handlePlayerListIsEmptyException(PlayerListIsEmptyException pliee) {
        return new ResponseEntity<>("Error P-402 : Player list is Empty", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    @ResponseStatus
    @Override
    public ResponseEntity<?> handlePlayerNameMustBeUniqueException(PlayerNameMustBeUniqueException pnmbue) {
        return new ResponseEntity<>("Error P-403 : Player name must be Unique!", HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler
    @ResponseStatus
    @Override
    public ResponseEntity<?> handlePlayerNotFoundException(PlayerNotFoundException pnf) {
        return new ResponseEntity<>("Error P-404 : Player not Found", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    @ResponseStatus
    @Override
    public ResponseEntity<?> handleIncorrectFormatException(IncorrectFormatException ife) {
        return new ResponseEntity<>("Error P-405 : It must need a Long parameter", HttpStatus.I_AM_A_TEAPOT);
    }
    @ExceptionHandler
    @ResponseStatus
    @Override
    public ResponseEntity<?> handleSomeErrorOcurredWhileCreating(SomeErrorOcurredWhileCreatingException seowc) {
        return new ResponseEntity<>("Error P-406 : An error ocurred while Creating Player", HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler
    @ResponseStatus
    @Override
    public ResponseEntity<?> handleSomeErrorOcurredWhileUpdating(SomeErrorOcurredWhileUpdatingException seowu) {
        return new ResponseEntity<>("Error P-407 : An error ocurred while Updating Player", HttpStatus.NOT_ACCEPTABLE);
    }
}
