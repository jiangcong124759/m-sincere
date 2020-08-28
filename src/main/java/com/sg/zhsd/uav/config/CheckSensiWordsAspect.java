package com.sg.zhsd.uav.config;

import com.alibaba.fastjson.JSON;
import com.sg.zhsd.uav.data.dto.SensitiveWordManageDto;
import com.sg.zhsd.uav.utils.C;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 敏感词拦截器
 */
@Component
@Aspect
@Slf4j
public class CheckSensiWordsAspect {

    @Autowired
    RedisTemplate redisTemplate;

    @Around("@annotation(CheckSensitiveWords)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
        
        Method method = methodSignature.getMethod();

        CheckSensitiveWords checkSensitiveWords = method.getAnnotation(CheckSensitiveWords.class);

        if(checkSensitiveWords!=null&&pjp.getArgs()!=null){  //含有该注解
            //获取参数
            Object[] args = pjp.getArgs();

            Map<String,List<String>> resultMap = new HashMap<>();

            for (Object arg : args) {

                if(arg.getClass().equals(checkSensitiveWords.object()) ){//类型相同
                    Class<?> clazz = arg.getClass();//获取字节码对象

                    String[] fields = checkSensitiveWords.field();//要校验的字段名称

                    for (String fieldName : fields) {

                        Field field = clazz.getDeclaredField(fieldName);//获取要校验的字段

                        field.setAccessible(true);

                        String value = (String)field.get(arg);  //获取值

                        System.err.println(value);

//                        List<String> resultList = sensitiveWordManageService.sensitiveWordsFilter(value);
//
//                        if(resultList != null && !resultList.isEmpty()){
//                            resultMap.put(field.getName(),resultList);//封装结果集
//                        }


                    }
                }

//                if(resultMap!=null && !resultMap.isEmpty()){
//                    return R.ok( resultMap);
//                }
            }
        }
          return  pjp.proceed();

    }



    public  List<String> sensitiveWordsFilter(String text) {

        if(StringUtils.isBlank(text)){
            return Collections.emptyList();
        }

        /**
         * 1.查询redis中的敏感词列表,redis中没有查询数据库,存入redis
         * 2.将敏感词列表和文章title和summary进行对比，如果有和敏感词中的某一个相同，则将该敏感词加入list
         */
        //对text进行正则处理，去除中间空格和特殊字符
        String regex = "[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";

        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(text);

        String newText = m.replaceAll("").trim();

        List<String> resultList = null;

        List sensitiveWords = redisTemplate.opsForList().range("sensitiveWords", 0, -1);

        List<SensitiveWordManageDto> typeList = JSON.parseArray(sensitiveWords.toString(), SensitiveWordManageDto.class);

        //redis中不存在,查询数据库,再存入redis缓存中
        if(typeList.size()==0){


            List<SensitiveWordManageDto> sensitiveWordManageDtos = new ArrayList<>();


            if(sensitiveWordManageDtos == null || C.isArrayEmpty(sensitiveWordManageDtos)){

                log.info("数据库中还没有敏感词记录...........");

                return Collections.emptyList();
            }

            redisTemplate.opsForList().leftPushAll("sensitiveWords",sensitiveWordManageDtos);

            log.info("敏感词列表查询数据库，存入缓存...........");

            //敏感词校验
            resultList = checkSensitiveWords(sensitiveWordManageDtos,newText);

            return resultList;
        }

        log.info("敏感词列表查询redis缓存.............");

        //敏感词校验
        resultList = checkSensitiveWords(typeList,newText);

        return resultList;
    }


    /**
     * 敏感词校验
     */
    private List<String> checkSensitiveWords(List<SensitiveWordManageDto> sensitiveWordManageDtos, String text){

        List<String> sensitiveWordsList = new ArrayList<>();

        for (SensitiveWordManageDto sensitiveWord : sensitiveWordManageDtos) {//如果文章标题、摘要或正文中包含敏感词列表中的某一个，将该敏感词加入敏感词提示列表
            if((StringUtils.isNotBlank(text) && text.contains(sensitiveWord.getWord()))) {
                sensitiveWordsList.add(sensitiveWord.getWord());
            }
        }

        return sensitiveWordsList;
    }





}
