package com.mkobo.hospitality.apiserverice.config;

import com.mkobo.hospitality.apiserverice.service.StaffService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class UUIDAuthenticationFilter extends GenericFilterBean {
    private final StaffService staffService;
    private final PathMatcher pathMatcher = new AntPathMatcher();

    public UUIDAuthenticationFilter(StaffService staffService) {
        this.staffService = staffService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();
        List<String> skipPaths = Arrays.asList("/api/staff/add", "/h2-console", "/h2-console/**");

        if (skipPaths.stream().anyMatch(path -> pathMatcher.match(path, requestURI))) {
            chain.doFilter(request, response);
            return;
        }

        String uuid = httpServletRequest.getHeader("UUID");
        log.info("Received UUID in header: {}", uuid);
        if (uuid == null || !staffService.isValidUUID(uuid)) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid UUID");
            return;
        }

        Authentication auth = new UsernamePasswordAuthenticationToken(uuid, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(auth);
        log.info("Security context set with auth: {}", SecurityContextHolder.getContext().getAuthentication());

        chain.doFilter(request, response);
    }
}
