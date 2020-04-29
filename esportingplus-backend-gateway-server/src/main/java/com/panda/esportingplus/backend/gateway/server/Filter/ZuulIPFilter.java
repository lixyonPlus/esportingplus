package com.panda.esportingplus.backend.gateway.server.Filter;

//import com.kaihei.esportingplus.common.tools.IpAddressUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

//@Component
@ConfigurationProperties(prefix = "zuul.ipfilter")
public class ZuulIPFilter extends ZuulFilter {

    final Logger logger = LoggerFactory.getLogger(ZuulIPFilter.class);


    private List<String> whiteIps = new ArrayList<>();

    public List<String> getWhiteIps() {
        return whiteIps;
    }

    public void setWhiteIps(List<String> whiteIps) {
        this.whiteIps = whiteIps;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        PathMatcher antPathMatcher = new AntPathMatcher();
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String ipAdrress = null;//IpAddressUtil.getIpAdrress(request);
        for (String whiteIp : whiteIps) {
            boolean match = antPathMatcher.match(whiteIp, ipAdrress);
            if (match) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
        return null;
    }
}
