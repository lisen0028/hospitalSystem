package com.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hospital.entity.po.Drug;
import org.apache.ibatis.annotations.Mapper;

/**
 * 药物 持久层（mapper）
 *
 * @author: ShanZhu
 * @date: 2023-11-17
 */
@Mapper
public interface DrugMapper extends BaseMapper<Drug> {

}
