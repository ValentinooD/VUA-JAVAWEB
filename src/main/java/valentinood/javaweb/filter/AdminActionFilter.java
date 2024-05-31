package valentinood.javaweb.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import valentinood.javaweb.service.LogService;

import java.io.IOException;

@Component
@AllArgsConstructor
public class AdminActionFilter implements Filter {
    private LogService logService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!(servletRequest instanceof HttpServletRequest request)
            || !(servletResponse instanceof HttpServletResponse response)) {
            return;
        }

        if (request.getMethod().equals("POST") && request.getServletPath().startsWith("/admin")) {
            logService.log(request, "Action " + request.getServletPath() + " returned " + response.getStatus());
        }

        if (request.getMethod().equals("GET") && request.getServletPath().startsWith("/payment") && response.getStatus() != 200) {
            logService.log(request, "Payment processing on " + request.getServletPath() + " returned " + response.getStatus());
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
