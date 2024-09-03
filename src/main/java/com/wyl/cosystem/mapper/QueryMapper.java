package com.wyl.cosystem.mapper;


import com.wyl.cosystem.entity.*;
import org.springframework.stereotype.Repository;
import java.lang.String;
import java.util.List;

@Repository
public interface QueryMapper {

    User query(QueryInfo queryInfo);
//    List<String> batch_query(BatchQueryInfo batchQueryInfo);
    List<String> xxx();

    int insertBatch(List<Message> messageList);

    List<CustTimeInfo> getTimeList();

    List<PushRatioInfo> getPushRatio();

    List<RiskRatioInfo> getRiskRatio();

    List<String> getProvince(String key);

}
