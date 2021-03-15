package com.example.mid_poj;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "db", value = "/db")
public class ChechAuthfilter implements Filter {
    private FilterConfig config = null;
    private boolean active = false;

    public void init (FilterConfig config) throws ServletException
    {
        this.config = config;
        String act = config.getInitParameter("active");
        if (act != null)
            active = (act.toUpperCase().equals("TRUE"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (active){
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            Cookie [] cookies = request.getCookies();
            String forw = "/login.jsp";
            for (Cookie cookie : cookies){
                if (cookie == null) {
                    forw = "/reg.jsp";
                    break;
                }
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(forw);
            dispatcher.forward(request, response);


        }

    }


    public void destroy()
    {
        config = null;
    }
}