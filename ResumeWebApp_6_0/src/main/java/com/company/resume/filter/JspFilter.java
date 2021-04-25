package com.company.resume.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "JSPFileFilter", urlPatterns = "*.jsp")
public class JspFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        try{
            httpServletResponse.sendRedirect("error?msg=not found");
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
