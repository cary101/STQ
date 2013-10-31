package com.cary.stq.controller;

import com.cary.stq.service.impl.TestItemsService;
import com.cary.stq.to.Testitems;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/testitems")
public class TestItemsController {

    @Resource(name = "testitemsService")
    private TestItemsService testItemsService;

    @RequestMapping("/search")
    public ModelAndView search(HttpServletRequest request, ModelMap model) {
        ModelAndView mav = new ModelAndView();
        return mav;
    }

    @RequestMapping("/getItems")
    @ResponseBody
    public Object getItems(HttpServletRequest request, ModelMap model) {
        List<Testitems> testitems = new ArrayList<>();
        HashMap<String, HashMap> r1 = new HashMap<String, HashMap>();
        List<Object> res = new ArrayList<Object>();
        try{
            testitems = testItemsService.getItems();

            for(Testitems temp: testitems){
                 String key = temp.getItemId();
                if(r1.containsKey(key)){
                    String c1id = temp.getSubItemId1();
                    String c1text = temp.getSubItemDesc1();
                    HashMap<String, String> c1 = new HashMap<String, String>();
                    c1.put("id", c1id);
                    c1.put("text", c1text);
                    List children = (List) r1.get("children");
                    children.add(c1);
                    r1.get(key).put("children", children);

                }else{
                    HashMap<String, Object> result = new HashMap<String, Object>();
                    result.put("id", key);
                    result.put("text", temp.getItemDesc());
                    if(StringUtils.isNotEmpty(temp.getSubItemId1())){
                        String c1id = temp.getSubItemId1();
                        String c1text = temp.getSubItemDesc1();
                        HashMap<String, String> c1 = new HashMap<String, String>();
                        c1.put("id", c1id);
                        c1.put("text", c1text);
                        List<Object> children = new ArrayList<Object>();
                        children.add(c1);
                        result.put("children", children);
                        r1.put(key, result);
                    }
//                    if(StringUtils.isNotEmpty(temp.getSubItemId2())){
//                        String c2id = temp.getSubItemId2();
//                        String c2text = temp.getSubItemDesc2();
//                        HashMap<String, String> c2 = new HashMap<String, String>();
//                        c2.put("id", c2id);
//                        c2.put("text", c2text);
//                        result.put("children", c2);
//                    }
                    res.add(result);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping("/create")
    public ModelAndView create(HttpServletRequest request, ModelMap model) {
        ModelAndView mav = new ModelAndView();
        return mav;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(HttpServletRequest request, ModelMap model) {
        ModelAndView mav = new ModelAndView();
        return mav;
    }

    @RequestMapping("/delete")
    public ModelAndView delete(HttpServletRequest request, ModelMap model) {
        ModelAndView mav = new ModelAndView();
        return mav;
    }

}