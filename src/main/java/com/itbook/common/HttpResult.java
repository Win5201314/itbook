package com.itbook.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * 响应体结构
 */
public class HttpResult {

    //成功状态
    public final static String SUCCESS = "success";
    //失败状态
    public final static String FAIL = "fail";

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应状态(成功 = success, 失败 = fail)
    private String status;

    // 响应中的数据(json格式)
    private Object data;

    public HttpResult() {}

    public HttpResult(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    public HttpResult(Object data) {
        this.status = "success";
        this.data = data;
    }

    public static HttpResult build(String status, Object data) {
        return new HttpResult(status, data);
    }

    public static HttpResult ok(Object data) {
        return new HttpResult(data);
    }

    public static HttpResult ok() {
        return new HttpResult(null);
    }

    public static HttpResult build(String status) {
        return new HttpResult(status, null);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为TaotaoResult对象
     *
     * @param jsonData json数据
     * @param clazz TaotaoResult中的object类型
     * @return
     */
    public static HttpResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, HttpResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").toString(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static HttpResult format(String json) {
        try {
            return MAPPER.readValue(json, HttpResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static HttpResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").toString(), obj);
        } catch (Exception e) {
            return null;
        }
    }

}
