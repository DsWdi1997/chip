package com.example.chip.controller;


import com.example.chip.Mapper.ChipMapper;
import com.example.chip.entity.Chip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/chip")
public class ChipController {
    @Autowired
    private ChipMapper chipMapper;
    private Chip chip;


    //查询OE型号
    @RequestMapping(value = "getone")
    public Object getone(int model) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            this.chip = chipMapper.getOne(model);
            map.put("OE", chip.getOE());
            return map;
        } catch (Exception e) {
            map.put("result", "查询失败");
            return map;
        }

    }

    //根据model型号查询出相关的OE
    @RequestMapping(value = "allmodel")
    public List<Chip> allmodel(int model){
        List<Chip> chips = chipMapper.allmodel(model);
        return chips;
    }

    /**查看全部
     * 请求路径:/chip/getalls
     * 请求方式:get
     * 请求格式:application/json
     * 请求参数:
     * @return
     */

    //查询所有
    @RequestMapping(value = "getalls",method = RequestMethod.GET)
    public List<Chip> getalls() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Chip> chips = chipMapper.getAll();
            return chips;
        }catch (Exception e) {
            map.put("code",500);
            return (List<Chip>) map;
        }
    }




    //新增芯片
    @RequestMapping("/addmodel")
    public Object addmodel(Chip chip) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            this.chipMapper.addmodel(chip);
            map.put("model型号增加成功", chip.getModel());
            map.put("产品OE", chip.getOE());
            return map;
        } catch (Exception e) {
            System.out.println(e);
            map.put("result", "增加失败");
            return map;
        }

    }

    //删除芯片
    @RequestMapping(value = "/delete")
    public Object delete(int model) {
        Map<String, Object> map = new HashMap<String, Object>();

        if (chipMapper.getOne(model) == null) {
            map.put("result", "删除失败");
        } else {
            try {
                this.chip = chipMapper.getOne(model);
                //map.put("OE", chip.getOE());
                this.chip = chipMapper.delete(model);
                return map;
            } catch (Exception e) {
                map.put("result", "删除成功,OE为"+" ' "+chip.getOE()+" '");
                return map;
            }
        }
        return map;
    }

}