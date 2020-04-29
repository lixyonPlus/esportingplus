package com.panda.esportingplus.common.enums;

public enum  OrderTypeEnum {

  GAME_ORDER (1, "游戏订单类型"),
  CERTIFIED_ORDER (2, "暴鸡认证订单类型"),
  RECHARGE_ORDER      (3, "充值订单类型"),
  BONUS_ORDER (4, "奖励订单类型"),
  ILLEGAL_CHARGE(5, "违规扣款类型"),
  BALANCE_WALFARE_ORDER   (6, "福利金类型"),
  ACTIVITY_BOUNTY  (7, "抽奖奖金"),
  PREMADE_ORDER   (8, "车队订单"),
  FEED_ORDER   (9, "发现订单"),
  SYS_RECHARGE_ORDER   (10, "系统充值订单类型"),
  TRANSFER_ORDER (11, "提现订单类型"),
  SYS_DEDUCT_ORDER(12, "系统扣款订单类型"),
  STUDIO_TRANSFER_ORDER (13, "工作室提现订单类型"),
  WEEKLY_LUCKY_ACTIVITY_ORDER(14, "每周好运礼包订单类型"),
  SERVICE_ORDER(16, "技能订单类型"),
  RPG_CERTIFIED_ORDER(18, "RPG保证金认证订单"),
  DNF_MINIPROGRAM_ORDER(17, "DNF小程序订单"),
  GCOIN_RECHARGE_ORDER(19, "暴鸡币充值订单类型"),
  STARLIGHT_CONVERT_ORDER(20, "暴鸡值兑换订单类型");

  private  int code;

  private  String desc;

  OrderTypeEnum(int code, String desc) {
    this.code = code;
    this.desc = desc;

  }




  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }


  public static String getDescByCode(int code){
     for(OrderTypeEnum types: values()){
         if(types.code == code){
           return types.getDesc();
         }
     }
     return "";
  }
}
