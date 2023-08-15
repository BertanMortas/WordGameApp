package com.promet.repository.annotation;

import com.promet.utility.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;
@Component
public final class RoleChecker {

    public boolean checkRoleAuthorization(String requiredRole, List<String> roles) {
        return roles.contains(requiredRole);
    }
}
