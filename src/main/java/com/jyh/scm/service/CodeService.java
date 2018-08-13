package com.jyh.scm.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyh.scm.dao.CodeItemMapper;
import com.jyh.scm.dao.CodeMapper;
import com.jyh.scm.entity.Code;
import com.jyh.scm.entity.CodeItem;

/**
 * @author jiangyonghua
 * @date 2018年3月5日 下午2:43:23
 */
@Service
public class CodeService {

	@Autowired
	private CodeItemMapper codeItemMapper;

	@Autowired
	private CodeMapper codeMapper;

	public List<Code> codes() {
		List<Code> codes = codeMapper.selectAll();
		for (Code code : codes) {
			List<CodeItem> itemList = new LinkedList<CodeItem>();
			CodeItem param = new CodeItem();
			param.setType(code.getCode());
			List<CodeItem> items = codeItemMapper.select(param);
			List<CodeItem> topItems = items.stream().filter(item -> "0".equals(item.getPid())).sorted()
					.collect(Collectors.toList());

			makeCodeItems(itemList, topItems, items);

			code.setItems(itemList);
		}
		return codes;
	}

	/**
	 * 递归收集编码项目
	 * 
	 * @param pItems
	 *            编码父项目列表
	 * @param codeTtems
	 *            编码项目列表
	 */
	private static void makeCodeItems(List<CodeItem> items, List<CodeItem> pItems, List<CodeItem> codeTtems) {
		for (CodeItem pItem : pItems) {
			items.add(pItem);
			// 收集下级子项目
			List<CodeItem> child = codeTtems.stream().filter(item -> item.getPid().equals(pItem.getId())).sorted()
					.collect(Collectors.toList());
			if (child.size() > 0) {
				makeCodeItems(items, child, codeTtems);
			}
		}
	}

}
