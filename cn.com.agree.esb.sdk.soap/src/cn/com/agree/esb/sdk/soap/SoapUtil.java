/*
 * Copyright(C) 2015 Agree Tech, All rights reserved.
 * 
 * Created on 2016年4月24日 下午3:42:58 by zhouzx
 */
package cn.com.agree.esb.sdk.soap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMContainer;
import org.apache.axiom.om.OMDocument;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;

/**
 * <DL>
 * <DT><B> soap报文的构建与解析 </B></DT>
 * <p>
 * <DD>提供三个功能：1.构建请求报文；2.构建响应报文；3.解析soap报文生成Map对象</DD>
 * </DL>
 * <p>
 * 
 * <DL>
 * <DT><B>使用范例</B></DT>
 * <p>
 * <DD>使用范例说明</DD>
 * </DL>
 * <p>
 * 
 * @author zhouzx
 * @email zhou.zx@agree.com.cn
 * @date 2016年4月24日 下午3:42:58
 * @version 1.0
 * @since 1.0
 *
 */
public class SoapUtil {
	
	private static final Pattern PROCESS_XML_HEADER_PATTERN = Pattern.compile("^\\s*<\\?xml.*\\?>");
	
	private static final String XML_ENCODING_HEADER = "<?xml version=\"1.0\" encoding=\"%s\"?>";

	private static final String ROOT_NAMESPACE = "http://schemas.xmlsoap.org/soap/envelope/";

	private static final String HEAD_NAMESPACE = "http://www.w3.org/2005/08/addressing";

	private static final String ACTION_PATTEN = "http://ksrcb.service.com/%s/%s/%s";

	private static final String COMMON_NAMESPACE = "http://ksrcb.service.com/AESB/Common/Schema";

	private static final String CONTENT_NAMESPACE = "http://ksrcb.service.com/%s/%s/Schema";

	private static final String G_PARENT_MAP = "T_SOAP_ANALYZER_PARENT_MAP";

	private static final String G_XML_PATH = "T_SOAP_XML_PATH";

	private static final String SOAP_ENVELOPE = "Envelope";

	private static final String SOAP_HEADER = "Header";

	private static final String SOAP_ACTION = "Action";

	private static final String SOAP_BODY = "Body";

	private static final String SOAP_REQ_INFO= "ReqInfo";

	private static final String SOAP_RSP_INFO = "RspInfo";

	private static final String SOAP_REQUEST = "Request";

	private static final String SOAP_RESPONSE = "Response";

	private static final String SOAP_FROM = "From";

	private static final String SOAP_TO = "To";

	private static final String SOAP_ADDRESS = "Address";

	private static final String SOAP_MESSAGEID = "MessageID";

	private static final String SOAP_RELATESTO = "RelatesTo";

	private static final String UUID_PATTEN = "urn:uuid:%s";

	private static final String RESPONSE_PATTEN = "%sResponse";

	private static final String DEFAULT_ENCODING = "UTF-8";

	private static XMLInputFactory inputFactory = XMLInputFactory.newInstance();
	
	private static XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

	/**
	 * 
	 *  构建请求SOAP报文,无需提供messageId(推荐使用)
	 * 
	 * @param requestSystemId  消费方系统ID
	 * @param providerSystemId 提供方系统ID
	 * @param serviceNameEn 服务英文名
	 * @param operationNameEn 操作英文名
	 * @param parameters 拼好值的Map对象
	 * @param encoding  消费方系统字符集
	 * @return 拼好的SOAP报文字节数组，字符集为传入的encoding
	 * @throws XMLStreamException
	 * @throws IOException
	 */
	public static byte[] buildRequestSoapMessage(String requestSystemId, String providerSystemId,
			String serviceNameEn, String operationNameEn, Map<String, Object> parameters, String encoding) 
					throws XMLStreamException, IOException{
		return buildRequestSoapMessage(requestSystemId, providerSystemId, null, serviceNameEn, operationNameEn,
				parameters, encoding);
	}
	
	/**
	 *  默认使用utf-8字符集拼请求soap报文
	 *  
	 * @param requestSystemId  消费方系统ID
	 * @param providerSystemId 提供方系统ID
	 * @param messageId 报文ID
	 * @param serviceNameEn 服务英文名
	 * @param operationNameEn 操作英文名
	 * @param parameters 拼好值的Map对象
	 * @return 构建的SOAP报文字节数组，字符集为传入的encoding
	 * @throws XMLStreamException
	 * @throws IOException
	 */
	public static byte[] buildRequestSoapMessage(String requestSystemId, String providerSystemId, String messageId,
			String serviceNameEn, String operationNameEn, Map<String, Object> parameters)
					throws XMLStreamException, IOException {
		return buildRequestSoapMessage(requestSystemId, providerSystemId, messageId, serviceNameEn, operationNameEn,
				parameters, DEFAULT_ENCODING);
	}
	
	/**
	 * 拼响应soap报文,无需messageId(推荐使用)
	 * 
	 * @param requestSystemId  消费方系统ID
	 * @param providerSystemId 提供方系统ID
	 * @param reqMessageId 请求报文ID
	 * @param serviceNameEn 服务英文名
	 * @param operationNameEn 操作英文名
	 * @param parameters 拼好值的Map对象
	 * @return 构建的SOAP报文字节数组，字符集为传入的encoding
	 * @throws XMLStreamException
	 * @throws IOException
	 */
	public static byte[] buildResponseSoapMessage(String requestSystemId, String providerSystemId, String reqMessageId,
			String serviceNameEn, String operationNameEn, Map<String, Object> parameters, String encoding)
					throws XMLStreamException, IOException {
		return buildResponseSoapMessage(requestSystemId, providerSystemId, reqMessageId, null, serviceNameEn, operationNameEn,
				parameters, encoding);
	}
	
	/**
	 * 默认使用utf-8字符集拼响应soap报文
	 * @param requestSystemId  消费方系统ID
	 * @param providerSystemId 提供方系统ID
	 * @param reqMessageId 请求报文ID
	 * @param serviceNameEn 服务英文名
	 * @param operationNameEn 操作英文名
	 * @param parameters 拼好值的Map对象
	 * @return 构建的SOAP报文字节数组，字符集为传入的encoding
	 * @throws XMLStreamException
	 * @throws IOException
	 */
	public static byte[] buildResponseSoapMessage(String requestSystemId, String providerSystemId, String reqMessageId,
			String messageId, String serviceNameEn, String operationNameEn, Map<String, Object> parameters)
					throws XMLStreamException, IOException {
		return buildResponseSoapMessage(requestSystemId, providerSystemId, reqMessageId, messageId, serviceNameEn, operationNameEn,
				parameters, DEFAULT_ENCODING);
	}
	
	/**
	 *  默认使用utf-8字符集拆soap报文
	 * @param messageBytes  soap报文字节数组
	 * @return Map形式的对象
	 * @throws IOException
	 * @throws XMLStreamException
	 */
	public static Map<String, Object> parseSoapMessage(byte[] messageBytes)
			throws IOException, XMLStreamException {
		return parseSoapMessage(messageBytes, DEFAULT_ENCODING);
	}

	/**
	 * 构建请求SOAP报文
	 * 
	 * @param requestSystemId  消费方系统ID
	 * @param providerSystemId 提供方系统ID
	 * @param messageId 报文ID
	 * @param serviceNameEn 服务英文名
	 * @param operationNameEn 操作英文名
	 * @param parameters 拼好值的Map对象
	 * @param encoding  消费方系统字符集
	 * @return 拼好的SOAP报文字节数组，字符集为传入的encoding
	 * @throws XMLStreamException
	 * @throws IOException
	 */
	public static byte[] buildRequestSoapMessage(String requestSystemId, String providerSystemId, String messageId,
			String serviceNameEn, String operationNameEn, Map<String, Object> parameters, String encoding)
					throws XMLStreamException, IOException {
		OMFactory factory = OMAbstractFactory.getOMFactory();
		OMDocument document = factory.createOMDocument();
		OMNamespace soapenv = factory.createOMNamespace(ROOT_NAMESPACE, "soapenv");
		OMNamespace p0Element = factory.createOMNamespace(COMMON_NAMESPACE, "p0");
		OMNamespace p1Element = factory
				.createOMNamespace(String.format(CONTENT_NAMESPACE, providerSystemId, serviceNameEn), "p1");
		OMElement rootElement = factory.createOMElement(SOAP_ENVELOPE, soapenv);
		rootElement.setNamespace(p0Element);
		rootElement.setNamespace(p1Element);
		rootElement.setNamespace(soapenv);

		OMElement headerElement = generateHeader(factory, soapenv, requestSystemId, providerSystemId, messageId,
				serviceNameEn, operationNameEn, true);

		OMElement bodyElement = generateBody(factory, soapenv, providerSystemId, serviceNameEn, operationNameEn,
				parameters, true, p0Element, p1Element);

		rootElement.addChild(headerElement);
		rootElement.addChild(bodyElement);
		document.setCharsetEncoding(DEFAULT_ENCODING);
		document.addChild(rootElement);
		return exportMessageData(document, encoding);
	}
	

	/**
	 * 构建响应SOAP报文
	 * 
	 * @param requestSystemId  消费方系统ID
	 * @param providerSystemId 提供方系统ID
	 * @param reqMessageId 请求报文ID
	 * @param serviceNameEn 服务英文名
	 * @param operationNameEn 操作英文名
	 * @param parameters 拼好值的Map对象
	 * @param encoding  消费方系统字符集
	 * @return 拼好的SOAP报文字节数组，字符集为传入的encoding
	 * @throws XMLStreamException
	 * @throws IOException
	 */
	public static byte[] buildResponseSoapMessage(String requestSystemId, String providerSystemId, String reqMessageId,
			String messageId ,String serviceNameEn, String operationNameEn, Map<String, Object> parameters, String encoding)
					throws XMLStreamException, IOException {
		OMFactory factory = OMAbstractFactory.getOMFactory();
		OMNamespace soapenv = factory.createOMNamespace(ROOT_NAMESPACE, "soapenv");
		OMNamespace p0Element = factory.createOMNamespace(COMMON_NAMESPACE, "p0");
		OMNamespace p1Element = factory
				.createOMNamespace(String.format(CONTENT_NAMESPACE, providerSystemId, serviceNameEn), "p1");
		OMElement rootElement = factory.createOMElement(SOAP_ENVELOPE, soapenv);
		OMDocument document = factory.createOMDocument();
		rootElement.setNamespace(p0Element);
		rootElement.setNamespace(p1Element);
		rootElement.setNamespace(soapenv);

		OMElement headerElement = generateHeader(factory, soapenv, requestSystemId, providerSystemId, 
				messageId, serviceNameEn, operationNameEn, false);
		OMElement toElement = (OMElement) headerElement.getChildElements().next();
		OMElement relatesToElement = factory.createOMElement(SOAP_RELATESTO, toElement.getNamespace());
		if (null == reqMessageId || "".equals(reqMessageId)) {
			throw new RuntimeException("请求报文reqMessageId不能为空！");
		}
		relatesToElement.addChild(factory.createOMText(relatesToElement, reqMessageId));
		headerElement.addChild(relatesToElement);

		OMElement bodyElement = generateBody(factory, soapenv, providerSystemId, serviceNameEn, operationNameEn,
				parameters, false, p0Element, p1Element);

		rootElement.addChild(headerElement);
		rootElement.addChild(bodyElement);
		document.setCharsetEncoding(DEFAULT_ENCODING);
		document.addChild(rootElement);
		return exportMessageData(document, encoding);
	}
	
	

	/**
	 * SOAP报文解析
	 * 
	 * @param messageBytes  soap报文字节数组
	 * @param encoding 报文的字符集
	 * @return Map形式的对象
	 * @throws IOException
	 * @throws XMLStreamException
	 */
	public static Map<String, Object> parseSoapMessage(byte[] messageBytes, String encoding)
			throws IOException, XMLStreamException {
		Map<String, Object> results = new HashMap<String, Object>();
		ByteArrayInputStream input = new ByteArrayInputStream(messageBytes);
		
		/*
		 * 处理器可以用单个存储块返回所有连续的字符数据，它也可以将其分割成几个存储块
		 * 如果属性 javax.xml.stream.isCoalescing 设置为 true，
		 * 	则元素内容必须组合，对于连续元素内容或 CDATA 节，只需要返回一个 CHARACTERS 事件
		 */
		inputFactory.setProperty("javax.xml.stream.isCoalescing", true);
		try {
			XMLStreamReader reader = inputFactory.createXMLStreamReader(input, encoding);
			parseReader(results, reader);
		} finally {
			input.close();
		}
		return results;
	}
	
	/**
	 * 处理循环节点
	 * @param parentMap	父节点对应的Map
	 * @param tagName 目标标签名称
	 * @return	返回List集合
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> resolveLoop(Map<String, Object> parentMap, String tagName) {
		Object obj =  parentMap.get(tagName);
		List<Map<String, Object>> result = null;
		if (obj instanceof List<?>) {
			result = (List<Map<String, Object>>) obj;
		} else {
			result = new ArrayList<Map<String,Object>>();
			Map<String, Object> map = (Map<String, Object>) obj;
			result.add(map);
		}
		
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void parseReader(Map<String,Object> parentMap,XMLStreamReader reader) throws XMLStreamException {
		try {
			List<String> loopPaths = new ArrayList<String>();
			Map<String,Object> currentMap = null;
			int event = reader.getEventType();
			boolean isFrist = true;
			String elementName = null;
			String elementValue = null;
			while (true) {
				switch (event) {
				case XMLStreamConstants.START_DOCUMENT:
					break;
				case XMLStreamConstants.START_ELEMENT:
					elementName = reader.getName().getLocalPart();
					if(SOAP_ENVELOPE.equals(elementName)){
						break;
					}
					//先存储父MAP
					currentMap = new LinkedHashMap<String,Object>();
					currentMap.put(G_PARENT_MAP, parentMap);
					//判断当前节点是否已经存在
					Object oldContext = parentMap.get(elementName);
					if(null == oldContext || isFrist){
						String path = "";
						if (isFrist) {
							path = elementName;
							currentMap.put(G_XML_PATH, path);
						} else {
							path = parentMap.get(G_XML_PATH) == null ? SOAP_BODY : parentMap.get(G_XML_PATH) + "." + elementName;
							currentMap.put(G_XML_PATH, path);
						}
						
						boolean isloop = loopPaths.contains(path);
						if (!isloop) {
							// 没有循环节点
							parentMap.put(elementName, currentMap);
							parentMap = currentMap;
							//把节点路径都加入，方便后面出现循环字段进行判断
							loopPaths.add(path);
						}else{
							parentMap.put(elementName, currentMap);
							parentMap = currentMap;
						}
					}else if (oldContext instanceof Map) {
						Map brotherMap = (Map)oldContext;
						// 存在重名节点,转成list存贮
						List<Object> list = new ArrayList<Object>();
						if(brotherMap.isEmpty()){
							list.add("");
						}else{
							list.add(oldContext);
						}
						list.add(currentMap);
						//add
						String path = parentMap.get(G_XML_PATH) + "." + elementName;
						currentMap.put(G_XML_PATH, path);
						//end
						parentMap.put(elementName, list);
						parentMap = currentMap;
						
					} else if (oldContext instanceof List<?>) {
						List<Object> list = ((List<Object>) oldContext);
						list.add(list.size(), currentMap);
						//add
						String path = parentMap.get(G_XML_PATH) + "." + elementName;
						currentMap.put(G_XML_PATH,path);
						//end
						parentMap = currentMap;
					} else {
						/** 名空间属性与字段属性重名时,忽略名空间的值 */
						/** 当前结点路径 */
						String path = "";
						if (isFrist) {
							path = elementName;
							currentMap.put(G_XML_PATH, path);
						} else {
							path = parentMap.get(G_XML_PATH) + "." + elementName;
							currentMap.put(G_XML_PATH, path);
						}
						
						loopPaths.remove(path);
						// 存在重名节点,转成list存储
						List<Object> list = new ArrayList<Object>();
						Object brother = parentMap.remove(elementName);
						String parentPath = (String) parentMap.get(G_XML_PATH);
						Object superObject = ((Map) parentMap.get(G_PARENT_MAP)).get(parentPath.substring(parentPath.lastIndexOf(".")+1));
						if(superObject instanceof Map){
							//将原兄弟节点重新存储
							Map<String,Object> superMap = (Map<String,Object>)superObject;
							list.add(0, brother);
							list.add(1, currentMap);
							superMap.put(elementName, list);
						}else if(superObject instanceof List){
							list.add(0, brother);
							parentMap.put(elementName, list);
						}
						
						parentMap = currentMap;
					}
					isFrist = false;
					break;
				case XMLStreamConstants.CHARACTERS:
					String text = reader.getText();
					if (reader.isWhiteSpace()) { 
						break;
					}
					String currentPath = (String) parentMap.get(G_XML_PATH);
					parentMap = (Map<String,Object>) currentMap.get(G_PARENT_MAP);
					String element = currentPath.substring(currentPath.lastIndexOf(".") + 1);
					Object object = parentMap.get(element);
					
					getConstainsValue(object,element,text,parentMap,currentMap);
					elementValue = text;
					break;
				case XMLStreamConstants.END_ELEMENT:
					parentMap = (Map<String,Object>) currentMap.remove(G_PARENT_MAP);
					currentMap.remove(G_XML_PATH);
					if(parentMap != null){
						Object value = parentMap.get(elementName);
						//判断节点的值是否为空，如果为空，则将空Map替换为空字符串。
						if(value instanceof Map){
							Map elementMap = (Map) value;
							if(elementMap.isEmpty()){
								parentMap.put(elementName, "");
							}
						}else if(value instanceof List){
							List elementList = (List) value;
							if(elementList.isEmpty()){
								parentMap.put(elementName, "");
							}else if(elementValue == null && elementList.contains(currentMap)){
								elementList.remove(currentMap);//节点的值为空，要移除Map
								elementList.add("");
							}
						}
					}
					elementValue = null;
					currentMap = parentMap;
					break;
				case XMLStreamConstants.END_DOCUMENT:
					break;
				}
				if (!reader.hasNext()){
					break;
				}
				event = reader.next();
			}
		} finally {
			reader.close();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void getConstainsValue(Object object, String element, String text,
			Map<String,Object> parentMap, Map<String,Object> currentMap){
		if(null == object){
			parentMap.put(element, text);
		}else if(object instanceof Map){
			Object o = ((Map)object).get(element);
			if(null != o){
				getConstainsValue(o,element,text,parentMap,currentMap);
			}else{
				parentMap.put(element, text);
			}
		}else if(object instanceof List<?>){
			List<Object> list = ((List<Object>) object);
			list.remove(currentMap);//子节点是值，需要移除Map
			list.add(text);
		}
	}

	/**
	 * 生成Body的方法。名空间定义在Envelope上。
	 * @param factory
	 * @param soapenv
	 * @param providerSystemId
	 * @param serviceNameEn
	 * @param operationNameEn
	 * @param parameters
	 * @param isRequest
	 * @param p0Element
	 * @param p1Element
	 * @return
	 */
	private static OMElement generateBody(OMFactory factory, OMNamespace soapenv, String providerSystemId,
			String serviceNameEn, String operationNameEn, Map<String, Object> parameters, boolean isRequest, OMNamespace p0Element, OMNamespace p1Element) {
		OMElement bodyElement = factory.createOMElement(SOAP_BODY, soapenv);
		String elementName = null;
		if (isRequest) {
			elementName = operationNameEn;
		} else {
			elementName = String.format(RESPONSE_PATTEN, operationNameEn);
		}
		OMElement contentElement = factory.createOMElement(elementName, p1Element);

		generateContent(contentElement, factory, null, p0Element, p1Element, parameters);
		bodyElement.addChild(contentElement);
		return bodyElement;
	}
	
	/**
	 * 生成Body的方法。名空间定义在Body上。
	 * @param factory
	 * @param soapenv
	 * @param providerSystemId
	 * @param serviceNameEn
	 * @param operationNameEn
	 * @param parameters
	 * @param isRequest
	 * @return
	 */
	@SuppressWarnings("unused")
	private static OMElement generateBody(OMFactory factory, OMNamespace soapenv, String providerSystemId,
			String serviceNameEn, String operationNameEn, Map<String, Object> parameters, boolean isRequest) {
		OMElement bodyElement = factory.createOMElement(SOAP_BODY, soapenv);
		OMNamespace p0Element = factory.createOMNamespace(COMMON_NAMESPACE, "p0");
		OMNamespace p1Element = factory
				.createOMNamespace(String.format(CONTENT_NAMESPACE, providerSystemId, serviceNameEn), "p1");
		String elementName = null;
		if (isRequest) {
			elementName = operationNameEn;
		} else {
			elementName = String.format(RESPONSE_PATTEN, operationNameEn);
		}
		OMElement contentElement = factory.createOMElement(elementName, p1Element);
		contentElement.setNamespace(p0Element);
		contentElement.setNamespace(p1Element);

		generateContent(contentElement, factory, null, p0Element, p1Element, parameters);
		bodyElement.addChild(contentElement);
		return bodyElement;
	}

	/**
	 * 递归生成循环节点
	 * 如果是value为空字符串，则视为空节点，组包时不予以处理
	 * @param childList
	 * @param parentElement
	 * @param factory
	 * @param superNamespace
	 * @param p0Element
	 * @param p1Element
	 */
	private static void generateContent(OMElement parentElement, OMFactory factory, OMNamespace superNamespace,
			OMNamespace p0Element, OMNamespace p1Element, Map<?, ?> parameters) {
		for (Object element : parameters.keySet()) {
			Object childParameters = parameters.get(element);
			
			/*
			 * 如需去空节点，释放注释 
			 */
//			if ("".equals(childParameters.toString())) {
//				continue;
//			}
			
			superNamespace = getNamespace(superNamespace, element, p0Element, p1Element);
			OMElement omElement = factory.createOMElement(element.toString(), superNamespace);
			parentElement.addChild(omElement);
			
			if (null != childParameters) {
				if (childParameters instanceof Map<?, ?>) {
					generateContent(omElement, factory, superNamespace, p0Element, p1Element,
							(Map<?, ?>) childParameters);
				} else if (childParameters instanceof List<?>) {
					generateContent(parentElement, ( List<?>) childParameters, omElement, factory, superNamespace, p0Element,
							p1Element);
				} else {
					omElement.addChild(factory.createOMText(omElement, childParameters.toString()));
				}
			}
		}
	}

	/**
	 * 递归生成循环节点
	 * 
	 * @param childList
	 * @param parentElement
	 * @param factory
	 * @param superNamespace
	 * @param p0Element
	 * @param p1Element
	 */
	private static void generateContent(OMContainer container, List<?> childList, OMElement parentElement, OMFactory factory,
			OMNamespace superNamespace, OMNamespace p0Element, OMNamespace p1Element) {
		for (Iterator<?> iterator = childList.iterator(); iterator.hasNext();) {
			Object child = iterator.next();
			
			/*
			 * 如需去空节点，释放注释
			 */
//			if ("".equals(child.toString())) {
//				continue;
//			}
			
			OMElement omElement = factory.createOMElement(parentElement.getQName().getLocalPart(), superNamespace);
			container.addChild(omElement);
			parentElement = omElement;
			
			if (child instanceof Map<?, ?>) {
				generateContent(parentElement, factory, superNamespace, p0Element, p1Element, (Map<?, ?>) child);
			} else if (child instanceof List<?>) {
				generateContent(parentElement.getParent(), (List<?>) child, parentElement, factory, superNamespace, p0Element, p1Element);
			} else {
				parentElement.addChild(factory.createOMText(parentElement, child.toString()));
			}
		}
	}

	private static OMNamespace getNamespace(OMNamespace superNamespace, Object element, OMNamespace p0Element,
			OMNamespace p1Element) {
		if (SOAP_REQ_INFO.equalsIgnoreCase(element.toString())
				|| SOAP_RSP_INFO.equalsIgnoreCase(element.toString())) {
			return p0Element;
		} else if (SOAP_REQUEST.equalsIgnoreCase(element.toString())
				|| SOAP_RESPONSE.equalsIgnoreCase(element.toString())) {
			return p1Element;
		}
		return superNamespace;
	}

	private static OMElement generateHeader(OMFactory factory, OMNamespace soapenv, String requestSystemId,
			String providerSystemId, String messageId, String serviceNameEn, String operationNameEn, boolean isRequest) {
		OMElement headerElement = factory.createOMElement(SOAP_HEADER, soapenv);
		OMNamespace wsa = factory.createOMNamespace(HEAD_NAMESPACE, "wsa");
		headerElement.setNamespace(wsa);
		headerElement.setNamespace(soapenv);

		OMElement toElement = factory.createOMElement(SOAP_TO, wsa);
		toElement.addChild(factory.createOMText(toElement, providerSystemId));

		OMElement fromElement = factory.createOMElement(SOAP_FROM, wsa);
		OMElement addressElement = factory.createOMElement(SOAP_ADDRESS, wsa);
		addressElement.addChild(factory.createOMText(addressElement, requestSystemId));
		fromElement.addChild(addressElement);

		OMElement actionElement = factory.createOMElement(SOAP_ACTION, wsa);
		actionElement.addChild(factory.createOMText(actionElement,
				String.format(ACTION_PATTEN, providerSystemId, serviceNameEn, operationNameEn)));

		OMElement messageIDElement = factory.createOMElement(SOAP_MESSAGEID, wsa);
		if (null == messageId || "".equals(messageId)) {
			messageId = String.format(UUID_PATTEN, UUID.randomUUID());
		}
		messageIDElement.addChild(factory.createOMText(messageIDElement, messageId));

		headerElement.addChild(toElement);
		headerElement.addChild(fromElement);
		headerElement.addChild(actionElement);
		headerElement.addChild(messageIDElement);

		return headerElement;
	}

	private static byte[] exportMessageData(OMDocument document, String encoding)
			throws XMLStreamException, IOException {
		ByteArrayOutputStream out = null;
		byte[] result = null;
		XMLStreamWriter writer = null;
		try {
			out = new ByteArrayOutputStream();
			writer = outputFactory.createXMLStreamWriter(out, DEFAULT_ENCODING);
			document.serialize(writer);
			writer.flush();
			result = transferSoapEncoding(out.toByteArray(), DEFAULT_ENCODING, encoding);
		} finally {
			if (writer != null) {
				writer.close();
			}
			if (out != null) {
				out.close();
			}
		}
		return result;
	}
	
	private static  byte[] transferSoapEncoding(byte[] bytes, String defaultEncoding, String targetEncoding)
			throws UnsupportedEncodingException {

		if (defaultEncoding.equals(targetEncoding)) {
			return bytes;
		}
		String xml = new String(bytes, defaultEncoding);

		Matcher matcher = PROCESS_XML_HEADER_PATTERN.matcher(xml);
		xml = matcher.replaceFirst(String.format(XML_ENCODING_HEADER, targetEncoding));

		return xml.toString().getBytes(targetEncoding);
	}

}
