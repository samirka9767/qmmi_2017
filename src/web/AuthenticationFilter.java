package web;

import domain.usersInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by vugar on 14.05.2015.
 */
public class AuthenticationFilter implements Filter {
    private Log logger = LogFactory.getLog(getClass());

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        usersInfo user = null;
        try {
            user = (usersInfo) session.getAttribute(constant.USER);
            if (user != null && !session.isNew()) {
                chain.doFilter(request, response);
            } else {
                session.removeAttribute(constant.USER);
                logger.info("Session time out login page");
                request.getRequestDispatcher(constant.PAGE_LOGIN).forward(request, response);
//                response.sendRedirect(request.getContextPath()+"/"+constant.PAGE_LOGIN);
            }
        }catch(Exception ex){
            ex.printStackTrace();
            logger.info("User is not logged in, redirect to login page");
            request.getRequestDispatcher(constant.PAGE_LOGIN).forward(request, response);
        }

    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void destroy() {
    }
}