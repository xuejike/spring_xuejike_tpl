package vip.xuejike.ktpl.common;

/**
 * @author xuejike
 */
public class ApiResult<T>{
    public int status;
    public String message;
    public T data;

    public ApiResult() {

    }

    public ApiResult(int status) {
        this.status = status;
    }

    public ApiResult(T data) {
        this.status = 200;
        this.data = data;
    }

    public ApiResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ApiResult<String> success(String s) {
        return new ApiResult<String>(s);
    }

    public static ApiResult<String> error(String verify) {
        return new ApiResult<>(300,verify);
    }
}
