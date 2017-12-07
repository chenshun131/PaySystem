package wusc.edu.pay.common.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.dispatcher.HttpParameters;
import org.apache.struts2.dispatcher.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @描述: 请求参数前合空格过滤器.
 * @作者: WuShuicheng.
 * @创建时间: 2014-1-1,下午9:01:12.
 * @版本号: V1.0 .
 */
public class TrimInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = -3738508612446369962L;

    private static final Logger log = LoggerFactory.getLogger(TrimInterceptor.class);

    /**
     * <p>
     * 方法描述: [trime掉空格]
     * </p>
     *
     * @param invocation
     *         参数说明
     * @return 返回结果的说明
     * @throws Exception
     *         抛出异常的原因
     */
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        log.info("过滤请求数据的首尾空格");
        HttpParameters parameters = invocation.getInvocationContext().getParameters();
        Set entrySet = parameters.entrySet();

        String[] strings = null;
        String[] values = null;
        int strLength = 0;
        for (Object anEntrySet : entrySet) {
            Entry entry = (Entry) anEntrySet;
            Object key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof String[]) {
                // 类型转换
                values = (String[]) value;
                strLength = values.length;
                strings = new String[strLength];
                for (int i = 0; i < strLength; i++) {
                    strings[i] = values[i].trim();
                }
//                parameters.put((String) key, strings); TODO
            }
        }
        invocation.getInvocationContext().setParameters(parameters);
        invocation.invoke();
        return null;
    }

}
