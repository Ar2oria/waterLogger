package edu.hrbust.waterLogger.service.impl;

import edu.hrbust.waterLogger.service.IWaterLoggerService;
import edu.hrbust.waterLogger.vo.WaterDataChgVO;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @describe:
 * @package: edu.hrbust.waterLogger.service.impl
 * @dateTime: 2019/5/16 20:05
 * @author: ar2oria
 */
@Slf4j
@Component
public class WaterLoggerServiceImpl implements IWaterLoggerService {
    @Override
    public void change(WaterDataChgVO waterDateChgVO) {
        //todo

        log.info(waterDateChgVO.getNotifyType());

    }
}
