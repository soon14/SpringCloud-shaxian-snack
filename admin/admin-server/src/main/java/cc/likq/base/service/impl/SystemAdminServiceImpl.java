package cc.likq.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.isxxc.constant.CommonStateEnum;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.redis.connection.jedis.RedisClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cc.likq.base.dao.SystemAdminDAO;
import cc.likq.base.dao.SystemAdminLoginLogDAO;
import cc.likq.base.dao.SystemDepartmentDAO;
import cc.likq.base.domain.dto.SystemAdminDTO;
import cc.likq.base.domain.dto.SystemAdminTokenDTO;
import cc.likq.base.domain.entity.SystemAdminDO;
import cc.likq.base.domain.entity.SystemAdminLoginLogDO;
import cc.likq.base.domain.entity.SystemDepartmentDO;
import cc.likq.base.service.SystemAdminService;
import cc.likq.constant.RedisKeyConstant;
import cc.likq.result.ResponseResult;
import cc.likq.result.Result;
import cc.likq.result.ResultCodeEnum;
import cc.likq.util.MyBeanUtils;
import cc.likq.util.RandomUtils;

/**
 * <p> 服务实现类 </p>
 *
 * @author likq
 * @since 2017-11-22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SystemAdminServiceImpl extends ServiceImpl<SystemAdminDAO, SystemAdminDO> implements SystemAdminService {

    @Resource
    private RedisClient redisClient;

    @Resource
    private SystemAdminDAO systemAdminDAO;

    @Resource
    private SystemAdminLoginLogDAO systemAdminLoginLogDAO;

    @Resource
    private SystemDepartmentDAO systemDepartmentDAO;

    @Override
    public Result save(SystemAdminDO systemAdminDO) {
        SystemAdminDO systemAdminDODB = systemAdminDAO.getByAccount(systemAdminDO.getAccount());
        if (systemAdminDODB != null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "帐号已存在");
        }
        systemAdminDO.setIsDisable(CommonStateEnum.IsDisable.NOT_DISABLE.code);
        systemAdminDO.setPassword(DigestUtils.sha1Hex(systemAdminDO.getPassword()));
        return insert(systemAdminDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result disable(Integer id) {
        return updateDisable(id, CommonStateEnum.IsDisable.HAVE_DISABLE);
    }

    @Override
    public Result cancelDisable(Integer id) {
        return updateDisable(id, CommonStateEnum.IsDisable.NOT_DISABLE);
    }

    @Override
    public Result updateInfo(SystemAdminDO systemAdminDO) {
        SystemAdminDO systemAdminDODB = systemAdminDAO.selectById(systemAdminDO.getId());
        if (systemAdminDODB == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "管理员不存在");
        }
        systemAdminDO.setPassword(null);
        systemAdminDO.setIsDisable(null);
        systemAdminDO.setAccount(null);
        try {
            MyBeanUtils.copyBeanNotNull2Bean(systemAdminDO, systemAdminDODB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateById(systemAdminDODB) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result updatePassword(Integer id, String password, String newPassword) {
        SystemAdminDO systemAdminDO = systemAdminDAO.selectById(id);
        if (systemAdminDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "管理员不存在");
        }
        if (!systemAdminDO.getPassword().equals(DigestUtils.sha1Hex(password))) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "原密码不正确");
        }
        systemAdminDO.setPassword(DigestUtils.sha1Hex(newPassword));
        return updateById(systemAdminDO) ? ResponseResult.success() : ResponseResult.serverError();
    }

    @Override
    public Result login(String account, String password, String ip) {
        SystemAdminDO systemAdminDO = systemAdminDAO.getByAccount(account);
        if (systemAdminDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "管理员不存在");
        }
        if (systemAdminDO.getIsDisable() == CommonStateEnum.IsDisable.HAVE_DISABLE.code) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "管理员已经禁用");
        }
        if (!systemAdminDO.getPassword().equals(DigestUtils.sha1Hex(password))) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "密码错误");
        }
        //生成Token持久化到Redis
        String token = RandomUtils.generateString(25);
        String adminTokenKey = RedisKeyConstant.getAdminTokenKey(systemAdminDO.getId());
        SystemAdminTokenDTO systemAdminTokenDTO = new SystemAdminTokenDTO(systemAdminDO.getId(), systemAdminDO.getName(), token);
        redisClient.set(adminTokenKey, JSON.toJSONString(systemAdminTokenDTO), 1800);

        //持久化登录日志
        systemAdminLoginLogDAO.insert(new SystemAdminLoginLogDO(systemAdminDO.getId(), ip));

        return ResponseResult.success(systemAdminTokenDTO);
    }

    @Override
    public Result logout(Integer id) {
        String adminTokenKey = RedisKeyConstant.getAdminTokenKey(id);
        redisClient.del(adminTokenKey);
        return ResponseResult.success();
    }

    @Override
    public Result listPage(Page page, SystemAdminDTO systemAdminDTO) {
        EntityWrapper<SystemAdminDO> entityWrapper = new EntityWrapper<>();
        page = selectPage(page, entityWrapper);
        List<SystemAdminDO> systemAdminDOList = page.getRecords();
        List<SystemAdminDTO> systemAdminDTOList = new ArrayList<>(systemAdminDOList.size());
        systemAdminDOList.forEach(systemAdminDO -> {
            SystemDepartmentDO systemDepartmentDO = systemDepartmentDAO.selectById(systemAdminDO.getDepartmentId());
            systemAdminDTOList.add(new SystemAdminDTO(systemAdminDO, systemDepartmentDO.getName()));
        });
        page.setRecords(systemAdminDTOList);
        return ResponseResult.success(page);
    }

    /***
     * 更新是否禁用状态
     */
    private Result updateDisable(Integer id, CommonStateEnum.IsDisable isDisable) {
        SystemAdminDO systemAdminDO = systemAdminDAO.selectById(id);
        if (systemAdminDO == null) {
            return ResponseResult.failResult(ResultCodeEnum.BAD_REQUEST, "管理员不存在");
        }
        systemAdminDO.setIsDisable(isDisable.code);
        return updateById(systemAdminDO) ? ResponseResult.success() : ResponseResult.serverError();
    }
}
