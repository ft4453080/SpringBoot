package com.highrock.style.action;

import com.alibaba.fastjson.JSON;
import com.highrock.utils.HttpHelper;
import com.highrock.utils.JSONHelper;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value="/style")
public class StyleController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/save", method= RequestMethod.POST)
    public String saveStyle(@RequestBody String style_info) {
        return null;
    }
}
