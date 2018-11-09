package web;

import javax.servlet.*;
import java.io.IOException;

public class UnicodeFilter implements Filter {    
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Throwable problem = null;
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            chain.doFilter(req, resp);
        } catch (Throwable t) {
            problem = t;
            t.printStackTrace();
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }  
}
