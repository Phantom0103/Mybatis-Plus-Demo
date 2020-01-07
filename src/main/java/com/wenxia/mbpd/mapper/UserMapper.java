package com.wenxia.mbpd.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.wenxia.mbpd.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author zhouw
 * @Description
 * @Date 2020-01-03
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * getOneBySql
     *
     * @param wrapper
     * @return
     */
    @Select("SELECT * FROM user ${ew.customSqlSegment}")
    User getOneBySql(@Param(Constants.WRAPPER) Wrapper wrapper);
}
