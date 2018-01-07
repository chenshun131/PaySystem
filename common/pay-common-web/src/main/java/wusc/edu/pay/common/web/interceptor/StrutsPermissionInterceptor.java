package wusc.edu.pay.common.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wusc.edu.pay.common.web.annotation.Permission;
import wusc.edu.pay.common.web.constant.PermissionConstant;

import java.util.List;

/**
 * 自定义的细粒度权限拦截.
 *
 * @author WuShuicheng(吴水成).
 * @version 1.0, 2013-4-2,上午3:58:26.
 */
public class StrutsPermissionInterceptor implements MethodInterceptor {

    private static final Logger log = LoggerFactory.getLogger(StrutsPermissionInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 判断该方法是否加了@Permission注解
        if (invocation.getMethod().isAnnotationPresent(Permission.class)) {
            log.info("=== invoke PermissionInterceptor");
            // 得到方法上的Permission注解
            final Permission pm = invocation.getMethod().getAnnotation(Permission.class);

            // 获取被注解方法中的request参数，要求方法中一定要有HttpServletRequest参数
            @SuppressWarnings("unchecked") final List<String> permissions =
                    (List<String>) ActionContext.getContext().getSession().get(PermissionConstant.ACTIONS_SESSION_KEY);

            // 拥有此功能点权限
            if (permissions.contains(pm.value())) {
                // 执行被拦截的方法，如果此方法不调用，则被拦截的方法不会被执行
                log.info("== contain permission:" + pm.value());
                return invocation.proceed();
            }
            // 没有此功能点权限
            log.warn("=== 您没有执行此操作的权限:" + pm.value());
            // 跳转到错误提示页面
            return "permissionError";
        }
        // 没加@Permission注解的方法可直接执行
        // 执行被拦截的方法，如果此方法不调用，则被拦截的方法不会被执行
        return invocation.proceed();
    }

}
