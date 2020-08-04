package cist4830.unomaha.tempo.controllers.utility;

import org.springframework.security.core.context.SecurityContextHolder;
import cist4830.unomaha.tempo.services.CustomUserPrincipal;
import cist4830.unomaha.tempo.model.User;

public class GetLoggedInUser {
	public static User getLoggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserPrincipal) {
            return ((CustomUserPrincipal) principal).getUser();
        }
        return null;
    }
}