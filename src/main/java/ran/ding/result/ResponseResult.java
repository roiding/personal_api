package ran.ding.result;

import lombok.Data;

@Data
public class ResponseResult<T> {
    private int status;
    private T data;
    private String message;

    /**
     * 统一成功回复消息
     * @param data 返回数据
     * @return  ResponseResult
     * @param <T>   返回数据类型
     */
    public static <T> ResponseResult<T> success(T data) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setStatus(200);
        result.setData(data);
        result.setMessage("success");
        return result;
    }
    /**
     * 统一失败回复消息
     * @param message   失败消息
     * @return  ResponseResult
     */
    public static ResponseResult failure(String message) {
        ResponseResult result = new ResponseResult();
        result.setStatus(500);
        result.setMessage(message);
        return result;
    }
}
