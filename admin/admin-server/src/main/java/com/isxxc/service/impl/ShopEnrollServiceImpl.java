package com.isxxc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.dao.ShopEnrollDAO;
import com.isxxc.domain.dto.ShopEnrollDTO;
import com.isxxc.domain.entity.ShopEnrollDO;
import com.isxxc.service.ShopEnrollService;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.isxxc.constant.CommonFolderConstant;import com.isxxc.constant.CommonStateEnum;
import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.util.TimeUtils;

;

/**
 * <p> 服务实现类 </p>
 *
 * @author likq
 * @since 2017-11-22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShopEnrollServiceImpl extends ServiceImpl<ShopEnrollDAO, ShopEnrollDO> implements ShopEnrollService {

    @Resource
    private ShopEnrollDAO shopEnrollDAO;

    @Override
    public Result findAll() {
        EntityWrapper<ShopEnrollDO> shopEnrollDOEntityWrapper = new EntityWrapper<>();
        shopEnrollDOEntityWrapper.eq("is_deleted", CommonStateEnum.IsDisable.NOT_DISABLE.code);
        List<ShopEnrollDO> shopEnrollDOList = shopEnrollDAO.selectList(shopEnrollDOEntityWrapper);
        return ResponseResult.success(shopEnrollDOList);
    }

    @Override
    public Result findById(Integer id) {
        ShopEnrollDO shopEnrollDO = selectById(id);
        if (shopEnrollDO == null) {
            return ResponseResult.paramNotNull("ID不存在");
        }
        ShopEnrollDTO shopEnrollDTO = new ShopEnrollDTO(shopEnrollDO);
        return ResponseResult.success(shopEnrollDTO);
    }

    @Override
    public Result exportExcel(HttpServletResponse response) {
        EntityWrapper<ShopEnrollDO> shopEnrollDOEntityWrapper = new EntityWrapper<>();
        shopEnrollDOEntityWrapper.eq("is_deleted", CommonStateEnum.IsDisable.NOT_DISABLE.code);
        List<ShopEnrollDO> shopEnrollDOList = shopEnrollDAO.selectList(shopEnrollDOEntityWrapper);
        try {
            //创建HSSFWorkbook对象(excel的文档对象)
            HSSFWorkbook wb = new HSSFWorkbook();
            //建立新的sheet对象（excel的表单）
            HSSFSheet sheet = wb.createSheet("成绩表");
            //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
            HSSFRow row1 = sheet.createRow(0);
            //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
            HSSFCell cell = row1.createCell(0);
            //设置单元格内容
            cell.setCellValue("沙县小吃登记列表");
            //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 25));
            //在sheet里创建第二行
            HSSFRow row2 = sheet.createRow(1);
            //创建单元格并设置单元格内容
            row2.createCell(0).setCellValue("姓名");
            row2.createCell(1).setCellValue("联系方式");
            row2.createCell(2).setCellValue("门店名称");
            row2.createCell(3).setCellValue("门店地址");
            row2.createCell(4).setCellValue("门店职工人数");
            row2.createCell(5).setCellValue("餐厅面积");
            row2.createCell(6).setCellValue("年营业额");
            row2.createCell(7).setCellValue("餐厅桌台数");
            row2.createCell(8).setCellValue("是否有POS机");
            row2.createCell(9).setCellValue("POS品牌");
            row2.createCell(10).setCellValue("喜欢POS还是扫码点餐");
            row2.createCell(11).setCellValue("收银方式");
            row2.createCell(12).setCellValue("银行卡号");
            row2.createCell(13).setCellValue("开户行名称");
            row2.createCell(14).setCellValue("留言");
            row2.createCell(15).setCellValue("银行卡照片");
            row2.createCell(16).setCellValue("身份证背面照片");
            row2.createCell(17).setCellValue("身份证照片");
            row2.createCell(18).setCellValue("营业执照照片");
            row2.createCell(19).setCellValue("门店店面照片");
            row2.createCell(20).setCellValue("门店厅堂照片");
            row2.createCell(21).setCellValue("热销菜单照片");
            row2.createCell(22).setCellValue("报时时间");
            for (int i = 0; i < shopEnrollDOList.size(); i++) {
                ShopEnrollDTO shopEnrollDTO = new ShopEnrollDTO(shopEnrollDOList.get(i));
                HSSFRow row = sheet.createRow(i + 2);
                row.createCell(0).setCellValue(StringUtils.isNotBlank(shopEnrollDTO.getName()) ? shopEnrollDTO.getName() : "");
                row.createCell(1).setCellValue(StringUtils.isNotBlank(shopEnrollDTO.getMobileNo()) ? shopEnrollDTO.getMobileNo() : "");
                row.createCell(2).setCellValue(StringUtils.isNotBlank(shopEnrollDTO.getShopName()) ? shopEnrollDTO.getShopName() : "");
                row.createCell(3).setCellValue(StringUtils.isNotBlank(shopEnrollDTO.getShopAddress()) ? shopEnrollDTO.getShopAddress() : "");
                row.createCell(4).setCellValue(shopEnrollDTO.getShopStaff() == null ? 0 : shopEnrollDTO.getShopStaff());
                row.createCell(5).setCellValue(shopEnrollDTO.getShopArea() == null ? 0 : shopEnrollDTO.getShopArea());
                row.createCell(6).setCellValue(shopEnrollDTO.getAnnualSalesVolume() == null ? 0 : shopEnrollDTO.getAnnualSalesVolume());
                row.createCell(7).setCellValue(shopEnrollDTO.getDiningTableNumber() == null ? 0 : shopEnrollDTO.getDiningTableNumber());
                row.createCell(8).setCellValue(shopEnrollDTO.getPosStatus() != null ? (shopEnrollDTO.getPosStatus() == 0 ? "无" : "有") : "");
                row.createCell(9).setCellValue(StringUtils.isNotBlank(shopEnrollDTO.getPosBrand()) ? shopEnrollDTO.getPosBrand() : "");
                row.createCell(10).setCellValue(shopEnrollDTO.getOrderDishesType() != null ? (shopEnrollDTO.getOrderDishesType() == 0 ? "POST" : "扫码") : "");
                row.createCell(11).setCellValue(gatheringTypesFun(gatheringTypesFun(shopEnrollDTO.getGatheringTypes())));
                row.createCell(12).setCellValue(StringUtils.isNotBlank(shopEnrollDTO.getBankNo()) ? shopEnrollDTO.getBankNo() : "");
                row.createCell(13).setCellValue(StringUtils.isNotBlank(shopEnrollDTO.getBankName()) ? shopEnrollDTO.getBankName() : "");
                row.createCell(14).setCellValue(StringUtils.isNotBlank(shopEnrollDTO.getLeaveMessage()) ? shopEnrollDTO.getLeaveMessage() : "");
                row.createCell(15).setCellValue(StringUtils.isNotBlank(shopEnrollDTO.getBankCardWebImg()) ? shopEnrollDTO.getBankCardWebImg() : "");
                row.createCell(17).setCellValue(StringUtils.isNotBlank(shopEnrollDTO.getIdentityCardWebImg()) ? shopEnrollDTO.getIdentityCardWebImg() : "");
                row.createCell(16).setCellValue(StringUtils.isNotBlank(shopEnrollDTO.getIdentityCardBackWebImg()) ? shopEnrollDTO.getIdentityCardBackWebImg() : "");
                row.createCell(18).setCellValue(StringUtils.isNotBlank(shopEnrollDTO.getBusinessLicenseWebImg()) ? shopEnrollDTO.getBusinessLicenseWebImg() : "");
                row.createCell(19).setCellValue(StringUtils.isNotBlank(shopEnrollDTO.getShopFacadeWebImg()) ? shopEnrollDTO.getShopFacadeWebImg() : "");
                row.createCell(20).setCellValue(StringUtils.isNotBlank(shopEnrollDTO.getShopHallWebImg()) ? shopEnrollDTO.getShopHallWebImg() : "");
                row.createCell(21).setCellValue(StringUtils.isNotBlank(shopEnrollDTO.getHotMenuWebImg()) ? shopEnrollDTO.getHotMenuWebImg() : "");
                row.createCell(22).setCellValue(TimeUtils.parseTime(TimeUtils.uDateToLocalDateTime(shopEnrollDTO.getGmtCreate()), TimeUtils.TimeFormat.LONG_DATE_PATTERN_LINE));
            }

            String fileName = TimeUtils.getCurrentDateTime(TimeUtils.TimeFormat.LONG_DATE_PATTERN_UNSIGNED_SSS) + ".xlsx";
            File folder = new File(CommonFolderConstant.getShopEnrollExcelPath());
            if (!folder.exists()) {
                folder.mkdir();
            }
            File xlsFile = new File(CommonFolderConstant.getShopEnrollExcelPath(), fileName);
            FileOutputStream fos = new FileOutputStream(xlsFile);
            wb.write(fos);
            fos.close();
            System.out.println("导出完成");
            Map<String, String> resultMap = new HashMap<>(1);
            resultMap.put("url", CommonFolderConstant.getShopEnrollExcelWebPath(fileName));
            return ResponseResult.success(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("导出失败");
            return ResponseResult.serverError();
        }
    }

    private String gatheringTypesFun(String gatheringTypes) {
        if (StringUtils.isBlank(gatheringTypes)) {
            return "";
        }
        gatheringTypes = gatheringTypes.replaceAll("0", "现金");
        gatheringTypes = gatheringTypes.replaceAll("1", "微信");
        gatheringTypes = gatheringTypes.replaceAll("2", "支付宝");
        gatheringTypes = gatheringTypes.replaceAll("3", "银行卡");
        return gatheringTypes;
    }
}
