package com.julioluis.singlesecurityapp.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

public class CsrfCookiFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CsrfToken csrfToken=(CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (Objects.isNull(csrfToken.getHeaderName()))
            response.setHeader(csrfToken.getHeaderName(),csrfToken.getToken());

        filterChain.doFilter(request,response);
    }
}
