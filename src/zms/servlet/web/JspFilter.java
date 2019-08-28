package zms.servlet.web;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JspFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String url = httpServletRequest.getRequestURI();
        String[] split = url.split("/");
        String s=split[split.length-1];
        String path=httpServletRequest.getContextPath();
        if(url != null && "home.jsp".equals(s)||path.equals("/"+s)) {
            httpServletResponse.sendRedirect("web/getproduct");
            return;
        }
        if(url!=null && "cart.jsp".equals(s)){
            Object user = httpServletRequest.getSession().getAttribute("user");
            if(user!=null){
                httpServletResponse.sendRedirect("web/shoppingGetProduct");
            }else {
                httpServletResponse.sendRedirect("web/cookiegetcert");
            }
            return;
        }
        if(url!=null && "product.jsp".equals(s)){
            httpServletResponse.sendRedirect("web/productdetil");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
