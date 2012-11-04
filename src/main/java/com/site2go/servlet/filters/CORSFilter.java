package com.site2go.servlet.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;

            if(httpRequest.getMethod().equals("OPTIONS")) {
                String corsRequestMethod = httpRequest.getHeader("Access-Control-Request-Method");
                if(corsRequestMethod != null) {
                    HttpServletResponse httpResponse = (HttpServletResponse)servletResponse;
                    httpResponse.addHeader("Access-Control-Allow-Origin", "*");
                    httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
                    httpResponse.addHeader("Access-Control-Allow-Headers", httpRequest.getHeader("Access-Control-Request-Headers"));
                    httpResponse.addHeader("Access-Control-Max-Age", "3600");
                    httpResponse.setStatus(200);
                    return;
                }
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
