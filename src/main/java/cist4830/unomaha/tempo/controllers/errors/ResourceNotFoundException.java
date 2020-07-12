@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Could not find item!")
public class ResourceNotFoundException extends RuntimeException {}