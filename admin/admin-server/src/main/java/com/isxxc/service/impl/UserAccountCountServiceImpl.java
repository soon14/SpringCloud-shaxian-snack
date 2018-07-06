package com.isxxc.service.impl;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.UserAccountCountDAO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.UserAccountDTO;
import com.isxxc.domain.dto.UserAccountOtherDTO;
import com.isxxc.domain.entity.UserAccountDO;
import com.isxxc.service.UserAccountCountService;
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
 * @since 2018-05-18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserAccountCountServiceImpl extends ServiceImpl<UserAccountCountDAO, UserAccountDO> implements UserAccountCountService {

    @Resource
    private UserAccountCountDAO userAccountCountDAO;

    @Override
    public Result countUser(UserAccountDTO authAccountDTO) {
        Integer integer = userAccountCountDAO.countUser(authAccountDTO);
        return ResponseResult.success(integer);
    }

    @Override
    public Pager listPage(Pager pager) {
        List<UserAccountOtherDTO> userAccountDTOS = userAccountCountDAO.listPage(pager, pager.getParamMap());
        pager.setRecords(userAccountDTOS);
        return pager;
    }


}
