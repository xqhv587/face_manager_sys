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

import java.time.LocalDateTime;
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
     * 创建日期
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    protected LocalDateTime createTime;
    /**
     * 更新日期
      */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime;


    public LocalDateTime getCreateTime() {
        return createTime;
    }


    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置插入的基本属性
     * */
    public void setCommonValue( ) {
        setCommonValues();
    }

    public void setCommonValues() {
        if (this.isNewRecord()) {
            this.setId(IdGen.snowflakeLongId());
            this.setNewRecord(true);
            this.createTime=LocalDateTime.now();
        }
        this.updateTime=LocalDateTime.now();

    }
    /**
     * 设置更新的基本属性
     * */
    public void setUpdateValue() {
        setUpdateValues();
    }

    public void setUpdateValues() {
        this.updateTime=LocalDateTime.now();
    }

}
