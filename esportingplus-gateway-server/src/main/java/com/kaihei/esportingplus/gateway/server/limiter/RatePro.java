package com.kaihei.esportingplus.gateway.server.limiter;

import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.Rate;

public class RatePro extends Rate {

    public RatePro(String key) {
        super(key, null, null, null, null);
    }

    private String urlRateKey;
    private String userRateKey;
    private Long avgLimit;
    private Long currentRequest;
    private Long rateTtl;

    public Long getCurrentRequest() {
        return currentRequest;
    }

    public void setCurrentRequest(Long currentRequest) {
        this.currentRequest = currentRequest;
    }

    public Long getRateTtl() {
        return rateTtl;
    }

    public void setRateTtl(Long rateTtl) {
        this.rateTtl = rateTtl;
    }

    public Long getAvgLimit() {
        return avgLimit;
    }

    public void setAvgLimit(Long avgLimit) {
        this.avgLimit = avgLimit;
    }

    public String getUrlRateKey() {
        return urlRateKey;
    }

    public void setUrlRateKey(String urlRateKey) {
        this.urlRateKey = urlRateKey;
    }

    public String getUserRateKey() {
        return userRateKey;
    }

    public void setUserRateKey(String userRateKey) {
        this.userRateKey = userRateKey;
    }
}
