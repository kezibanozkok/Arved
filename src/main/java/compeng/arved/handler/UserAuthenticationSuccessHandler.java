package compeng.arved.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String email = authentication.getName();

        if (email.equals("comptelgraf@ankara.edu.tr")) {
            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/admin/onayBekleyenler");
        } else {
            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/home");
        }
    }
}
