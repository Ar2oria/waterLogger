package edu.hrbust.common.service.impl;

import edu.hrbust.common.BasePagination;
import edu.hrbust.common.dao.DaoMapper;
import edu.hrbust.common.entity.BaseEntity;
import edu.hrbust.common.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Implements {@link GenericService}
 *
 * @author lan
 * @since 1.0.0.0
 */
public abstract class GenericSqlMapServiceImpl<T extends BaseEntity, ID extends Serializable>
        implements GenericService<T, ID> {

    @Autowired
    protected DaoMapper<T, ID> dao;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.baidu.rigel.platform.service.GenericService#delete(java.lang.Object)
     */
    @Override
    public void delete(T entity) {
        Assert.notNull(entity, "delete failed due to entity is null");
        dao.deleteByPrimaryKey((ID) entity.getPrimaryKey());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.baidu.rigel.platform.service.GenericService#deleteBatch(java.util
     * .Collection)
     */
    @Override
    public void deleteBatch(Collection<T> objects) {
        if (objects == null || objects.isEmpty()) {
            return;
        }
        for (T t : objects) {
            delete(t);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.baidu.rigel.platform.service.GenericService#findAll()
     */
    @Override
    public List<T> findAll() {
        return dao.selectAll();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.baidu.rigel.platform.service.GenericService#findById(java.io.Serializable
     * , boolean)
     */
    @Override
    public T findById(ID id, boolean lock) {
        Assert.notNull(id, "id is null");
        return dao.selectByPrimaryKey(id);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.baidu.rigel.platform.service.GenericService#findById(java.io.Serializable
     * )
     */
    @Override
    public T findById(ID id) {
        return findById(id, false);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.baidu.rigel.platform.service.GenericService#saveEntity(java.lang.
     * Object)
     */
    @Override
    public ID saveEntity(T entity) {
        Assert.notNull(entity, "save entity failed due to entity is null");
        dao.insert(entity);
        return (ID) entity.getPrimaryKey();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.baidu.rigel.platform.service.GenericService#saveOrUpdate(java.lang
     * .Object)
     */
    @Override
    public T saveOrUpdate(T entity) {
        Assert.notNull(entity,
                "save or update entity failed due to entity is null");
        ID id = (ID) entity.getPrimaryKey();
        if (id == null) {
            // do save
            dao.insert(entity);
        } else {
            if (findById(id) != null) {
                // do update
                dao.updateByPrimaryKey(entity);
            } else {
                dao.insert(entity);
            }
        }

        return entity;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.baidu.rigel.platform.service.GenericService#saveOrUpdateAll(java.
     * util.Collection)
     */
    @Override
    public void saveOrUpdateAll(Collection<T> objects) {
        if (objects == null || objects.isEmpty()) {
            return;
        }
        for (T t : objects) {
            saveOrUpdate(t);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.baidu.rigel.platform.service.GenericService#updateEntity(java.lang
     * .Object)
     */
    @Override
    public void updateEntity(T entity) {
        Assert.notNull(entity, "update entity failed due to entity is null");
        dao.updateByPrimaryKey(entity);
    }

    /**
     * save and ignore null field
     *
     * @param entity
     *
     * @return
     */
    @Override
    public ID saveEntitySelective(T entity) {
        Assert.notNull(entity, "save entity failed due to entity is null");
        dao.insertSelective(entity);
        return (ID) entity.getPrimaryKey();
    }

    /**
     * update and ignore null field
     *
     * @param entity
     */
    @Override
    public void updateEntitySelective(T entity) {
        Assert.notNull(entity, "update entity failed due to entity is null");
        dao.updateByPrimaryKeySelective(entity);
    }

    /**
     * 保存或更新(如果对象已存在)
     *
     * @param entity
     *
     * @return TODO
     */
    @Override
    public T saveOrUpdateSelective(T entity) {
        Assert.notNull(entity,
                "save or update entity failed due to entity is null");
        ID id = (ID) entity.getPrimaryKey();
        if (id == null) {
            // do save
            saveEntitySelective(entity);
        } else {
            if (findById(id) != null) {
                // do update
                updateEntitySelective(entity);
            } else {
                saveEntitySelective(entity);
            }
        }

        return entity;
    }

    @Override
    public T findSelective(T entity) {
        return dao.findSelective(entity);
    }

    /**
     * 获取列表
     */
    @Override
    public List<T> findLists(T t) {
        return dao.findLists(t);
    }

    /**
     * 获取分页列表
     */
    @Override
    public List<T> findPageLists(BasePagination t) {
        return dao.findPageLists(t);
    }
}
