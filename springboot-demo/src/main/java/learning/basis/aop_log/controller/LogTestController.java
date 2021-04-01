package learning.basis.aop_log.controller;

import learning.basis.aop_log.annotation.Log;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import learning.vo.ServiceResult;

/**
 * @Description:AOP记录日志测试
 * @Author LinJia
 * @Date 2020/5/20
 **/
@RestController
public class LogTestController {

    /**
     * @return readinglist.vo.ServiceResult
     * @Description:测试记录log
     * @Author LinJia
     * @Date 2020/5/20 16:07
     * @Param []
     **/
    @Log
    @PostMapping("/recordingLog/{username}")
    public ServiceResult recordingLog(@PathVariable String username, @RequestParam(value = "blogId") int blogId) {
        return ServiceResult.success("Log记录日志");
    }
}
