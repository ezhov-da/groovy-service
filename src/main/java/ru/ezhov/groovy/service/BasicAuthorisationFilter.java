package ru.ezhov.groovy.service;

import sun.misc.BASE64Decoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class BasicAuthorisationFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(BasicAuthorisationFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String authHeader = ((HttpServletRequest) servletRequest).getHeader("Authorization");
        LOG.info("authHeader: " + authHeader);
        if (authHeader == null) {
            errorAuth(((HttpServletResponse) servletResponse));
        } else {
            String afterClear = authHeader.replaceAll("Basic ", "");
            LOG.info("afterClear: " + afterClear);
            String lpass = new String(new BASE64Decoder().decodeBuffer(afterClear), "UTF-8");
            LOG.info("lpass: " + lpass);
            String[] arr = lpass.split(":");
            String login = arr[0];
            String pass = arr[1];
            LOG.info("login: [" + login + "] pass:[" + pass + "]");
            if (!"1".equals(login) && !"1".equals(pass)) {
                errorAuth(((HttpServletResponse) servletResponse));
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }

    private void errorAuth(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setHeader("WWW-Authenticate", "Basic");
        servletResponse.setStatus(401);
        servletResponse.getOutputStream().print("Sorry, but bad, very bad authorization");
    }
}
