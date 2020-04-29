package com.panda.esportingplus.common.constant;

public class IMMessageJumpType {
    /**
     * 不跳转
     */
    public static final Integer JUMP_NOTHING = -1;

    /***
     * 跳转到H5页面 只要是2就需要传扩展字段
     */
    public static final Integer JUMP_TYPE_H5 = 2;

    /***
     * 跳转到专属订单详情
     */
    public static final Integer JUMP_EXCLUSIVE_ORDER = 4;

    /***
     * 跳转到免费车队订单详情
     */
    public static final Integer JUMP_PVP_FREE_TEAM_ORDER = 5;

    /***
     * 跳转到钱包-暴鸡币
     */
    public static final Integer JUMP_WALLET_GCOIN = 10;

    /***
     * 跳转到钱包-鸡分 页面
     */
    public static final Integer JUMP_CHICKEN_POINTS = 11;

    /***
     * 跳转到跳转至QQ
     */
    public static final Integer JUMP_QQ_GROUP = 14;

    /**
     * 跳转加入车队房间
     */
    public static final Integer JUMP_JOIN_ROOM=100;
}
