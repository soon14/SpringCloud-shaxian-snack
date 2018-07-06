package cc.likq.util;



import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

public class AESUtil {
    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";
    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS7Padding";
    /**
     * 生成key
     */
    private static final String key = "BXws5XRXz4Ipf9yjycYVteDCFKZbqFCG";//此处为测试key，正式环境请替换成商户密钥
    private static SecretKeySpec secretKey = new SecretKeySpec(Md5Util.MD5Encode(key, "UTF-8").toLowerCase().getBytes(), ALGORITHM);

    /**
     * AES加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encryptData(String data) throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        // 创建密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);
        // 初始化
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64Util.encode(cipher.doFinal(data.getBytes()));
    }

    /**
     * AES解密
     *
     * @param base64Data
     * @return
     * @throws Exception
     */
    public static String decryptData(String base64Data) throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        // 创建密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);
        // 初始化
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64Util.decode(base64Data)));
    }

//    public static void main(String[] args) throws Exception {
//        //String A 为测试字符串，是微信返回过来的退款通知字符串
//        String A = "g9rhHiX7OILGOIlGXO5LOUV/HyWY7WheRzcth8w1GH6AgGsc/7xAtbBstt9T14cepwR3op5eUEwkXSp1SyVVn7yzDotwOfgCfEleO4R/8IOM5AR+g6jbSV0K8EeLJG1HZZu+yeG+NKCyjrgYG/nr6xqPie1Dwq2RIIZ9VKIZm+ffwlP2UOlv/QD3psSPSBUmqQvuXq/OMByFomXe5bE2HD037EKBuHTXQXpptHda/AM/pB2JY3Qw976yqub6fhJ3rvagAwt44WWaUhHmoexS/d/p2SJmwQzoRFMXmmDIJjFctG5zemfS9+GtpfjVC3n+DbbPWl0f9VzEp8nFwkH+Kh6hzJcIIjiwsjL0NfFM0SoQEPRrQP75GHTSMxdHRclaOZGnhLlvxCgYoLvu84arBQDXQ9yiduiVXTuYXZlGni6j0xZIe8bVsIrbYyaud8T8m1vjZK5+cwSxKTK6jIzn0H6Etbvge5tnggi/Jyhewb2jKFegMdLleXsv4j7frZz0s9AtxxDdPbv0OQEAeS49KK4KhPfZRHVU77uwhMAel4GeVF+8scVRCYm23JKRBJaESRXdtUqXZKFcVTo3KEjt7GnKMsilzP6ZvwWgu/rql2oqYByjb8Gfg5Mhl9uAczXatWqtNiwcSpjPb20d7Kc3f/O0Drcw4cyp2mbkHoM30iFvEt5RmLxBCBqLZ09UQV1wM+HtBVyrKAydPH36t+to9LNUSH3SJl44dQPyp6gmrhX83/86OFZdcTb/lvHppmlL7NMoz6Ib6nn20vHSc7aQJsrUCDzi8+awYtwdoj0XAoRLGK/AP0gIgWplNOWYINWxadlZHDlKfAHfsh4FQF6kcgxdL8OHpCfjkR1FB+t4mv12zVlRZUG03fG1QVy5Mu11xHQmMzhX7W0a9mGVxlqRj2mVoFh1BycNc1tlB/x94DrlF4qy3pIeYA+YnpBuZDtrOmVAR+Vo+F2hZ8hkGmPum0h792WCd82TqQbFRxVCcE/zbDD8fbG2wnpYRWq56DrBUKqZ6bkmJOjMEVoIgaTAqTk2rD70D36VWmk0p+grEuY03o9zBcHXT4my10MyU3y6";
//        String B = AESUtil.decryptData(A);
//        System.out.println(B);
//    }
}
