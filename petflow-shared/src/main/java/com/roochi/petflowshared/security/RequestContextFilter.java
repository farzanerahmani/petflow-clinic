package com.roochi.petflowshared.security;

import com.roochi.petflowshared.contants.HeaderConstants;
import com.roochi.petflowshared.context.RequestContext;
import com.roochi.petflowshared.context.RequestContextHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


/**
 * @author farzane.rahmani
 * @created 7/5/2026
 */
@Component
public class RequestContextFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        RequestContext context = RequestContext.builder()
                .branchId(parseLong(request.getHeader(HeaderConstants.BRANCH_ID)))
                .language(request.getHeader(HeaderConstants.LANGUAGE))
                .timeZone(request.getHeader(HeaderConstants.TIMEZONE))
                .appVersion(request.getHeader(HeaderConstants.APP_VERSION)).build();

        try {
            RequestContextHolder.set(context);
            filterChain.doFilter(request, response);
        } finally {
            RequestContextHolder.clear();
        }
    }

    private Long parseLong(String value) {
        if (value == null || value.isBlank())
            return null;
        return Long.valueOf(value);
    }
}
