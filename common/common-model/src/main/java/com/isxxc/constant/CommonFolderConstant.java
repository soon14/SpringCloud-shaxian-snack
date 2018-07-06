package com.isxxc.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author vp
 */
@Component
@ConfigurationProperties(prefix = "CommonFolderConstant")
public class CommonFolderConstant {
    /***
     * 系统默认路径
     */
    private static String systemPath;
    /***
     * WEB默认路径
     */
    private static String webPath;
    /***
     * 图片临时文件夹
     */
    private final static String IMAGE_TEMP = "image_temp";
    /***
     * 店铺登记图片文件夹
     */
    private final static String SHOP_ENROLL = "shop_enroll";
    /***
     * 登记表文件夹
     */
    private final static String SHOP_ENROLL_EXCEL = "shop_enroll_excel";
    /***
     * 头像图片文件夹
     */
    private final static String AVATER = "avater";
    /***
     * 图片文件夹
     */
    private final static String IMAGE = "image";
    /***
     * 视频文件夹
     */
    private final static String VIDEO = "video";
    /***
     * 描述内容
     */
    private final static String DESC = "desc";
    /***
     * 档案照片文件档
     */
    private final static String USER_PROFIL = "user_profil";
    /***
     * 资讯文件夹
     */
    private final static String INFORMATION = "information";
    /***
     *封面图片
     */
    private final static String COVER_IMAGE = "coverImg";
    /***
     *投资融资
     */
    private final static String FINANCING = "financing";
    /***
     *小贷
     */
    private final static String LOANSINFO = "loansInfo";
    /***
     * 产品品牌
     */
    private final static String PRODUCT_BRAND = "productBrand";
    /***
     * 产品信息
     */
    private final static String PRODUCT_SUP = "productSpu";
    /***
     * 产品评价图片
     */
    private final static String PRODUCT_COMMENT = "productComment";
    /***
     * 广告图片
     */
    private final static String AD = "ad";
    /***
     * 订单信息
     */
    private final static String ORDER_INFO = "orderInfo";

    /***
     *转让/求租照片文件夹
     */
    private final static String SHOP_TRANSFER = "shopTransfer";

    public static String getImageTempPath(String fileName) {
        return systemPath + IMAGE_TEMP + File.separator + fileName;
    }

    public static String getImageTempWebPath(String fileName) {
        return webPath + IMAGE_TEMP + "/" + fileName;
    }

    public static String getShopEnrollPath() {
        return systemPath + SHOP_ENROLL + File.separator;
    }

    public static String getShopEnrollWebPath(String fileName) {
        return webPath + SHOP_ENROLL + "/" + fileName;
    }

    public static String getSystemPath() {
        return systemPath;
    }

    public static void setSystemPath(String systemPath) {
        CommonFolderConstant.systemPath = systemPath;
    }

    public static String getWebPath() {
        return webPath;
    }

    public static void setWebPath(String webPath) {
        CommonFolderConstant.webPath = webPath;
    }

    public static String getShopEnrollExcelPath() {
        return systemPath + SHOP_ENROLL_EXCEL + File.separator;
    }

    public static String getShopEnrollExcelWebPath(String fileName) {
        return webPath + SHOP_ENROLL_EXCEL + "/" + fileName;
    }

    public static String getAvaterPath(String fileName) {
        return systemPath + AVATER + File.separator + fileName;
    }

    public static String getAvaterPath() {
        return systemPath + AVATER + File.separator;
    }

    public static String getAvaterWebPath(String fileName) {
        return webPath + AVATER + "/" + fileName;
    }

    public static String getUserProfilPath() {
        return systemPath + USER_PROFIL + File.separator;
    }

    public static String getUserProfilWebPath(String fileName) {
        return webPath + USER_PROFIL + "/" + fileName;
    }

    public static String getInformationImgPath(String folderName) {
        return systemPath + INFORMATION + File.separator + folderName + File.separator + IMAGE + File.separator;
    }

    public static String getInformationPagePath(String folderName) {
        return systemPath + INFORMATION + File.separator + folderName + File.separator;
    }

    public static String getInformationPageWebPath(String folderName, String fileName) {
        return webPath + INFORMATION + "/" + folderName + "/" + fileName;
    }

    public static String getInformationVideoPath(String folderName) {
        return systemPath + INFORMATION + File.separator + folderName + File.separator + VIDEO + File.separator;
    }

    public static String getInformationCoverImgPath(String folderName) {
        return systemPath + INFORMATION + File.separator + folderName + File.separator + COVER_IMAGE + File.separator;
    }

    public static String getInformationCoverImgWebPath(String folderName, String fileName) {
        return webPath + INFORMATION + "/" + folderName + "/" + COVER_IMAGE + "/" + fileName;
    }

    public static String getInformationImgWebPath(String folderName, String fileName) {
        return webPath + INFORMATION + "/" + folderName + "/" + IMAGE + "/" + fileName;
    }

    public static String getImgWebRelativePath(String folderName, String fileName) {
        return IMAGE + "/" + fileName;
    }

    public static String geVideoWebRelativePath(String folderName, String fileName) {
        return VIDEO + "/" + fileName;
    }

    public static String getInformationVideoWebPath(String folderName, String fileName) {
        return webPath + INFORMATION + "/" + folderName + "/" + VIDEO + "/" + fileName;
    }

    public static String getInformationWebPath(String sourceName, String fileName) {
        return webPath + INFORMATION + "/" + sourceName + "/" + fileName;
    }

    public static String getShopTransferPath() {
        return systemPath + SHOP_TRANSFER + File.separator;
    }

    public static String getShopTransferWebPath(String fileName) {
        return webPath + SHOP_TRANSFER + "/" + fileName;
    }

    public static String getFinancingPath() {
        return systemPath + FINANCING + File.separator;
    }

    public static String getFinancingWebPath(String fileName) {
        return webPath + FINANCING + "/" + fileName;
    }

    public static String getLoansInfoPath() {
        return systemPath + FINANCING + File.separator;
    }

    public static String getLoansInfoWebPath(String fileName) {
        return webPath + FINANCING + "/" + fileName;
    }

    public static String getProductBrandPath() {
        return systemPath + PRODUCT_BRAND + File.separator;
    }

    public static String getProductBrandWebPath(String fileName) {
        return webPath + PRODUCT_BRAND + "/" + fileName;
    }

    public static String getProductSpuImgPath(Integer spuId) {
        return systemPath + PRODUCT_SUP + File.separator + spuId + File.separator + IMAGE + File.separator;
    }

    public static String getProductSpuImgWebPath(Integer spuId, String fileName) {
        return webPath + PRODUCT_SUP + "/" + spuId + "/" + IMAGE + "/" + fileName;
    }

    public static String getOrderInfoImg(Integer orderId) {
        return systemPath + ORDER_INFO + File.separator + orderId + File.separator + IMAGE + File.separator;
    }

    public static String getOrderInfoImgWebPath(Integer orderId, String fileName) {
        return webPath + ORDER_INFO + "/" + orderId + "/" + IMAGE + "/" + fileName;
    }

    public static String getProductSpuDescPath(Integer spuId) {
        return systemPath + PRODUCT_SUP + File.separator + spuId + File.separator + DESC + File.separator;
    }

    public static String getProductSpuDescWebPath(Integer spuId, String fileName) {
        return webPath + PRODUCT_SUP + "/" + spuId + "/" + DESC + "/" + fileName;
    }

    public static String getAdPath() {
        return systemPath + AD + File.separator;
    }

    public static String getAdWebPath(String fileName) {
        return webPath + AD + "/" + fileName;
    }

    public static String getProductCommentPath() {
        return systemPath + PRODUCT_COMMENT + File.separator;
    }

    public static String getProductCommentWebPath(String fileName) {
        return webPath + PRODUCT_COMMENT + "/" + fileName;
    }
}
