package com.xqh.commoncore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author yz3702
 * @since 2019/10/16
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExamRecordStatusEnum {

    /**
     *
     */
    STATUS_NOT_EXAM(0,"待考试"),
    STATUS_NEED_MAKEUP(30,"待补考"),
    STATUS_EXAM(50,"考试中"),
    STATUS_PASS(100,"通过"),
    STATUS_NOT_PASS(200,"未合格"),
    ;
    private Integer code;
    private String description;

    public static  String getDescByCode(Integer code){
        if (code == null){
            return  StringUtils.EMPTY;
        }
        for(ExamRecordStatusEnum anEnum : ExamRecordStatusEnum.values()){
            if(code.equals(anEnum.getCode())){
                return anEnum.getDescription();
            }
        }
        return  StringUtils.EMPTY;
    }

}
