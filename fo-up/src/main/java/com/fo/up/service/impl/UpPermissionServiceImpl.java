package com.fo.up.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fo.common.core.exception.UpException;
import com.fo.up.entity.UpPermission;
import com.fo.up.entity.UpUser;
import com.fo.up.repository.UpPermissionRepository;
import com.fo.up.repository.UpUserRepository;
import com.fo.up.service.UpPermissionService;

@Service
public class UpPermissionServiceImpl implements UpPermissionService{

    @Autowired
    private UpPermissionRepository upPermissionRepository;

    @Autowired
    private UpUserRepository upUserRepository;

    /**
     * 根据 user id 获取所有权限
     * 
     * @param userId
     * @return
     */
    @Override
    public List<UpPermission> findPermissionByUserId(Long userId) {
        UpUser upUser = upUserRepository.findById(userId).get();
        if (null == upUser || upUser.getLocked() == 1) {
            throw new UpException(String.format("不存在的用户 userId: %s", userId)) ;
        }
        return this.upPermissionRepository.findPermissionByUserId(userId);
    }
    
    /**
     * 查看权限
     * @param upPermissionId
     * @return
     */
    @Override
	public UpPermission findByUpPermission(Long upPermissionId) {
		return upPermissionRepository.findById(upPermissionId).get();
	}

    /**
     * 新增权限
     * @param upPermission
     */
    @Override
	public UpPermission addPermission(UpPermission upPermission) {
		return upPermissionRepository.save(upPermission);
	}

    /**
     * 修改权限
     * 
     * @param upPermission
     */
    @Override
    public void updatePermission(UpPermission upPermission) {
    	upPermissionRepository.updateByUpPermissionId(upPermission);
    }

    /**
     * 根据 id 删除 permission
     * 
     * @param permissionId
     */
    @Override
    public void deletePermissionByPermissionId(Long permissionId) {
        this.upPermissionRepository.deleteById(permissionId);
    }

    /**
     * 分页查询
     */
    @Override
    public Page<UpPermission> findPermissionByPage(UpPermission upPermission, Pageable pageable) {
        Example<UpPermission> example = Example.of(upPermission);
        return this.upPermissionRepository.findAll(example, pageable);
    }

	
	

	
}
