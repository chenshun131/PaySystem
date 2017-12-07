package wusc.edu.pay.common.web.taglib;

import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wusc.edu.pay.common.web.constant.PermissionConstant;

import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.List;

/**
 * @描述: 自定义权限权标签 .
 * @作者: WuShuicheng .
 * @创建时间: 2013-7-17,下午4:04:24 .
 * @版本: 1.0 .
 */
public class PermissionTaglib extends BodyTagSupport {

    private static final long serialVersionUID = -3516539529725445580L;

    private static final Logger log = LoggerFactory.getLogger(PermissionTaglib.class);

    /** 权限值 */
    private String value;

    @Override
    public int doStartTag() {
        log.info("permission tag value=" + value);
        if (StringUtils.isNotBlank(value)) {
            @SuppressWarnings("unchecked")
            final List<String> permissions =
                    (List<String>) ActionContext.getContext().getSession().get(PermissionConstant.ACTIONS_SESSION_KEY);
            if (permissions.contains(value.trim())) { // 拥有此功能点权限
                return EVAL_BODY_INCLUDE;
            }
        }
        return SKIP_BODY;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
