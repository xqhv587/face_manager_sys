package com.xqh.commoncore.persistence;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Entity基类
 *
 * @author tangyi
 * @date 2018-08-24 18:58
 */
@Data
@KeySequence("id")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class BaseEntity<T extends Model> extends Model<T> {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type= IdType.ID_WORKER)
    protected Long id;


    /**
     * 是否为新记录
     */
    @TableField(exist = false)
    protected boolean isNewRecord;

    public BaseEntity(Long id) {
        this();
        this.id = id;
    }

    @JsonSerialize(using=ToStringSerializer.class)
    public Long getId() {
        return id;
    }




    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        BaseEntity<?> that = (BaseEntity<?>) obj;
        return null != this.getId() && this.getId().equals(that.getId());
    }


    public boolean isNewRecord() {
        return this.isNewRecord|| (this.getId()==null);
    }

}

