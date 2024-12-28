package com.hospital.service;

import com.hospital.entity.po.Arrange;
import com.hospital.entity.vo.user.ArrangeDoctorVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 排班 服务层
 *
 * @author: ShanZhu
 * @date: 2023-11-17
 */
public interface ArrangeService {

    /**
     * 通过日期查询排班
     *
     * @param arTime   排班时间
     * @param dSection 科室
     * @return 排班信息
     */
    List<Arrange> findArrange(String arTime, String dSection);

    /**
     * 添加排班
     *
     * @param arrange 排班信息
     * @return 结果
     */
    Boolean addArrange(Arrange arrange);

    /**
     * 删除排班
     *
     * @param arId 排班id
     * @return 结果
     */
    Boolean deleteArrange(String arId);

    /**
     * 排班表
     * @param response
     * @throws Exception
     */

    void findArrangeDoctor(HttpServletResponse response) throws Exception;
}
