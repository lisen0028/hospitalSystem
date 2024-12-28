package com.hospital.entity.vo.user;

import com.hospital.entity.po.Arrange;
import lombok.Data;

/**
 * @Author lisiwen
 * @Date 2024/12/24 14:51
 * @Description:
 */
@Data
public class ArrangeDoctorVo extends Arrange {
    /**
     * 医生姓名
     */
    private String dName;
    /**
     * 医生科室
     */
    private String dSection;
}
