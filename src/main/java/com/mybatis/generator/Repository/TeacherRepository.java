package com.mybatis.generator.Repository;

import com.mybatis.generator.Entity.teacher;
import com.mybatis.generator.Entity.teacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository {
    long countByExample(teacherExample example);

    int deleteByExample(teacherExample example);

    int deleteByPrimaryKey(Long id);

    int insert(teacher record);

    int insertSelective(teacher record);

    List<teacher> selectByExample(teacherExample example);

    teacher selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") teacher record, @Param("example") teacherExample example);

    int updateByExample(@Param("record") teacher record, @Param("example") teacherExample example);

    int updateByPrimaryKeySelective(teacher record);

    int updateByPrimaryKey(teacher record);
}