package com.isxxc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.dao.UserFunctionDAO;
import com.isxxc.domain.dto.UserFunctionDTO;
import com.isxxc.domain.entity.UserFunctionDO;
import com.isxxc.service.UserFunctionService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2017-12-21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserFunctionServiceImpl extends ServiceImpl<UserFunctionDAO, UserFunctionDO> implements UserFunctionService {

    @Resource
    private UserFunctionDAO userFunctionDAO;

    @Override
    public Result save(UserFunctionDO userFunctionDO) {
        UserFunctionDTO userFunctionDTO = userFunctionDAO.selectByCode(userFunctionDO.getCode());
        if (userFunctionDTO != null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "功能唯一标识已经存在");
        }
        if (userFunctionDO.getIsAuth() == null) {
            userFunctionDO.setIsAuth(CommonStateEnum.IsAuth.YES.code);
        }
        if (userFunctionDO.getIsShow() == null) {
            userFunctionDO.setIsShow(CommonStateEnum.IsShow.NO.code);
        }
        return insert(userFunctionDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result functionTree() {
        List<UserFunctionDTO> userFunctionDTOList = userFunctionDAO.list();
        userFunctionDTOList.sort(Comparator.comparing(UserFunctionDTO::getParentId).thenComparing(UserFunctionDTO::getLevel));
        List<UserFunctionDTO> userFunctionDTOParentList = new ArrayList<>();
        userFunctionDTOList.stream().filter(userFunctionDTO -> userFunctionDTO.getParentId() == 0).forEach(userFunctionDTO -> {
            buildTree(userFunctionDTO, userFunctionDTOList);
            userFunctionDTOParentList.add(userFunctionDTO);
        });
        return ResponseResult.success(userFunctionDTOParentList);
    }

    @Override
    public Result updateInfo(UserFunctionDO userFunctionDO) {
        UserFunctionDTO userFunctionDTO = userFunctionDAO.selectByCode(userFunctionDO.getCode());
        if (userFunctionDTO != null && !userFunctionDO.getId().equals(userFunctionDTO.getId())) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "功能唯一标识已经存在");
        }
        return updateById(userFunctionDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    private void buildTree(UserFunctionDTO userFunctionDTO, List<UserFunctionDTO> userFunctionDTOList) {
        userFunctionDTOList.forEach(userFunctionDTODB -> {
            if (userFunctionDTO.getId().equals(userFunctionDTODB.getParentId())) {
                if (userFunctionDTO.getChildList() == null) {
                    userFunctionDTO.setChildList(new ArrayList<UserFunctionDTO>() {{
                        add(userFunctionDTODB);
                    }});
                } else {
                    userFunctionDTO.getChildList().add(userFunctionDTODB);
                }
                buildTree(userFunctionDTODB, userFunctionDTOList);
            }
        });
    }
}
