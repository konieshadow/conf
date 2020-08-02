package io.github.konieshadow.conf.security.util;

import io.github.konieshadow.conf.security.model.ConfUserDetail;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static ConfUserDetail getCurrentUserDetail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        return (ConfUserDetail) authentication.getPrincipal();
    }

}
