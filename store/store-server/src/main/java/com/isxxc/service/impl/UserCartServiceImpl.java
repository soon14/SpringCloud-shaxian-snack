package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.constant.ProductStateEnum;
import com.isxxc.dao.UserCartDAO;
import com.isxxc.domain.dto.ProductSkuInfoDTO;
import com.isxxc.domain.dto.UserCartDTO;
import com.isxxc.domain.dto.UserStoreCartDTO;
import com.isxxc.domain.dto.UserStoreProfilDTO;
import com.isxxc.domain.entity.ProductSkuInfoDO;
import com.isxxc.domain.entity.ProductSpuDO;
import com.isxxc.domain.entity.UserCartDO;
import com.isxxc.service.ProductSkuInfoService;
import com.isxxc.service.ProductSpuImgService;
import com.isxxc.service.ProductSpuService;
import com.isxxc.service.UserCartService;
import com.isxxc.service.UserStoreProfilService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;

/**
 * <p>
 * 会员购物车 服务实现类
 * </p>
 *
 * @author 泥水佬123
 * @since 2018-01-29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserCartServiceImpl extends ServiceImpl<UserCartDAO, UserCartDO> implements UserCartService {

    @Resource
    private UserCartDAO userCartDAO;

    @Resource
    private ProductSpuService productSpuService;

    @Resource
    private ProductSkuInfoService productSkuInfoService;

    @Resource
    private UserStoreProfilService userStoreProfilService;

    @Resource
    private ProductSpuImgService productSpuImgService;

    @Override
    public Result list(Integer userId) {
        List<UserCartDTO> userCartDTOList = userCartDAO.selectDTOByUserId(userId);
        Map<Integer, List<UserCartDTO>> userCartGroup = userCartDTOList.stream().collect(Collectors.groupingBy(UserCartDTO::getStoreId));
        List<UserStoreCartDTO> userStoreCartDTOList = new ArrayList<>(userCartGroup.size());
        userCartGroup.forEach((storeId, userCartList) -> {
            UserStoreProfilDTO userStoreProfilDTO = userStoreProfilService.selectDTOById(storeId);
            UserStoreCartDTO userStoreCartDTO = new UserStoreCartDTO();
            userStoreCartDTO.setStoreId(userStoreProfilDTO.getId());
            userStoreCartDTO.setStoreName(userStoreProfilDTO.getCompanyName());
            userStoreCartDTO.setCartList(userCartList);
            userCartList.forEach(userCartDTO -> {
                ProductSkuInfoDTO skuInfoDTO = productSkuInfoService.selectDTOById(userCartDTO.getSkuId());
                ProductSpuDO productSpuDO = productSpuService.selectById(skuInfoDTO.getSpuId());
                skuInfoDTO.setTitle(productSpuDO.getTitle());
                //查询商品主图
                userCartDTO.setMainImgUrl(CommonFolderConstant.getProductSpuImgWebPath(productSpuDO.getId(), productSpuImgService.selectMainBySpuID(userCartDTO.getSpuId())));
                userCartDTO.setSkuInfoDTO(skuInfoDTO);
            });
            userStoreCartDTOList.add(userStoreCartDTO);
        });
        return ResponseResult.success(userStoreCartDTOList);
    }

    @Override
    public Result updateNum(Integer userId, Integer id, Integer num) {
        EntityWrapper<UserCartDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("id", id);
        entityWrapper.eq("user_id", userId);
        UserCartDO userCartDO = selectOne(entityWrapper);
        if (userCartDO == null) {
            return ResponseResult.paramNotNull("购物车信息不存在");
        }
        userCartDO.setNum(num);
        return updateById(userCartDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result deleteByIdAndUserId(Integer userId, Integer id) {
        EntityWrapper<UserCartDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("id", id);
        entityWrapper.eq("user_id", userId);
        return delete(entityWrapper) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result deleteByIds(Integer userId, String ids) {
        List<Integer> idList = Arrays.stream(ids.split(",")).map(id -> Integer.parseInt(id.trim())).distinct().collect(Collectors.toList());
        return userCartDAO.deleteByIdList(userId, idList) > 0 ? ResponseResult.success() : ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "删除失败,数据有误");
    }

    @Override
    public Result save(UserCartDO userCartDO) {
        ProductSkuInfoDO skuInfoDO = productSkuInfoService.selectById(userCartDO.getSkuId());
        if (skuInfoDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "抱歉! 商品信息错误,请联系商家");
        }
        ProductSpuDO productSpuDO = productSpuService.selectById(skuInfoDO.getSpuId());
        if (productSpuDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "抱歉! 商品信息错误,请联系商家");
        }
        if (productSpuDO.getIsDeleted() == CommonStateEnum.IsDeleted.HAVE_DELETED.code ||
                productSpuDO.getIsShelves() == ProductStateEnum.IsShelves.OFF.code ||
                skuInfoDO.getIsDeleted() == CommonStateEnum.IsDeleted.HAVE_DELETED.code ||
                skuInfoDO.getIsShelves() == ProductStateEnum.IsShelves.OFF.code) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "抱歉! 商品 " + productSpuDO.getName() + " 已经下架");
        }

        EntityWrapper<UserCartDO> userCartDOEntityWrapper = new EntityWrapper<>();
        userCartDOEntityWrapper.eq("user_id", userCartDO.getUserId());
        userCartDOEntityWrapper.eq("sku_id", userCartDO.getSkuId());
        UserCartDO userCartDODB = selectOne(userCartDOEntityWrapper);
        if (userCartDODB == null) {
            userCartDO.setSpuId(productSpuDO.getId());
            userCartDO.setStoreId(productSpuDO.getStoreId());
            insert(userCartDO);
        } else {
            userCartDODB.setNum(userCartDODB.getNum() + userCartDO.getNum());
            updateById(userCartDODB);
        }
        return ResponseResult.success();
    }
}
