package test.test.test.test.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class MrJackson {
    public static void main(String[] args) throws IOException {
        JsonNodeFactory jsonNode = JsonNodeFactory.instance;
        System.out.println(jsonNode.textNode("123").toString());

        ObjectNode jsonObject = jsonNode.objectNode();

//        jsonObject.put("code", ResponseCode.SAVE_FAIL.getCode());
//        jsonObject.put("message", ResponseCode.SAVE_FAIL.getMsg());
        String[] strArr = new String[] {"321", "231", "123"};
//        File file = new File();
//        file.setFid("this is fid");
//        file.setFileName("this is fileName");
        ArrayNode arr = new ObjectMapper().valueToTree(strArr);
        // jsonObject.putObject("file").s(new ObjectMapper().valueToTree(file));
        Map<String, JsonNode> props = new HashMap<String, JsonNode>();
//        props.put("file", new ObjectMapper().valueToTree(file));
        props.put("list", new ObjectMapper().valueToTree(arr));
        jsonObject.putObject("data").setAll(props);
        // jsonObject.set("file", new ObjectMapper().valueToTree(file));
        // jsonObject.putArray("list").addAll(arr);
        System.out.println(jsonObject.toString());



//        System.out.println(new ObjectMapper().writeValueAsString(ResponseCode.SAVE_FAIL));



        JsonGenerator jsonGenerator = new ObjectMapper().getFactory().createGenerator(System.out);
        jsonGenerator.writeStartObject();
//        jsonGenerator.writeNumberField("code", ResponseCode.SAVE_SUCCESS.getCode());
//        jsonGenerator.writeStringField("message", ResponseCode.SAVE_SUCCESS.getMsg());
        jsonGenerator.writeEndObject();
        jsonGenerator.close();
    }
}
