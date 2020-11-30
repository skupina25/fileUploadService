package fri.uni_lj.si.fileUploadService.filters;

import fri.uni_lj.si.fileUploadService.config.RestProperties;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MaintenanceFilter implements Filter {

    @Autowired
    private RestProperties restProperties;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (restProperties.getMaintenanceMode()) {
            ((HttpServletResponse) servletResponse).sendError(HttpStatus.SC_FORBIDDEN);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}