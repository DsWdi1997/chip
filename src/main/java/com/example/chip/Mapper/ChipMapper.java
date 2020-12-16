package com.example.chip.Mapper;

import com.example.chip.entity.Chip;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface ChipMapper {
    //根据型号查询OE
    Chip getOne(@Param("model") int model);

    //根据model型号查询出相关的OE
    List<Chip> allmodel(@Param("model") int model);

    //新增
    void addmodel(Chip chip);

    //查询所有数据
    List<Chip> getAll();

    //根据芯片型号删除
    Chip delete(@Param("model") int model);

    //根据芯片型号模糊查询
    List<Chip>  like(@Param("model") int model);


}
