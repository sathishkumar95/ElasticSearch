package com.stackz.elastic.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component("secured")
public class AccessSecurityMethods {


    public boolean hasPermission(Authentication authentication, String permission) {
        ObjectMapper objectMapper = new ObjectMapper();
        OAuth2AuthenticationDetails authDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
        Jwt jwt = JwtHelper.decode(authDetails.getTokenValue());
        try{
            Map<String, Object> claims =  objectMapper.readValue(jwt.getClaims(), Map.class);
            List<String> permissionList = (List) claims.get("permissions");
            if(permissionList.contains(permission)) {
                return true;
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            log.error("Something went wrong while parsing jwt token");
        }

        return false;
    }


}
