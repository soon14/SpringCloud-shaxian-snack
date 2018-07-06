package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonFolderConstant;
import com.isxxc.constant.CommonStateEnum;
import com.isxxc.constant.OrderStateEnum;
import com.isxxc.constant.ProductStateEnum;
import com.isxxc.dao.OrderInfoDAO;
import com.isxxc.domain.dto.ConsignmentDTO;
import com.isxxc.domain.dto.OrderInfoDTO;
import com.isxxc.domain.dto.OrderItemDTO;
import com.isxxc.domain.dto.Pager;
import com.isxxc.domain.dto.ProductAttrInfoDTO;
import com.isxxc.domain.dto.SubmitOrderDTO;
import com.isxxc.domain.dto.SubmitOrderSkuDTO;
import com.isxxc.domain.dto.SubmitStoreOrderDTO;
import com.isxxc.domain.entity.OrderInfoDO;
import com.isxxc.domain.entity.OrderItemAttrDO;
import com.isxxc.domain.entity.OrderPayMergerDO;
import com.isxxc.domain.entity.ProductFreightTemplateDO;
import com.isxxc.domain.entity.ProductSalesVolumeDO;
import com.isxxc.domain.entity.ProductSkuInfoDO;
import com.isxxc.domain.entity.ProductSkuPriceMultiDO;
import com.isxxc.domain.entity.ProductSkuStockDO;
import com.isxxc.domain.entity.ProductSkuStockLogDO;
import com.isxxc.domain.entity.ProductSpuDO;
import com.isxxc.service.OrderInfoService;
import com.isxxc.service.OrderItemAttrService;
import com.isxxc.service.OrderItemService;
import com.isxxc.service.OrderPayMergerService;
import com.isxxc.service.ProductFreightTemplateService;
import com.isxxc.service.ProductSalesVolumeService;
import com.isxxc.service.ProductSkuAttrRelationService;
import com.isxxc.service.ProductSkuInfoService;
import com.isxxc.service.ProductSkuPriceMultiService;
import com.isxxc.service.ProductSkuStockLogService;
import com.isxxc.service.ProductSkuStockService;
import com.isxxc.service.ProductSpuImgService;
import com.isxxc.service.ProductSpuService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;
import cc.likq.util.FileUtils;
import cc.likq.util.OrderNoUtils;
import cc.likq.util.PayMergerNoUtils;

/**
 * <p>
 * 订单信息 服务实现类
 * </p>
 *
 * @author 泥水佬
 * @since 2018-02-03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoDAO, OrderInfoDO> implements OrderInfoService {

    @Resource
    private OrderInfoDAO orderInfoDAO;

    @Resource
    private OrderItemService orderItemService;

    @Resource
    private OrderItemAttrService orderItemAttrService;

    @Resource
    private OrderPayMergerService orderPayMergerService;

    @Resource
    private ProductSpuService productSpuService;

    @Resource
    private ProductSkuInfoService productSkuInfoService;

    @Resource
    private ProductSkuStockService productSkuStockService;

    @Resource
    private ProductSkuPriceMultiService productSkuPriceMultiService;

    @Resource
    private ProductSkuStockLogService productSkuStockLogService;

    @Resource
    private ProductFreightTemplateService freightTemplateService;

    @Resource
    private ProductSpuImgService productSpuImgService;

    @Resource
    private ProductSkuAttrRelationService skuAttrRelationService;

    @Resource
    private ProductSalesVolumeService productSalesVolumeService;

    @Override
    public Result submitOrderDTO(SubmitOrderDTO submitOrderDTO) {
        //订单号
        List<String> orderNoList = new ArrayList<>();
        //所有订单应付总价;
        Long allOrderActualPrice = 0L;
        //商品主图路径
        Map<Integer, List<String>> orderImgMap = new HashMap<>(submitOrderDTO.getStoreOrderList().size());

        //销量记录
        List<ProductSalesVolumeDO> productSalesVolumeDOList = new ArrayList<>();

        //获取商店订单信息
        for (SubmitStoreOrderDTO storeOrder : submitOrderDTO.getStoreOrderList()) {
            //订单号生成
            String orderNo = OrderNoUtils.generate(OrderNoUtils.OrderType.SHOP_ORDER);

            //商店订单应付运费
            Long actualFreightPricePrice = null;

            //订单详细信息
            List<OrderItemDTO> orderItemDOList = new ArrayList<>();

            //商品主图路径
            List<String> spuMainImgList = new ArrayList<>(storeOrder.getSkuList().size());

            //获取商品SKU详细信息
            for (SubmitOrderSkuDTO orderSkuDTO : storeOrder.getSkuList()) {
                ProductSkuInfoDO skuInfoDO = productSkuInfoService.selectById(orderSkuDTO.getSkuId());
                if (skuInfoDO == null) {
                    return ResponseResult.paramNotNull("抱歉! 商品信息错误,无该商品,请联系商家处理");
                }
                ProductSpuDO spuDO = productSpuService.selectById(skuInfoDO.getSpuId());
                if (spuDO == null || !storeOrder.getStoreId().equals(spuDO.getStoreId())) {
                    return ResponseResult.paramNotNull("抱歉! 商品信息错误,无该商品,请联系商家处理");
                }
                //校对当前商品SPU/SKU是否已经删除或下架
                if (spuDO.getIsDeleted() == CommonStateEnum.IsDeleted.HAVE_DELETED.code ||
                        spuDO.getIsShelves() == ProductStateEnum.IsShelves.OFF.code ||
                        skuInfoDO.getIsDeleted() == CommonStateEnum.IsDeleted.HAVE_DELETED.code ||
                        skuInfoDO.getIsShelves() == ProductStateEnum.IsShelves.OFF.code) {
                    return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "抱歉! 商品 " + spuDO.getName() + " 已下架");
                }

                //校对库存,并更新库存,注:高并发情况下,容易产生超卖
                ProductSkuStockDO skuStockDO = productSkuStockService.selectBySkuId(skuInfoDO.getId());
                if (skuStockDO == null) {
                    return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "抱歉! 商品 " + spuDO.getName() + " 库存异常,请联系商家处理");
                }
                if (skuStockDO.getNum() <= 0) {
                    return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "抱歉! 商品 " + spuDO.getName() + " 卖光啦!");
                }
                if (skuStockDO.getNum() < orderSkuDTO.getNum()) {
                    return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "抱歉! 商品 " + spuDO.getName() + " 库存不足");
                }

                //持久化库存日志
                ProductSkuStockLogDO productSkuStockLogDO = new ProductSkuStockLogDO();
                productSkuStockLogDO.setSkuStockId(skuStockDO.getId());
                productSkuStockLogDO.setBoforeNum(skuStockDO.getNum());
                productSkuStockLogDO.setNum(orderSkuDTO.getNum());
                productSkuStockLogDO.setRemark("订单: " + orderNo + " 购买扣除");
                productSkuStockLogDO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
                productSkuStockLogDO.setGmtModified(new Date());
                productSkuStockLogDO.setGmtCreate(new Date());
                productSkuStockLogService.insert(productSkuStockLogDO);
                //更新库存
                skuStockDO.setNum(skuStockDO.getNum() - orderSkuDTO.getNum());
                productSkuStockService.updateById(skuStockDO);

                //校对商品单价
                if (skuInfoDO.getPriceType() == ProductStateEnum.PriceType.MULTI.type) {
                    //批量价
                    EntityWrapper<ProductSkuPriceMultiDO> priceMultiDOEntityWrapper = new EntityWrapper<>();
                    priceMultiDOEntityWrapper.eq("sku_id", skuInfoDO.getId());
                    priceMultiDOEntityWrapper.eq("is_deleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
                    priceMultiDOEntityWrapper.orderBy("min_num", true);
                    List<ProductSkuPriceMultiDO> skuPriceMultiDOList = productSkuPriceMultiService.selectList(priceMultiDOEntityWrapper);
                    boolean skuPriceMultiFlag = true;
                    for (ProductSkuPriceMultiDO skuPriceMultiDO : skuPriceMultiDOList) {
                        if (skuPriceMultiDO.getMinNum() <= orderSkuDTO.getNum() && orderSkuDTO.getNum() <= skuPriceMultiDO.getMaxNum()) {
                            if (skuInfoDO.getSalePrice().equals(orderSkuDTO.getSalePrice())) {
                                skuPriceMultiFlag = false;
                                break;
                            } else {
                                return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, spuDO.getName() + " 商品价格已经产生变动,请返回重新下单");
                            }
                        }
                    }
                    if (skuPriceMultiFlag) {
                        return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, spuDO.getName() + " 商品定价异常,请联系商家处理");
                    }
                } else {
                    //零售价
                    if (!skuInfoDO.getSalePrice().equals(orderSkuDTO.getSalePrice())) {
                        return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, spuDO.getName() + " 商品价格已经产生变动,请返回重新下单");
                    }
                }

                //计算运费
                ProductFreightTemplateDO freightTemplateDO = freightTemplateService.selectById(spuDO.getFreightTemplateId());
                if (actualFreightPricePrice == null) {
                    actualFreightPricePrice = freightTemplateDO.getPrice();
                } else if (actualFreightPricePrice > freightTemplateDO.getPrice()) {
                    actualFreightPricePrice = freightTemplateDO.getPrice();
                }

                //生成订单详细
                OrderItemDTO orderItemDO = new OrderItemDTO();
                orderItemDO.setSpuId(spuDO.getId());
                orderItemDO.setSkuId(skuInfoDO.getId());
                orderItemDO.setSpuTitle(spuDO.getTitle());
                orderItemDO.setSalePrice(skuInfoDO.getSalePrice());
                orderItemDO.setNum(orderSkuDTO.getNum());
                orderItemDO.setTotalPrice(orderSkuDTO.getTotalPrice());
                orderItemDO.setImg(productSpuImgService.selectMainBySpuID(spuDO.getId()));
                orderItemDO.setGmtModified(new Date());
                orderItemDO.setGmtCreate(new Date());
                //生成订单详情项
                List<ProductAttrInfoDTO> attrInfoDTOList = skuAttrRelationService.selectDTOBySkuId(skuInfoDO.getId(), CommonStateEnum.IsDeleted.NOT_DELETED.code);
                List<OrderItemAttrDO> itemAttrDOList = new ArrayList<>();
                attrInfoDTOList.forEach(productAttrInfoDTO -> {
                    OrderItemAttrDO itemAttrDO = new OrderItemAttrDO();
                    itemAttrDO.setAttrName(productAttrInfoDTO.getAttrKeyName());
                    itemAttrDO.setAttrValue(productAttrInfoDTO.getAttrValueName());
                    itemAttrDO.setGmtModified(new Date());
                    itemAttrDO.setGmtCreate(new Date());
                    itemAttrDOList.add(itemAttrDO);
                });
                orderItemDO.setItemAttrList(itemAttrDOList);
                orderItemDOList.add(orderItemDO);

                //记录商品主图
                String spuMainImgPath = CommonFolderConstant.getProductSpuImgPath(spuDO.getId()) + orderItemDO.getImg();
                spuMainImgList.add(spuMainImgPath);

                //记录产品销量
                ProductSalesVolumeDO salesVolumeDO = new ProductSalesVolumeDO();
                salesVolumeDO.setNum(orderItemDO.getNum());
                salesVolumeDO.setSpuId(spuDO.getId());
                salesVolumeDO.setSkuId(skuInfoDO.getId());
                productSalesVolumeDOList.add(salesVolumeDO);
            }

            //校对运费
            if (actualFreightPricePrice == null) {
                return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "计算运费失败,请联系商家");
            }
            if (!actualFreightPricePrice.equals(storeOrder.getFreightPrice())) {
                return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "运费错误,请联系商家");
            }

            //统计所有商店订单总价
            allOrderActualPrice += storeOrder.getActualPrice();

            //生成订单信息,并持久化
            OrderInfoDO orderInfoDO = new OrderInfoDO();
            orderInfoDO.setOrderNo(orderNo);
            orderInfoDO.setUserId(submitOrderDTO.getUserId());
            orderInfoDO.setStoreId(storeOrder.getStoreId());
            orderInfoDO.setMasterState(OrderStateEnum.MasterState.PAYMENT_STATE.code);
            orderInfoDO.setPaymentState(OrderStateEnum.PaymentState.UNPAY.code);
            orderInfoDO.setOrderSource(submitOrderDTO.getOrderSource());
            orderInfoDO.setTotalPrice(storeOrder.getTotalPrice());
            orderInfoDO.setActualPrice(storeOrder.getActualPrice());
            orderInfoDO.setFreightPrice(storeOrder.getFreightPrice());
            orderInfoDO.setRemarkUser(storeOrder.getRemarkUser());
            orderInfoDO.setConsigneeName(submitOrderDTO.getConsigneeName());
            orderInfoDO.setConsigneeMobile(submitOrderDTO.getConsigneeMobile());
            orderInfoDO.setConsigneeAddress(submitOrderDTO.getConsigneeAddress());
            orderInfoDO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
            orderInfoDO.setGmtModified(new Date());
            orderInfoDO.setGmtCreate(new Date());
            orderInfoDAO.insert(orderInfoDO);

            //持久化订单详情信息
            orderItemDOList.forEach(orderItemDTO -> {
                orderItemDTO.setOrderInfoId(orderInfoDO.getId());
                orderItemService.insert(orderItemDTO);
                orderItemDTO.getItemAttrList().forEach(orderItemAttrDO -> orderItemAttrDO.setOrderItemId(orderItemDTO.getId()));
                orderItemAttrService.insertBatch(orderItemDTO.getItemAttrList());
            });
            orderNoList.add(orderInfoDO.getOrderNo());

            //记录商品主图
            orderImgMap.put(orderInfoDO.getId(), spuMainImgList);
        }

        //更新商品销量
        productSalesVolumeDOList.forEach(productSalesVolumeDO -> {
            ProductSalesVolumeDO salesVolumeDO = productSalesVolumeService.selectBySkuId(productSalesVolumeDO.getSkuId());
            if (salesVolumeDO != null) {
                //已有记录更新
                salesVolumeDO.setNum(salesVolumeDO.getNum() + productSalesVolumeDO.getNum());
                salesVolumeDO.setGmtModified(new Date());
                productSalesVolumeService.updateById(salesVolumeDO);
            } else {
                //未有记录新增
                salesVolumeDO = new ProductSalesVolumeDO();
                salesVolumeDO.setSpuId(productSalesVolumeDO.getSpuId());
                salesVolumeDO.setSkuId(productSalesVolumeDO.getSkuId());
                salesVolumeDO.setNum(productSalesVolumeDO.getNum());
                salesVolumeDO.setGmtModified(new Date());
                salesVolumeDO.setGmtCreate(new Date());
                salesVolumeDO.setIsDeleted(CommonStateEnum.IsDeleted.NOT_DELETED.code);
                productSalesVolumeService.insert(salesVolumeDO);
            }
        });

        //复制商品主图到订单信息
        for (Map.Entry<Integer, List<String>> orderMainImg : orderImgMap.entrySet()) {
            orderMainImg.getValue().forEach(imgPath -> FileUtils.copyFileToDirectoryStr(imgPath, CommonFolderConstant.getOrderInfoImg(orderMainImg.getKey())));
        }

        //结果整合
        Map<String, Object> resultMap = new HashMap<>(3);
        if (orderNoList.size() > 1) {
            //合并订单支付
            OrderPayMergerDO payMerger = new OrderPayMergerDO();
            payMerger.setMergerNo(PayMergerNoUtils.generate());
            payMerger.setOrderIds(String.join(",", orderNoList));
            payMerger.setState(OrderStateEnum.MergerPayState.UNPAY.code);
            payMerger.setGmtModified(new Date());
            payMerger.setGmtCreate(new Date());
            orderPayMergerService.insert(payMerger);
            resultMap.put("type", 1);
            resultMap.put("payMergerNo", payMerger.getMergerNo());
        } else {
            //单个订单
            resultMap.put("type", 0);
            resultMap.put("orderNo", orderNoList.get(0));
        }
        resultMap.put("orderNum", orderNoList.size());
        resultMap.put("actualPrice", allOrderActualPrice);
        return ResponseResult.success(resultMap);
    }

    @Override
    public Result getFreightPriceByOrder(Integer storeId, String spuIds) {
        List<Integer> spuIdList = Arrays.stream(spuIds.split(",")).map(s -> Integer.parseInt(s.trim())).distinct().collect(Collectors.toList());
        List<ProductSpuDO> spuDOList = productSpuService.selectBatchIds(spuIdList);
        if (spuDOList.size() != spuIdList.size()) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "信息有误,获取运费失败,请联系商家(注:SPUID有误)");
        }
        Long price = null;
        for (ProductSpuDO spuDO : spuDOList) {
            if (!storeId.equals(spuDO.getStoreId())) {
                return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "信息有误,获取运费失败,请联系商家(注:商店ID或SPUID有误)");
            }
            ProductFreightTemplateDO freightTemplateDO = freightTemplateService.selectById(spuDO.getFreightTemplateId());
            if (price == null) {
                price = freightTemplateDO.getPrice();
            } else if (price > freightTemplateDO.getPrice()) {
                price = freightTemplateDO.getPrice();
            }
        }
        if (price == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "获取运费失败,请联系商家(注:未找到运费模版)");
        } else {
            Map<String, Object> resultMap = new HashMap<>(2);
            resultMap.put("price", price);
            resultMap.put("storeId", storeId);
            return ResponseResult.success(resultMap);
        }
    }

    @Override
    public List<OrderInfoDO> selectByOrderNoList(List<String> orderNoList) {
        return orderInfoDAO.selectByOrderNoList(orderNoList);
    }

    @Override
    public Result listPageByUser(Pager pager) {
        baseList(pager);
        return ResponseResult.success(pager);
    }

    @Override
    public Result listPageByStore(Pager pager) {
        baseList(pager);
        return ResponseResult.success(pager);
    }

    @Override
    public Result getInfoByIdAndUser(Integer userId, Integer id) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("userId", userId);
        params.put("id", id);
        return baseInfo(params);
    }

    @Override
    public Result getInfoByIdAndStore(Integer storeId, Integer id) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("storeId", storeId);
        params.put("id", id);
        return baseInfo(params);
    }


    private Result baseInfo(Map<String, Object> params) {
        OrderInfoDTO orderInfoDTO = orderInfoDAO.selectDTOById(params);
        if (orderInfoDTO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "查询订单不存在");
        }
        List<OrderItemDTO> orderItemDTOList = orderItemService.selectDTOByOrderId(orderInfoDTO.getId());
        orderItemDTOList.forEach(orderItemDTO -> {
            EntityWrapper<OrderItemAttrDO> itemAttrDOEntityWrapper = new EntityWrapper<>();
            itemAttrDOEntityWrapper.eq("order_item_id", orderItemDTO.getId());
            orderItemDTO.setItemAttrList(orderItemAttrService.selectList(itemAttrDOEntityWrapper));
        });
        orderInfoDTO.setItemList(orderItemDTOList);
        return ResponseResult.success(orderInfoDTO);
    }

    private void baseList(Pager pager) {
        pager.putParam("isDeleted", CommonStateEnum.IsDeleted.NOT_DELETED.code);
        if (pager.getParamMap().containsKey("state")) {
            switch ((int) pager.getParamMap().get("state")) {
                case 0:
                    pager.putParam("masterState", OrderStateEnum.MasterState.PAYMENT_STATE.code);
                    pager.putParam("paymentState", OrderStateEnum.PaymentState.UNPAY.code);
                    break;
                case 1:
                    pager.putParam("masterState", OrderStateEnum.MasterState.DELIVER_STATE.code);
                    pager.putParam("deliverState", OrderStateEnum.DeliverState.WAIT_DELIVERY.code);
                    break;
                case 2:
                    pager.putParam("masterState", OrderStateEnum.MasterState.DELIVER_STATE.code);
                    pager.putParam("deliverState", OrderStateEnum.DeliverState.ALREADY_DELIVERY.code);
                    break;
                case 3:
                    pager.putParam("masterState", OrderStateEnum.MasterState.COMPLETE_TRANSACTION.code);
                    pager.putParam("commentState", OrderStateEnum.CommentState.WAIT_COMMENT.code);
                    break;
                case 4:
                    pager.putParam("masterState", OrderStateEnum.MasterState.COMPLETE_TRANSACTION.code);
                    break;
                case 5:
                    pager.putParam("masterState", OrderStateEnum.MasterState.TRADING_CLOSE.code);
                    break;
                default:
                    break;
            }
        }
        List<OrderInfoDTO> orderInfoDTOList = orderInfoDAO.selectListPage(pager, pager.getParamMap());
        orderInfoDTOList.forEach(orderInfoDTO -> {
            List<OrderItemDTO> orderItemDTOList = orderItemService.selectDTOByOrderId(orderInfoDTO.getId());
            orderItemDTOList.forEach(orderItemDTO -> {
                EntityWrapper<OrderItemAttrDO> itemAttrDOEntityWrapper = new EntityWrapper<>();
                itemAttrDOEntityWrapper.eq("order_item_id", orderItemDTO.getId());
                orderItemDTO.setItemAttrList(orderItemAttrService.selectList(itemAttrDOEntityWrapper));
            });
            orderInfoDTO.setItemList(orderItemDTOList);
        });
        pager.setRecords(orderInfoDTOList);
    }

    @Override
    public Result consignment(ConsignmentDTO consignmentDTO) {
        OrderInfoDO orderInfoDO = orderInfoDAO.selectByOrderNoAndStoreId(consignmentDTO.getOrderNo(), consignmentDTO.getStoreId());
        if (orderInfoDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "订单信息有误,请确认订单信息");
        }
        if (orderInfoDO.getMasterState() != OrderStateEnum.MasterState.DELIVER_STATE.code ||
                orderInfoDO.getDeliverState() == null ||
                orderInfoDO.getDeliverState() != OrderStateEnum.DeliverState.WAIT_DELIVERY.code) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "当前订单不在待发货状态,请确认订单");
        }
        orderInfoDO.setDeliverState(OrderStateEnum.DeliverState.ALREADY_DELIVERY.code);
        orderInfoDO.setExpressState(OrderStateEnum.ExpressState.WAITING_TAKING.code);
        orderInfoDO.setExpressCode(consignmentDTO.getExpressCode());
        orderInfoDO.setExpressName(consignmentDTO.getExpressName());
        orderInfoDO.setExpressNo(consignmentDTO.getExpressNo());
        orderInfoDO.setDeliverTime(new Date());
        return updateById(orderInfoDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result getOrderPayState(Integer userId, String orderNo) {
        EntityWrapper<OrderInfoDO> orderInfoDOEntityWrapper = new EntityWrapper<>();
        orderInfoDOEntityWrapper.eq("user_id", userId);
        orderInfoDOEntityWrapper.eq("order_no", orderNo);
        OrderInfoDO orderInfoDO = selectOne(orderInfoDOEntityWrapper);
        if (orderInfoDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "订单信息有误,请确认订单信息");
        }
        Integer state = orderInfoDO.getPaymentState();
        Map<String, Object> resultMap = new HashMap<>(1);
        resultMap.put("state", state);
        return ResponseResult.success(resultMap);
    }

    @Override
    public Result getMergerPayState(String payMergerNo) {
        EntityWrapper<OrderPayMergerDO> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("merger_no", payMergerNo);
        OrderPayMergerDO orderPayMergerDO = orderPayMergerService.selectOne(entityWrapper);
        if (orderPayMergerDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "合并订单信息有误,请确认信息");
        }
        Integer state = orderPayMergerDO.getState();
        Map<String, Object> resultMap = new HashMap<>(1);
        resultMap.put("state", state);
        return ResponseResult.success(resultMap);
    }

    @Override
    public Result confirmDeliver(Integer userId, String orderNo) {
        EntityWrapper<OrderInfoDO> orderInfoDOEntityWrapper = new EntityWrapper<>();
        orderInfoDOEntityWrapper.eq("user_id", userId);
        orderInfoDOEntityWrapper.eq("order_no", orderNo);
        OrderInfoDO orderInfoDO = selectOne(orderInfoDOEntityWrapper);
        if (orderInfoDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "订单号有误");
        }
        if (orderInfoDO.getDeliverState() == OrderStateEnum.DeliverState.COMPLETE_DELIVER.code) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "已经确认收货,不要重复确认");
        }
        if (orderInfoDO.getMasterState() != OrderStateEnum.MasterState.DELIVER_STATE.code ||
                orderInfoDO.getDeliverState() != OrderStateEnum.DeliverState.ALREADY_DELIVERY.code) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "确认收货失败,状态错误,请联系管理员");
        }
        //更新订单状态
        orderInfoDO.setMasterState(OrderStateEnum.MasterState.COMPLETE_TRANSACTION.code);
        orderInfoDO.setDeliverState(OrderStateEnum.DeliverState.COMPLETE_DELIVER.code);
        orderInfoDO.setExpressState(OrderStateEnum.ExpressState.ACKNOWLEDGE_RECEIPT.code);
        orderInfoDO.setCommentState(OrderStateEnum.CommentState.WAIT_COMMENT.code);
        orderInfoDAO.updateById(orderInfoDO);
        orderItemService.updateCommentByOrderId(orderInfoDO.getId(), OrderStateEnum.CommentState.WAIT_COMMENT);
        return ResponseResult.success();
    }

    @Override
    public Result listPage(Pager pager) {
        baseList(pager);
        return ResponseResult.success(pager);
    }

    @Override
    public Result getInfoById(Integer id) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("id", id);
        return baseInfo(params);
    }
}
