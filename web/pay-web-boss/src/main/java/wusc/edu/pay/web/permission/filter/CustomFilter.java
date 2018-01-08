package wusc.edu.pay.web.permission.filter;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import wusc.edu.pay.common.web.constant.PermissionConstant;
import wusc.edu.pay.common.web.context.ThreadLocalContext;
import wusc.edu.pay.common.web.filter.XssHttpServletRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


/**
 * 自定义过滤器 <br/>
 * 1、对静态资源不处理 <br/>
 * 2、要求管理员一定要先登录才能进入后台（对request进行安全过滤），否则跳转到登录页面 <br/>
 * 3、判断系统是否已安装，未安装则跳转到安装页 <br/>
 * <p>
 * 对Request里的传值参数进行安全过滤，防御XSS攻击
 *
 * @author WuShuicheng
 * @version 1.0, 2013-3-31,下午3:15:06
 */
@Component
public class CustomFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CustomFilter.class);

    private static ThreadLocal<Map<String, Object>> outPutMsg = new ThreadLocal<>();

    /**
     * 线程绑定，其内容会在outPrint方法调用后清空
     *
     * @return the outputMsg
     */
    public Map<String, Object> getOutputMsg() {
        Map<String, Object> output = outPutMsg.get();
        if (output == null) {
            output = new HashMap<>(0);
            outPutMsg.set(output);
        }
        return output;
    }

    protected void setOutputMsg(String key, String value) {
        getOutputMsg().put(key, value);
    }

    /**
     * 输出，同时清空outPutMsg
     *
     * @param response
     * @param result
     */
    public void outPrint(HttpServletResponse response, Object result) {
        try {
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.print(result.toString());
            getOutputMsg().clear();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void init(FilterConfig arg) throws ServletException {
        logger.info("=== CustomFilter init");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        // 请求路径
        String uri = request.getServletPath();
        logger.info("=== uri=" + uri);

        try {
            // 静态资源不处理
            if (uri.startsWith("/statics")) {
                chain.doFilter(request, response);
                return;
            }

            // 判断会话是否过期
            Object operator = request.getSession().getAttribute(PermissionConstant.OPERATOR_SESSION_KEY);
            if (!uri.startsWith("/login") && !uri.startsWith("/posTicket") && uri.endsWith(".action") &&
                    operator == null) {
                // 如果操作员未登录或登录超时，重定向到后台登录界面
                logger.info("=======> 请求已拦截 url：" + uri);
                response.sendRedirect(request.getContextPath() + "/login_timeoutConfirm.action");
                return;
            }

            // 为自定义的上下文工具类设值
            ThreadLocalContext.setHttpRequest(request);
            ThreadLocalContext.setHttpResponse(response);
            // 对Request里的传值参数进行安全过滤
            HttpServletRequest xssRequest = new XssHttpServletRequestWrapper(request);
            // 其他情况
            chain.doFilter(xssRequest, response);
        } catch (RuntimeException e) {
            logger.error("== ", e);
            getOutputMsg().put("STATE", "FAIL");
            getOutputMsg().put("MSG", e.getMessage());
            outPrint(response, JSONObject.fromObject(getOutputMsg()));
        } finally {
            ThreadLocalContext.remove();
        }
    }

    @Override
    public void destroy() {
        logger.info("=== CustomFilter destroy");
    }

}
