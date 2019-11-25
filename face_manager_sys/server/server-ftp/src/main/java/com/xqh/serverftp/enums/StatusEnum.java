package com.xqh.serverftp.enums;

/**
 * @author xqh3622
 */
public enum StatusEnum {
    SUCCESS(200,"code","操作成功","result"),
    FAILED(404,"code","操作失败","result"),
    ERROR(500,"code","操作无效","result");
    private Integer code;
    private String codeName;
    private String result;
    private String resultName;

    StatusEnum(Integer code,String codeName,String result,String resultName){
        this.code=code;
        this.codeName=codeName;
        this.result=result;
        this.resultName=resultName;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultName() {
        return resultName;
    }

    public void setResultName(String resultName) {
        this.resultName = resultName;
    }
}
