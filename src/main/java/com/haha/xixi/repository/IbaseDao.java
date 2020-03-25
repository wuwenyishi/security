package com.haha.xixi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author weilai
 * @param <T>
 * @data 2018年12月6日 上午11:27:03
 *
 * @desc 类描述
 *       <li>
 */
@NoRepositoryBean
public interface IbaseDao<T, PK> extends JpaRepository<T, PK>, JpaSpecificationExecutor<T> {



}
