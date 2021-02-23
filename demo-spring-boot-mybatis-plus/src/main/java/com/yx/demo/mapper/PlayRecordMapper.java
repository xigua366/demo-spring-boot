package com.yx.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yx.demo.model.entity.PlayRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlayRecordMapper extends BaseMapper<PlayRecord> {

    /**
     * 保存下单支付记录
     * @param playRecord
     * @return
     */
    int saveRecord(PlayRecord playRecord);

}