package learning.vo;

/**
 * @ClassName:  ServiceResult1   
 * @Description:TODO 数据返回
 * @author: linjia
 * @date:   2019年3月8日 下午2:30:44   
 * @param <T>
 */
public class ServiceResult1<T> {
    public static final String SYSTEM_INTERNAL_ERROR = "系统内部错误";
    public static final String OPERATION_SUCCESS = "操作成功";
    public static final String QUERY_NON_DATA = "查询无数据或结果";

    private boolean isSuccess;
    private String message;
    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public static <V> ServiceResult1<V> successResult(V data) {
        ServiceResult1<V> result = new ServiceResult1<>();
        result.setData(data);
        result.setSuccess(true);
        result.setMessage(OPERATION_SUCCESS);
        return result;
    }

    public static <V> ServiceResult1<V> internalErrorResult() {
        ServiceResult1<V> result = new ServiceResult1<>();
        result.setSuccess(false);
        result.setMessage(SYSTEM_INTERNAL_ERROR);
        return result;
    }

    public static <V> ServiceResult1<V> customResult(boolean isSuccess, V data, String message) {
        ServiceResult1<V> result = new ServiceResult1<>();
        result.setData(data);
        result.setSuccess(isSuccess);
        result.setMessage(message);
        return result;
    }

    public static <V> ServiceResult1<V> nonDataResult() {
        ServiceResult1<V> result = new ServiceResult1<>();
        result.setSuccess(false);
        result.setMessage(QUERY_NON_DATA);
        return result;
    }
}
