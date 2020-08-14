package learning.controller;

import learning.annotation.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import learning.vo.ServiceResult;

/**
 * @Description:拦截器测试
 * @Author LinJia
 * @Date 2020/5/20
 **/
@RestController
public class AuthorizeInterceptorTest {

    /**
     * @return readinglist.vo.ServiceResult
     * @Description:测试验证token
     * @Author LinJia
     * @Date 2020/5/20 16:07
     * @Param []
     **/
    @Log
    @PostMapping("/authToken")
    public ServiceResult authToken() {
        return ServiceResult.success("通过了");
    }
}
