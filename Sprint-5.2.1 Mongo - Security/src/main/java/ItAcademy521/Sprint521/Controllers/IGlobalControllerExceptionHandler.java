package ItAcademy521.Sprint521.Controllers;

import ItAcademy521.Sprint521.Model.Exceptions.*;
import org.springframework.http.ResponseEntity;

public interface IGlobalControllerExceptionHandler {

    public ResponseEntity<?> handleGameListIsEmptyException(GameListIsEmptyException gliee);
    public ResponseEntity<?> handlePlayerListIsEmptyException(PlayerListIsEmptyException pliee);
    public ResponseEntity<?> handlePlayerNameMustBeUniqueException(PlayerNameMustBeUniqueException pnmbue);
    public ResponseEntity<?> handlePlayerNotFoundException(PlayerNotFoundException pnf);
    public ResponseEntity<?> handleIncorrectFormatException(IncorrectFormatException ife);
    public ResponseEntity<?> handleSomeErrorOcurredWhileCreating(SomeErrorOcurredWhileCreatingException seowc);
    public ResponseEntity<?> handleSomeErrorOcurredWhileUpdating(SomeErrorOcurredWhileUpdatingException seowu);

}
