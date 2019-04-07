package cn.qinguu.common.pojo;

import java.io.Serializable;

public class TaoBaoResult implements Serializable {

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    //构建其他状态的taobaoresult对象
    public static TaoBaoResult build(Integer status, String msg, Object data) {
        return new TaoBaoResult(status, msg, data);
    }

    public static TaoBaoResult ok(Object data) {
        return new TaoBaoResult(data);
    }

    public static TaoBaoResult ok() {
        return new TaoBaoResult(null);
    }

    public static TaoBaoResult forbidden() {
        return new TaoBaoResult(403,"forbidden",null);
    }

    public TaoBaoResult() {

    }

    public static TaoBaoResult build(Integer status, String msg) {
        return new TaoBaoResult(status, msg, null);
    }

    public TaoBaoResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public TaoBaoResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}