package com.hospital.entity.vo;

import com.hospital.entity.po.Doctor;
import lombok.Data;

import java.util.List;

/**
 * 医生列表 返回对象
 *
 * @author: ShanZhu
 * @date: 2023-11-15
 */
@Data
public class DoctorListVo {

    /**
     * 医生列表
     */
    private List<Doctor> doctors;
}

  