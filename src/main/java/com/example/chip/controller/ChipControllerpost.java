package com.example.chip.controller;


import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.chip.Mapper.ChipMapper;
import com.example.chip.entity.Chip;
import com.example.chip.entity.CustomType;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/chippost")
public class ChipControllerpost {

    @Autowired
    private ChipMapper chipMapper;
    private Chip chip;

    public ChipControllerpost() {
    }

    /**根据model查询OE
     * 请求路径:/chippost/selectfdataoe
     * 请求方式:post
     * 请求格式:form-data
     * 请求参数:model
     * @return
     */

    @RequestMapping(value = "/selectfdataoe", method = RequestMethod.POST)
    public ResponseEntity<?> selectfdataoe(@RequestParam("model") int model){
        //System.out.println(model);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            this.chip = chipMapper.getOne(model);
            map.put("OE", chip.getOE());
            return ResponseEntity.ok(map);
        }catch (Exception e){
            CustomType customType = new CustomType(500,"查询失败，无此数据");
            return ResponseEntity.ok(customType);
        }
    }



    /**根据model查询OE
     * 请求路径:/chippost/selectjsoe
     * 请求方式:post
     * 请求格式:application/json
     * 请求参数:model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectjsoe", method = RequestMethod.POST)
    public ResponseEntity<?> selectajsonoe( @RequestBody JSONObject  jsonObject) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();
        String a =jsonObject.get("model").toString();
        Integer model = Integer.parseInt(a);
        CustomType customType;
        try {
            this.chip=chipMapper.getOne(model);
            //chipMapper.getOne(model);
            map.put("code",200);
            map.put("OE",chip.getOE());
            return ResponseEntity.ok(map);
        }catch (Exception e){
            customType = new CustomType(500,"查询失败，无此数据");
            return ResponseEntity.ok(customType);
        }

    }

    /**根据芯片型号模糊查询OE
     * 请求路径:/chippost/likejsoe
     * 请求方式:post
     * 请求格式:application/json
     * 请求参数:model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/likejsoe",method = RequestMethod.POST)
    public ResponseEntity<?> likejsoe(@RequestBody JSONObject jsonObject){
        Map<String, Object> map = new HashMap<String, Object>();
        String a =jsonObject.get("model").toString();
        Integer model = Integer.parseInt(a);
        CustomType customType;
        //System.out.println(model);
        List<Chip> chips = chipMapper.like(model);

        if(chips != null && chips.size()==0){
            customType = new CustomType(500,"查询失败，无此数据");
            return  ResponseEntity.ok(customType);
        }else {
            //map.put("code",200);
            //map.put("OE",chip.getOE());
            return ResponseEntity.ok(chips);
        }
        /*
        try {
            List<Chip> chips = chipMapper.like(model);
            //System.out.println(chipMapper.like(model));
            //chipMapper.getOne(model);
            //map.put("code",200);
            //map.put("OE",chip.getOE());
            return ResponseEntity.ok(chips);
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",500);
            return  ResponseEntity.ok(map);
        }
    */



    }




    /**新增芯片
     * 请求路径:/chippost/addjsoe
     * 请求方式:post
     * 请求格式:application/json
     * 请求参数:model ,oe
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/addjsoe",method = RequestMethod.POST)
    public ResponseEntity<?>  addjsonoe(@RequestBody Chip chip){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            chipMapper.addmodel(chip);
            map.put("code",200);
            map.put("model型号增加成功", chip.getModel());
            map.put("产品OE", chip.getOE());
            return ResponseEntity.ok(map);
        }catch (Exception e){
            CustomType customType = new CustomType(500,"添加失败，已有此数据");
            return ResponseEntity.ok(customType);
        }

    }






    /**根据型号删除芯片
     * 请求路径:/chippost/deletejsoe
     * 请求方式:post
     * 请求格式:application/json
     * 请求参数:model
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/deletejsoe",method = RequestMethod.POST)
    public ResponseEntity<?>  deletejsoe(@RequestBody JSONObject jsonObject){
        Map<String, Object> map = new HashMap<String, Object>();
        String a = jsonObject.get("model").toString();
        Integer model =Integer.parseInt(a);
        CustomType customType;

        if (chipMapper.getOne(model) == null) {
           customType = new CustomType(500,"删除失败");
            //map.put("result", "删除失败");
        } else {
            try {
                this.chip = chipMapper.getOne(model);
                //map.put("OE", chip.getOE());
                this.chip = chipMapper.delete(model);
               return ResponseEntity.ok("正在删除该型号"+model);
            } catch (Exception e) {
                customType = new CustomType(200,"删除成功,OE:"+" ' "+chip.getOE()+" '");
                //map.put("result", "删除成功,OE为"+" ' "+chip.getOE()+" '");
                return ResponseEntity.ok(customType);
            }
        }
        return ResponseEntity.ok(customType);
    }
}



