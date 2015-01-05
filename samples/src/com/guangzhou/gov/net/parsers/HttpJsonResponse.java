package com.guangzhou.gov.net.parsers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.guangzhou.gov.net.tools.HttpLog;

/**
 * 
 * @ClassName: HttpJsonResponse
 * @author chenjianping
 * @date 2014-11-11
 * 
 */
public class HttpJsonResponse {
    private JsonObject mJo;

    public HttpJsonResponse(String json) {

        HttpLog.d(null, "json = " + json);
        if (json != null) {
            this.mJo = (JsonObject) new JsonParser().parse(json);
        }
    }

    public JsonObject getJSONObject()
    {
        return mJo;
    }

    public JsonObject getBodys()
    {
        JsonElement je = getJsonElement("data");
        if (je != null) {
            return je.getAsJsonObject();
        }
        return null;
    }

    public String getHead()
    {
        JsonElement JE = getJsonElement("ack_code");
        if (JE != null) {
            return JE.getAsString();
        }
        return "";
    }

    public String getBodyString()
    {
        String data = "";
        JsonElement je = getJsonElement("data");
        try {
            if (je != null) {
                data = je.getAsString();
            }
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }

        return data;
    }

    public JsonArray getBodyArray()
    {
        JsonElement je = getJsonElement("data");
        if (je != null) {
            return je.getAsJsonArray();
        }
        return null;
    }
    
    public String getAsString(String key) {
        JsonObject jo = getBodys();
        JsonElement je = jo.get(key);
        if (je != null) {
            return je.getAsString();
        }
        return null;
    }

    private JsonElement getJsonElement(String key)
    {
        if (mJo != null) {
            return this.mJo.get(key);
        }
        return null;
    }

    public boolean responstSuccess()
    {
        return "SUCESS".equals(getHead());
    }

    public boolean responstFail()
    {
        return "FAIL".equals(getHead());
    }

}
