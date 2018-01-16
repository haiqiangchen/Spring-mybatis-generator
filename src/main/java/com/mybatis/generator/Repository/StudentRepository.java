package com.mybatis.generator.Repository;

import com.mybatis.generator.Entity.student;
import com.mybatis.generator.Entity.studentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository {
    long countByExample(studentExample example);

    int deleteByExample(studentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(student record);

    int insertSelective(student record);

    List<student> selectByExample(studentExample example);

    student selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") student record, @Param("example") studentExample example);

    int updateByExample(@Param("record") student record, @Param("example") studentExample example);

    int updateByPrimaryKeySelective(student record);

    int updateByPrimaryKey(student record);
}