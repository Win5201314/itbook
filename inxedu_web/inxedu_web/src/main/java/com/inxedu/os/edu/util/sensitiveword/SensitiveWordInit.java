package com.inxedu.os.edu.util.sensitiveword;

import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.edu.entity.sensitivewords.SensitiveWords;
import com.inxedu.os.edu.service.sensitivewords.SensitiveWordsService;

import java.util.*;

import static com.inxedu.os.edu.util.sensitiveword.SensitivewordFilter.sensitiveWordsService;

/**
 * @Description: 初始化敏感词库，将敏感词加入到HashMap中，构建DFA算法模型
 */
public class SensitiveWordInit {
	public HashMap sensitiveWordMap;

	public SensitiveWordInit(){
		super();
	}

	/**
	 * 读取敏感词库
	 */
	public Map initKeyWord(SensitiveWordsService sensitiveWordsService){
		try {
			//读取敏感词库
			Set<String> keyWordSet=null;
			List<SensitiveWords> sensitiveWordsList=sensitiveWordsService.querySensitiveWordsList(null);
			if(ObjectUtils.isNotNull(sensitiveWordsList)){
				keyWordSet = new HashSet<String>();
				for(SensitiveWords sensitiveWords:sensitiveWordsList){
					keyWordSet.add(sensitiveWords.getSensitiveWord());
				}
			}
			//将敏感词库加入到HashMap中
			addSensitiveWordToHashMap(keyWordSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sensitiveWordMap;
	}

	/**
	 * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：<br>
	 * 中 = {
	 *      isEnd = 0
	 *      国 = {<br>
	 *      	 isEnd = 1
	 *           人 = {isEnd = 0
	 *                民 = {isEnd = 1}
	 *                }
	 *           男  = {
	 *           	   isEnd = 0
	 *           		人 = {
	 *           			 isEnd = 1
	 *           			}
	 *           	}
	 *           }
	 *      }
	 *  五 = {
	 *      isEnd = 0
	 *      星 = {
	 *      	isEnd = 0
	 *      	红 = {
	 *              isEnd = 0
	 *              旗 = {
	 *                   isEnd = 1
	 *                  }
	 *              }
	 *      	}
	 *      }
	 * @param keyWordSet  敏感词库
	 */
	private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
		sensitiveWordMap = new HashMap(keyWordSet.size());     //初始化敏感词容器，减少扩容操作
		String key ;
		Map nowMap;
		Map<String, String> newWorMap;
		//迭代keyWordSet
		Iterator<String> iterator = keyWordSet.iterator();
		while(iterator.hasNext()){
			key = iterator.next();    //关键字
			nowMap = sensitiveWordMap;
			for(int i = 0 ; i < key.length() ; i++){
				char keyChar = key.charAt(i);       //转换成char型
				Object wordMap = nowMap.get(keyChar);       //获取

				if(wordMap != null){        //如果存在该key，直接赋值
					nowMap = (Map) wordMap;
				}
				else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
					newWorMap = new HashMap<String,String>();
					newWorMap.put("isEnd", "0");     //不是最后一个
					nowMap.put(keyChar, newWorMap);
					nowMap = newWorMap;
				}

				if(i == key.length() - 1){
					nowMap.put("isEnd", "1");    //最后一个
				}
			}
		}
	}

	/**
	 * 读取敏感词库中的内容，将内容添加到set集合中
	 * @throws Exception
	 */
	private Set<String> readSensitiveWordFile() throws Exception{
		Set<String> set = null;
		List<SensitiveWords> sensitiveWordsList=sensitiveWordsService.querySensitiveWordsList(null);
		if(ObjectUtils.isNotNull(sensitiveWordsList)){
			set = new HashSet<String>();
			for(SensitiveWords sensitiveWords:sensitiveWordsList){
				set.add(sensitiveWords.getSensitiveWord());
			}
		}
		return set;
	}
}
