package com.yx.demo.mapper;

import com.yx.demo.model.entity.PlayRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlayRecordMapper {

    /**
     * 保存下单支付记录
     * @param playRecord
     * @return
     */
    int saveRecord(PlayRecord playRecord);

}