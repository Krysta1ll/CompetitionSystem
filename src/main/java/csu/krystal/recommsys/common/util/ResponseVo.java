package csu.krystal.recommsys.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseVo<T> {

    private Integer status;

    private String msg;

    private T data;

    private ResponseVo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ResponseVo(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResponseVo<T> success(){
        return new ResponseVo<T>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMsg());
    }

    public static <T> ResponseVo<T> success(String msg){
        return new ResponseVo<T>(ResponseEnum.SUCCESS.getCode(), msg);
    }

    public static <T> ResponseVo<T> success(T data){
        return new ResponseVo<T>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMsg(), data);
    }

    public static <T> ResponseVo<T> success(String msg, T data){
        return new ResponseVo<T>(ResponseEnum.SUCCESS.getCode(), msg, data);
    }

    public static <T> ResponseVo<T> error(){
        return new ResponseVo<T>(ResponseEnum.ERROR.getCode(), ResponseEnum.ERROR.getMsg());
    }

    public static <T> ResponseVo<T> error(String msg){
        return new ResponseVo<T>(ResponseEnum.ERROR.getCode(), msg);
    }

    public static <T> ResponseVo<T> error(String msg, T data){
        return new ResponseVo<T>(ResponseEnum.ERROR.getCode(), msg, data);
    }
}
