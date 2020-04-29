package com.panda.esportingplus.common.enums;


public enum FleetStatus {
  READY(0, "ready"),
  PAY(1, "pay"),
  RUNNING(2, "running"),
  FINISH(3, "finish"),
  DISBAND(4, "disband"),
  ;

  FleetStatus(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  private int code ;
  private String desc;

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

  public static String getDescByCode(int code) {
    for (FleetStatus fleetStatus : values()) {
      if (fleetStatus.getCode() == code) {
        return fleetStatus.desc;
      }
    }
    return "";
  }

  public static void main(String[] args) {
    System.out.println(getDescByCode(1));
  }
}
