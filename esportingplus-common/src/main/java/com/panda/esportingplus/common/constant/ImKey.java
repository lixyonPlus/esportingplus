package com.panda.esportingplus.common.constant;

public class ImKey {

    /**
     * 踢人命令
     */
    public static final String TEAM_LEADER_OUT_MEMBER_KEY = "team.out.member";
    /**
     * 踢人出房间
     */
    public static final String ROOM_LEADER_OUT_MEMBER_KEY = "room.out.member";

    /**
     * 有人退出房间
     */
    public static final String ROOM_EXIT_KEY = "room.member.exit";

    /**
     * 车队解散命令
     */
    public static final String ROOM_DISMISS_KEY = "room.dismiss";

    /**
     * 车队开车命令
     */
    public static final String TEAM_START_KEY = "team.start";
    /**
     * 车队解散命令
     */
    public static final String TEAM_DISMISS_KEY = "team.dismiss";
    /**
     * 车队位置发生变化命令
     */
    public static final String TEAM_COUNT_CHANGE = "team.count.change";
    /**
     * 车队服务结束命令
     */
    public static final String END_TEAM_SERVER = "team.server.end";
    /**
     * 支付完成命令
     */
    public static final String PAID_FINISH_KEY = "team.member.paid.finish";
    /**
     * 确认入团命令
     */
    public static final String CONFIRM_IN_TEAM = "team.member.confirm";
    /**
     * 加入车队命令
     */
    public static final String JOIN_TEAM_KEY = "team.member.join";

    public static final String JOIN_ROOM_KEY = "room.member.join";

    /**
     * 车队冻结命令
     */
    public static final String TEAM_FREEZED_KEY = "team.freezed";
    /**
     * 车队老板派单
     */
    public static final String TEAM_BOSS_DISPATCH_KEY = "team.boss.dispatch";

    /**
     * 车队解冻命令
     */
    public static final String TEAM_UNFREEZED_KEY = "team.unfreezed";
    /**
     * 加入车队命令
     */
    public static final String JOIN_TEAM_LEADER_NOTIFY_KEY = "team.member.join.leader.nofity";
    /**
     * 有人退出命令
     */
    public static final String EXIT_TEAM_KEY = "team.member.exit";

    /**
     * 有人退出命令
     */
    public static final String TEAM_STATUS_KEY = "team.status.update";
    /**
     * 满员通知命令
     */
    public static final String TEAM_MEMBER_FULL_KEY = "team.member.full";

    /**
     * 匹配失败命令
     */
    public static final String MEMBER_MATCH_FAIL_KEY = "member.match.fail";

    /**
     * 匹配成功命令
     */
    public static final String MEMBER_MATCH_SUCCESS_KEY = "member.match.success";
    /**
     * 成员准备
     */
    public static final String TEAM_MEMBER_READY = "member.ready";

    /**
     * 取消准备
     */
    public static final String TEAM_MEMBER_CANCEL_READY = "member.cancel.ready";
    /**
     * 车队位置更新
     */
    public static final String TEAM_SEAT_UPDATE = "team.seat.update";
    /**
     * 再来一单命令
     */
    public static final String TEAM_MEMBER_ANOTHER_ORDER = "member.another.order";

    /**
     * 专属单-等待暴鸡接单
     */
    public static final String SPECIAL_ORDER_PREPARE_ACCEPT = "special:order:prepare:accept";
    public static final String PREPARE_PAID = "special:prepare:paid";
    /***
     *  待接单
     */
    public static final String PREPARE_ACCEPT = "special:prepare:accept";

    /***
     * 待开始
     */
    public static final String PREPARE_START = "special:prepare:start";
    /***
     *  游戏进行中
     */
    public static final String STARTED = "special:started";
    /***
     * 游戏进行中老板取消
     */
    public static final String STARTED_BOSS_CANCEL = "special:started:boss:cancel";

    public static final String PENDING_CONFIRM_RESULT = "special:pending:confirm";

    public static final String COMPLAINT_PROCESSING = "special:complaint:processing";
    public static final String COMPLAINT_SUCCESS = "special:complaint:success";
    public static final String COMPLAINT_FAILED = "special:complaint:failed";
    /***
     * 已完成
     */
    public static final String COMPLETED = "special:completed";
    /***
     * 暴鸡超时未提交结果订单完成
     */
    public static final String COMPLETED_BAOJI_SUBMIT_RESULT_TIMEOUT = "special:completed:baoji:submit:result:timeout";

    /***
     * 比赛结果未打已返还免费次数(自动退款)
     */
    public static final String COMPLETED_AUTO_REFUND = "special:completed:auto:refund";

    /***
     * 待接单-老板取消
     */
    public static final String CANCELED_PREPARE_ACCEPT_BOSS_CANCEL = "special:canceled:prepare:accept:boss:cancel";

    /***
     * 待接单-超时
     */
    public static final String CANCELED_PREPARE_ACCEPT_TIMEOUT = "special:canceled:prepare:accept:timeout";

    /***
     * 待接单-暴鸡拒绝
     */
    public static final String CANCELED_PREPARE_ACCEPT_BAOJI_REFUSE = "special:canceled:prepare:accept:baoji:refuse";
    /****
     * 待开始-老板取消
     */
    public static final String CANCELED_PREPARE_START_BOSS_CANCEL = "special:canceled:prepare:start:boss:cancel";

    /***
     * 待开始-超时取消
     */
    public static final String CANCELED_PREPARE_START_TIMEOUT = "special:canceled:prepare:start:timeout";

    /***
     * 待开始-超时取消
     */
    public static final String CANCELED_PAID_TIMEOUT = "special:canceled:paid:timeout";


    public static final String AFTER_EVALUATE_REWARD = "after.evaluate.reward";

}
