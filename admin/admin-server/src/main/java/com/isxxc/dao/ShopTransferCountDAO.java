package com.isxxc.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.UserAccountOtherDTO;
import com.isxxc.domain.entity.ShopTransferDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 盘店寻租-旺铺转让/求租旺铺 Mapper 接口
 * </p>
 *
 * @author 吴坚
 * @since 2018-05-22
 */
@Repository
public interface ShopTransferCountDAO extends BaseMapper<ShopTransferDO> {
    /***
     * 盘店寻租-旺铺转让  统计
     * @param shopTransferDO
     * @return
     */
    Integer countAttornShop(ShopTransferDO shopTransferDO);
    /***
     * 盘店寻租-求租旺铺  统计
     * @param shopTransferDO
     * @return
     */
    Integer countRentingShop(ShopTransferDO shopTransferDO);
    /***
     * 查询手机注册用户列表 -- 时间段,自带分页
     * @param pager
     * @return
     */
    List<UserAccountOtherDTO> listPage(Pager pager, @Param("userAccountR") Map<String, Object> paramMap);

}
