package com.learn.system.service.impl;

import com.learn.commom.config.mysql.*;
import com.learn.commom.utils.DateUtil;
import com.learn.commom.utils.IDGenerator;
import com.learn.system.constant.SysContant;
import com.learn.system.dao.DeptMapper;
import com.learn.system.model.dto.DeptDTO;
import com.learn.system.model.entity.Dept;
import com.learn.system.model.entity.ResponseBean;
import com.learn.system.service.DeptService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 部门service实现层
 * @author: PENGLW
 * @date: 2020/10/21
 */
@Service
@CacheConfig(cacheNames = "c1")
public class DeptServiceImpl implements DeptService {

    private static final Logger logger = LoggerFactory.getLogger(DeptServiceImpl.class);

    @Autowired
    private DeptMapper deptMapper;

    /**
     * @param parentId 父id
     * @return
     * @Read 注解：调用mysql从数据库
     */
    @Override
    @Read
    @Cacheable(key = "#parentId")
    public ResponseBean getDeptByFilter(String parentId) {
        logger.info("当前使用数据源：" + DynamicDBTypeUtil.get().toString());
        if (StringUtils.isBlank(parentId)) {
            //默认查询部门为最上级部门
            parentId = "1";
        }
        List<DeptDTO> deptList = deptMapper.getDeptByFilter(parentId);
        if (CollectionUtils.isNotEmpty(deptList)) {
            deptList.forEach(deptDTO -> {
                //转换时间和设置叶子属性
                deptDTO.setCreateTime(DateUtil.getStringDate(deptDTO.getCreateTime()));
                deptDTO.setUpdateTime(DateUtil.getStringDate(deptDTO.getUpdateTime()));
                if (deptDTO.getIsLeaf() == 1) {
                    deptDTO.setHasChildren(false);
                } else {
                    deptDTO.setHasChildren(true);
                }
            });
        }
        return new ResponseBean(deptList);
    }

    /**
     * allEntries 注解：当前方法调用后清除缓存
     *
     * @param dept
     * @return
     * @Write 注解：调用mysql主数据库
     */
    @Write
    @Override
    @CacheEvict(allEntries = true)
    public Integer addDept(Dept dept) {
        logger.info("当前使用数据源：" + DynamicDBTypeUtil.get().toString());
        dept.setDeptId(IDGenerator.newID());
        dept.setStatus(1);
        dept.setCreateTime(String.valueOf(System.currentTimeMillis()));
        dept.setUpdateTime(String.valueOf(System.currentTimeMillis()));
        dept.setIsLeaf(1);
        return deptMapper.insert(dept);
    }

    @Override
    @CacheEvict(allEntries = true)
    public Integer delDept(String deptId) {
        //todo 判断部门下面是否有关联用户
        return deptMapper.deleteById(deptId);
    }

    @Override
    public ResponseBean getAllDept() {
        List<DeptDTO> allDepts = deptMapper.getAllDept();
        List<DeptDTO> rootDeptList = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(allDepts)) {
            //获取根节点部门
            allDepts.forEach(deptDTO -> {
                if (SysContant.DEPT_PARENTID.equals(deptDTO.getParentId())) {
                    rootDeptList.add(deptDTO);
                }
            });
            //获取当前节点的子节点
            for (DeptDTO deptDTO : rootDeptList) {
                List<DeptDTO> childList = getChild(deptDTO.getDeptId(), allDepts);
                deptDTO.setChildren(childList);
            }
        }
        return new ResponseBean(rootDeptList);
    }

    /**
     * 获取子节点
     *
     * @param deptId   父节点部门id
     * @param allDepts 所有部门list
     * @return 当前节点下的所有菜单
     */
    private List<DeptDTO> getChild(String deptId, List<DeptDTO> allDepts) {
        List<DeptDTO> childList = new ArrayList<>();
        //遍历所有节点，获取当前节点下的子节点
        allDepts.forEach(deptDTO -> {
            if (deptId.equals(deptDTO.getParentId())) {
                childList.add(deptDTO);
            }
        });
        //递归调用
        if (CollectionUtils.isNotEmpty(childList)) {
            for (DeptDTO deptDTO : childList) {
                deptDTO.setChildren(getChild(deptDTO.getDeptId(), allDepts));
            }
        }
        return childList;
    }
}
