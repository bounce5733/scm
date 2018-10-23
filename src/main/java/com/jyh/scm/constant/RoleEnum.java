package com.jyh.scm.constant;

/**
 * 默认角色
 * 
 * @author jiangyonghua
 * @date 2018年10月12日 上午8:04:58
 */
public enum RoleEnum {

    admin("系统管理员"), busiOwner("业务负责人"), orderInspector("订单审核员"), financeInspector("财务审核员"), deliveryInspector(
            "发货审核员"), busiManager(
                    "业务经理"), warehouseAdmin("仓库管理员"), materialAdmin("资料维护员"), customOne("自定义角色一"), customTwo("自定义角色二");

    private String name;

    private RoleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
