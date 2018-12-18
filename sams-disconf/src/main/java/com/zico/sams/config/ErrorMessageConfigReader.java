package com.zico.sams.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Map;

/**
 * @author zico
 * @version v1.0
 * @title ErrorMessageConfigReader
 * @package com.zico.sams.config
 * @since 2018-12-14
 * description 错误信息读取类
 **/
@Slf4j
public class ErrorMessageConfigReader {

    private static final Map<String, ErrorMessageConfig> ERROR_MESSAGE_MAPS = Maps.newConcurrentMap();

    private static final String BIZ_SYSTEM_PROPERTIES = "errorMessage.json";

    static {
        try {
            String path = Resources.getResource(BIZ_SYSTEM_PROPERTIES).getPath();
            File jsonFile = FileUtils.getFile(path);
            String jsonString = FileUtils.readFileToString(jsonFile);
            JSONArray jsonArray = JSON.parseArray(jsonString);
            jsonArray.forEach(json -> {
                Map map = JSON.parseObject(json.toString());
                ErrorMessageConfig errorMessageConfig = new ErrorMessageConfig();
                String desc = MapUtils.getString(map, "desc");
                errorMessageConfig.setErrorNo(MapUtils.getString(map, "errorNo"));
                errorMessageConfig.setMessage(MapUtils.getString(map, "message"));
                ERROR_MESSAGE_MAPS.put(desc, errorMessageConfig);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 刷新错误信息
     */
    public static void reload() {
        try {
            String path = Resources.getResource(BIZ_SYSTEM_PROPERTIES).getPath();
            File jsonFile = FileUtils.getFile(path);
            String jsonString = FileUtils.readFileToString(jsonFile);
            JSONArray jsonArray = JSON.parseArray(jsonString);
            jsonArray.forEach(json -> {
                Map map = JSON.parseObject(json.toString());
                ErrorMessageConfig errorMessageConfig = new ErrorMessageConfig();
                String desc = MapUtils.getString(map, "desc");
                errorMessageConfig.setErrorNo(MapUtils.getString(map, "errorNo"));
                errorMessageConfig.setMessage(MapUtils.getString(map, "message"));
                ERROR_MESSAGE_MAPS.put(desc, errorMessageConfig);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ErrorMessageConfig getConfig(String key) {
        ErrorMessageConfig errorMessageConfig = ERROR_MESSAGE_MAPS.get(key);
        log.debug("");
        return errorMessageConfig;
    }

    public static void main(String[] args) {
        System.out.println(ErrorMessageConfigReader.getConfig("LOGIN_TIME_OUT"));
    }
}
