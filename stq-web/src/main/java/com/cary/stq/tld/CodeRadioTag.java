package com.cary.stq.tld;

import com.cary.stq.cache.CodeCache;
import com.cary.stq.dao.ICodeDAO;
import com.cary.stq.to.Code;
import com.cary.stq.utils.ObjectUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 字典数据以Radio形式展现
 * @author Cary
 *
 */
public class CodeRadioTag extends BaseTag {

	private static final long serialVersionUID = 660059466296981149L;

	// attribute
	private String id = null;
	private String name = null;
	private String dicmeta = null;
	private String value = null;
	private String code = null;
    private String reg = null;
    private String uninclude = null;
    private String onchange = null;

    @Autowired
    private ICodeDAO codeDAO;
    
	public int doStartTagInternal() throws JspTagException {
		inject();
		List<Code> list = this.getCodes();
		String html = null;
		String key = id + "|" + name + "|" + dicmeta + "|" + value + "|" + code+"|" + reg + "|" + uninclude + "|" + onchange;
		if (CodeCache.dicRadioMap.containsKey(key)) {
			html = CodeCache.dicRadioMap.get(key);
		} else {
			StringBuilder sb = new StringBuilder();
			
			if(ObjectUtil.isNotEmpty(list)) {
				for (Code dic : list) {
					this.getRadio(dic, sb);
				}
			}
			html = sb.toString();
			CodeCache.dicRadioMap.put(key, html);
		}
		try {
			JspWriter jw = pageContext.getOut();
			jw.write(html);
		} catch (IOException e) {
		}
		return SKIP_BODY;
	}

	public int doEndTagInternal() throws JspException {
		return super.doEndTag();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDicmeta() {
		return dicmeta;
	}

	public void setDicmeta(String dicmeta) {
		this.dicmeta = dicmeta;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getReg() {
        return reg;
    }

	public String getUninclude() {
		return uninclude;
	}

	public void setUninclude(String uninclude) {
		this.uninclude = uninclude;
	}

    public String getOnchange() {
        return onchange;
    }

    public void setOnchange(String onchange) {
        this.onchange = onchange;
    }

    private List<Code> getCodes() {
        Code codeParam = new Code();
        codeParam.setDicCode(dicmeta);
        List<Code> temp = codeDAO.search(codeParam);// WebCache.CodeList.get(dicmeta);
		List<Code> list = new ArrayList<Code>();
	
		if(StringUtils.isNotEmpty(this.code) && StringUtils.isNotEmpty(this.uninclude)) {
			return temp;
		}
		if(StringUtils.isNotEmpty(this.code)) {
			list = this.getIncludeCodes(temp);
		} else if(StringUtils.isNotEmpty(this.uninclude)) {
			list = this.getUnincludeCodes(temp);
		} else {
			return temp;
		}
		return list;
	}
	
	/**
	 * 根据code获取code所包含的数据
	 * @return
	 */
	private List<Code> getIncludeCodes(List<Code> temp) {
		List<Code> list = new ArrayList<Code>();
		String[] codes = this.code.split(",");
		for (String code : codes) {
			for (Code item : temp) {
				if (StringUtils.equals(code, item.getItemCode())) {
					list.add(item);
					break;
				}
			}
		}
		return list;
	}
	
	/**
	 * 根据uninclude排除其包含的数据
	 * @return
	 */
	private List<Code> getUnincludeCodes(List<Code> temp) {
		List<Code> list = new ArrayList<Code>();
		list.addAll(temp);
		String[] unincludes = this.uninclude.split(",");
		for (String code : unincludes) {
			for (Code item : temp) {
				if (StringUtils.equals(code, item.getItemCode())) {
					list.remove(item);
					break;
				}
			}
		}
		return list;
	}
	
	private StringBuilder getRadio(Code dic, StringBuilder sb) {
		sb.append("<input type=\"radio\"");
		if (StringUtils.isNotEmpty(id)) {
			sb.append(" id=\"" + id + "\"");
		}
		sb.append(" name=\"" + name + "\"");
		sb.append(" class=\"radioGroup\"" );

        if (StringUtils.isNotEmpty(reg)) {
            sb.append(" reg=\"" + reg.replaceAll("\"", "'") + "\"");
            this.setReg(null);
        }

        if(StringUtils.isNotEmpty(onchange)){
            sb.append(" onchange=\"" + onchange + "\"");
        }

        sb.append("onclick=\"this.blur()\"");

		if (dic.getItemCode().equals(value)) {
			sb.append(" value=\"" + dic.getItemCode() +"\" checked=\"checked\"> " + dic.getItemValue() + "&emsp;</input>");
		} else{
			sb.append(" value=\"" + dic.getItemCode() +"\" > " + dic.getItemValue() + "&emsp;</input>");
		}
		
		return sb;
	}
}
