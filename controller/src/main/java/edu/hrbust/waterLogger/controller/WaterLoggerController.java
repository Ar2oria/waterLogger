package edu.hrbust.waterLogger.controller;

import edu.hrbust.common.ResultResponse;
import edu.hrbust.waterLogger.service.IWaterLoggerService;
import edu.hrbust.waterLogger.vo.WaterDataChgVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class WaterLoggerController {

    @Resource
    IWaterLoggerService waterLoggerService;

    @PostMapping("/water/Chg")
    public ResultResponse getDataChgMsg(@RequestBody WaterDataChgVO waterDataChgVO){
        waterLoggerService.change(waterDataChgVO);
        return ResultResponse.success("ok");
    }

}
