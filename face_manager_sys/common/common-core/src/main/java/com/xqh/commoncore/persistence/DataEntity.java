/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xqh.commoncore.persistence;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.xqh.commoncore.constant.CommonConstant;
import com.xqh.commoncore.utils.IdGen;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Date;

/**
 * 数据Entity类
 *
 * @author xqh3622
 * @param <T>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class DataEntity<T extends Model> extends BaseEntity<T> {

    private static final long serialVersionUID = 1L;

    /**
     *  创建者
     */
//    @TableField(value = "creator", fill = FieldFill.INSERT)
//    protected String creator;

    /**
     * 创建日期
     */
//    @TableField(value = "create_time", fill = FieldFill.INSERT)
//    protected Date createTime;

    /**
     * 更新者
     */
//    @TableField(value = "updater", fill = FieldFill.INSERT_UPDATE)
//    protected String updater;

    /**
     * 更新日期
      */
//    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
//    protected Date updateTime;

    /**
     * 删除标记（Y：正常；N：删除；A：审核；）
     */
//    @TableField(value = "del_flag")
//    protected Integer delFlag = CommonConstant.DEL_FLAG_NORMAL;

    /**
     * 系统编号
     */
    //protected String applicationCode;






//    public Date getCreateTime() {
//        return createTime;
//    }
//
//
//    public Date getUpdateTime() {
//        return updateTime;
//    }


//
//    public DataEntity() {
//        super();
//        this.delFlag = CommonConstant.DEL_FLAG_NORMAL;
//    }
//
//    public DataEntity(long id) {
//        super(id);
//        this.delFlag = CommonConstant.DEL_FLAG_NORMAL;
//    }

    /**
     * 设置基本属性
     *
     * @param userCode        用户编码
     * @param applicationCode 系统编号
     */
//    public void setCommonValue(String userCode, String applicationCode) {
//        setCommonValue(userCode, applicationCode, "");
//    }

    /**
     * 设置基本属性
     *
     * @param userCode        用户编码
     * @param applicationCode 系统编号
     * @param tenantCode      租户编号
     */
//    public void setCommonValue(String userCode, String applicationCode, String tenantCode) {
//        Date currentDate = DateTime.now().withZone(DateTimeZone.forID(CommonConstant.TIMEZONE_SHANGHAI)).toDate();
//        if (this.isNewRecord()) {
//            this.setId(IdGen.snowflakeLongId());
//            this.setNewRecord(true);
//            this.creator = userCode;
//            this.createTime = currentDate;
//        }
//        this.updater = userCode;
//        this.updateTime = currentDate;
//        this.applicationCode = applicationCode;
//    }
    /**
     * 设置基本属性
     *
     * @param userCode        用户编码
     * @param applicationCode 系统编号
     */
//    public void setUpdateValue(String userCode, String applicationCode) {
//        setUpdateValue(userCode, applicationCode, "");
//    }
//
//    public void setUpdateValue(String userCode, String applicationCode, String tenantCode) {
//        Date currentDate = DateTime.now().withZone(DateTimeZone.forID(CommonConstant.TIMEZONE_SHANGHAI)).toDate();
//        this.updater = userCode;
//        this.updateTime = currentDate;
//        this.applicationCode = applicationCode;
//    }



    /**
     * 复写的方法
     * */

    /**
     * 设置插入的基本属性
     * */
    public void setCommonValue( String applicationCode) {
        setCommonValue( applicationCode, "");
    }

    public void setCommonValue( String applicationCode, String tenantCode) {
        Date currentDate = DateTime.now().withZone(DateTimeZone.forID(CommonConstant.TIMEZONE_SHANGHAI)).toDate();
        if (this.isNewRecord()) {
            this.setId(IdGen.snowflakeLongId());
            this.setNewRecord(true);
        }

    }
/**
 * 设置更新的基本属性
 * */
//    public void setUpdateValue(String userCode, String applicationCode) {
//        setUpdateValue(userCode, applicationCode, "");
//    }
//
//    public void setUpdateValue(String userCode, String applicationCode, String tenantCode) {
//        Date currentDate = DateTime.now().withZone(DateTimeZone.forID(CommonConstant.TIMEZONE_SHANGHAI)).toDate();
//    }

}
