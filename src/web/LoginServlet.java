package web;

import domain.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import service.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by a.ulviyya on 30.06.2016.
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    Log logger = LogFactory.getLog(getClass());
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean forward = false;
        String address = constant.PAGE_MAIN;
        logger.info("LoginServlet.processRequest action = " );
        UserService userService = new UserServiceImpl();
        try {
            if (request.getParameter("action").equalsIgnoreCase(constant.ACTION_LOGIN)) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String currLang = request.getParameter("sLangList");
                logger.info("username  "+username );
                logger.info("password  "+password );
                HttpSession session = request.getSession(false);
                if (session == null) {
                    request.getSession().invalidate();
                    request.getSession(true);
                    session = request.getSession(false);
                }
                if (username != null && username.trim().length() > 0 && password != null && password.trim().length() > 0) {
                    usersInfo _user = (usersInfo) session.getAttribute(constant.USER);
                    if (_user == null) {
//                        usersInfo user= userService.login(username, password);
                        usersInfo user = userService.getUser(0L, username, password,Long.parseLong(currLang));
//                        usersInfo user11 = userService.getUser(0L, username, password,Long.parseLong(currLang));
                        if (user != null) {
                            session.setAttribute("sesID", session.getId());
                            session.setAttribute(constant.USER, user);
                            address = constant.PAGE_MAIN;
                            forward = false;
                        } else {
                            logger.error("User " + username + " is null");
                            session.setAttribute("message", "İstifadəçi adı və şifrə düzgün daxil olunmayıb.");
                            address = constant.PAGE_LOGIN;
                            forward = true;
                        }

                    }
                }

            }
//     catch (){
//
//     }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(" Eroror"+e.toString());
            forward = true;
            address = constant.PAGE_LOGIN;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        if (forward) {
            dispatcher.forward(request, response);
        } else {
            logger.info("main page redirect = "+address);
            response.sendRedirect(address);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = null;
        boolean forward = false;
        UserService userService = new UserServiceImpl();
        HttpSession session = request.getSession(false);

        usersInfo user1 = (usersInfo) session.getAttribute(constant.USER);
        try {
            if (request.getParameter("action").equalsIgnoreCase(constant.ACTION_USER_ACCESS)) {
                response.setContentType("text/html;charset=UTF-8");
                try{
                    String st = request.getParameter("st");
                    long uID = 0;
                    if("0".equals(st)){
                        uID = user1.getId();
                    }else{
                        uID = Long.parseLong(st);
                    }
//                    logger.info(" st= "+st);
                    List<roles> rols = userService.userAccess(uID);
                    JsonFactory factory = new JsonFactory();
                    PrintWriter out = response.getWriter();
                    JsonGenerator generator = factory.createJsonGenerator(out);
                    generator.writeStartObject();
                    generator.writeArrayFieldStart("dsList");
                    for (roles ds : rols) {
                        generator.writeStartObject();
                        generator.writeStringField("menuID",ds.getMenuID());
                        generator.writeStringField("levelID",ds.getLevelID());
                        generator.writeStringField("buttonID",String.valueOf(ds.getButtons()));
                        generator.writeStringField("bID",String.valueOf(ds.getButtonID()));
                        generator.writeEndObject();
                    }
                    generator.writeEndArray();
                    generator.writeEndObject();
                    generator.flush();
                }catch (Exception ex) {
                    logger.info(" userAccess Info "+ ex.getMessage());
                }
                address =null;
                forward=false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(" Eroror"+ex.toString());
            forward = true;
            address = constant.PAGE_LOGIN;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        if (forward) {
            dispatcher.forward(request, response);
        } else {
            if(address!=null){response.sendRedirect(address);}//request.getContextPath()+"/"+
        }
    }

}
