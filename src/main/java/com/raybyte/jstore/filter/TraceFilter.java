package com.raybyte.jstore.filter;

import com.raybyte.jstore.utils.TraceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class TraceFilter extends HttpFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TraceFilter.class);

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        TraceUtils.createTraceId(request);
        try {
            // process
            super.doFilter(request, response, chain);
        } finally {
            TraceUtils.destroyTraceId();
        }
    }
}
