package com.rah.predix.filter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ge.predix.uaa.token.lib.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter
    implements Filter {
    private String tokenScope = "application-read";  //$NON-NLS-1$

    public String getTokenScope() {
        return this.tokenScope;
    }

    public void setTokenScope(String tokenScope) {
        this.tokenScope = tokenScope;
    }

    private static final String SECURITY_TOKEN_HEADER = "Authorization"; //$NON-NLS-1$
    private static final String authorizationSchema   = "Bearer";              //$NON-NLS-1$

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String stringToken = request.getHeader(SECURITY_TOKEN_HEADER);

        if ( StringUtils.isEmpty(stringToken) )
        {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new InsufficientAuthenticationException("Authorization header not found"); //$NON-NLS-1$
        }

        if ( !StringUtils.startsWithIgnoreCase(stringToken, authorizationSchema) )
        {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new InsufficientAuthenticationException("Authorization schema " + authorizationSchema + " not found"); //$NON-NLS-1$ //$NON-NLS-2$
        }

        stringToken = stringToken.trim().substring(authorizationSchema.length());

        Jwt accessToken = JwtHelper.decode(stringToken);
        Map<String, Object> claims = JsonUtils.readValue(accessToken.getClaims(),
            new TypeReference<Map<String, Object>>()
            {//
            });
        if ( claims == null )
        {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new InsufficientAuthenticationException(
                "Authorization header does not have the required scope to access the resource claims are not set"); //$NON-NLS-1$
        }
        Set<String> scopes = new HashSet<>();
        if ( claims.containsKey("scope") ) //$NON-NLS-1$
        {
            @SuppressWarnings("unchecked")
            Collection<String> values = (Collection<String>) claims.get("scope");//$NON-NLS-1$
            scopes.addAll(values);
        }
        // check for the right scope
        Boolean foundScope = Boolean.FALSE;
        for (String scope : scopes)
        {
            if ( StringUtils.equalsIgnoreCase(scope, this.tokenScope) ) {
                foundScope = Boolean.TRUE;
            }

        }

        request.setAttribute("userToken", accessToken); //$NON-NLS-1$
        request.setAttribute("isAdmin", foundScope); //$NON-NLS-1$
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig arg0)
        throws ServletException {
    }

}
