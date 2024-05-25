package com.pc3r.vfarm.middleware;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class CorsFilter implements Filter {

    private ServletContext context;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.addHeader("Access-Control-Expose-Headers", "Location" );
        response.addHeader("Access-Control-Allow-Credentials", "true");


        filterChain.doFilter(servletRequest, servletResponse);
    }
    @Override
    public void init(FilterConfig fConfig) {
        this.context = fConfig.getServletContext();
        this.context.log("CorsFilter initialized");
    }

    @Override
    public void destroy() {
        //close any resources here
    }
}