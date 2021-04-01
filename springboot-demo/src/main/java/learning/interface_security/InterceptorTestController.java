package learning.interface_security;

import learning.vo.ServiceResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LinJia
 * @description: 拦截器测试
 * @date 2021/4/1
 */
@RestController
public class InterceptorTestController {

    @PostMapping("/authToken")
    public ServiceResult authToken() {
        return ServiceResult.success("通过了");
    }
}