package com.xqh.severshiro.entity.Model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.xqh.commoncore.persistence.DataEntity;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author gjf
 * @since 2019-04-08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("permission")
public class Permission extends DataEntity<Permission> {

private static final long serialVersionUID = 1L;

   //@TableId(value="id", type= IdType.INPUT)
        
    private Long id;

    private String title;

    private String icon;

    private String spread;

    private String href;

    private Long parentId;

    private Integer sort;

//    private LocalDateTime createTime;
//
//    private LocalDateTime updateTime;

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String ICON = "icon";
    public static final String SPREAD = "spread";
    public static final String HREF = "href";
    public static final String PARENT_ID = "parent_id";
    public static final String SORT = "sort";
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_TIME = "update_time";

    @Override
    protected Serializable pkVal() {
            return this.id;
        }

}