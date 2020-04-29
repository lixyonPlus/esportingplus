package com.panda.esportingplus.common.data.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用 mapper -- form tk.mapper --
 *
 * @param <T>
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
public interface CommonRepository<T> extends Mapper<T>, MySqlMapper<T> {

}
