package valentinood.javaweb.listener;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import valentinood.javaweb.service.LogService;

@Component
@AllArgsConstructor
public class LoginEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final LogService logService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();

        String ipAddress = request.getRemoteAddr();
        UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();

        logService.log(request, "User " + userDetails.getUsername() + " successfully logged in from IP " + ipAddress);
    }
}
