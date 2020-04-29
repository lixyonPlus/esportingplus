package com.panda.esportingplus.common.constant;

/**
 * rocketmq的一些常量
 *
 * @author shusong.liang
 */
public class RocketMQConstant {

    /**
     * topic
     */
    public static final String TOPIC_RPG = "esportingplus_prg";

    public static final String TOPIC_PVP = "esportingplus_pvp";

    public static final String TOPIC_PVP_EXCLUSIVE = "esportingplus_pvp_exclusive";


    public static final String TOPIC_PVP_FREE = "esportingplus_pvp_free";
    public static final String TOPIC_SHARE_INVIT = "esportingplus_share_invit";

    public static final String TOPIC_ORDER_STATISTIC = "topic_order_statistic";
    public static final String TOPIC_CERTIFICATE_RENEW = "esportingplus_certificate_renew";

    /**
     * 暴鸡等级积分topic
     */
    public static final String TOPIC_BAOJI_SCORE = "esportingplus_baoji_score";

    /**
     * 分享邀请注册 tag
     */
    public static final String INVIT_REGIST_TAG = "invit_regist_tag";
    /**
     * 品牌推广 tag
     */
    public static final String BRAND_PROMOTION_TAG = "brand_promotion_tag";
    /**
     * 创建暴鸡订单(立即开车) tag
     */
    public static final String CREATE_BAOJI_ORDER_TAG = "create_baoji_order";

    /**
     * 修改订单状态(退出、踢出车队、解散、结束车队) tag
     */
    public static final String UPDATE_ORDER_STATUS_TAG = "update_order_status";

    /**
     * 订单统计tag
     */
    public static final String ORDER_STATISTICS_TAG = "order_statistics";

    public static final String ORDER_FINISH_STATISTICS_TAG = "order_finish_statistics";

    /**
     * 车队的分布式事务 id
     */
    public static final String GAMINGTEAM_TRANSACTION_ID = "gamingteam_%s_%s";

    /**
     * MQ TAG：开车后离开车队
     */
    public static final String QUIT_TEAM_AFTER_RUNNING = "quit_team_after_running";

    /**
     * 退款 tags
     */
    public static final String REFUND_ORDER_TAGS = "refund_order";

    /**
     * 车队发起退款 tags
     */
    public static final String REFUND_ORDER_TAGS_FROM_TEAM = "refund_order_from_team";

    /**
     * 创建暴鸡订单生产组
     */
    public static final String CREATE_BAOJI_ORDER_PRODUCER_GROUP = "create_baoji_order_producer_group";

    /**
     * 创建暴鸡订单消费组
     */
    public static final String CREATE_BAOJI_ORDER_CONSUMER_GROUP = "create_baoji_order_consumer_group";
    public static final String PVP_FREE_ORDER_CONSUMER_GROUP = "pvp_free_order_consumer_group";

    public static final String COMPLAINT_CALL_BACK_ORDER_CONSUMER_GROUP = "complaint_call_back_order_consumer_group";
    /**
     * 修改订单状态生产组
     */
    public static final String UPDATE_ORDER_STATUS_PRODUCER_GROUP = "update_order_status_producer_group";

    /**
     * 修改PVP订单状态消费组
     */
    public static final String UPDATE_ORDER_STATUS_CONSUMER_PVP_GROUP = "update_order_status_consumer_pvp_group";
    /**
     * 车队结束修改PVP_FREE订单状态消费组
     */
    public static final String UPDATE_ORDER_CONSUMER_PVP_FREE_END_TEAM_GROUP = "update_order_consumer_pvp_free_end_team_group";
    /**
     * 已开车主动退出修改PVP_FREE订单状态消费组
     */
    public static final String UPDATE_ORDER_CONSUMER_PVP_FREE_LEAVE_TEAM_GROUP = "UPDATE_ORDER_CONSUMER_PVP_FREE_LEAVE_TEAM_GROUP";
    /**
     * 好友完成免费车队消费组
     */
    public static final String FRIEND_FINISH_ORDER_CONSUMER_PVP_FREE_GROUP = "friend_finish_order_consumer_pvp_free_group";

    /**
     * 订单统计消费组
     */
    public static final String ORDER_STATISTICS_CONSUMER_GROUP = "order_statistics_consumer_group";

    /**
     * 段位变化消费组
     */
    public static final String GAME_DAN_CHANGE_CONSUMER_GROUP = "game_dan_change_consumer_group";

    /**
     * 分数变化消费组
     */
    public static final String SCORE_CHANGE_CONSUMER_GROUP = "score_change_consumer_group";

    /**
     * 好友完成免费车队消费组
     */
    public static final String INVIT_SHARE_CONSUMER_GROUP = "invit_share_consumer_group";

    /**
     * 品牌推广消费组
     */
    public static final String BRAND_PROMOTION_CONSUMER_GROUP = "brand_promotion_consumer_group";
    /**
     * 修改RPG订单状态消费组
     */
    public static final String UPDATE_ORDER_STATUS_CONSUMER_RPG_GROUP = "update_order_status_consumer_rpg_group";
    /**
     * RPG退款生产组
     */
    public static final String REFUND_ORDER_PRODUCER_RPG_GROUP = "refund_order_producer_rpg_group";
    /**
     * PVP退款生产组
     */
    public static final String REFUND_ORDER_PRODUCER_PVP_GROUP = "refund_order_producer_pvp_group";
    /**
     * PVP付费专属单退款生产组
     */
    public static final String REFUND_ORDER_PRODUCER_PVP_EXCLUSIVE_GROUP = "refund_order_producer_pvp_exclusive_group";
    /**
     * RPG退款消费组
     */
    public static final String REFUND_ORDER_CONSUMER_RPG_GROUP = "refund_order_consumer_rpg_group";
    /**
     * PVP退款消费组
     */
    public static final String REFUND_ORDER_CONSUMER_PVP_GROUP = "refund_order_consumer_pvp_group";
    /**
     * PVP专属单退款消费组
     */
    public static final String REFUND_ORDER_CONSUMER_PVP_EXCLUSIVE_GROUP = "refund_order_consumer_pvp_exclusive_group";

    /**
     * 车队发起的退款消费组
     */
    public static final String REFUND_ORDER_CONSUMER_GROUP_FROM_TEAM = "refund_order_consumer_group_fromt_team";

    /***
     * 支付模块group
     */

    public static final String PAYMENT_TOPIC = "payment_group";

    /**
     * 支付模块group的分布式事务 id
     */
    public static final String PAYMENT_TRANSACTION_ID = "payment_%s_%s";

    /**
     * 暴鸡币支付 tag
     */
    public static final String GCOIN_PAYMENT_TAG = "gcoin_payment";

    /**
     * 暴鸡币退款 tag
     */
    public static final String GCOIN_REFUND_TAG = "gcoin_refund";

    /**
     * 暴鸡币充值 tag
     */
    public static final String GCOIN_RECHARGE_TAG = "gcoin_recharge";

    /**
     * 暴击值提现 tag
     */
    public static final String STARLIGHT_WITHDRAW_TAG = "starlight_withdraw";

    /**
     * 暴击值兑换 tag
     */
    public static final String STARLIGHT_EXCHANGE_TAG = "starlight_exchange";

    /***
     * 内部支付-暴鸡币支付
     */
    public static final String EXTERNAL_GCOIN_PAY_TAG = "external_gcoin_pay";

    /***
     * 内部支付-暴鸡币支付回调
     */
    public static final String EXTERNAL_GCOIN_NOTIFY_TAG = "external_gcoin_notify";

    /***
     * 暴鸡币充值生产组
     */
    public static final String GCOIN_RECHARGE_PRODUCER_GROUP = "gcoin_recharge_producer_group";

    /***
     * 暴鸡币充值消费组
     */
    public static final String GCOIN_RECHARGE_CONSUMER_GROUP = "gcoin_recharge_consumer_group";

    /***
     * 暴鸡币支付生产组
     */
    public static final String GCOIN_PAYMENT_PRODUCER_GROUP = "gcoin_payment_producer_group";

    /**
     * 暴鸡币支付消费组
     */
    public static final String GCOIN_PAYMENT_CONSUMER_GROUP = "gcoin_payment_consumer_group";

    /**
     * 暴鸡币退款生产组
     */
    public static final String GCOIN_REFUND_PRODUCER_GROUP = "gcoin_refund_producer_group";

    /**
     * 暴鸡币退款消费组
     */
    public static final String GCOIN_REFUND_CONSUMER_GROUP = "gcoin_refund_consumer_group";

    /**
     * 暴击值提现消费组
     */
    public static final String STARLIGHT_WITHDRAW_CONSUMER_GROUP = "starlight_withdraw_consumer_group";

    /**
     * 暴击值兑换消费组
     */
    public static final String STARLIGHT_EXCHANGE_CONSUMER_GROUP = "starlight_exchange_consumer_group";

    /**
     * 云账户提现-下单tag
     */
    public static final String EXTERNEL_CLOUD_CREATEORDER_TAG = "cloud_create_order";
    /**
     * 云账户提现-支付回调tag
     */
    public static final String EXTERNEL_CLOUD_NOTIFYORDER_TAG = "cloud_notify_order";
    /**
     * 云账户提现-下单生产组
     */
    public static final String EXTERNAL_CLOUD_CREATE_PRODUCER_GROUP = "external_cloud_create_producer_group";
    /**
     * 云账户提现-下单消费组
     */
    public static final String EXTERNAL_CLOUD_CREATE_CONSUMER_GROUP = "external_cloud_create_consumer_group";
    /**
     * 云账户提现-回调生产组
     */
    public static final String EXTERNAL_CLOUD_NOTIFY_PRODUCER_GROUP = "external_cloud_notify_producer_group";
    /**
     * 云账户提现-回调消费组
     */
    public static final String EXTERNAL_CLOUD_NOTIFY_CONSUMER_GROUP = "external_cloud_notify_consumer_group";

    /**
     * 内部支付-暴鸡币支付调用生产组
     */
    public static final String EXTERNAL_GCOIN_PAY_PRODUCER_GROUP = "external_gcoin_pay_producer_group";

    /**
     * 内部支付-暴鸡币支付调用消费组
     */
    public static final String EXTERNAL_GCOIN_PAY_CONSUMER_GROUP = "external_gcoin_pay_consumer_group";

    /**
     * 内部支付-暴鸡币支付回调生产组
     */
    public static final String EXTERNAL_GCOIN_NOTIFY_PRODUCER_GROUP = "external_gcoin_notify_producer_group";

    /**
     * 内部支付-暴鸡币支付回调消费组
     */
    public static final String EXTERNAL_GCOIN_NOTIFY_CONSUMER_GROUP = "external_gcoin_notify_consumer_group";

    /**
     * 订单消息tag
     */
    public static final String EXTERNAL_ORDER_MESSAGE_TAG = "order_message";

    /**
     * 支付模块-订单消息生产组
     */
    public static final String EXTERNAL_ORDER_PRODUCTOR_GROUP = "external_order_producer_group";

    /**
     * 支付模块-订单消息消费组
     */
    public static final String EXTERNAL_ORDER_CONSUMER_GROUP = "external_order_consumer_group";

    /**
     * 支付模块-打赏消费组
     */
    public static final String PAYMENT_REWARD_CONSUMER_GROUP = "payment_reward_consumer_group";

    /**
     * 支付模块-打赏TAG
     */
    public static final String PAYMENT_REWARD_TAG = "payment_reward_tag";

    /**
     * 支付模块-打赏成功TAG
     */
    public static final String PAYMENT_REWARD_SUCCESS_TAG = "payment_reward_success_tag";

    /***
     * 支付服务-暴鸡币业务tag
     */
    public static final String PAYMENT_GCOIN_TAG = "payment_gcoin_tag";

    /***
     * 支付服务-暴鸡币业务-MQ生产者
     */
    public static final String PAYMENT_GCOIN_PRODUCTOR_GROUP = "payment_gcoin_productor_group";

    /***
     * 支付服务-暴鸡币业务-MQ消费者
     */
    public static final String PAYMENT_GCOIN_CONSUMER_GROUP = "payment_gcoin_consumer_group";

    /***
     * 支付服务-暴鸡币业务--MQ消费者
     */
    public static final String PAYMENT_GCOIN_REFUND_CONSUMER_GROUP = "payment_gcoin_refund_consumer_group";

    /**
     * 支付模块-打赏成功消费组
     */
    public static final String PAYMENT_REWARD_SUCCESS_CONSUMER_TAG = "payment_reward_success_consumer_group";

    /**
     * 游戏认证段位变更 tag
     */
    public static final String CERT_RENEW_TAG = "cert_renew_tag";

    /**
     * 用户模块-暴鸡等级分变更tag
     */
    public static final String BAOJI_SCORE_CHANGE_TAG = "user_baoji_score_change_tag";

    /**
     * 用户模块-暴鸡已完成订单消费组
     */
    public static final String BAOJI_FINISH_ORDER_CONSUMER_GROUP = "user_baoji_finish_order_consumer_group";

    /**
     * 客服模块-用户投诉消息生产组
     */
    public static final String CUSTOMER_COMPLAIN_PRODUCER_GROUP = "complain_producer_group";

    /**
     * 客服模块-用户投诉消息消费组
     */
    public static final String CUSTOMER_COMPLAIN_CONSUMER_GROUP = "complain_consumer_group";

    /**
     * 客服模块-用户投诉成功
     */
    public static final String CUSTOMER_COMPLAIN_SUCCESS_TAG = "user_complain_success";

    /**
     * 客服模块-订单评价消息生产组
     */
    public static final String CUSTOMER_ORDER_EVALUATE_PRODUCER_GROUP = "order_evaluate_producer_group";

    /**
     * 客服模块-订单评价消费组
     */
    public static final String CUSTOMER_ORDER_EVALUATE_CONSUMER_GROUP = "order_evaluate_consumer_group";

    /**
     * 客服模块-订单评价完成
     */
    public static final String CUSTOMER_ORDER_EVALUATE_TAG = "order_evaluate_finish";

    /**
     * 订单模块-投诉处理完成Tag
     */
    public static final String TRADE_COMPLAIN_FINISH_TAG = "complain_finish_notify_trade";

    /**
     * Customer模块-消息Topic
     */
    public static final String CUSTOMER_CENTER_TOPIC = "customer_center_group";

    /**
     * 暴鸡统计上报topic
     */
    public static final String TOPIC_BAOJI_CENSUS = "esportingplus_baoji_census";

    /**
     * 暴鸡统计上报-计数器TAG
     */
    public static final String TAG_BAOJI_CENSUS_COUNTER = "esportingplus_baoji_census_counter_tag";

    /**
     * 暴鸡统计上报消费者组
     */
    public static final String GROUP_BAOJI_CENSUS_CONSUMER = "esportingplus_baoji_census_consumer_group";


    /**
     * live模块-消息Topic
     */
    public static final String TOPIC_CUSTOMER_LIVE_AUCTION = "live_auction_topic";
    public static final String GROUP_AUCTION_CONSUMER = "live_auction_consumer_group";
    public static final String TAG_END_AUCTION = "live_auction_end_auction_tag";

}
