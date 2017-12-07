package wusc.edu.pay.common.web.taglib;

import wusc.edu.pay.common.page.PageBean;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * ClassName: PageTag <br/>
 * Function: 分页标签<br/>
 * date: 2013-11-13 上午12:05:36 <br/>
 *
 * @author laich
 */
public class PageTag extends TagSupport {

    private static final long serialVersionUID = 6273144997314042815L;

    private PageBean pageBean;

    /**  URL */
    private String url;

    /** 当前页数 */
    private Integer currentPage;

    /** 传入参数 */
    private String parameter;

    @Override
    public int doEndTag() throws JspException {
        try {
            this.pageContext.getOut().write(getTextString());
        } catch (Exception e) {
            throw new JspException();
        }
        return 6;
    }

    @Override
    public int doStartTag() {
        return 1;
    }

    private String getTextString() {
        if (pageBean == null) {
            return "";
        }
        Integer maxPage;
        if (currentPage == null) {
            pageBean.setCurrentPage(1);
        }

        // 获取最大页数
        if (pageBean.getTotalCount() == 0) {
            maxPage = 0;
        } else if (pageBean.getTotalCount() % pageBean.getNumPerPage() > 0) {
            maxPage = pageBean.getTotalCount() / pageBean.getNumPerPage() + 1;
        } else {
            maxPage = pageBean.getTotalCount() / pageBean.getNumPerPage();
        }

        // 打印分页
        StringBuffer str = new StringBuffer();
        str.append("当前是第").append(pageBean.getCurrentPage()).append("页，总共有").append(maxPage).append("页  ");
        if (pageBean.getCurrentPage() > 1) {
            str.append("<a href='").append(url).append("?pageNum=1").append(parameter).append("'>首页</a>  ");
            str.append("<a href='").append(url).append("?pageNum=").append(pageBean.getCurrentPage() - 1)
                    .append(parameter).append("'>上一页</a>  ");
        }

        // 显示-1234
        int k = pageBean.getCurrentPage() - 4 > 0 ? pageBean.getCurrentPage() - 4 : 1;
        for (int j = 1; j <= 9 && k <= pageBean.getPageCount(); k++, j++) {
            if (pageBean.getCurrentPage() == k) {
                // str.append
                // ("<a class=\"on\" href=\"javascript:void(0);\" onclick=\"fenye('" + k + "','" + pageInfo.getRowsOfPage() + "');\" >" + k + "</a>");
                str.append("<a class=\"selectPnum\" href='").append(url).append("?pageNum=").append(k)
                        .append(parameter).append("'>").append(k).append("</a>  ");
            } else {
                // str.append
                // ("<a href=\"javascript:void(0);\" onclick=\"fenye('" + k + "','" + pageInfo.getRowsOfPage() + "');\">" + k + "</a>");
                str.append("<a class=\"onPnum\" href='").append(url).append("?pageNum=").append(k).append(parameter)
                        .append("'>").append(k).append("</a>  ");
            }
        }

        if (pageBean.getCurrentPage() < maxPage) {
            str.append("<a href='").append(url).append("?pageNum=").append(pageBean.getCurrentPage() + 1)
                    .append(parameter).append("'>下一页</a>  ").append("<a href='").append(url).append("?pageNum=")
                    .append(maxPage).append(parameter).append("'>末页</a>");
        }

        /*
         * str.append("<select id=pageChange onchange='forChange(this.value)'>"); for(int i=1;i<=maxPage;i++){ if
         * (pageInfo.getCurrentPageNum()==i){
         * str.append("<option value="+i+" selected>第"+i+"页</option>"); }else{ str.append("<option
         * value="+i+">第"+i+"页</option>"); } }
         */
        str.append("</select>");
        return str.toString();
    }

    /**
     * pageInfo.
     *
     * @return the pageInfo
     */
    public PageBean getPageBean() {
        return pageBean;
    }

    /**
     * @param pageBean
     *         the pageInfo to set
     */
    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    /**
     * url. URL
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *         the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * currentPage.
     *
     * @return the currentPage
     */
    public Integer getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage
     *         the currentPage to set
     */
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * parameter.
     *
     * @return the parameter
     */
    public String getParameter() {
        return parameter;
    }

    /**
     * @param parameter
     *         the parameter to set
     */
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

}
