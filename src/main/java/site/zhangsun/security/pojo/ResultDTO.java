package site.zhangsun.security.pojo;

/**
 * <p> Function: </p>
 *
 * @author : zhangsunjiankun 2018/5/19 上午8:39
 */
public class ResultDTO<T> {

    private String message;

    private Integer code;

    private Boolean status;

    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
