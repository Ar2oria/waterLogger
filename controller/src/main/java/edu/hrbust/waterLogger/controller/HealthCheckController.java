package edu.hrbust.waterLogger.controller;

import edu.hrbust.common.ResultResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe:
 * @package: edu.hrbust.mapquery.controller
 * @dateTime: 2019/3/12 0:56
 * @author: ar2oria
 */
@RestController
public class HealthCheckController {

    @RequestMapping("/healthCheck")
    public ResultResponse<String> healthCheck() {
        return ResultResponse.success("ok");
    }

}
