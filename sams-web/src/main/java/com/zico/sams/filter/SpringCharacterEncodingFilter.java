package com.zico.sams.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author zico
 * @version v1.0
 * @title SpringCharacterEncodingFilter
 * @package com.zico.sams.filter
 * @since 2018-12-17
 * description
 **/
@Slf4j
public class SpringCharacterEncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("servletFilter init...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        filter.doFilter(request, response, chain);
    }

    @Override
    public void destroy() {
        log.debug("servletFilter destory...");
    }
}
