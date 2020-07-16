package cist4830.unomaha.tempo.controllers.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Could not find item!")
public class ResourceNotFoundException extends RuntimeException {
}