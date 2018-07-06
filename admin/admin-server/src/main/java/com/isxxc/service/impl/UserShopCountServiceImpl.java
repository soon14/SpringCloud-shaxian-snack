package com.isxxc.service.impl;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.UserShopCountDAO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.UserAccountOtherDTO;
import com.isxxc.domain.entity.UserShopProfilDO;
import com.isxxc.domain.entity.UserShopProfilImgDO;
import com.isxxc.service.UserShopCountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 吴坚
 * @since 2018-05-21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserShopCountServiceImpl extends ServiceImpl<UserShopCountDAO, UserShopProfilDO> implements UserShopCountService {

    @Resource
    private UserShopCountDAO userShopCountDAO;

    @Override
    public Result countUserShop(UserShopProfilDO userShopProfilDO) {
        Integer integer = userShopCountDAO.countUserShop(userShopProfilDO);
        return ResponseResult.success(integer);
    }

    @Override
    public Pager listPage(Pager pager) {
        List<UserAccountOtherDTO> userAccountOtherDTOS = userShopCountDAO.listPage(pager, pager.getParamMap());
        pager.setRecords(userAccountOtherDTOS);
        return pager;
    }
}
