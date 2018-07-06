package com.isxxc.service.impl;

import com.isxxc.domain.entity.ShopTransferImgDO;
import com.isxxc.dao.ShopTransferImgDAO;
import com.isxxc.service.ShopTransferImgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author likq
 * @since 2017-12-07
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShopTransferImgServiceImpl extends ServiceImpl<ShopTransferImgDAO, ShopTransferImgDO> implements ShopTransferImgService {
	
}
