package com.muyan.pms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

/**
 * @program: BaseService
 * @description:
 * @author: muyan
 * @date: 2023/04/19 08:27:29
 **/
public interface BaseService<T> {

    default Page<T> findAll(Pageable pageableDefault){return null;}

    void create(T t);

    default void update(T t){}

    default void delete(Long t){}

    default T findOne(Long id){ return null;}

    default void batchDelete(Set<Long> ids){}

}
