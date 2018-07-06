package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.FinancingInfoImgDAO;
import com.isxxc.domain.entity.FinancingInfoImgDO;
import com.isxxc.service.FinancingInfoImgService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FinancingInfoImgServiceImpl extends ServiceImpl<FinancingInfoImgDAO, FinancingInfoImgDO> implements FinancingInfoImgService {
	
}
