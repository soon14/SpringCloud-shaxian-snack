package com.isxxc.service.impl;

import com.isxxc.domain.entity.AdContentImgDO;
import com.isxxc.dao.AdContentImgDAO;
import com.isxxc.service.AdContentImgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 广告内容图片 服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdContentImgServiceImpl extends ServiceImpl<AdContentImgDAO, AdContentImgDO> implements AdContentImgService {
	
}
