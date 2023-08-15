package com.promet.repository.annotation;

import com.promet.dto.request.CreateGameRequestDto;
import com.promet.exception.ErrorType;
import com.promet.exception.GameManagerException;
import com.promet.utility.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
public class RoleAnnotationProcessor<T> {

    private final RoleChecker roleChecker;
    private final JwtTokenProvider jwtTokenProvider;

    @Pointcut("@annotation(RoleAnnotation)")
    public void roleAnnotatedMethod() {}

    @Around("roleAnnotatedMethod()")
    public Object checkRoleAuthorization(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RoleAnnotation annotation = method.getAnnotation(RoleAnnotation.class);
        String requiredRole = annotation.newValue();

        Object[] args = joinPoint.getArgs();
        T dto = (T) args[0];
        String token = getTokenFromDto(dto);
        List<String> roles = jwtTokenProvider.getRolesFromToken(token);
        if (roleChecker.checkRoleAuthorization(requiredRole, roles)) {
            return joinPoint.proceed();
        } else {
            throw new GameManagerException(ErrorType.AUTHORIZATION_ERROR);
        }
    }
    private String getTokenFromDto(T dto) {
        try {
            Method getTokenMethod = dto.getClass().getMethod("getToken");
            return (String) getTokenMethod.invoke(dto);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}




