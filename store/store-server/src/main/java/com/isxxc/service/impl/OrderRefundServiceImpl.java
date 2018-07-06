package com.isxxc.service.impl;

import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;
import cc.likq.util.*;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.StoreAmountEnum;
import com.isxxc.constant.WeixinConstant;
import com.isxxc.dao.*;
import com.isxxc.domain.dto.*;
import com.isxxc.domain.entity.*;
import com.isxxc.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

import static com.isxxc.constant.StoreAmountEnum.*;

/**
 * <p>
 * 订单退款 服务实现类
 * </p>
 *
 * @author mc
 * @since 2018-05-10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderRefundServiceImpl extends ServiceImpl<OrderRefundDAO, OrderRefundDO> implements OrderRefundService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private OrderRefundDAO orderRefundDAO;

    @Resource
    private OrderInfoDAO orderInfoDAO;

    @Resource
    private OrderRefundLogDAO orderRefundLogDAO;

    @Resource
    private WxRefundNotifyDAO wxRefundNotifyDAO;

    @Resource
    private UserAccountDAO userAccountDAO;


    @Resource
    private StoreAmountDAO storeAmountDAO;

    @Resource
    private UserStoreProfilDAO userStoreProfilDAO;

    @Resource
    private OrderItemService orderItemService;

    @Resource
    private OrderItemAttrService orderItemAttrService;

    @Resource
    private StoreAmountService storeAmountService;





    /***
     * 客户提交退款申请
     * @param orderRefundListDTO
     * @return
     */
    @Override
    public Result consumerApplyRefund(OrderRefundListDTO orderRefundListDTO) {
        //定义集合将入参实体保存，便于后面写表
        List<OrderRefundDO> orderRefundDOlistnew = new ArrayList<OrderRefundDO>();
        logger.info("进入 store server OrderRefundServiceImpl consumerApplyRefund 方法 orderRefundDTOlist.size() :{}",orderRefundListDTO.getListorderRefundDTO().size());
        //1,先进行参数校验，判断能否进行退款
        String verfied = this.consumerApplyVerify(orderRefundListDTO.getListorderRefundDTO());
        if(!"verify success".equals(verfied)){
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, verfied);
        }
        logger.info("consumerApplyRefund 完成参数校验！");
        //2,校验同一个支付号，是否存在同一个支付号已经进行过退款操作
        //目前分为两种情况，一种是同一个支付号，只能退款一次。一种是同一个单据号只能退款一次
        //现在先做同一个单据号只能退款一次
        //遍历集合校验是否存在提交单据号已经进行过退款
//-------------------------------------------------------------------------------------------
        //处理一个支付号只能退款一次校验
//        String paymentNo = orderRefundDTOlist.get(0).getPaymentNo();
//        if(StringUtils.isBlank(paymentNo)){
//            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "支付单据号为空!");
//        }
//        EntityWrapper<OrderRefundDO> OrderRefundDOEntityWrapper = new EntityWrapper<>();
//        OrderRefundDOEntityWrapper.eq("payment_no",paymentNo);
//        List<OrderRefundDO> orderRefundDOlist =  orderRefundDAO.selectList(OrderRefundDOEntityWrapper);
//        if(orderRefundDOlist !=null && orderRefundDOlist.size()>0){
//            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "同一个支付单据号已经进行过退款，无法再次进行退款!");
//        }
//---------------------------------------------------------------------------------------------
        //处理同一个单据号的退款申请校验
        for(OrderRefundDTO orderRefundDTO : orderRefundListDTO.getListorderRefundDTO()){
            EntityWrapper<OrderRefundDO> OrderRefundDOEntityWrapper = new EntityWrapper<>();
            OrderRefundDOEntityWrapper.eq("order_no",orderRefundDTO.getOrderNo());
            List<OrderRefundDO> orderRefundDOlist =  orderRefundDAO.selectList(OrderRefundDOEntityWrapper);
            if(orderRefundDOlist !=null && orderRefundDOlist.size()>0){
                String rmsg = "同一个订单号已经进行过退款，无法再次进行退款! 订单号:"+orderRefundDTO.getOrderNo();
                return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, rmsg);
            }
            //将入参实体信息转入对应实体集合
            OrderRefundDO orderRefundDO = new OrderRefundDO();
            orderRefundDO.setOrderNo(orderRefundDTO.getOrderNo());
//            orderRefundDO.setMasterState(orderRefundDTO.getMasterState());
            //客户申请订单状态改为5，退款中。  6是微信已经退款
            orderRefundDO.setMasterState(5);
            orderRefundDO.setOrderSource(orderRefundDTO.getOrderSource());
            orderRefundDO.setPaymentNo(orderRefundDTO.getPaymentNo());
            orderRefundDO.setPaymentState(orderRefundDTO.getPaymentState());
            orderRefundDO.setPaymentType(orderRefundDTO.getPaymentType());
            orderRefundDO.setPaymentTime(orderRefundDTO.getPaymentTime());
            orderRefundDO.setRemarkService(orderRefundDTO.getRemarkService());
            orderRefundDO.setRemarkUser(orderRefundDTO.getRemarkUser());
            orderRefundDO.setStoreId(orderRefundDTO.getStoreId());
            orderRefundDO.setStoreName(orderRefundDTO.getStoreName());
            orderRefundDO.setStoreMobileno(orderRefundDTO.getStoreMobileno());
            orderRefundDO.setUserNickname(orderRefundDTO.getUserNickname());
            orderRefundDO.setTotalPrice(orderRefundDTO.getTotalPrice());
            orderRefundDO.setActualPrice(orderRefundDTO.getActualPrice());
            orderRefundDO.setFreightPrice(orderRefundDTO.getFreightPrice());
            orderRefundDO.setCouponPrice(orderRefundDTO.getCouponPrice());
            orderRefundDO.setActivityDiscountPrice(orderRefundDTO.getActivityDiscountPrice());
            orderRefundDO.setCouponId(orderRefundDTO.getCouponId());
            orderRefundDO.setActivityDiscountId(orderRefundDTO.getActivityDiscountId());
            orderRefundDO.setConsigneeName(orderRefundDTO.getConsigneeName());
            orderRefundDO.setConsigneeMobile(orderRefundDTO.getConsigneeMobile());
            orderRefundDO.setConsigneeAddress(orderRefundDTO.getConsigneeAddress());
            orderRefundDO.setDeliverState(orderRefundDTO.getDeliverState());
            orderRefundDO.setDeliverTime(orderRefundDTO.getDeliverTime());
            orderRefundDO.setExpressName(orderRefundDTO.getExpressName());
            orderRefundDO.setExpressNo(orderRefundDTO.getExpressNo());
            orderRefundDO.setExpressCode(orderRefundDTO.getExpressCode());
            orderRefundDO.setExpressState(orderRefundDTO.getExpressState());
            orderRefundDO.setOrderId(orderRefundDTO.getOrderId());
            orderRefundDO.setOrderGmtcreate(orderRefundDTO.getOrderGmtcreate());
            orderRefundDO.setOrderGmtmodified(orderRefundDTO.getOrderGmtmodified());
            orderRefundDO.setApplyState(orderRefundDTO.getApplyState());
            orderRefundDO.setReturnType(orderRefundDTO.getReturnType());
            orderRefundDO.setReturnState(orderRefundDTO.getReturnState());
            orderRefundDO.setReturnCreate(new Date());
            orderRefundDO.setReturnUpdate(new Date());
            orderRefundDO.setAuditor1(orderRefundDTO.getAuditor1());
            orderRefundDO.setAuditor2(orderRefundDTO.getAuditor2());
            orderRefundDO.setAuditReason1(orderRefundDTO.getAuditReason1());
            orderRefundDO.setAuditReason2(orderRefundDTO.getAuditReason2());
            orderRefundDO.setAuditTime1(orderRefundDTO.getAuditTime1());
            orderRefundDO.setAuditTime2(orderRefundDTO.getAuditTime2());
            orderRefundDO.setRefundPerson(orderRefundDTO.getRefundPerson());
            orderRefundDO.setRefundTime(orderRefundDTO.getRefundTime());
            orderRefundDO.setField1(orderRefundDTO.getField1());
            orderRefundDO.setField2(orderRefundDTO.getField2());
            orderRefundDO.setField3(orderRefundDTO.getField3());
            orderRefundDO.setField4(orderRefundDTO.getField4());
            orderRefundDO.setField5(orderRefundDTO.getField5());

            orderRefundDOlistnew.add(orderRefundDO);
        }


        //持久化退款订单数据
        for(OrderRefundDO orderRefundDOnew : orderRefundDOlistnew){
            //退款订单号生成
            String refundNo = OrderNoUtils.generate(OrderNoUtils.OrderType.RETURN_ORDER);
            orderRefundDOnew.setRefundNo(refundNo);
            orderRefundDAO.insert(orderRefundDOnew);

            //修改订单状态
            OrderInfoDO orderInfoDO = orderInfoDAO.selectById(orderRefundDOnew.getOrderId());
            orderInfoDO.setMasterState(5);
            orderInfoDO.setGmtModified(new Date());
            orderInfoDAO.updateById(orderInfoDO);

        }
        return ResponseResult.success(orderRefundDOlistnew);
    }

    /***
     * 退款信息查询
     * @param pager
     * @return
     */
    @Override
    public Result consumerRefundPagelist(Pager pager) {
        Integer storeId = null;
        //手机号查询用户storeId
        if(pager.getParamMap().containsKey("account")){
            String  account = (String) pager.getParamMap().get("account");
            UserAccountDO userAccountDO = userAccountDAO.selectByMobileNo(account.trim());
            if (userAccountDO == null) {
                return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "账号不存在");
            }
            storeId = userStoreProfilDAO.getIdByUserId(userAccountDO.getId());
            pager.putParam("storeId",storeId);
        }




        List<OrderRefundDTO> orderRefundDTOList = new ArrayList<OrderRefundDTO>();

        List<OrderRefundDO> orderRefundDOList = new ArrayList<OrderRefundDO>();
        if (pager.getParamMap().containsKey("storeId")) {
            //商家退款信息列表查询
            orderRefundDOList = orderRefundDAO.storeRefundListPage(pager, pager.getParamMap());
        }else{
            //客户退款信息列表查询
            orderRefundDOList = orderRefundDAO.comsumerRefundListPage(pager, pager.getParamMap());
        }
        for(OrderRefundDO orderRefundDO : orderRefundDOList){
            OrderRefundDTO  OrderRefundDTO  =  new OrderRefundDTO();
//            BeanUtils.copyProperties(OrderRefundDTO,orderRefundDO);
            OrderRefundDTO.setId(orderRefundDO.getId());
            OrderRefundDTO.setRefundNo(orderRefundDO.getRefundNo());
            OrderRefundDTO.setManualRefundPrice(orderRefundDO.getManualRefundPrice());
            OrderRefundDTO.setOrderNo(orderRefundDO.getOrderNo());
            OrderRefundDTO.setMasterState(orderRefundDO.getMasterState());
            OrderRefundDTO.setOrderSource(orderRefundDO.getOrderSource());
            OrderRefundDTO.setPaymentNo(orderRefundDO.getPaymentNo());
            OrderRefundDTO.setPaymentState(orderRefundDO.getPaymentState());
            OrderRefundDTO.setPaymentType(orderRefundDO.getPaymentType());
            OrderRefundDTO.setPaymentTime(orderRefundDO.getPaymentTime());
            OrderRefundDTO.setRemarkService(orderRefundDO.getRemarkService());
            OrderRefundDTO.setRemarkUser(orderRefundDO.getRemarkUser());
            OrderRefundDTO.setStoreId(orderRefundDO.getStoreId());
            OrderRefundDTO.setStoreMobileno(orderRefundDO.getStoreMobileno());
            OrderRefundDTO.setStoreName(orderRefundDO.getStoreName());
            OrderRefundDTO.setUserNickname(orderRefundDO.getUserNickname());
            OrderRefundDTO.setTotalPrice(orderRefundDO.getTotalPrice());
            OrderRefundDTO.setActualPrice(orderRefundDO.getActualPrice());
            OrderRefundDTO.setFreightPrice(orderRefundDO.getFreightPrice());
            OrderRefundDTO.setCouponPrice(orderRefundDO.getCouponPrice());
            OrderRefundDTO.setActivityDiscountPrice(orderRefundDO.getActivityDiscountPrice());
            OrderRefundDTO.setCouponId(orderRefundDO.getStoreId());
            OrderRefundDTO.setActivityDiscountId(orderRefundDO.getActivityDiscountId());
            OrderRefundDTO.setConsigneeName(orderRefundDO.getConsigneeName());
            OrderRefundDTO.setConsigneeMobile(orderRefundDO.getConsigneeMobile());
            OrderRefundDTO.setConsigneeAddress(orderRefundDO.getConsigneeAddress());
            OrderRefundDTO.setDeliverState(orderRefundDO.getDeliverState());
            OrderRefundDTO.setDeliverTime(orderRefundDO.getDeliverTime());
            OrderRefundDTO.setExpressName(orderRefundDO.getExpressName());
            OrderRefundDTO.setExpressCode(orderRefundDO.getExpressCode());
            OrderRefundDTO.setExpressNo(orderRefundDO.getExpressNo());
            OrderRefundDTO.setExpressState(orderRefundDO.getExpressState());
            OrderRefundDTO.setOrderId(orderRefundDO.getOrderId());
            OrderRefundDTO.setOrderGmtcreate(orderRefundDO.getOrderGmtcreate());
            OrderRefundDTO.setOrderGmtmodified(orderRefundDO.getOrderGmtmodified());
            OrderRefundDTO.setApplyState(orderRefundDO.getApplyState());
            OrderRefundDTO.setReturnType(orderRefundDO.getReturnType());
            OrderRefundDTO.setReturnState(orderRefundDO.getReturnState());
            OrderRefundDTO.setReturnCreate(orderRefundDO.getReturnCreate());
            OrderRefundDTO.setReturnUpdate(orderRefundDO.getReturnUpdate());
            OrderRefundDTO.setAuditor1(orderRefundDO.getAuditor1());
            OrderRefundDTO.setAuditor2(orderRefundDO.getAuditor2());
            OrderRefundDTO.setAuditTime1(orderRefundDO.getAuditTime1());
            OrderRefundDTO.setAuditTime2(orderRefundDO.getAuditTime2());
            OrderRefundDTO.setAuditReason1(orderRefundDO.getAuditReason1());
            OrderRefundDTO.setAuditReason2(orderRefundDO.getAuditReason2());
            OrderRefundDTO.setRefundPerson(orderRefundDO.getRefundPerson());
            OrderRefundDTO.setRefundTime(orderRefundDO.getRefundTime());
            OrderRefundDTO.setField1(orderRefundDO.getField1());
            OrderRefundDTO.setField2(orderRefundDO.getField2());
            OrderRefundDTO.setField3(orderRefundDO.getField3());
            OrderRefundDTO.setField4(orderRefundDO.getField4());
            OrderRefundDTO.setField5(orderRefundDO.getField5());

            orderRefundDTOList.add(OrderRefundDTO);
        }

        orderRefundDTOList.forEach(OrderRefundDTO -> {
            List<OrderItemDTO> orderItemDTOList = orderItemService.selectDTOByOrderId(OrderRefundDTO.getOrderId());
            orderItemDTOList.forEach(orderItemDTO -> {
                EntityWrapper<OrderItemAttrDO> itemAttrDOEntityWrapper = new EntityWrapper<>();
                itemAttrDOEntityWrapper.eq("order_item_id", orderItemDTO.getId());
                orderItemDTO.setItemAttrList(orderItemAttrService.selectList(itemAttrDOEntityWrapper));
            });
            OrderRefundDTO.setItemList(orderItemDTOList);
        });

//        Collections.sort(orderRefundDTOList, new Comparator<OrderRefundDTO>() {
//            @Override
//            public int compare(OrderRefundDTO o1, OrderRefundDTO o2) {
//                int i = 1;
//                Long a = o1.getReturnCreate().getTime() - o2.getReturnCreate().getTime();
//                if(a < 0L){
//                    i = -1;
//                }
//                return i;
//            }
//        });



        pager.setRecords(orderRefundDTOList);
        return ResponseResult.success(pager);
    }



    /***
     * 后台退款信息列表查询
     * @param pager
     * @return
     */
    @Override
    public Result listRefundPage(Pager pager) {
        List<OrderRefundDTO> orderRefundDTOList = new ArrayList<OrderRefundDTO>();

        List<OrderRefundDO> orderRefundDOList = new ArrayList<OrderRefundDO>();

        //后台退款信息列表查询
        orderRefundDOList = orderRefundDAO.listRefundPage(pager, pager.getParamMap());

        for(OrderRefundDO orderRefundDO : orderRefundDOList){
            OrderRefundDTO  OrderRefundDTO  =  new OrderRefundDTO();
            OrderRefundDTO.setId(orderRefundDO.getId());
            OrderRefundDTO.setRefundNo(orderRefundDO.getRefundNo());
            OrderRefundDTO.setManualRefundPrice(orderRefundDO.getManualRefundPrice());
            OrderRefundDTO.setOrderNo(orderRefundDO.getOrderNo());
            OrderRefundDTO.setMasterState(orderRefundDO.getMasterState());
            OrderRefundDTO.setOrderSource(orderRefundDO.getOrderSource());
            OrderRefundDTO.setPaymentNo(orderRefundDO.getPaymentNo());
            OrderRefundDTO.setPaymentState(orderRefundDO.getPaymentState());
            OrderRefundDTO.setPaymentType(orderRefundDO.getPaymentType());
            OrderRefundDTO.setPaymentTime(orderRefundDO.getPaymentTime());
            OrderRefundDTO.setRemarkService(orderRefundDO.getRemarkService());
            OrderRefundDTO.setRemarkUser(orderRefundDO.getRemarkUser());
            OrderRefundDTO.setStoreId(orderRefundDO.getStoreId());
            OrderRefundDTO.setStoreMobileno(orderRefundDO.getStoreMobileno());
            OrderRefundDTO.setStoreName(orderRefundDO.getStoreName());
            OrderRefundDTO.setUserNickname(orderRefundDO.getUserNickname());
            OrderRefundDTO.setTotalPrice(orderRefundDO.getTotalPrice());
            OrderRefundDTO.setActualPrice(orderRefundDO.getActualPrice());
            OrderRefundDTO.setFreightPrice(orderRefundDO.getFreightPrice());
            OrderRefundDTO.setCouponPrice(orderRefundDO.getCouponPrice());
            OrderRefundDTO.setActivityDiscountPrice(orderRefundDO.getActivityDiscountPrice());
            OrderRefundDTO.setCouponId(orderRefundDO.getStoreId());
            OrderRefundDTO.setActivityDiscountId(orderRefundDO.getActivityDiscountId());
            OrderRefundDTO.setConsigneeName(orderRefundDO.getConsigneeName());
            OrderRefundDTO.setConsigneeMobile(orderRefundDO.getConsigneeMobile());
            OrderRefundDTO.setConsigneeAddress(orderRefundDO.getConsigneeAddress());
            OrderRefundDTO.setDeliverState(orderRefundDO.getDeliverState());
            OrderRefundDTO.setDeliverTime(orderRefundDO.getDeliverTime());
            OrderRefundDTO.setExpressName(orderRefundDO.getExpressName());
            OrderRefundDTO.setExpressCode(orderRefundDO.getExpressCode());
            OrderRefundDTO.setExpressNo(orderRefundDO.getExpressNo());
            OrderRefundDTO.setExpressState(orderRefundDO.getExpressState());
            OrderRefundDTO.setOrderId(orderRefundDO.getOrderId());
            OrderRefundDTO.setOrderGmtcreate(orderRefundDO.getOrderGmtcreate());
            OrderRefundDTO.setOrderGmtmodified(orderRefundDO.getOrderGmtmodified());
            OrderRefundDTO.setApplyState(orderRefundDO.getApplyState());
            OrderRefundDTO.setReturnType(orderRefundDO.getReturnType());
            OrderRefundDTO.setReturnState(orderRefundDO.getReturnState());
            OrderRefundDTO.setReturnCreate(orderRefundDO.getReturnCreate());
            OrderRefundDTO.setReturnUpdate(orderRefundDO.getReturnUpdate());
            OrderRefundDTO.setAuditor1(orderRefundDO.getAuditor1());
            OrderRefundDTO.setAuditor2(orderRefundDO.getAuditor2());
            OrderRefundDTO.setAuditTime1(orderRefundDO.getAuditTime1());
            OrderRefundDTO.setAuditTime2(orderRefundDO.getAuditTime2());
            OrderRefundDTO.setAuditReason1(orderRefundDO.getAuditReason1());
            OrderRefundDTO.setAuditReason2(orderRefundDO.getAuditReason2());
            OrderRefundDTO.setRefundPerson(orderRefundDO.getRefundPerson());
            OrderRefundDTO.setRefundTime(orderRefundDO.getRefundTime());
            OrderRefundDTO.setField1(orderRefundDO.getField1());
            OrderRefundDTO.setField2(orderRefundDO.getField2());
            OrderRefundDTO.setField3(orderRefundDO.getField3());
            OrderRefundDTO.setField4(orderRefundDO.getField4());
            OrderRefundDTO.setField5(orderRefundDO.getField5());

            orderRefundDTOList.add(OrderRefundDTO);
        }

        orderRefundDTOList.forEach(OrderRefundDTO -> {
            List<OrderItemDTO> orderItemDTOList = orderItemService.selectDTOByOrderId(OrderRefundDTO.getOrderId());
            orderItemDTOList.forEach(orderItemDTO -> {
                EntityWrapper<OrderItemAttrDO> itemAttrDOEntityWrapper = new EntityWrapper<>();
                itemAttrDOEntityWrapper.eq("order_item_id", orderItemDTO.getId());
                orderItemDTO.setItemAttrList(orderItemAttrService.selectList(itemAttrDOEntityWrapper));
            });
            OrderRefundDTO.setItemList(orderItemDTOList);
        });

        pager.setRecords(orderRefundDTOList);
        return ResponseResult.success(pager);
    }


    /***
     * 商家第一次退款审核
     * @param orderRefundDTO
     * @return
     */
    @Override
    public Result sellerFirstAuditRefund(OrderRefundDTO orderRefundDTO) {
        //1，退款信息 。校验订单号，退款单号，支付单号，校验退款状态
        if(StringUtils.isBlank(orderRefundDTO.getOrderNo())){
            String rmsg = "入参订单号为空，不能退款！";
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, rmsg);
        }
        if(StringUtils.isBlank(orderRefundDTO.getRefundNo())){
            String rmsg = "入参退款单号为空，不能退款!";
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, rmsg);
        }
        if(StringUtils.isBlank(orderRefundDTO.getPaymentNo())){
            String rmsg = "入参支付单号为空，不能退款！";
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, rmsg);
        }

        //2，根据订单号，查询订单信息
        OrderInfoDO orderInfoDO = orderInfoDAO.selectById(orderRefundDTO.getOrderId());

        //3,校验订单状态
        if(orderInfoDO.getMasterState() != 5){
            String rmsg = "商家第一次审核，订单信息不在退款中状态 订单号："+orderInfoDO.getOrderNo();
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, rmsg);
        }
        //校验支付状态
        if(orderInfoDO.getPaymentState() != 1 ){
            String rmsg = "商家第一次审核，订单信息非已支付状态 订单号："+orderInfoDO.getOrderNo();
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, rmsg);
        }

        //校验订单货物发送状态
        if(orderInfoDO.getDeliverState() != null){
            if(orderInfoDO.getDeliverState() == 2){
                String rmsg = "商家第一次审核，订单信息 已收货状态 订单号："+orderInfoDO.getOrderNo();
                return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, rmsg);
            }
        }
        //校验退款状态
        if(orderInfoDO.getReturnState()  != null){
            if(orderInfoDO.getReturnState() == 0 || orderInfoDO.getReturnState() == 1){
                String rmsg = "商家第一次审核，订单信息 已经退款过 订单号："+orderInfoDO.getOrderNo();
                return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, rmsg);
            }
        }



        //4，审核校验完成后对退款表信息修改
        //根据退款订单id查询退款订单信息
        OrderRefundDO orderRefundDO = orderRefundDAO.selectById(orderRefundDTO.getId());

        //校验退款订单审核状态
        if(orderRefundDO.getApplyState() != 1){
            String rmsg = "商家第一次审核 申请状态审核失败 无法再次审核！退款单号："+orderRefundDTO.getRefundNo();
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, rmsg);
        }

        orderRefundDO.setApplyState(orderRefundDTO.getApplyState());
        orderRefundDO.setReturnType(orderRefundDTO.getReturnType());
        orderRefundDO.setRefundMode(orderRefundDTO.getRefundMode());

        //根据商家选择状态计算出商家退款金额
        //4，计算最后退款金额
        Long lastactualrefundprice1 = 0L;
        //,判断该订单是部分退款还是全部退款,并计算需要退款金额
        if("1".equals(orderRefundDTO.getRefundMode())){
            //退款金额，等于订单金额
            lastactualrefundprice1 = orderRefundDO.getActualPrice();
        }else if("2".equals(orderRefundDTO.getRefundMode())){
            //退款金额，等于订单金额减去邮费金额
            lastactualrefundprice1 = orderRefundDO.getActualPrice() - orderRefundDO.getFreightPrice();
        }else if("3".equals(orderRefundDTO.getRefundMode())){
            //退款金额，等于商家自定义输入金额
            lastactualrefundprice1 = orderRefundDTO.getManualRefundPrice();
        }else{
            logger.info("OrderRefundServiceImpl sellerFirstAuditRefund 退款信息没有退款方式 refundMode orderNo:"+orderRefundDTO.getOrderNo());
            String rmsg = "OrderRefundServiceImpl sellerFirstAuditRefund 退款信息没有退款方式 refundMode orderNo:"+orderRefundDTO.getOrderNo();
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, rmsg);
        }

        //校验商家退款金额是否小于商家剩余金额
        //=====================
        Result storeamount = storeAmountService.getInfoByStore(orderRefundDO.getStoreId());
        StoreAmountDO storeAmountDO = new StoreAmountDO();
        if(storeamount.getCode() == 200){
            storeAmountDO = (StoreAmountDO)storeamount.getData();
        }else{
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "未查询到商家剩余金额！");
        }
        if(storeAmountDO !=null && storeAmountDO.getAmount() < orderRefundDO.getActualPrice()){
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "商家剩余金额小于退款金额!无法通过退款！");
        }

        orderRefundDO.setManualRefundPrice(lastactualrefundprice1);
        //将支付状态改为退款中状态
//        if(orderRefundDTO.getApplyState() == 2){
//            orderRefundDO.setPaymentState(2);
//        }

        orderRefundDO.setReturnUpdate(new Date());
        orderRefundDO.setRemarkService(orderRefundDTO.getRemarkService());
        orderRefundDO.setAuditor1(orderRefundDTO.getAuditor1());
        orderRefundDO.setAuditReason1(orderRefundDTO.getAuditReason1());
        orderRefundDO.setAuditTime1(new Date());

        Integer orderRefundint = orderRefundDAO.updateById(orderRefundDO);

        if(orderRefundint == 1 && orderRefundDTO.getApplyState() == 2){
            logger.info("商家同意退款,退款信息表更新成功！");
            //更新订单信息
//            orderInfoDO.setPaymentState(2);
            orderInfoDO.setReturnState(orderRefundDTO.getReturnType());
            Integer orderinfoint = orderInfoDAO.updateById(orderInfoDO);
            if(orderinfoint == 1){
                logger.info("商家同意退款,订单信息表更新成功！");
                //记录和修改商家金额，减去退款的金额
                Long lastactualrefundprice = 0L;
                //,判断该订单是部分退款还是全部退款,并计算需要退款金额
                if("1".equals(orderRefundDO.getRefundMode())){
                    //退款金额，等于订单金额
                    lastactualrefundprice = orderRefundDO.getActualPrice();
                }else if("2".equals(orderRefundDO.getRefundMode())){
                    //退款金额，等于订单金额减去邮费金额
                    lastactualrefundprice = orderRefundDO.getActualPrice() - orderRefundDO.getFreightPrice();
                }else if("3".equals(orderRefundDO.getRefundMode())){
                    //退款金额，等于商家自定义输入金额
                    lastactualrefundprice = orderRefundDO.getManualRefundPrice();
                }else{
                    logger.info("OrderRefundServiceImpl sellerFirstAuditRefund 退款信息没有退款方式 refundMode orderRefundDO:"+orderRefundDO.getOrderNo());
                    String rmsg = "OrderRefundServiceImpl sellerFirstAuditRefund 退款信息没有退款方式 refundMode orderRefundDO:"+ orderRefundDO.getOrderNo();
                    return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, rmsg);
                }
                storeAmountService.updateAmount(orderInfoDO.getStoreId(), lastactualrefundprice, orderInfoDO.getOrderNo(), Type.REFUND_AMOUNT);

            }else{
                logger.info("商家同意退款,订单信息表更新失败！更新入参 orderInfoDO:"+orderInfoDO.toString());
                String rmsg = "商家同意退款,订单信息表更新失败！更新入参 orderInfoDO:"+orderInfoDO.toString();
                return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, rmsg);
            }
        }

        if(orderRefundint == 1 && orderRefundDTO.getApplyState() != 2){
            logger.info("OrderRefundServiceImpl sellerFirstAuditRefund 退款信息表更新成功，但是商家未同意退款！");
        }

        if(orderRefundint != 1 ){
            logger.info("OrderRefundServiceImpl sellerFirstAuditRefund 退款信息表更新失败！"+orderRefundDO.toString());
            String rmsg = "退款信息表更新失败！更新入参orderRefundDO:"+orderRefundDO.toString();
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, rmsg);
        }

        return ResponseResult.success(orderRefundDO);
    }


    /***
     * 后台审核商家审核后退款订单
     * @param orderRefundListDTO
     * @return
     */
    @Override
    public Result backstageAuditRefund(OrderRefundListDTO orderRefundListDTO) {

        List<String> result = new ArrayList<String>();
        for(OrderRefundDTO orderRefundDTO : orderRefundListDTO.getListorderRefundDTO()){
            //根据入参id，查询退款订单信息
            OrderRefundDO orderRefundDO = orderRefundDAO.selectById(orderRefundDTO.getId());
            if(orderRefundDO != null){
                //校验商家是否已经审核
                if(orderRefundDO.getApplyState() !=2){
                    logger.info("OrderRefundServiceImpl backstageAuditRefund 后台审核退款失败,该单据号商家审核未通过！orderNo:"+orderRefundDTO.getOrderNo());
                    String rmsg = orderRefundDTO.getOrderNo()+":商家审核未通过!";
                    result.add(rmsg);
                }else{

                    //如果商家已经审核通过则，进行后台审核
                    orderRefundDO.setApplyState(orderRefundDTO.getApplyState());
                    orderRefundDO.setAuditor2(orderRefundDTO.getAuditor2());
                    orderRefundDO.setAuditReason2(orderRefundDTO.getAuditReason2());
                    orderRefundDO.setAuditTime2(new Date());
                    orderRefundDO.setReturnUpdate(new Date());
                    Integer orderRefundint = orderRefundDAO.updateById(orderRefundDO);
                    if(orderRefundint == 1){
                        logger.info("OrderRefundServiceImpl backstageAuditRefund 后台审核退款成功！orderNo:"+orderRefundDTO.getOrderNo());
                        String rmsg = orderRefundDTO.getOrderNo()+":后台审核成功!";
                        //如果后台审核不通过则退款商家账户上金额
                        if(orderRefundDTO.getApplyState() == 13){
                            Result amountresult =  storeAmountService.updateAmount(orderRefundDO.getStoreId(), orderRefundDO.getManualRefundPrice(), orderRefundDO.getOrderNo(), Type.IN_AMOUNT);
                            if(amountresult.getCode() !=200){
                                rmsg = "后台审核返还商家账户上金额失败！";
                                return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, rmsg);
                            }
                        }

                        result.add(rmsg);
                    }else{
                        logger.info("OrderRefundServiceImpl backstageAuditRefund 后台审核退款失败！orderNo:"+orderRefundDTO.getOrderNo());
                        String rmsg = orderRefundDTO.getOrderNo()+":后台审核失败,修改表错误!";
                        result.add(rmsg);

                    }
                }
            }else{
                logger.info("OrderRefundServiceImpl backstageAuditRefund 后台审核退款失败,该退款信息id，未查询到退款订单！orderNo:"+orderRefundDTO.getOrderNo());
                String rmsg = orderRefundDTO.getOrderNo()+":退款id未找到对应退款信息;id==>"+orderRefundDTO.getId();
                result.add(rmsg);
            }
        }

        return ResponseResult.success(result);
    }

    /***
     * 向支付方，发起退款
     * @param pager
     * @return
     */
    @Override
    public Result orderPayRefund(Pager pager) {
        Integer id = (Integer) pager.getParamMap().get("id");
        Integer userId = (Integer) pager.getParamMap().get("userId");
        String returnPerson = (String) pager.getParamMap().get("returnPerson");
        //1，根据入参退款订单表的id获取退款订单信息
        OrderRefundDO orderRefundDO = orderRefundDAO.selectById(id);

        //2,根据申请状态判断是否能够退款
        if(orderRefundDO == null){
            logger.info("OrderRefundServiceImpl orderPayRefund 后台退款失败！根据id未找到对应数据 id:"+id);
            String rmsg = "OrderRefundServiceImpl orderPayRefund 后台退款失败！根据id未找到对应数据 id:"+ id;
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, rmsg);
        }
        if(orderRefundDO.getApplyState() != 3){
            logger.info("OrderRefundServiceImpl orderPayRefund 后台退款失败！退款订单平台未审核通过 id:"+id);
            String rmsg = "OrderRefundServiceImpl orderPayRefund 后台退款失败！退款订单平台未审核通过 id:"+ id;
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, rmsg);
        }

        //3,校验支付时间是否超过一年，超过一年无法退款
        Date nowdate = new Date();
        //注意减去的时间为Long型数据，不然会数据溢出
        Long minusTime = nowdate.getTime() - 365*24*60*60*1000L;
        if(minusTime > orderRefundDO.getPaymentTime().getTime() ){
            logger.info("OrderRefundServiceImpl orderPayRefund 后台退款失败！支付时间大于1年 id:"+id);
            String rmsg = "OrderRefundServiceImpl orderPayRefund 后台退款失败！支付时间大于1年 id:"+ id;
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, rmsg);
        }

        //4，计算最后退款金额
        Long lastactualrefundprice = 0L;
        //,判断该订单是部分退款还是全部退款,并计算需要退款金额
        if("1".equals(orderRefundDO.getRefundMode())){
            //退款金额，等于订单金额
            lastactualrefundprice = orderRefundDO.getActualPrice();
        }else if("2".equals(orderRefundDO.getRefundMode())){
            //退款金额，等于订单金额减去邮费金额
            lastactualrefundprice = orderRefundDO.getActualPrice() - orderRefundDO.getFreightPrice();
        }else if("3".equals(orderRefundDO.getRefundMode())){
            //退款金额，等于商家自定义输入金额
            lastactualrefundprice = orderRefundDO.getManualRefundPrice();
        }else{
            logger.info("OrderRefundServiceImpl orderPayRefund 退款信息没有退款方式 refundMode id:"+id);
            String rmsg = "OrderRefundServiceImpl orderPayRefund 退款信息没有退款方式 refundMode id:"+ id;
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, rmsg);
        }

        //5,计算退款限制金额
        Long limitactualprice = 0L;
        //根据订单号查询订单信息
        OrderInfoDO orderInfoDO = orderInfoDAO.selectById(orderRefundDO.getOrderId());
        if(orderInfoDO == null){
            logger.info("OrderRefundServiceImpl orderPayRefund 根据退款信息orderId 未找到订单信息！ id:"+id+"==>orderId:"+orderRefundDO.getOrderId());
            String rmsg = "OrderRefundServiceImpl orderPayRefund 根据退款信息orderId 未找到订单信息! id:"+id+"==>orderId:"+orderRefundDO.getOrderId();
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, rmsg);
        }
        //,判断该订单是部分退款还是全部退款,并计算需要退款金额
        if("1".equals(orderRefundDO.getRefundMode()) || "3".equals(orderRefundDO.getRefundMode())){
            //限制金额，等于订单金额
            limitactualprice = orderInfoDO.getActualPrice();
        }else if("2".equals(orderRefundDO.getRefundMode())){
            //退款金额，等于订单金额减去邮费金额
            limitactualprice = orderInfoDO.getActualPrice() - orderInfoDO.getFreightPrice();
        }else{
            logger.info("OrderRefundServiceImpl orderPayRefund 退款信息没有退款方式 refundMode id:"+id);
            String rmsg = "OrderRefundServiceImpl orderPayRefund 退款信息没有退款方式 refundMode id:"+ id;
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, rmsg);
        }

        //6，校验退款金额，是否大于限制金额
        if(lastactualrefundprice > limitactualprice){
            logger.info("OrderRefundServiceImpl orderPayRefund 退款金额，大于订单金额  id:"+id+"==>lastactualrefundprice:"+lastactualrefundprice+"==>limitactualprice:"+limitactualprice);
            String rmsg = "OrderRefundServiceImpl orderPayRefund 退款金额，大于订单金额  id:"+ id+"==>lastactualrefundprice:"+lastactualrefundprice+"==>limitactualprice:"+limitactualprice;
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, rmsg);
        }

        //7,判断用户是一次支付多个单据，还是一次支付单个单据
        //定义支付订单总金额
        Long totalprice = 0L;
        List<OrderInfoDO> listOrderInfoDO = orderInfoDAO.selectListBypaymentNo(orderRefundDO.getPaymentNo());
        Integer returntype = null;
        if(listOrderInfoDO != null){
            if(listOrderInfoDO.size() == 1){
                returntype = 0;
                totalprice = listOrderInfoDO.get(0).getActualPrice();
            }

            if(listOrderInfoDO.size()>1){
                returntype = 1;
                for(OrderInfoDO orderInfoDO1 : listOrderInfoDO){
                    totalprice = totalprice + orderInfoDO1.getActualPrice();
                }
            }
        }

        //8,判断退款类型,是需要微信退款还是需要支付宝退款,0为微信支付退款，1为支付宝支付退款
        String payOrderNo = orderRefundDO.getPaymentNo();
        String refundNo= orderRefundDO.getRefundNo();
        long amountPayable= totalprice;
        long refund_fee= lastactualrefundprice;
        String notifyUrl = WeixinConstant.wxrefundnotify_url;

        JSONObject payInfojson = new JSONObject();

        if(orderRefundDO.getPaymentType() == 0){
            //调用微信退款接口
            payInfojson = WeiXinRefundCommonUtils.wxRefundPayment(payOrderNo,refundNo,amountPayable,refund_fee,notifyUrl);

            //记录微信请求日志
            OrderRefundLogDO orderRefundLogDO = new OrderRefundLogDO();
            orderRefundLogDO.setOrderRefundNo(orderRefundDO.getRefundNo());
            orderRefundLogDO.setOrderNos(orderRefundDO.getOrderNo());
            orderRefundLogDO.setOrderCommitNo(orderRefundDO.getPaymentNo());
            orderRefundLogDO.setReturnType(returntype);
            orderRefundLogDO.setPaymentType(orderRefundDO.getPaymentType());
            orderRefundLogDO.setRequestPlaintext(payInfojson.get("mingreqxml").toString());
            orderRefundLogDO.setRequestCiphertext(payInfojson.get("mireqxml").toString());
            orderRefundLogDO.setResponseCode(payInfojson.get("status").toString());
            orderRefundLogDO.setResponsePlaintext(payInfojson.get("mingrspentity").toString());
            orderRefundLogDO.setResponseCiphertext(payInfojson.get("mirspentity").toString());
            orderRefundLogDO.setRefundPerson(returnPerson);
            orderRefundLogDO.setCreateTime(new Date());

            if("success".equals(payInfojson.get("status"))){
                //若微信退款申请成功，首先写入日志表
                orderRefundLogDO.setReturnState(2);
                orderRefundLogDAO.insert(orderRefundLogDO);

                //修改退款信息表状态
                orderRefundDO.setPaymentState(2);//支付状态改为退款中
                orderRefundDO.setReturnState(2);//退款状态改为，已经向微信提交申请，且申请成功
                orderRefundDO.setReturnUpdate(new Date());
                orderRefundDO.setRefundPerson(returnPerson);
                orderRefundDO.setRefundTime(new Date());
                orderRefundDAO.updateById(orderRefundDO);

                //修改订单表状态
                orderInfoDO.setPaymentState(2);
                orderInfoDO.setGmtModified(new Date());
                orderInfoDAO.updateById(orderInfoDO);

                return ResponseResult.success(payInfojson);
            }else{
                //若微信退款申请失败，首先写入日志表
                orderRefundLogDO.setReturnState(12);
                orderRefundLogDAO.insert(orderRefundLogDO);

                //修改退款信息表状态
                if("false".equals(payInfojson.get("status"))) {
                    orderRefundDO.setReturnState(12);//退款状态改为，已经向微信提交申请，且申请失败
                }

                orderRefundDO.setReturnUpdate(new Date());
                orderRefundDO.setRefundPerson(returnPerson);
                orderRefundDO.setRefundTime(new Date());
                orderRefundDAO.updateById(orderRefundDO);

                //返还商户退款金额
                //如果后台审核不通过则退款商家账户上金额

                Result amountresult =  storeAmountService.updateAmount(orderRefundDO.getStoreId(), orderRefundDO.getManualRefundPrice(), orderRefundDO.getOrderNo(), Type.IN_AMOUNT);
                if(amountresult.getCode() !=200){
                    String rmsg = "微信退款申请失败，返还商家金额失败！ orderNo:"+orderRefundDO.getOrderNo();
                    return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, rmsg);
                }

                return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, payInfojson.toJSONString());
            }
        }

        //调用支付宝退款接口
//        if(orderRefundDO.getPaymentType() == 1){
//
//        }
        return  ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "该支付方式不支持退款，orderRefundDO.getPaymentType()："+orderRefundDO.getPaymentType());
    }


    /***
     * 微信退款回调
     * @param paramsMap
     * @return
     */
    @Override
    public Result wxRefundResult(Map<String, String> paramsMap) {
        //1,取出回调返回数据，进行是否退款成功判断
        SortedMap<String, String> requestParams = PayCommonUtil.doXMLParse(paramsMap.get("resultXml"));
        String resXml;
        //2，判断是否为空返回数据
        if (paramsMap == null ){
            logger.info("***********************微信退款,微信退款失败，返回数据requestParams为空 **********************");
            resXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml>";
            return  ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST,resXml);
        }


        if(requestParams.containsKey("return_code") && "SUCCESS".equals(requestParams.get("return_code"))){
            String reqInfo = requestParams.get("req_info");
            try {
                String result  = AESUtil.decryptData(reqInfo);
                SortedMap<String, String> requestParams1 = PayCommonUtil.doXMLParse(result);
                String out_refund_no = null;
                String out_trade_no = null;
                String refund_account = null;
                String refund_fee = null;
                String refund_id = null;
                String refund_recv_accout = null;
                String refund_request_source = null;
                String refund_status = null;
                String settlement_refund_fee = null;
                String settlement_total_fee = null;
                String success_time = null;
                String total_fee = null;
                String transaction_id = null;



                if(requestParams1.containsKey("out_refund_no")){
                    out_refund_no = requestParams1.get("out_refund_no");
                }
                if(requestParams1.containsKey("out_trade_no")){
                    out_trade_no = requestParams1.get("out_trade_no");
                }
                if(requestParams1.containsKey("refund_account")){
                    refund_account = requestParams1.get("refund_account");
                }
                if(requestParams1.containsKey("refund_fee")){
                    refund_fee = requestParams1.get("refund_fee");
                }
                if(requestParams1.containsKey("refund_id")){
                    refund_id = requestParams1.get("refund_id");
                }
                if(requestParams1.containsKey("refund_recv_accout")){
                    refund_recv_accout = requestParams1.get("refund_recv_accout");
                }
                if(requestParams1.containsKey("refund_request_source")){
                    refund_request_source = requestParams1.get("refund_request_source");
                }
                if(requestParams1.containsKey("refund_status")){
                    refund_status = requestParams1.get("refund_status");
                }
                if(requestParams1.containsKey("settlement_refund_fee")){
                    settlement_refund_fee = requestParams1.get("settlement_refund_fee");
                }
                if(requestParams1.containsKey("settlement_total_fee")){
                    settlement_total_fee = requestParams1.get("settlement_total_fee");
                }
                if(requestParams1.containsKey("success_time")){
                    success_time = requestParams1.get("success_time");
                }
                if(requestParams1.containsKey("total_fee")){
                    total_fee = requestParams1.get("total_fee");
                }
                if(requestParams1.containsKey("transaction_id")){
                    transaction_id = requestParams1.get("transaction_id");
                }

                //防重复写入
                OrderRefundDO orderRefundDO = new OrderRefundDO();
                if(out_refund_no != null){
                    orderRefundDO = orderRefundDAO.selectRefundDOByRefundNo(out_refund_no);
                    if(orderRefundDO != null && orderRefundDO.getReturnState() == 3 &&  orderRefundDO.getMasterState() == 6 && orderRefundDO.getPaymentState() == 3){
                        return ResponseResult.success("已经回调成功！多次请求了！");
                    }
                }

                WxRefundNotifyDO wxRefundNotifyDO = new WxRefundNotifyDO();
                wxRefundNotifyDO.setOutRefundNo(out_refund_no);
                wxRefundNotifyDO.setOutTradeNo(out_trade_no);
                wxRefundNotifyDO.setRefundAccount(refund_account);
                wxRefundNotifyDO.setRefundFee(refund_fee);
                wxRefundNotifyDO.setRefundId(refund_id);
                wxRefundNotifyDO.setRefundRecvAccout(refund_recv_accout);
                wxRefundNotifyDO.setRefundRequestSource(refund_request_source);
                wxRefundNotifyDO.setRefundStatus(refund_status);
                wxRefundNotifyDO.setSettlementRefundFee(settlement_refund_fee);
                wxRefundNotifyDO.setSettlementTotalFee(settlement_total_fee);
                wxRefundNotifyDO.setSuccessTime(success_time);
                wxRefundNotifyDO.setTotalFee(total_fee);
                wxRefundNotifyDO.setTransactionId(transaction_id);
                wxRefundNotifyDO.setCreateTime(new Date());

                wxRefundNotifyDAO.insert(wxRefundNotifyDO);

                //若成功，修改退款单状态，修改订单状态
                if(requestParams1.containsKey("refund_status") && "SUCCESS".equals(requestParams1.get("refund_status"))){
                    //修改退款单状态
                    orderRefundDO = orderRefundDAO.selectRefundDOByRefundNo(out_refund_no);
                    orderRefundDO.setMasterState(6);
                    orderRefundDO.setPaymentState(3);
                    orderRefundDO.setReturnState(3);
                    orderRefundDO.setReturnUpdate(new Date());
                    orderRefundDAO.updateById(orderRefundDO);
                    //修改订单状态
                    OrderInfoDO orderInfoDO = orderInfoDAO.selectById(orderRefundDO.getOrderId());
                    orderInfoDO.setMasterState(6);
                    orderInfoDO.setPaymentState(3);
                    orderInfoDO.setGmtModified(new Date());
                    orderInfoDAO.updateById(orderInfoDO);
                }
            } catch (Exception e) {
                logger.error("退款回调解密失败："+e.toString());
                e.printStackTrace();
            }
            return ResponseResult.success("wx notify success !");
        }else{

            logger.info("***********************微信退款,微信退款失败，返回数据 **********************");
            resXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[result_code 为false]]></return_msg></xml>";
            return  ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST,resXml);
        }
    }


    /***
     * 用户申请退款参数校验
     * @param orderRefundDTOlist
     * @return
     */
    public String consumerApplyVerify(List<OrderRefundDTO> orderRefundDTOlist) {
        String consumerApplyVerifystr = "verify success";

        for(OrderRefundDTO orderRefundDTO : orderRefundDTOlist){
            //1，校验订单状态，是否可以申请退款
            if(orderRefundDTO.getMasterState() != 1){
                if(orderRefundDTO.getMasterState() == 0){
                    consumerApplyVerifystr = "订单号："+orderRefundDTO.getOrderNo()+"MasterState 0,还未付款！";
                    break;
                }
                if(orderRefundDTO.getMasterState() == 2){
                    consumerApplyVerifystr = "订单号："+orderRefundDTO.getOrderNo()+"MasterState 2,交易完成！";
                    break;
                }
                if(orderRefundDTO.getMasterState() == 3){
                    consumerApplyVerifystr = "订单号："+orderRefundDTO.getOrderNo()+"MasterState 3,取消订单状态！";
                    break;
                }
                if(orderRefundDTO.getMasterState() == 4){
                    consumerApplyVerifystr = "订单号："+orderRefundDTO.getOrderNo()+"MasterState 4,交易关闭！";
                    break;
                }
                consumerApplyVerifystr = "订单号："+orderRefundDTO.getOrderNo()+"MasterState 订单状态异常！";
                break;

            }

            //2,校验支付状态,是否可以退款
            if(orderRefundDTO.getPaymentState() != 1){
                if(orderRefundDTO.getPaymentState() == 0){
                    consumerApplyVerifystr = "订单号："+orderRefundDTO.getOrderNo()+"PaymentState 0,待支付！";
                    break;
                }
                if(orderRefundDTO.getPaymentState() == 2){
                    consumerApplyVerifystr = "订单号："+orderRefundDTO.getOrderNo()+"PaymentState 2,退款中！";
                    break;
                }
                if(orderRefundDTO.getPaymentState() == 3){
                    consumerApplyVerifystr = "订单号："+orderRefundDTO.getOrderNo()+"PaymentState 3,已退款！";
                    break;
                }
                if(orderRefundDTO.getPaymentState() == 4){
                    consumerApplyVerifystr = "订单号："+orderRefundDTO.getOrderNo()+"PaymentState 4,已作废！";
                    break;
                }
                consumerApplyVerifystr = "订单号："+orderRefundDTO.getOrderNo()+"PaymentState 支付状态异常！";
                break;
            }

            //3，校验支付时间是否大于100天，微信支付只支持1年内单据退款，这里考虑到商户审核等操作，把时间暂定100天
            if(orderRefundDTO.getPaymentTime() != null){
                Date nowdate = new Date();
                //注意减去的时间为Long型数据，不然会数据溢出
                Long minusTime = nowdate.getTime() - 100*24*60*60*1000L;
                if(minusTime > orderRefundDTO.getPaymentTime().getTime() ){
                    consumerApplyVerifystr = "订单号："+orderRefundDTO.getOrderNo()+"PaymentTime 支付时间大于100天！"+orderRefundDTO.getPaymentTime().toString();
                    break;
                }
            }else{
                consumerApplyVerifystr = "订单号："+orderRefundDTO.getOrderNo()+"PaymentTime 入参支付时间为空！";
                break;
            }

            //4,校验货物发送状态
            if(orderRefundDTO.getDeliverState() == 2){
                consumerApplyVerifystr = "订单号："+orderRefundDTO.getOrderNo()+"DeliverState 2 已收货！";
                break;
            }

        }
        return consumerApplyVerifystr;
    }

}
