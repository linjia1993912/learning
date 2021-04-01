package learning.basis.aop_log;

/**
 * @author LinJia
 * @description: TODO
 * @date 2021/4/1
 */
public class RequestInfo {
    private String ip;
    private String url;
    private String classMethod;
    private Object requestParams;
    private Object result;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClassMethod() {
        return classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }

    public Object getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(Object requestParams) {
        this.requestParams = requestParams;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "RequestInfo{" +
                "ip='" + ip + '\'' +
                ", url='" + url + '\'' +
                ", classMethod='" + classMethod + '\'' +
                ", requestParams=" + requestParams +
                ", result=" + result +
                '}';
    }
}