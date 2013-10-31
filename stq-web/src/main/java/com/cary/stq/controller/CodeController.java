package com.cary.stq.controller;

import com.cary.stq.service.impl.CodeService;
import com.cary.stq.to.Code;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/code")
public class CodeController {
    @Resource(name = "codeService")
    private CodeService codeService;

    @RequestMapping("/getCodeList")
    @ResponseBody
    public Object getCodeList(String category) throws IOException {
        Code code = new Code();
        //TODO
        code.setDicCode("RPTYPE");
        List<Code> result = codeService.search(code);
        return result;
    }
}