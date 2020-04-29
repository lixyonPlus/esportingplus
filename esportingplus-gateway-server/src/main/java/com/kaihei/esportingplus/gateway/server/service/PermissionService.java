package com.kaihei.esportingplus.gateway.server.service;

import com.kaihei.esportingplus.common.ResponsePacket;
import javax.servlet.http.HttpServletRequest;

/**
 * 描述
 *
 * @author Orochi-Yzh
 * @dateTime 2018/6/22 17:04
 * @updatetor
 */
public interface PermissionService {
    ResponsePacket hasPermission(String newToken, String url);
}
