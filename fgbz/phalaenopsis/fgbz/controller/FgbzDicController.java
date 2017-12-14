package phalaenopsis.fgbz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import phalaenopsis.fgbz.service.FgbzDicService;

import java.util.Map;

/**
 * Created by 13260 on 2017/12/13.
 */
@Controller
@RequestMapping("/FgbzDic")
public class FgbzDicController {

    @Autowired
    private FgbzDicService fgbzDicService;

    /**
     * 获取所有法规字典
     * @return
     */
    @RequestMapping(value = "/getAllFgbzDictory", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object,Object> getAllFgbzDictory() {

        return fgbzDicService.getAllFgbzDictory();
    }
}
