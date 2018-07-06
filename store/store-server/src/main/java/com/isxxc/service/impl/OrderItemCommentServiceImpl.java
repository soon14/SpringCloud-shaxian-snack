package com.isxxc.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.constant.OrderStateEnum;
import com.isxxc.dao.OrderItemCommentDAO;
import com.isxxc.domain.dto.OrderItemCommentDTO;
import com.isxxc.domain.dto.OrderItemCommentImgDTO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.entity.OrderInfoDO;
import com.isxxc.domain.entity.OrderItemAttrDO;
import com.isxxc.domain.entity.OrderItemCommentDO;
import com.isxxc.domain.entity.OrderItemDO;
import com.isxxc.service.OrderInfoService;
import com.isxxc.service.OrderItemAttrService;
import com.isxxc.service.OrderItemCommentImgService;
import com.isxxc.service.OrderItemCommentService;
import com.isxxc.service.OrderItemService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;
import cc.likq.util.FileUtils;

/**
 * <p>
 * 订单商品评价 服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2018-03-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderItemCommentServiceImpl extends ServiceImpl<OrderItemCommentDAO, OrderItemCommentDO> implements OrderItemCommentService {

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private OrderItemService orderItemService;

    @Resource
    private OrderItemCommentDAO orderItemCommentDAO;

    @Resource
    private OrderItemCommentImgService orderItemCommentImgService;

    @Resource
    private OrderItemAttrService orderItemAttrService;
    Logger logger = LogManager.getLogger(OrderItemCommentServiceImpl.class);
    @Override
    public Result add(OrderItemCommentDTO orderItemCommentDTO) {
        logger.info("OrderItemCommentServiceImpl+add:"+JSON.toJSONString(orderItemCommentDTO));
        if(orderItemCommentDTO.getCommentImgList()!=null){
            for (OrderItemCommentImgDTO commentImgDTO : orderItemCommentDTO.getCommentImgList()) {
                if (!FileUtils.exists(CommonFolderConstant.getImageTempPath(commentImgDTO.getName()))) {
                    return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "图片已经失效,请重新上传", new HashMap<String, String>(1) {{
                        put("key", commentImgDTO.getName());
                    }});
                }
            }
        }
        OrderItemDO orderItemDO = orderItemService.selectById(orderItemCommentDTO.getOrderItemId());
        if (orderItemDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "订单详情项ID错误");
        }
        OrderInfoDO orderInfoDO = orderInfoService.selectById(orderItemDO.getOrderInfoId());
        if (orderInfoDO == null || !orderInfoDO.getUserId().equals(orderItemCommentDTO.getUserId())) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "订单信息错误,请联系管理员");
        }
        if (OrderStateEnum.MasterState.COMPLETE_TRANSACTION.code != orderInfoDO.getMasterState()) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "当前订单不能评价");
        }
        if (OrderStateEnum.CommentState.WAIT_COMMENT.code != orderItemDO.getCommentState()) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "当前订单商品已评价,请不要重复评价");
        }

        orderItemCommentDTO.setStoreId(orderInfoDO.getStoreId());
        orderItemCommentDTO.setSpuId(orderItemDO.getSpuId());
        orderItemCommentDTO.setSkuId(orderItemDO.getSkuId());
        orderItemCommentDTO.setGmtModified(new Date());
        orderItemCommentDTO.setGmtCreated(new Date());
        orderItemCommentDAO.insert(orderItemCommentDTO);

        orderItemDO.setCommentState(OrderStateEnum.CommentState.ALREADY_COMMENT.code);
        orderItemService.updateById(orderItemDO);

        if(orderItemCommentDTO.getCommentImgList()!=null) {
            orderItemCommentDTO.getCommentImgList().forEach(orderItemCommentImgDTO -> {
                orderItemCommentImgDTO.setOrderItemCommentId(orderItemCommentDTO.getId());
                orderItemCommentImgDTO.setGmtCreate(new Date());
                orderItemCommentImgDTO.setGmtModified(new Date());
                orderItemCommentImgService.insert(orderItemCommentImgDTO);
            });
            orderItemCommentDTO.getCommentImgList().forEach(orderItemCommentImgDTO -> FileUtils.cutFileStr(CommonFolderConstant.getImageTempPath(orderItemCommentImgDTO.getName()), CommonFolderConstant.getProductCommentPath()));
        }
        return ResponseResult.success();
    }

    @Override
    public Result listPage(Pager pager) {
        logger.info("查询商品SPUID评论列表："+pager+"/pager.getParamMap:"+ pager.getParamMap());
        List<OrderItemCommentDO> itemCommentDOList = orderItemCommentDAO.selectDTOList(pager, pager.getParamMap());
        List<OrderItemCommentDTO> orderItemCommentDTOList = new ArrayList<>();
        for(OrderItemCommentDO orderItemCommentDO:itemCommentDOList){
            OrderItemCommentDTO orderItemCommentDTO = new OrderItemCommentDTO();

            orderItemCommentDTO.setId(orderItemCommentDO.getId());
            orderItemCommentDTO.setOrderItemId(orderItemCommentDO.getOrderItemId());
            orderItemCommentDTO.setComment(orderItemCommentDO.getComment());
            orderItemCommentDTO.setUserId(orderItemCommentDO.getUserId());
            orderItemCommentDTO.setDescribeScore(orderItemCommentDO.getDescribeScore());
            orderItemCommentDTO.setGmtCreated(orderItemCommentDO.getGmtCreated());
            orderItemCommentDTO.setGmtModified(orderItemCommentDO.getGmtModified());
            orderItemCommentDTO.setPrice(orderItemCommentDO.getPrice());
            orderItemCommentDTO.setProductName(orderItemCommentDO.getProductName());
            orderItemCommentDTO.setReply(orderItemCommentDO.getReply());
            orderItemCommentDTO.setServiceScore(orderItemCommentDO.getServiceScore());
            orderItemCommentDTO.setSkuId(orderItemCommentDO.getSkuId());
            orderItemCommentDTO.setSpuId(orderItemCommentDO.getSpuId());
            orderItemCommentDTO.setStoreId(orderItemCommentDO.getStoreId());
            orderItemCommentDTO.setStoreName(orderItemCommentDO.getStoreName());
            //昵称模糊处理
            orderItemCommentDTO.setNickName(processName(orderItemCommentDO.getNickName()));
            //图片集合
            List<OrderItemCommentImgDTO> commentImgDTOList = orderItemCommentImgService.selectDTOByCommentId(orderItemCommentDO.getId());
            orderItemCommentDTO.setCommentImgList(commentImgDTOList);
            //商品属性集合
            EntityWrapper<OrderItemAttrDO> itemAttrDOEntityWrapper = new EntityWrapper<>();
            itemAttrDOEntityWrapper.eq("order_item_id", orderItemCommentDO.getOrderItemId());
            orderItemCommentDTO.setAttrList(orderItemAttrService.selectList(itemAttrDOEntityWrapper));

            orderItemCommentDTOList.add(orderItemCommentDTO);
        }
        pager.setRecords(orderItemCommentDTOList);
        return ResponseResult.success(pager);
    }

    @Override
    public Result getInfoByOrderItemId(Integer orderItemId) {
        OrderItemCommentDTO itemCommentDTO = orderItemCommentDAO.getInfoByOrderItemId(orderItemId);
        List<OrderItemCommentImgDTO> commentImgDTOList = orderItemCommentImgService.selectDTOByCommentId(itemCommentDTO.getId());
        itemCommentDTO.setCommentImgList(commentImgDTOList);
        EntityWrapper<OrderItemAttrDO> itemAttrDOEntityWrapper = new EntityWrapper<>();
        itemAttrDOEntityWrapper.eq("order_item_id", itemCommentDTO.getOrderItemId());
        itemCommentDTO.setAttrList(orderItemAttrService.selectList(itemAttrDOEntityWrapper));
        return ResponseResult.success(itemCommentDTO);
    }

    /**
     * 查询用户所有评价 by xdp
     * @param pager
     * @return
     */
    @Override
    public Result getInfoByUserId(Pager pager,Integer userId) {
        List<OrderItemCommentDO> itemCommentDOList = orderItemCommentDAO.getInfoByUserId(pager, userId);
        List<OrderItemCommentDTO> orderItemCommentDTOList = new ArrayList<>();
        for(OrderItemCommentDO orderItemCommentDO:itemCommentDOList){
            OrderItemCommentDTO orderItemCommentDTO = new OrderItemCommentDTO();

            orderItemCommentDTO.setId(orderItemCommentDO.getId());
            orderItemCommentDTO.setOrderItemId(orderItemCommentDO.getOrderItemId());
            orderItemCommentDTO.setComment(orderItemCommentDO.getComment());
            orderItemCommentDTO.setUserId(orderItemCommentDO.getUserId());
            orderItemCommentDTO.setDescribeScore(orderItemCommentDO.getDescribeScore());
            orderItemCommentDTO.setGmtCreated(orderItemCommentDO.getGmtCreated());
            orderItemCommentDTO.setGmtModified(orderItemCommentDO.getGmtModified());
            orderItemCommentDTO.setPrice(orderItemCommentDO.getPrice());
            orderItemCommentDTO.setProductName(orderItemCommentDO.getProductName());
            orderItemCommentDTO.setReply(orderItemCommentDO.getReply());
            orderItemCommentDTO.setServiceScore(orderItemCommentDO.getServiceScore());
            orderItemCommentDTO.setSkuId(orderItemCommentDO.getSkuId());
            orderItemCommentDTO.setSpuId(orderItemCommentDO.getSpuId());
            orderItemCommentDTO.setStoreId(orderItemCommentDO.getStoreId());
            orderItemCommentDTO.setStoreName(orderItemCommentDO.getStoreName());
            //昵称模糊处理
            orderItemCommentDTO.setNickName(processName(orderItemCommentDO.getNickName()));
            //图片集合
            List<OrderItemCommentImgDTO> commentImgDTOList = orderItemCommentImgService.selectDTOByCommentId(orderItemCommentDO.getId());
            orderItemCommentDTO.setCommentImgList(commentImgDTOList);
            //商品属性集合
            EntityWrapper<OrderItemAttrDO> itemAttrDOEntityWrapper = new EntityWrapper<>();
            itemAttrDOEntityWrapper.eq("order_item_id", orderItemCommentDO.getOrderItemId());
            orderItemCommentDTO.setAttrList(orderItemAttrService.selectList(itemAttrDOEntityWrapper));

            orderItemCommentDTOList.add(orderItemCommentDTO);
        }
        pager.setRecords(orderItemCommentDTOList);
        return ResponseResult.success(pager);
    }

    /**
     * 查询商家所用评价
     * @param pager
     * @return
     */
    @Override
    public Result getInfoByStoreId(Pager pager,Integer storeId) {
        logger.info("查询商家评价列表："+pager+"/pager.getParamMap:"+ pager.getParamMap());
        List<OrderItemCommentDO> itemCommentDOList = orderItemCommentDAO.getInfoByStoreId(pager, storeId);
        List<OrderItemCommentDTO> orderItemCommentDTOList = new ArrayList<>();
        for(OrderItemCommentDO orderItemCommentDO:itemCommentDOList){
            OrderItemCommentDTO orderItemCommentDTO = new OrderItemCommentDTO();

            orderItemCommentDTO.setId(orderItemCommentDO.getId());
            orderItemCommentDTO.setOrderItemId(orderItemCommentDO.getOrderItemId());
            orderItemCommentDTO.setComment(orderItemCommentDO.getComment());
            orderItemCommentDTO.setUserId(orderItemCommentDO.getUserId());
            orderItemCommentDTO.setDescribeScore(orderItemCommentDO.getDescribeScore());
            orderItemCommentDTO.setGmtCreated(orderItemCommentDO.getGmtCreated());
            orderItemCommentDTO.setGmtModified(orderItemCommentDO.getGmtModified());
            orderItemCommentDTO.setPrice(orderItemCommentDO.getPrice());
            orderItemCommentDTO.setProductName(orderItemCommentDO.getProductName());
            orderItemCommentDTO.setReply(orderItemCommentDO.getReply());
            orderItemCommentDTO.setServiceScore(orderItemCommentDO.getServiceScore());
            orderItemCommentDTO.setSkuId(orderItemCommentDO.getSkuId());
            orderItemCommentDTO.setSpuId(orderItemCommentDO.getSpuId());
            orderItemCommentDTO.setStoreId(orderItemCommentDO.getStoreId());
            orderItemCommentDTO.setStoreName(orderItemCommentDO.getStoreName());
            //昵称模糊处理
            orderItemCommentDTO.setNickName(processName(orderItemCommentDO.getNickName()));
            //图片集合
            List<OrderItemCommentImgDTO> commentImgDTOList = orderItemCommentImgService.selectDTOByCommentId(orderItemCommentDO.getId());
            orderItemCommentDTO.setCommentImgList(commentImgDTOList);
            //商品属性集合
            EntityWrapper<OrderItemAttrDO> itemAttrDOEntityWrapper = new EntityWrapper<>();
            itemAttrDOEntityWrapper.eq("order_item_id", orderItemCommentDO.getOrderItemId());
            orderItemCommentDTO.setAttrList(orderItemAttrService.selectList(itemAttrDOEntityWrapper));

            orderItemCommentDTOList.add(orderItemCommentDTO);
        }
        pager.setRecords(orderItemCommentDTOList);
        return ResponseResult.success(pager);
    }

    /**
     * 商家回复
     * @param orderItemCommentDTO
     * @return
     */
    @Override
    public Result replyComments(OrderItemCommentDTO orderItemCommentDTO) {
        orderItemCommentDTO.setGmtModified(new Date());
        logger.info("商家回复入参：orderItemCommentDTO："+orderItemCommentDTO);
        return updateById(orderItemCommentDTO)?ResponseResult.success():ResponseResult.serverError();
    }

    /**
     * 名字 做模糊处理
     */
    public static String processName(String name) {
        String token = "*";
        if (name.length() < 2) {
            return name;
        } else if (name.length() == 2) {
            StringBuilder nameStart = new StringBuilder(name.substring(0, 1));
            return nameStart.append(token).toString();
        } else if (name.length() == 3) {
            StringBuilder nameStart = new StringBuilder(name.substring(0, 1));
            StringBuilder nameEnd = new StringBuilder(name.substring(2, name.length()));
            return nameStart.append(token).append(nameEnd).toString();
        } else if (name.length() == 11) {
            StringBuilder nameStart = new StringBuilder(name.substring(0, 3));
            StringBuilder nameEnd = new StringBuilder(name.substring(7, name.length()));
            return nameStart.append(token).append(token).append(token).append(token).append(nameEnd).toString();
        } else {
            StringBuilder nameStart = new StringBuilder(name.substring(0, 1));
            StringBuilder nameEnd = new StringBuilder(name.substring(3, name.length()));
            return nameStart.append(token).append(token).append(nameEnd).toString();
        }
    }
}
