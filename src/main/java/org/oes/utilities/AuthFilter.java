/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oes.utilities;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>Class for filtering the request and response cycle
 * of the application. The class is used for basic authorization
 * in the application</p>
 * @author Mingso
 * 
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {
    
    public AuthFilter() {
    }
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
         
    }
    
    
    /**
     * <p>Method to perform the
     * filtering operations on
     * incoming response and 
     * outgoing requests</p>
     * @param request the request object
     * @param response the response object
     * @param chain the object to view the 
     * invoked chain of the filtered request
     * for a resource.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         try {
 
            // check whether session variable is set
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession ses = req.getSession(false);
            //  allow user to proccede if url is Authentication.xhtml or user logged in or user is accessing any page in //public folder
            String reqURI = req.getRequestURI();
            if ( reqURI.indexOf("/pages/Authentication.xhtml") >= 0 || (ses != null && ses.getAttribute("username") != null)
                                       || reqURI.indexOf("/public/") >= 0 || reqURI.contains("javax.faces.resource") )
                   chain.doFilter(request, response);
            else   // user didn't log in but asking for a page that is not allowed so take user to authentication page
                   res.sendRedirect(req.getContextPath() + "/pages/Authentication.xhtml");  // Anonymous user. Redirect to login page
      }
     catch(Throwable t) {
         System.out.println( t.getMessage());
     }
    } //doFilter
 
    @Override
    public void destroy() {
         
    }
    
}
