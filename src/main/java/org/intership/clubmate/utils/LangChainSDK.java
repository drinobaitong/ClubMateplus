package org.intership.clubmate.utils;

import cn.hutool.core.net.multipart.UploadFile;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class LangChainSDK {

    private final WebClient webClient;

    public LangChainSDK() {
        // 初始化 WebClient 配置
        this.webClient = WebClient.builder()
                .baseUrl("http://127.0.0.1:7861") // 设置基础URL
                .build();
    }

    public Mono<String> chatWithAI(String content) {
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("query", content); //问题内容
            body.put("knowledge_base_name", "clubmate"); //知识库名字
            body.put("top_k", 5);
            body.put("score_threshold", 1); //0-2 模型匹配值
            body.put("stream", false);
            body.put("model_name", "qwen-api"); //模型名字
            body.put("temperature", 0.7); //默认
            body.put("max_tokens", 0);
            body.put("prompt_name", "default");

            return webClient.post()
                    .uri("/chat/knowledge_base_chat")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(String.class)
                    .map(response -> {
                        //现在返回值的类型是String类型的JSON----data:{answer:"xxx"}
                        String jsonStr = response.replace("data:", "");
                        //转成真JSON
                        JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
                        //只要answer
                        return jsonObject.getStr("answer");
                    })
                    .doOnError(error -> {
                        log.error("LangChain Chat Error: {}", error.getMessage());
                    });
        } catch (Exception e) {
            log.error("Exception occurred: {}", e.getMessage());
            throw new RuntimeException();
        }
    }

    public Mono<String> uploadToKLB(MultipartFile files) {
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("files", files); //上传文件
            body.put("knowledge_base_name", "clubmate"); //知识库名字
            body.put("override", false);
            //body.put("to_vector_store", true); //是否向量化
            //body.put("chunk_size", 250);//单段文本最大长度
            //body.put("chunk_overlap", 50); //相邻文本重合长度
            //body.put("zh_title_enhance", false); //默认
            //body.put("docs", "{  \"test.txt\": [    {      \"page_content\": \"custom doc\",      \"metadata\": {},      \"type\": \"Document\"    }  ]}");
            //body.put("not_refresh_vs_cache", false);
            return webClient.post()
                    .uri("/knowledge_base/upload_docs")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(String.class)
                    .map(response -> {
                        //现在返回值的类型是String类型的JSON----data:{answer:"xxx"}
                        String jsonStr = response.replace("data:", "");
                        //转成真JSON
                        JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
                        //只要answer
                        return jsonObject.getStr("answer");
                    })
                    .doOnError(error -> {
                        log.error("LangChain Chat Error: {}", error.getMessage());
                    });
        } catch (Exception e) {
            log.error("Exception occurred: {}", e.getMessage());
            throw new RuntimeException();
        }
    }
}
