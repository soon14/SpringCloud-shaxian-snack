package cc.likq.base.service.impl;

import cc.likq.base.domain.entity.SystemAdminLoginLogDO;
import cc.likq.base.dao.SystemAdminLoginLogDAO;
import cc.likq.base.service.SystemAdminLoginLogService;
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
public class SystemAdminLoginLogServiceImpl extends ServiceImpl<SystemAdminLoginLogDAO, SystemAdminLoginLogDO> implements SystemAdminLoginLogService {
	
}
