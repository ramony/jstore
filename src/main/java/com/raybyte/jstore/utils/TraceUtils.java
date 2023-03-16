package com.raybyte.jstore.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class TraceUtils {

    private static final String TRACE_ID = "traceId";

    public static void createTraceId(HttpServletRequest request) {
        try {
            String traceId = request.getHeader("trace-id");
            if (StringUtils.isEmpty(traceId)) {
                traceId = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
            }
            MDC.put(TRACE_ID, traceId);
        } catch (Exception e) {

        }
    }

    public static void destroyTraceId() {
        try {
            MDC.remove(TRACE_ID);
        } catch (Exception e) {

        }
    }
}
