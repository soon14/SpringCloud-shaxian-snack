package com.isxxc.service.impl;

import com.isxxc.domain.entity.InformationImgDO;
import com.isxxc.dao.InformationImgDAO;
import com.isxxc.service.InformationImgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author likq
 * @since 2017-12-06
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InformationImgServiceImpl extends ServiceImpl<InformationImgDAO, InformationImgDO> implements InformationImgService {
	
}
