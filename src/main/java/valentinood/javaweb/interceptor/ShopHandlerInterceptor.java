package valentinood.javaweb.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import valentinood.javaweb.domain.WebUser;
import valentinood.javaweb.service.CategoryService;
import valentinood.javaweb.service.WebUserService;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ShopHandlerInterceptor implements HandlerInterceptor {

    private CategoryService categoryService;
    private WebUserService webUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView == null)
            return;

        // always inject it into the model so that we don't have to spam it everywhere
        modelAndView.addObject("categories", categoryService.getCategories());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<WebUser> user = webUserService.getUserByUsername(authentication.getName());
        user.ifPresent(webUser -> modelAndView.addObject("username", webUser.getUsername()));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {}
}
