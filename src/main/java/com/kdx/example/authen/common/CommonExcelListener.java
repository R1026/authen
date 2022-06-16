package com.kdx.example.authen.common;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/3/16
 */
public class CommonExcelListener<T> extends AnalysisEventListener<T> {

    private final Logger log = LoggerFactory.getLogger(CommonExcelListener.class);

    private Integer DATA_COUNT = 0;

    private final Integer BATCH_COUNT = 1000;

    private List<T> list = ListUtils.newArrayList();

    private Consumer<List<T>> consumer;



    public CommonExcelListener(Consumer<List<T>> consumer){
        this.consumer = consumer;
    }


    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info(String.format("------>解析头部数据: %s", JSON.toJSONString(headMap)));
    }

    /**
     * 解析每条数据调用的方法
     * @param t
     * @param analysisContext
     */
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        String jsonStr = JSON.toJSONString(t);
        log.info("------>解析数据：[{}]",jsonStr);
        DATA_COUNT+=1;
        list.add(t);
    }

    /**
     * 所有数据解析完成了都会调用方法
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("------>数据解析完成，数据量：[{}]",DATA_COUNT);
        consumer.accept(list);

    }
}
