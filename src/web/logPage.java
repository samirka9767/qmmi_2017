package web;

import domain.usersInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class logPage implements Filter {

    private Log logger = LogFactory.getLog(getClass());

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(true);
        usersInfo user = null;
        try {
            user = (usersInfo) session.getAttribute(constant.USER);
            if (user != null && !session.isNew()) {
//                request.getRequestDispatcher(constant.PAGE_MAIN).forward(request, response);   
                response.sendRedirect(request.getContextPath() + "/" + constant.PAGE_MAIN);
            } else {
                chain.doFilter(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("User is not logged in, error redirect to login page");
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
