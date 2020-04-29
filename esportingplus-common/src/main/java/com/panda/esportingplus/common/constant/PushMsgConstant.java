package com.panda.esportingplus.common.constant;

/**
 * @program: esportingplus
 * @description: push消息常量
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 **/
public class PushMsgConstant {

    /***
     * push消息类型
     */
    public static final String PUSH_TYPE = "push_type";
    /***
     * 数据集合
     */
    public static final String DATA = "data";

    /***
     * 序列号/跳转类型为H5时显示URL
     */
    public static final String SEQUENCE_NO = "sequence_no";

    /***
     * 跳转类型
     * 0、H5页面
     * 1、系统通知
     * 2、IM消息，
     * 3、订单详情，
     * 100、车队房间
     */
    public static final String JUMP_TYPE = "jump_type";

    /**
     * 不跳转
     */
    public static final Integer JUMP_NOTHING = -1;
    /***
     * 跳转到H5页面
     */
    public static final Integer JUMP_TYPE_H5 = 0;

    /***
     * 跳转到系统通知
     */
    public static final Integer JUMP_TYPE_SYSTEM_NOTICE = 1;

    /***
     * 跳转到IM消息
     */
    public static final Integer JUMP_TYPE_IM = 2;

    /***
     * 跳转到订单详情
     */
    public static final Integer JUMP_TYPE_ORDER_DETAIL = 3;
    /***
     * 跳转到接单中心
     */
    public static final Integer JUMP_TYPE_ORDER_CENTER = 4;

    /***
     * 跳转到消息-系统消息-消息详情 页面
     */
    public static final Integer JUMP_FREE_TEAM_ROOM = 100;

    /***
     * 跳转到系统消息
     */
    public static final Integer JUMP_SYSTEM_MESSGAE = 20;


    /***
     * 跳转到消息-审核进度页
     */
    public static final Integer JUMP_AUDITING_PROCESS = 15;

    /***
     * 扩展字段信息
     */
    public static final String EXTRA = "extra";

    /***
     * extra中的消息发送人ID
     */
    public static final String FROM_USER_ID = "from_user_id";

    //系统类型
    /***
     * 系统通知-运营推送通知
     */
    public static final String SYSTEM_OPERATIONS_MESSAGE = "SYSTEM_OPERATIONS_MESSAGE";


    //单聊类型
    /***
     * 单聊通知-专属单-订单详情消息
     */
    public static final String SYSTEM_ORDER_SPECIAL_DETAIL = "SYSTEM_ORDER_SPECIAL_DETAIL";

    /***
     * 单聊通知-专属单-小灰条消息(暂未使用)
     */
    public static final String SYSTEM_ORDER_SPECIAL_GREY = "SYSTEM_ORDER_SPECIAL_GREY";
    /***
     * 系统通知，车队老板派单
     */
    public static final String SYSTEM_TEAM_BOSS_DISPATCH = "SYSTEM_TEAM_BOSS_DISPATCH";

    /***
     * push通知中的pushType,一般性消息通知
     */
    public static final String SYSTEM_COMMON_MESSAGE="SYSTEM_COMMON_MESSAGE";

    /***
     * 系统通知-专属单-次数回退（暂未使用）
     */
    public static final String SYSTEM_ORDER_SPECIAL_RETURN = "SYSTEM_ORDER_SPECIAL_RETURN";
}
