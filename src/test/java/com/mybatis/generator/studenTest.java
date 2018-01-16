package com.mybatis.generator;

import com.mybatis.generator.Entity.student;
import com.mybatis.generator.Repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by haiqiang on 2018/1/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class studenTest {
    @Autowired
    StudentRepository studentRepository;
    public final static  Long KEY=1l;
    @Test
    public void Student(){
        System.out.print(studentRepository);
         student students= studentRepository.selectByPrimaryKey(KEY);
        System.out.print(students);
    }
}
