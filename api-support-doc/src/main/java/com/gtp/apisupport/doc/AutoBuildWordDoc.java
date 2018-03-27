package com.gtp.apisupport.doc;

import java.awt.Color;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gtp.apisupport.annotation.ApiMethod;
import com.gtp.apisupport.annotation.ApiService;
import com.gtp.apisupport.common.JsonUtils;
import com.gtp.apisupport.common.ReflectUtil;
import com.gtp.apisupport.common.ScannerUtils;
import com.gtp.apisupport.doc.common.DocReflectUtil;
import com.gtp.apisupport.model.ApiBaseResult;
import com.gtp.apisupport.model.ApiPageResult;
import com.gtp.apisupport.model.ApiParamInfo;
import com.gtp.apisupport.model.ApiResult;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.style.RtfParagraphStyle;

/**
 * 构建API文档-word版
 */
public class AutoBuildWordDoc {

	/**
	 * 构建接口文档
	 * 
	 * @param docPath		文档输出地址
	 * @param p				所需要扫描的包
	 * @throws Exception
	 */
	public static void build(String docPath, List<String> p) throws Exception {

		Document doc = new Document(PageSize.A4);
		RtfWriter2.getInstance(doc, new FileOutputStream(docPath));
		doc.open();
		
		//写全局约束
		writerGlobalRule(doc);

		Font font10 = getFont(12, Font.NORMAL,Color.BLACK);

		List<Class<?>> list = ScannerUtils.getServiceEntry(p,ApiService.class);

		for (int i = 0; i < list.size(); i++) {

			Class<?> c = list.get(i);

			// h1
			ApiService apiService = c.getAnnotation(ApiService.class);
			doc.add(new Paragraph((i + 1) + " " + apiService.value(), RtfParagraphStyle.STYLE_HEADING_1));

			Method[] m = c.getDeclaredMethods();
			for (int j = 0; j < m.length; j++) {
				Method tm = m[j];
				if (Modifier.isPublic(tm.getModifiers()) && tm.getDeclaringClass() != Object.class) {

					// h2
					doc.add(new Paragraph(""));
					ApiMethod apiMethod = tm.getAnnotation(ApiMethod.class);
					if (apiMethod != null) {

						doc.add(new Paragraph((i + 1) + "." + (j + 1) + " " + apiMethod.desc(),
								RtfParagraphStyle.STYLE_HEADING_2));
						doc.add(new Paragraph(""));

						Table table = new Table(2);
						table.setWidth(32 * 3);
						table.setAlignment(Element.ALIGN_LEFT);
						table.setWidths(new float[] { 20f, 76f });

						// 方法编码
						Cell c1 = new Cell(new Phrase("服务编码", font10));
						table.addCell(c1);

						Cell c2 = new Cell(new Phrase(apiMethod.value(), font10));
						table.addCell(c2);

						// 输入						
						JSONObject args = new JSONObject();
						List<ApiParamInfo> params = ReflectUtil.getParameterInfo(tm);
						if(params!=null){
							for(ApiParamInfo p3:params){
								if(p3.getApiParam()!=null){
									if(p3.getIsList()){
										JSONArray tmpData=new JSONArray();
										tmpData.add(DocReflectUtil.allFields(p3.getType()));
										args.put(p3.getApiParam().value(),tmpData);
									}else if(DocReflectUtil.isBaseType(p3.getType())){
										args.put(p3.getApiParam().value(),p3.getApiParam().desc());
									}else{
										args.put(p3.getApiParam().value(), DocReflectUtil.allFields(p3.getType()));
									}
								}else{
									//没加注解
									if(p3.getIsList()){
										throw new RuntimeException("API接口不支持直接注入无注解List!"+tm);
									}else if(DocReflectUtil.isBaseType(p3.getType())){
										throw new RuntimeException("API接口不支持直接注入无注解基本类型!"+tm);
									}else{
										args=DocReflectUtil.allFields(p3.getType());
									}
								}
							}
						}
						String paramStr = JsonUtils.formatJson(args.toJSONString());
						Cell c3 = new Cell(new Phrase("输入", font10));
						table.addCell(c3);

						Cell c4 = new Cell(new Phrase(paramStr, font10));
						table.addCell(c4);

						// 输出
						Cell c5 = new Cell(new Phrase("输出", font10));
						table.addCell(c5);
						
						Class<?> r = tm.getReturnType();
						if(r == List.class){ //List
							
							Class<?> tmpC = getClassByType(tm.getGenericReturnType());
							
							JSONObject returnJson = DocReflectUtil.allFields(tmpC);
							JSONArray returnData=new JSONArray();
							returnData.add(returnJson);
							String returnStr = JsonUtils.formatJson(returnData.toJSONString());
							Cell c6 = new Cell(new Phrase(returnStr, font10));
							table.addCell(c6);
							
						}else{
							JSONObject returnJson = DocReflectUtil.allFields(r);
							String returnStr = JsonUtils.formatJson(returnJson.toJSONString());
							Cell c6 = new Cell(new Phrase(returnStr, font10));
							table.addCell(c6);
						}

						doc.add(table);
					}
				}
			}
		}

		doc.close();
	}

	private static Class<?> getClassByType(Type t) {
		
		if(t instanceof ParameterizedType){
			Type[] actualTypes = ((ParameterizedType) t).getActualTypeArguments();
			Class<?> tmpC = (Class<?>) actualTypes[0];
			return tmpC;
		}
		
		return null;
	}

	private static void writerGlobalRule(Document doc) throws DocumentException {
		
		//标题
		doc.add(new Paragraph("文档约定(说明)", RtfParagraphStyle.STYLE_HEADING_1));
		doc.add(new Paragraph(""));
		Font font10 = getFont(12, Font.NORMAL,Color.RED);
		
		//输入
		doc.add(new Paragraph("输入",RtfParagraphStyle.STYLE_HEADING_2));
		
		JSONObject args=new JSONObject();
		args.put("xx1","参数1");
		args.put("xxN","参数N");
		args.put("token","登录后获得的访问凭证");
		
		
		JSONObject p1=new JSONObject();
		p1.put("serviceModule", "模块名称");
		p1.put("serviceNumber", "方法编码");
		p1.put("token","登录后获得的访问凭证");
		p1.put("args",args);
		
		doc.add(new Paragraph(JsonUtils.formatJson(p1.toJSONString()),font10));
		doc.add(new Paragraph(""));
		
		//输出
		doc.add(new Paragraph(""));
		doc.add(new Paragraph("输出",RtfParagraphStyle.STYLE_HEADING_2));
		doc.add(new Paragraph(JsonUtils.formatJson(DocReflectUtil.allFields(ApiResult.class).toJSONString()),font10));
		doc.add(new Paragraph(""));
		
		doc.add(new Paragraph(""));
		doc.add(new Paragraph("*其中serviceResult:",font10));
		doc.add(new Paragraph(JsonUtils.formatJson(DocReflectUtil.allFields(ApiBaseResult.class).toJSONString()),font10));
		doc.add(new Paragraph(""));
		
		doc.add(new Paragraph(""));
		doc.add(new Paragraph("*其中result分页查询时:",font10));
		doc.add(new Paragraph(JsonUtils.formatJson(DocReflectUtil.allFields(ApiPageResult.class).toJSONString()),font10));
		doc.add(new Paragraph(""));
		
	}

	private static Font getFont(int fontSize,int fontStyle,Color c) {
		Font chineseFont = null;
		try {
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			chineseFont = new Font(bfChinese, fontSize, fontStyle,c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chineseFont;
	}
}
