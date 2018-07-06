package cc.likq.base.domain.dto;

/**
 * 会员登录信息
 *
 * @author likq
 */
public class SystemAdminTokenDTO {
    /***
     * 会员ID
     */
    private Integer adminId;
    /***
     * 会员名称
     */
    private String name;
    /***
     * 会话密钥
     */
    private String token;

    public SystemAdminTokenDTO() {
    }

    public SystemAdminTokenDTO(Integer adminId, String name, String token) {
        this.adminId = adminId;
        this.name = name;
        this.token = token;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
