package com.example.filters

import groovy.transform.CompileStatic
import io.micronaut.core.order.Ordered
import io.micronaut.http.HttpRequest
import io.micronaut.http.annotation.RequestFilter
import io.micronaut.http.annotation.ServerFilter
import io.micronaut.http.filter.ServerFilterPhase
import io.micronaut.http.util.HttpHeadersUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@ServerFilter('/**')
@CompileStatic
final class LoggingHeadersFilter implements Ordered {
    private static final Logger LOG = LoggerFactory.getLogger(this.class)

    @RequestFilter
    void filterRequest(HttpRequest<?> request) {
        HttpHeadersUtil.trace(LOG, request.headers)
    }

    @Override
    int getOrder() {
        ServerFilterPhase.FIRST.order()
    }
}
