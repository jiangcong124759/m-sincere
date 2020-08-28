package com.sg.zhsd.uav.utils.model;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

public class ModelListener extends AnalysisEventListener<ReadModel> {

    List<ReadModel> resultList = new ArrayList();

    @Override
    public void invoke(ReadModel readModel, AnalysisContext analysisContext) {
        resultList.add(readModel);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }


    public List<ReadModel> getInsertDataList(){
        return resultList;
    }
}
