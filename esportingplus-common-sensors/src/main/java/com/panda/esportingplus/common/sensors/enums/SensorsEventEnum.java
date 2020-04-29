package com.panda.esportingplus.common.sensors.enums;

public enum SensorsEventEnum {

    REGISTER_SUCCESS("RegisterSuccess", "注册成功"),
    LOGIN("Login", "登录"),
    FOLLOW("Follow", "关注和取关"),
    ACCOUNT_BINDING("AccountBinding", "绑定和解绑"),
    START_MATCHING_FREETEAM("StartMatchingFreeTeam", "开始匹配免费车队"),
    END_MATCHING_FREETEAM("EndMatchingFreeTeam", "结束匹配免费车队"),
    ORDER_STATUSCHANGE_FREETEAM("OrderStatusChangeFreeTeam", "免费车队订单状态变更"),
    BUILD_FREETEAM("BuildFreeTeam", "创建免费车队"),
    ACTION_FREETEAM("ActionFreeTeam", "进入/退出免费车队"),
    DISBAND_FREETEAM("DisbandFreeTeam", "解散免费车队"),
    KICK_TEAMMATE_FREETEAM("KickTeammateFreeTeam", "暴鸡踢人"),
    START_FREETEAM("StartFreeTeam", "暴鸡发车"),
    BOSS_ORDER_FREETEAM("BossOrderFreeTeam", "老板订单生成"),
    END_FREETEAM("EndFreeTeam", "免费车队结束服务"),
    BAOJI_ORDER_FREETEAM("BaojiOrderFreeTeam", "暴鸡订单生成"),
    SUCCESSED_INVITE_FREETEAM("SuccessedInviteFreeTeam", "邀请成功"),
    GET_FREE_FREETEAM("GetFreeFreeTeam", "获得免单机会"),
    GET_POINT_FREETEAM("GetPointFreeTeam", "获得鸡分"),
    USE_POINT_FREETEAM("UsePointFreeTeam", "使用鸡分"),
    USER_COMPLAIN_USERCOMPLAIN("UserComplain", "用户投诉"),
    USER_REVIEW("UserReview", "用户评价"),
    BAOJI_ACCQUIRE_LABEL("BaojiAccquireLabel", "暴鸡获得标签"),
    GCOIN_RECHARGE("RefillBaojibi", "充值暴鸡币"),
    GCOIN_PAY("SpendBaojibi", "支出暴鸡币"),
    REWARD_GIFT("RewardGift", "打赏礼物"),
    SUBMIT_PERSONAL_CERTIFY("SubmitPersonalCertify", "提交个人资料认证"),
    SUBMIT_GAME_CERTIFY("SubmitGameCertify", "提交游戏资料认证"),
    INVITE_NEWYEAR_REWARD_GET("InviteNewyearRewardGet", "运营新年拉新活动-领取奖励"),
    INVITE_NEWYEAR_ASSISTANCE("InviteNewyearAssistance", "运营新年拉新-点击助力"),
    STARLIGHT_EXCHANGE("ExchangeBaojizhi", "鸡分兑换暴击值"),
    BAOJI_GAME_CHANGE("BaojiGameChange", "暴鸡游戏资格变化"),
    PASS_GAME_CERTIFY("PassGameCertify", "暴鸡游戏审核通过"),
    SEARCH_RESULT("SearchResult", "搜索结果"),

    /**
     * 专属单相关
     */
    EXCLUSIVE_ORDER_STATUS_CHANGE("ExclusiveOrderStatusChange","专属订单状态变更"),

    /**
     * 投诉申诉-神策上报枚举类
     */
    COMPLAIN_RESULT("ComplainResult","比赛结果投诉"),
    COMPLAIN_SERVICE("ComplainService","服务态度投诉"),
    APPEAL_COMPLAINT("AppealComplaint","提交申诉"),
    COMPLAINT_STATUS_CHANGE("ComplaintStatusChange","投诉单状态变化")
    ;

    private String code;
    private String desc;

    SensorsEventEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
