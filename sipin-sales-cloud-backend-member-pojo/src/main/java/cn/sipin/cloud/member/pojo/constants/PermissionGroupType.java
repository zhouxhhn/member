/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package cn.sipin.cloud.member.pojo.constants;

public enum PermissionGroupType {

  ACTION(0, "控制器方法"),

  MODEL(1, "模型属性");

  private Integer typeCode;

  private String description;

  PermissionGroupType(Integer typeCode, String description) {
    this.typeCode = typeCode;
    this.description = description;
  }

  public Integer getTypeCode() {
    return typeCode;
  }

  public String getDescription() {
    return description;
  }

  public static PermissionGroupType contains(Integer typeCode) {
    if (typeCode == null) {
      return null;
    }
    for (PermissionGroupType permissionGroupType : PermissionGroupType.values()) {
      if (permissionGroupType.getTypeCode().equals(typeCode)) {
        return permissionGroupType;
      }
    }
    return null;
  }
}
