package com.drool.engine.controller;

import javax.annotation.Resource;

import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drool.engine.config.SpringContextUtil;
import com.drool.engine.entity.QueryParam;
import com.drool.engine.entity.RuleResult;
import com.drool.engine.service.RuleEngineService;

@RestController
@RequestMapping("/rule")
public class RuleController {

    @Resource
    private RuleEngineService ruleEngineService ;

    @RequestMapping("/param")
    public void param (){
        QueryParam queryParam1 = new QueryParam() ;
        queryParam1.setParamId("1");
        queryParam1.setParamSign("+");
        QueryParam queryParam2 = new QueryParam() ;
        queryParam2.setParamId("2");
        queryParam2.setParamSign("-");
        KieSession kieSession = (KieSession) SpringContextUtil.getBean("kieSession");
        // 入参
        kieSession.insert(queryParam1) ;
        kieSession.insert(queryParam2) ;
        kieSession.insert(this.ruleEngineService) ;

        // 返参
        RuleResult resultParam = new RuleResult() ;
        kieSession.insert(resultParam) ;
        kieSession.fireAllRules() ;
        kieSession.destroy();
    }
}
