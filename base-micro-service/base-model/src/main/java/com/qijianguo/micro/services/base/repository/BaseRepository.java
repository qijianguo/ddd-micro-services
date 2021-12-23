package com.qijianguo.micro.services.base.repository;

import com.qijianguo.micro.services.base.model.dto.PageResponse;

import java.util.List;

/**
 * @author qijianguo
 */
public interface BaseRepository<T, ID, Criteria> {

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    T findById(ID id);

    /**
     * 根据条件查询
     * @param criteria
     * @return
     */
    List<T> findByCriteria(Criteria criteria);

    /**
     * 根据条件分页查询
     * @param criteria
     * @return
     */
    PageResponse<T> findPageByCriteria(Criteria criteria);

    /**
     * 保存或修改
     * @param t
     */
    void saveOrUpdate(T t);

    /**
     * 根据ID删除
     * @param id
     */
    void deleteById(ID id);

}
