package com.zsy.test.jm2;

public class CustomizeException extends Exception{
        
    /**
     * 自定义编码
     * 0 成功
     * -1 系统级错误
     * 1001-1999 初始级错误(如：字段为空)
     * 2001-2999 业务级错误(如：特殊账户不支持业务)
     */
    private String code;
    
    //自定义异常内容
    private String msg;
    
    public String getCode() {
        return code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public CustomizeException(){
        super();
    }
    
    public CustomizeException(String message){
        super(message);
        msg = message;
    }
    
    public CustomizeException(String code, String msg){
        super();
        this.code = code;
        this.msg = msg;
    }
}
