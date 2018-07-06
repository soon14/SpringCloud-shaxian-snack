package cc.likq.base.service.impl;

import cc.likq.base.domain.entity.SystemAdminRoleRelationDO;
import cc.likq.base.dao.SystemAdminRoleRelationDAO;
import cc.likq.base.service.SystemAdminRoleRelationService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author likq
 * @since 2017-11-22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SystemAdminRoleRelationServiceImpl extends ServiceImpl<SystemAdminRoleRelationDAO, SystemAdminRoleRelationDO> implements SystemAdminRoleRelationService {
	
}
