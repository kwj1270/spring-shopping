package shopping.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class MainCategoryLockAspect {
    private final MainCategoryLockHandler mainCategoryLockHandler;
    private final CustomSpringELParser customSpringELParser;

    public MainCategoryLockAspect(final MainCategoryLockHandler mainCategoryLockHandler, final CustomSpringELParser customSpringELParser) {
        this.mainCategoryLockHandler = mainCategoryLockHandler;
        this.customSpringELParser = customSpringELParser;
    }

    @Around("@annotation(shopping.common.aspect.MainCategoryLock)")
    public Object mainCategoryLockPointcut(final ProceedingJoinPoint joinPoint) throws Throwable {
        final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        final Method method = signature.getMethod();
        final MainCategoryLock mainCategoryLock = method.getAnnotation(MainCategoryLock.class);
        final String[] parameterNames = signature.getParameterNames();
        final String key = customSpringELParser.getDynamicValue(parameterNames, joinPoint.getArgs(), mainCategoryLock.value()).toString();
        try {
            mainCategoryLockHandler.lock(key);
            return joinPoint.proceed();
        } finally {
            mainCategoryLockHandler.unlock(key);
        }
    }

}
