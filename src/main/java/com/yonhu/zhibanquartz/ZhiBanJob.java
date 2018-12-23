package com.yonhu.zhibanquartz;

import com.alibaba.fastjson.JSONObject;
import com.yonhu.utils.ConfigUtil;
import com.yonhu.utils.DateUtil;
import com.yonhu.utils.HttpUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;
import java.util.List;

public class ZhiBanJob implements Job {
    private static final String WEBHOOK_URL = "https://oapi.dingtalk.com/robot/send?access_token=aaf8b34eb97350d8f423ab70a2177b4262df56949ebc9a7de5a12d517f14dd90";
    private static String template = "值班信息\n\n今天是: %s\n本周值班同学是: %s";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String param = generalParam();
        HttpUtil.post(WEBHOOK_URL, param);
    }

    private String generalParam() {

        String nowDateStr = DateUtil.nowStr(DateUtil.yyyyMMddEEEE);
        String students = getStudents();
        String msg = String.format(template, nowDateStr, students);

        JSONObject text = new JSONObject();
        text.put("content", msg);

        JSONObject at = new JSONObject();
        at.put("atMobiles", "");
        at.put("isAtAll", true);

        JSONObject requestParams = new JSONObject();
        requestParams.put("msgtype", "text");
        requestParams.put("text", text);
        requestParams.put("at", at);

        return requestParams.toJSONString();
    }

    private static String getStudents() {
        String studentsValue = ConfigUtil.getConfigValue("students");
        List<String> students = JSONObject.parseArray(studentsValue, String.class);
        int index = DateUtil.getWeek(new Date()) % students.size();
        return students.get(index);
    }


}
