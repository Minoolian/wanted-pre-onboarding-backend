package com.wanted.preonboarding.config;

import com.wanted.preonboarding.user.auth.PrincipalDetails;
import com.wanted.preonboarding.user.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Collections;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        User user = new User();
        user.setUserId(1L);
        PrincipalDetails principalDetails = new PrincipalDetails(user, Collections.singletonList(new SimpleGrantedAuthority("USER")));
        Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, "NO_PASS", principalDetails.getAuthorities());
        context.setAuthentication(authentication);

        return context;
    }
}