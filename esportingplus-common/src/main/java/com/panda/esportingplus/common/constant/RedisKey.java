package com.panda.esportingplus.common.constant;

/**
 * redis key 常量
 *
 * @author shusong.liang
 */
public final class RedisKey {

    public static final String FIND_SUCCESS = "OK";
    public static final String SET_IF_NOT_EXIST = "NX";
    public static final String SET_WITH_EXPIRE_TIME = "PX";

    public static final String QUARTZ_JOB_QUEUE = "v3:quartz:job:queue";

    public static final String QUARTZ_JOB_RM_QUEUE = "v3:quartz:job:rm:queue";

    public static final String QUARTZ_JOB_RM_TAG_PRE = "v3:quartz:job:rm:tag:";


    /**
     * 执行Job队列前缀
     */
    public final static String JOB_EXECUTION_QUEUE_KEY_PRE = "v3:quartz:job:execution:";
    /**
     * key 分隔符
     */
    public static final String KEY_SEPARATOR = ":";

    /**
     * 缓存在 redis 中的车队路线列表集合
     */
    public static final String TEAM_PREFIX = "team:%s";

    /**
     * 车队用户操作锁前缀
     */
    public static final String TEAM_USER_OPT_LOCK_PREFIX = "team:lock:opt:user:";

    /**
     * 车队用户订单操作锁前缀
     */
    public static final String TEAM_ORDER_OPT_LOCK_PREFIX = "team:order:lock:opt:";
    /**
     * 缓存在 redis 中的车队房间号列表集合
     */
    public static final String TEAM_ROOM_NUM_SET = "team:roomNum";
    /**
     * 缓存在 redis 中的车队实际位置数
     */
    public static final String TEAM_ACTUALLY_POSITION_PREFIX = "team:actuallyPosition:%s";
    /**
     * 缓存在车队 redis 中的游戏列表
     */
    public static final String TEAM_GAME_LIST_KEY = "team:game:list";
    /**
     * 缓存在车队 redis 中的游戏副本列表
     */
    public static final String TEAM_GAME_RAID_LIST_KEY = "team:game:%s:raid:list";
    /**
     * 缓存在车队redis中的游戏小区code对应大区和跨区关系列表
     **/
    public static final String TEAM_SMALL_ZONE_REF_ACROSS_ZONE_KEY = "team:game:%d:small:%d:big:across:zone";

    public static final String USER_GAME_ROLE_CERTS_KEY = "user:%s:game:%d";

    /**
     * 雷达头像
     */
    public static final String RADAR_PICTURE = "user:radar_picture";

    /**
     * 暴鸡头像
     */
    public static final String USER_BAOJI_AVATAR_KEY = "user:baoji_avatar";

    /**
     * 用户游戏分片 key 前缀(校验是否已经在车队中用)
     */
    public static final String TEAM_USER_GAME_PREFIX = "team:user:game";
    /**
     * 车队老板定时任务分片 key
     */
    public static final String TEAM_BOSS_JOB_PREFIX = "team:boss:job";
    /**
     * 车队游戏结果 key, 默认过期一天
     */
    public static final String TEAM_GAME_RESULT_PREFIX = "team:gameResult:%s";
    /**
     * 缓存在 redis 中的车队本地事务幂等标识 key
     */
    public static final String TEAM_LOCAL_TRANSACTION_IDEMPOTENCE = "team:%s:%s";
    /**
     * 车队发起的退款 MQ标识
     */
    public static final String TEAM_REFUND_MQ = "team:%s:%s";

    /**
     * 缓存在用户 redis 中的游戏副本列表
     */
    public static final String USER_GAME_RAID_LIST_KEY = "user:game:%d:raid:list";

    /**
     * 缓存在用户 redis 中的 游戏小区code对应大区和跨区
     **/
    public static final String USER_SMALL_ZONE_REF_ACROSS_ZONE_KEY = "user:game:%d:small:%d:big:across:zone";

    /**
     * 缓存在用户 redis 中的 游戏认证副本对应的其所属副本key
     **/
    public static final String USER_CERT_RAID_REF_TYPE_RAID_KEY = "user:game:%d:cert_raid:%d:raid";

    public static final String USER_CREATE_ROLE_THRESHOLD_KEY = "u:%s:g:c:r:threshold";
    /**
     * 缓存在用户 redis 中的 小区名称key
     */
    public static final String USER_SMALL_ZONE_INFO_KEY = "rpg:game:%d:small:%d:info";
    /**
     * 车队队员信息 key 前缀
     */
    public static final String TEAM_MEMBER_PREFIX = "team:member:%s";
    /**
     * 游戏小区code对应大区和跨区
     **/
    public static final String SMALL_ZONE_REF_ACROSS_ZONE_KEY = "rpg:game:%d:small:%d:big:across:zone";
    /**
     * 游戏跨区code对应大区和小区
     **/
    public static final String ACROSS_ZONE_REF_BIG_SMALL_ZONE_KEY = "rpg:game:%d:across:%d:big:small:zone";
    /**
     * 游戏跨区code对应小区
     **/
    public static final String ACROSS_ZONE_REF_SMALL_ZONE_KEY = "rpg:game:%d:across:%d:small:zone";
    /**
     * 游戏大小区关联key
     **/
    public static final String BIG_ZONE_AND_SMALL_ZONE_KEY = "rpg:game:%d:big:small:zone";
    /**
     * 游戏职业列表key
     **/
    public static final String GAME_CAREER_LIST_KEY = "rpg:game:%d:awakening:career";
    /**
     * 游戏列表key
     **/
    public static final String GAME_LIST_KEY = "rpg:game:list";
    /**
     * 游戏副本key
     **/
    public static final String GAME_RAID_KEY = "rpg:game:%d:raid";
    /**
     * 游戏认证副本key
     **/
    public static final String GAME_CERT_RAID_KEY = "rpg:game:%d:cert_raid";
    /**
     * 游戏认证副本对应的其所属副本key
     **/
    public static final String CERT_RAID_REF_TYPE_RAID_KEY = "rpg:game:%d:cert_raid:%d:raid";
    public static final String GAME_ACROSS_ZONE_KEY = "rpg:game:%d:across:zone";
    /**
     * 跨区key
     */
    public static final String ACROSS_ZONE_INFO_KEY = "rpg:game:%d:across:%d:info";
    /**
     * 大区key
     */
    public static final String BIG_ZONE_INFO_KEY = "rpg:game:%d:big:%d:info";
    /**
     * 小区key
     */
    public static final String SMALL_ZONE_INFO_KEY = "rpg:game:%d:small:%d:info";

    /**
     * 小程序需要认证的接口路径
     */
    public static final String NEED_AUTH_URLS = "esports:auth:urls";
    /**
     * 客户端信息
     */
    public static final String CLIENT_DETAIL = "esports:auth:client_detail";

    /**
     * 限流计数器
     */
    public static final String RATELIMITE = "esports:ratelimite:";

    /**
     * 限流黑名单IP
     */
    public static final String RATELIMITE_BLACKLIST = RATELIMITE + "blacklist";

    /**
     * uid与access token的关联
     */
    public static final String UID_ACCESS_TOKEN = "user:";
    public static final String TOKEN_REFRESH_EXPIRE = "token:expire:refresh";

    public static final String ACCESS = "access:";
    public static final String AUTH_TO_ACCESS = "auth_to_access:";
    public static final String AUTH = "auth:";
    public static final String REFRESH_AUTH = "refresh_auth:";
    /**
     * access token 和 refresh token 的关联
     */
    public static final String ACCESS_TO_REFRESH = "access_to_refresh:";
    public static final String REFRESH = "refresh:";
    public static final String REFRESH_TO_ACCESS = "refresh_to_access:";
    public static final String CLIENT_ID_TO_ACCESS = "client_id_to_access:";
    public static final String UNAME_TO_ACCESS = "uname_to_access:";

    public static final String BAOJI_LEVEL_RATE_KEY = "config:baoji:level:rate";

    public static final String NOTIFY_GAMETEAM_RETRY = "retry:order:notify2game:";
    public static final String PAY4NOTIFY_RETRY = "retry:order:pay4notify:";
    public static final String REFUND4NOTIFY_RETRY = "retry:order:refund4notify:";
    public static final String GET_UID_RETRY = "retry:auth:uid:";
    public static final String GET_UERINFO_RETRY = "retry:auth:userInfo:";

    /**
     * 退款记录 幂等
     */
    public static final String REFUND_HISTORY = "history:order:refund:";
    /**
     * 车队服务发起退款记录 幂等
     */
    public static final String REFUND_HISTORY_FROM_TEAM = "history:order:refund_from_team:";

    /**
     * PVP免费车队结束消息 幂等
     */
    public static final String UPDATE_ORDER_HISTORY_FROM_TEAM_END = "history:order:update_from_team_end:";

    /**
     * 好友完成免费车队订单消息 幂等
     */
    public static final String FRIEND_FINISH_GAMETEAM_ORDER = "history:order:friend_finish_gameteam_order:";

    /**
     * 好友完成免费车队奖励 幂等
     */
    public static final String FRIEND_FINISH_GAMETEAM_REWARD = "history:order:friend_finish_gameteam_reward:";

    /**
     * 更新订单记录 幂等
     */
    public static final String UPDATE_ORDER_HISTORY = "history:order:update:";
    /**
     * 品牌推广消息 幂等
     */
    public static final String BRANDPROMOTION_HISTORY = "history:user:brandpromotion:";
    /**
     * 更新订单记录完成标识
     */
    public static final String UPDATED_ORDER = "order:updated:";

    /**
     * 分布式锁 key
     */
    public static final String REDIS_DISTLOCK_PREFIX = "distlock:%s";
    /**
     * 创建暴鸡订单放重复幂等可以
     **/
    public static final String CREATE_BAOJI_ORDER_LOCK_KEY = "order:baoji:create:game:%d:teamSequence:%s";

    /***
     * 创建暴鸡币支付订单加锁
     * eg:create:gcoin:payment:orderType:outTradeNo
     */
    public static final String CREATE_GCOIN_PAYMENT_LOCK_KEY = "create:gcoin:payment:%s:%s";

    /***
     * 创建暴鸡币退款订单加锁
     * eg:create:gcoin:refund:orderType:outRefundNo
     */
    public static final String CREATE_GCOIN_REFUND_LOCK_KEY = "create:gcoin:refund:%s:%s";

    /**
     * 暴鸡币充值订单重复幂等
     **/
    public static final String GCOIN_RECHARGE_LOCK_KEY = "gcoin:recharge:lock:%s";

    /**
     * 暴鸡币支付订单重复幂等
     **/
    public static final String GCOIN_PAYMENT_LOCK_KEY = "gcoin:payment:lock:%s";
    /**
     * 暴鸡币退款订单重复幂等
     **/
    public static final String GCOIN_REFUND_LOCK_KEY = "gcoin:refund:lock:%s";

    /**
     * 暴鸡币充值订单
     * eg:gcoin:recharge:userId:orderId
     **/
    public static final String GCOIN_RECHARGE_KEY = "gcoin:recharge:%s:%s";

    /**
     * 暴鸡币支付订单
     * eg:gcoin:payment:userId:orderId
     **/
    public static final String GCOIN_PAYMENT_KEY_WITH_USER_ID_ORDER_ID = "gcoin:payment:%s:%s";

    /***
     * 暴鸡币支付订单
     * gcoin:payment:orderId
     */
    public static final String GCOIN_PAYMENT_KEY_WITH_ORDER_ID = "gcoin:payment:%s";

    /**
     * 暴鸡币退款订单
     * eg:gcoin:refund:userId:orderId
     **/
    public static final String GCOIN_REFUND_KEY = "gcoin:refund:%s:%s";

    /***
     * 暴鸡币打赏订单状态
     * eg:gcoin:consume:state:orderId
     */
    public static final String GCOIN_CONSUME_ORDER_STATE_KEY = "gcoin:consume:state:%s";

    /**
     * 第三方支付-支付宝-支付订单 eg:trade:alipay:pay:orderType:outTradeNo
     **/
    public static final String EXTERNAL_ALIPAY_PAY_KEY = "trade:alipay:pay:%s:%s";

    /**
     * 第三方支付-支付宝-退款订单 eg:trade:alipay:refund:outRefundNo
     */
    public static final String EXTERNAL_ALIPAY_REFUND_KEY = "trade:alipay:refund:%s";

    /**
     * 第三方支付-微信/QQ-支付订单 eg:trade:tenpay:pay:orderType:outTradeNo
     */
    public static final String EXTERNAL_TENPAY_PAY_KEY = "trade:tenpay:pay:%s:%s";

    /**
     * 第三方支付--微信/QQ-支付消费标记 eg:trade:tenpay:consumer:orderType:outTradeNo
     */
    public static final String EXTERNAL_TENPAY_NOTIFY_CONSUMER_KEY = "trade:tenpay:consumer:%s:%s";

    /**
     * 第三方支付-微信/QQ-退款订单 eg:trade:tenpay:refund:outRefundNo
     */
    public static final String EXTERNAL_TENPAY_REFUND_KEY = "trade:tenpay:refund:%s";

    /**
     * 第三方支付-微信/QQ-退款回调标记 eg:trade:tenpay:refund:notify:%s
     */
    public static final String EXTERNAL_TENPAY_REFUND_NOTIFY_KEY = "trade:tenpay:refund:notify:%s";

    /**
     * 内部支付-暴鸡币支付
     **/
    public static final String EXTERNAL_GCOIN_PAY_KEY = "trade:gcoin:pay:%s";

    /**
     * 内部支付-暴鸡币支付回调
     * trade:gcoin:notify:gcoinPaymentOrderId
     **/
    public static final String EXTERNAL_GCOIN_NOTIFY_KEY = "trade:gcoin:notify:%s";

    ///**
    // * 第三方支付-微信/QQ-支付回调标记
    // * trade:tenpay:notify:pay:orderId
    // */
    //public static final String EXTERNAL_TENPAY_PAY_NOTIFY_KEY = "trade:mq::lock:tenpay:notify:pay:%s:%s";
    /**
     * 支付配置信息Key eg:payment:pay_setting:channelTag
     */
    public static final String PAY_SETTING_KEY_WITH_CHANNEL_TAG = "payment:pay_setting:%s";

    /**
     * 支付渠道配置信息Key eg:payment:pay_channel:channelId
     */
    public static final String PAY_CHANNEL_KEY_WITH_CHANNEL_ID = "payment:pay_channel:%s";

    /***
     * 第三方支付-微信/QQ回调消息消费锁
     * trade:mq:lock:tenpay:pay:notify:orderId
     */
    public static final String EXTERNAL_MQ_LOCK_TENPAY_PAY_NOTIFY = "trade:mq:lock:tenpay:pay:notify:%s";

    /***
     * 第三方支付-微信/QQ回调消息消费锁
     * trade:mq:lock:tenpay:refund:notify:orderId
     */
    public static final String EXTERNAL_MQ_LOCK_TENPAY_REFUND_NOTIFY = "trade:mq:lock:tenpay:refund:notify:%s";

    /***
     * 第三方支付-支付宝回调消息消费锁
     */
    public static final String EXTERNAL_MQ_LOCK_ALIPAY_NOTIFY = "trade:mq:lock:alipay:notify:%s";

    /***
     * 第三方支付-支付宝退款消费锁
     */
    public static final String EXTERNAL_MQ_LOCK_ALIPAY_REFUND = "trade:mq:lock:alipay:refund:%s";

    /***
     * 第三方支付-微信、QQ支付 退款消费锁
     */
    public static final String EXTERNAL_MQ_LOCK_TENPAY_REFUND = "trade:mq:lock:tenpay:refund:%s";

    /***
     * 第三方支付配置信息
     * eg:payment:pay_setting:%s
     */
    public static final String PAY_SETTING_KEY_PREFIX = "payment:pay_setting:%s";
    /**
     * 支付服务扣减比率配置前缀
     */
    public static final String PAYMENT_DEDUCT_RATIO_PREFIX = "payment:deduct_ratio:%s";

    /**
     * 设备每小时充值次数
     **/
    public static final String DEVICE_HOUR_RECHARGE_COUNT = "risk:device:%s:hour:%s:recharge:count";
    /**
     * 设备每天充值次数
     **/
    public static final String DEVICE_DAY_RECHARGE_COUNT = "risk:device:%s:day:%s:recharge:count";
    /**
     * 用户每小时充值次数
     **/
    public static final String USER_HOUR_RECHARGE_COUNT = "risk:uid:%s:hour:%s:recharge:count";
    /**
     * 用户每天充值次数
     **/
    public static final String USER_DAY_RECHARGE_COUNT = "risk:uid:%s:day:%s:recharge:count";

    /**
     * 免费车队类型
     **/
    public static final String FREE_TEAM_TYPE_ID = "freeteamtype:id:%s";

    /**
     * 免费车队类型--车队服务的备份
     **/
    public static final String TEAM_FREE_TEAM_TYPE_BACKUP = "team:freeteamtype:id:%s";

    /**
     * 免费车队类型段位
     **/
    public static final String FREE_TEAM_TYPE_DAN = "freeteamtype:id:%s:dan:%s";

    /**
     * pvp 免费车队 老板匹配惩罚池
     */
    public static final String PVP_FREE_TEAM_MATCHING_PUNISHMENT = "pvp:freeteam:matching:punishment";

    /**
     * 每日渠道UV
     */
    public static final String CHANNEL_USER_VIEW = "channel:user:view:%s:%s";

    /**
     * 渠道名称
     */
    public static final String CHANNEL_NAME = "channel:name";

    /**
     * 每日渠道PV
     */
    public static final String CHANNEL_PAGE_VIEW = "channel:page:view:%s:%s";

    /**
     * pvp 免费车队 老板匹配上车后退出池
     */
    public static final String PVP_FREE_TEAM_MATCHING_QUIT = "pvp:freeteam:matching:quit";
    /**
     * pvp 免费车队 老板匹配上车后被踢
     */
    public static final String PVP_FREE_TEAM_MATCHING_KICKOUT = "pvp:freeteam:matching:kickout";

    /**
     * pvp 免费车队 老板匹配队列
     */
    public static final String PVP_FREE_TEAM_MATCHING_BOSS_QUEUE = "pvp:freeteam:matching:boss:";

    /**
     * 用于存放已经派发订单的暴鸡列表 bitmap
     */
    public static final String PVP_FREE_TEAM_BAOJI_FOR_DISTRIBUTED_BITMAP = "pvp:freeteam:baojis:distributed:bitmap";

    /**
     * 暴鸡当天派单计数器
     */
    public static final String PVP_FREE_TEAM_BAOJI_DISTRIBUTION_COUNTER = "pvp:freeteam:baojis:distribution:counter";

    /**
     * PVP免费车队老板订单
     */
    public static final String PVP_FREE_TEAM_BOSS_ORDERS = "pvp:freeteam:orders:boss";

    /**
     * 暴鸡当前可匹配的订单列表
     * <p>
     * {@link com.kaihei.commons.cache.api.spi.common.CacheManager#lrange(String, int, int, Class)}
     * <p>
     * key + 暴鸡uid  -> List 老板uid 类型 String.class
     */
    public static final String PVP_FREE_TEAM_BAOJI_ORDERS = "pvp:freeteam:orders:baoji:";

    /**
     * PVP 免费车队 等待匹配 老板队列
     */
    public static final String PVP_FREE_TEAM_WAIT_MATCHING_BOSS_QUEUE = "pvp:freeteam:waitmatching:boss";

    /**
     * 当前节点分发订单记录
     */
    public static final String PVP_FREE_LASTORDERS_PRE = "pvp:freeteam:lastorders:";
    /**
     * 老板订单详情
     */
    public static final String PVP_FREE_TEAM_ORDER_DETAIL_PREFIX = "pvp:freeteam:order:detail:boss";

    /**
     * pvp 免费车队 老板点击匹配时的记录
     */
    public static final String PVP_FREE_TEAM_MATCHING_BOSS_HISTORY = "pvp:freeteam:matching:history:boss:";

    /**
     * 公会暴鸡队列
     */
    public static final String UNION_BAOJIS = "union_baojis";

    /**
     * pvp 免费车队 车队匹配队列
     */
    public static final String PVP_FREE_TEAM_MATCHING_TEAM_QUEUE = "v2:pvp:freeteam:matching:team:";

    /**
     * 订单收益的抽成比例：如果支付系统那边禁用抽成后，默认为 0
     */
    public static final String ORDER_PROPORTION = "order:proportion";

    /**
     * 专属订单:订单信息
     */
    public static final String EXCLUSIVE_ORDER = "order:exclusive";

    /**
     * 专属订单:操作历史
     */
    public static final String EXCLUSIVE_ORDER_OPERATION_HISTORY =
            EXCLUSIVE_ORDER + ":operation:history";

    /**
     * 专属订单:玩家订单关系
     */
    public static final String EXCLUSIVE_ORDER_RELATION = "order:exclusive:relation";

    /**
     * 专属订单:超时未接单
     */
    public static final String EXCLUSIVE_ORDER_TIME_OUT_NO_AGREE = "order:exclusive:timeout:noagree";

    /**
     * 免费专属单游戏配置--订单服务备份
     */
    public static final String TRADE_EXCLUSIVE_ORDER_CONFIG_BACKUP = "trade:exclusiveorderconfig:gameid:%s";

    /**
     * 付费专属单游戏配置--订单服务备份
     */
    public static final String TRADE_PVP_EXCLUSIVE_ORDER_CONFIG_BACKUP = "trade:exclusive:config:pvp";

    /**
     * 暴鸡接单范围
     */
    public static final String BAOJI_DAN_RANGE_GAME = "baojidanrange:game:%s";

    /**
     * 暴鸡接单范围
     */
    public static final String BAOJI_DAN_RANGE_BAOJILEVEL = "baojidanrange:game:%s:levelcode:%s";

    /**
     * 云账户订单-创建订单key
     */
    public static final String EXTERNAL_CLOUD_PAY_KEY = "thirdtrade:cloud:pay:%s";

    //重构常量

    /***
     * appSetting key
     */
    public static final String EXTERNAL_APP_SETTING_KEY = "payment:app_setting:%s";

    /***
     * 支付渠道配置
     * eg:external:pay:channel:appId:channelTag
     */
    public static final String EXTERNAL_PAY_CHANNEL_KEY = "external:pay:channel:%s:%s";

    /**
     * 保存数据到Redis的时间
     */
    public static final int SAVE_DATA_TIME = 30 * 60;

    /***
     * 第三方支付订单信息,业务订单类型，业务订单号
     * eg:external:payment:orderType:outTradeNo
     */
    public static final String EXTERNAL_PAYMENT_ORDER_TYPE_AND_OUT_TRADE_NO_KEY = "external:payment:%s:%s";

    /***
     * 第三方支付订单信息，ORDER_ID
     * eg:external:payment:orderId;
     */
    public static final String EXTERNAL_PAYMENT_ORDER_WITH_ORDER_ID_KEY = "external:payment:%s";

    /***
     * 第三方退款订单信息,业务退款订单号
     * eg:external:refund:order:outRefundNo
     */
    public static final String EXTERNAL_REFUND_OUT_REFUND_NO_KEY = "external:refund:outRefundNo:%s";

    /***
     * 第三方退款订单信息,orderId
     * eg:external:refund:order:key:orderId
     */
    public static final String EXTERNAL_REFUND_ORDER_WITH_ORDER_ID_KEY = "external:refund:orderId:%s";

    /***
     * 第三方退款订单消费锁
     * eg:external:refund:consumer:lock:orderId
     */
    public static final String EXTERNAL_REFUND_CONSUMER_LOCK = "external:refund:consumer:lock:%s";

    /**
     * 提现配置
     */
    public static final String PAYMENT_WITHDRAW_CONFIG = "payment:withdraw_config";

    /**
     * 用户免费车队次数
     */
    public static final String FREE_TEAM_CHANCES = "freeteamchances:uid:%s";


    /**
     * 免费车队首页暴鸡打赏榜
     */
    public static final String FREE_TEAM_BAOJI_RNAK = "freeteam:bj_rank";


    /**
     * 排行榜获取打赏信息
     */
    public static final String USER_REWARD_INCOME = "user:reward:income";


    /**
     * 今日免费单完成数
     */
    public static final String USER_FREE_COMPLETE_ORDER_TODAY = "user:free:complete:order:today";


    /**
     * 今日订单总收益
     */
    public static final String USER_ORDER_INCOME_TODAY = "user:order:income:today";

    /**
     * 今日接收打赏收益 user:reword:in:{yyyy-YY-dd}
     */
    public static final String USER_REWORD_IN = "user:reword:in:";

    /**
     * 今日接收打赏收益 user:reword:in:{yyyy-YY-dd}
     */
    public static final String USER_REWORD_STARLIGHT_IN = "user:reword_sl:in:";

    /**
     * 今日打赏金额 user:reword:out:{yyyy-YY-dd}
     */
    public static final String USER_REWORD_OUT = "user:reword:out:";

    /**
     * 今日收费单完成数
     */
    public static final String USER_PAID_COMPLETE_ORDER_TODAY = "user:paid:complete:order:today";

    /**
     * 排行榜获取打赏信息
     */
    public static final String USER_REWARD_PAY = "user:reward:pay";


    /**
     * 免费车队首页老板打赏榜
     */
    public static final String FREE_TEAM_PLAYER_RNAK = "freeteam:player_rank";

    /**
     * 防止用户重复提交
     */
    public static final String PREVENT_REPEAT_OPERATION = "prevent_repeat_operation:%s:%s";

    /***
     * 前端礼物列表信息
     */
    public static final String FRONT_GIFT_LIST = "front:gift:list";

    /**
     * 上架的礼物信息
     */
    public static final String FRONT_GIFT = "front:gift:%s";

    /**
     * 暴鸡评价综合评分:gameCode
     */
    public static final String EVALUATE_BAOJI_SCORE = "baoji:score:";

    /**
     * 暴鸡游戏胜率:gameCode
     */
    public static final String EVALUATE_BAOJI_WINNING_RATE = "baoji:winrate:";

    /**
     * 缓存新增的评论
     */
    public static final String EVALUATE_CACHE = "evaluate:cache:";

    /**
     * 用户主页形象标签显示数量
     */
    public static final String EVALUATE_USER_HOME_TAG_SHOW_COUNT = "tag:show:count";

    /**
     * 车队开车扣匹配券 幂等
     */
    public static final String REDUCE_FREE_COUPONS_BATCH = "reduce:free:coupons:batch:";

    /**
     * 车队返回匹配券 幂等
     */
    public static final String RETURN_FREE_COUPONS = "return:free:coupons:";
    /**
     * 更新暴击值余额-消费锁Key
     */
    public static final String WITHDRAW_BALANCE_NOTIFY_LOCK_KEY = "capay:notify:withdraw:lock:%s";

    /**
     * 操作暴鸡币账户余额锁
     */
    public static final String PAYMENT_GCOIN_BALANCE_LOCK_KEY = "payment:gcoin:balance:lock:";

    /**
     * 操作暴击值账户余额锁
     */
    public static final String PAYMENT_STARLIGHT_BALANCE_LOCK_KEY = "payment:starlight:balance:lock:";

    /**
     * 启动任务用户奖励记录缓存
     */
    public static final String EXEMPTION_RECORD = "exemption:record:";

    /**
     * 风控词典key
     */
    public static final String RISK_DICT_GROUP_KEY = "risk:dict:group:";

    /**
     * 预打赏单号
     */
    public static final String REWARD_PRE_ID_KEY = "reward:pre:";

    public static final String REWARD_CONSUMER_STATISTICS_LOCK_KEY = "reward:consumer:statistics:lock:";

    public static final String EVALUATE_CONSUMER_STATISTICS_LOCK_KEY = "evaluate:consumer:lock:";

    public static final String COMPLAIN_CONSUMER_STATISTICS_LOCK_KEY = "complain:consumer:lock:";

    public static final String FINISH_ORDER_CONSUMER_STATISTICS_LOCK_KEY = "order:consumer:lock:";


    /**
     * 助力活动--奖励配置
     */
    public static final String ASSIST_ACTIVITY_REWARD_CONFIG_KEY = "activity:config:assist:reward";

    /**
     * 助力活动--弹窗配置
     */
    public static final String ASSIST_ACTIVITY_ALERT_CONFIG_KEY = "activity:config:assist:alert";

    /**
     * 助力活动--奖励数已用数
     */
    public static final String ASSIST_ACTIVITY_REWARD_STOCK_COUNT = "activity:reward:stock:count:";

    /**
     * 首页活动弹窗
     */
    public static final String ACTIVITY_INDEX_ALERT = "activity:index:alert";

    /**
     * 用户在房间标识Hash前缀
     */
    public static final String USER_ROOM_FLAG_HASH_PRE = "userroomfalg";
    /**
     * 房间用户前缀
     */
    public static final String ROOM_USERS_PRE = "roomusers:";

    /**
     * 房间锁前缀
     */
    public static final String ROOM_OPT_LOCK_PRE = "lock:room:opt:";

    /**
     * 用户操作分布式锁前缀
     */
    public static final String USER_OPT_LOCK_PRE = "lock:user:opt:";


    /**
     * 用户操作分布式锁前缀
     */
    public static final String SLUG_OPT_LOCK_PRE = "lock:slug:opt:";

    /**
     * 车队准备分布式锁前缀
     */
    public static final String SLUG_PREPARE_LOCK_PRE = "lock:prepare:opt:";

    /**
     * 投诉分布式锁前缀
     */
    public static final String SLUG_COMPLAINT_LOCK_PRE = "lock:complaint:opt:";

    /**
     * 评价分布式锁前缀
     */
    public static final String SLUG_EVALUATE_LOCK_PRE = "lock:evaluate:opt:";

    /**
     * 渠道优惠券领取布式锁前缀
     */
    public static final String CHANNEL_RECEIVE_LOCK_PRE = "lock:channel:receive:opt:";

    /**
     * 车队开车分布式锁前缀
     */
    public static final String SLUG_START_LOCK_PRE = "lock:start:opt:";

    /**
     * 用户操作分布式锁前缀
     */
    public static final String USER_ORDER_OPT_LOCK_PRE = "lock:user:order:opt:";

    /**
     * 用户排行版锁前缀
     */
    public static final String USER_RANK_LOCK_PRE = "lock:user:rank:opt:";

    /**
     * 车队操作锁
     */
    public static final String TEAM_OPT_LOCK_PRE = "lock:team:opt:";


    /**
     * 车队位置锁
     */
    public static final String TEAM_BAN_LOCK_PRE = "lock:team:ban:";
    /**
     * 暴鸡房间分片前缀
     */
    public static final String BAOJI_ROOM_KEY_HASH_PRE = "room";

    /**
     * 车队用户成员组前缀
     */
    public static final String CUSTOMER_TEAM_MEMBER_PRE = "customer:team:member:%s_%s";

    /**
     * 风控-设备每日登陆赠送次数上限配置
     */
    public static final String RISKRATING_FREE_CHANCE_LIMIT = "risk:chance_day_limit:%s";


    ////////////付费车队key

    public static final String RANDORI_PREMADE_CREATE_LOCK = "randori:premade:create:lock:";
    /**
     * 踢出车队时间限制
     */
    public static final String RANDORI_PREMADE_KICK_TIMEOUT_KEY = "randori:premade:kick:timeout:";

    /**
     * 车队成员
     */
    public static final String RANDORI_PREMADE_MEMBERS_KEY = "randori:premade:members:";

    /**
     * V5版进行中车队
     */
    public static final String RANDORI_PREMADE_V5_RUNNING_LIST = "randori:premade:v5:running:list:";

    /**
     * 进行中车队
     */
    public static final String RANDORI_PREMADE_RUNNING_LIST = "randori:premade:running:list:";

    /**
     * 车队详情
     */
    public static final String RANDORI_PREMADE_DETAIL_KEY = "randori:premade:detail:";


    /**
     * 用户车队列表刷新锁
     */
    public static final String RANDORI_REFRESH_INTERVAL_USER = "randori:refresh:interval:user:";


    /************拍卖相关*************/

    /**
     * 进行中的拍卖
     * auction:active:{auction_no}
     */
    public static final String AUCTION_ACTIVE_P = "auction:active:";

    /**
     * 拍卖出价次数计数器
     * auction:bid:counter:{auction_no}
     */
    public static final String AUCTION_BID_COUNTER_P = "auction:bid:counter:";

    /**
     * 拍卖出价排行榜
     * auction:bid:ranking:{auction_no}
     */
    public static final String AUCTION_BID_RANKING_P = "auction:bid:ranking:";

    /**
     * 拍卖出价锁
     * auction:bid:lock:{auction_no}:{uid}
     */
    public static final String AUCTION_BID_LOCK_P = "auction:bid:lock:";

    /**
     * 拍卖状态锁
     * auction:status:lock:{auction_no}
     */
    public static final String AUCTION_STATUS_LOCK_P = "auction:status:lock:";

    /**
     * 直播房间缓存
     * streaming:room
     */
    public static final String STREAMING_ROOM = "streaming:room";

    /**
     * 拍卖创建锁
     * auction:create:lock:{uid}
     */
    public static final String AUCTION_CREATE_LOCK_P = "auction:create:lock:";

    /**
     * 拍卖刷票锁
     * auction:ticket:create:lock:{uid}
     */
    public static final String AUCTION_TICKET_CREATE_LOCK_P = "auction:ticket:create:lock:";

    /**
     * 房间变更锁
     * streaming:room:lock:{room_id}
     */
    public static final String STREAMING_ROOM_LOCK_P = "streaming:room:lock:";

    /**
     * 后管房间变更锁
     * streaming:room:pos:lock:{room_id}
     */
    public static final String STREAMING_ROOM_OPS_LOCK_P = "streaming:room:pos:lock:";

    /**
     * 后管房间新增锁
     * streaming:room:create:lock
     */
    public static final String STREAMING_ROOM_CREATE_LOCK_P = "streaming:room:create:lock";

    /**
     * 房间号计数器
     * streaming:room:no
     */
    public static final String STREAMING_ROOM_NO = "streaming:room:no";

    /**
     * 主播心跳检测
     * streaming:room:heartbeat:streamer:{roomId}:{uid}
     */
    public static final String STREAMING_ROOM_HEARTBEAT_STREAMER_T = "streaming:room:heartbeat:streamer:%s:%s";
    /**
     * 主持人心跳检测
     * streaming:room:heartbeat:presenter:{roomId}:{uid}
     */
    public static final String STREAMING_ROOM_HEARTBEAT_PRESENTER_T = "streaming:room:heartbeat:presenter:%s:%s";

    /************拍卖相关*************/


}
