package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logger.Log;
import actions.ActionException;
import actions.ActionManager;
import actions.ActionManagerFactory;
import constants.Attributes;
import constants.Messages;
import constants.Parameters;
import constants.Uri;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet(name = "/DispatcherServlet", loadOnStartup = 1, urlPatterns = { "/index", "/all_words", "/add_word",
        "/del_word", "/eng_to_rus", "/rus_to_eng" })
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        response.setContentType("text/html;charset=UTF-8");

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String actionName = null;
        
        String action = request.getParameter(Parameters.COMMAND_KEY);

        if (action == null) {
            int beginActionName = contextPath.length();
            int endActionName = uri.lastIndexOf(Uri.URI_POINT_DELIMITER);
            if (endActionName >= 0) {
                actionName = uri.substring(beginActionName, endActionName);
            } else {
                actionName = uri.substring(beginActionName);
            }
        } else {
            actionName = action;
        }
        System.out.println(actionName);
        ActionManager actionManager = ActionManagerFactory.getActionManager();
        try {
            String path = actionManager.execute(actionName, request, response);
            if (path != null) {
                request.getRequestDispatcher(path).forward(request, response);
            } else {
                if (Uri.MAIN_URI.equals(actionName)) {
                    request.getRequestDispatcher(actionName + Uri.JSP_SUFFIX).forward(request, response);
                } else {
                    if (Uri.ADD_WORD_URI.substring(Uri.ADD_WORD_URI.lastIndexOf(Uri.URI_SLASH_DELIMITER)).equals(actionName)) {
                        request.getRequestDispatcher(Uri.JSP_PREFIX + Uri.ADD_WORD_URI + Uri.JSP_SUFFIX).forward(
                                request, response);
                    }
                }
            }
        } catch (ActionException e) {
            Log.error(Messages.DATABASE_ERROR);
            String path = Uri.JSP_PREFIX + Uri.ERROR_URI + Uri.JSP_SUFFIX;
            request.setAttribute(Attributes.ERROR_KEY, Messages.DATABASE_ERROR);
            getServletContext().getRequestDispatcher(path).forward(request, response);
        }
    }
}