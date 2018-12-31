package cn.com.agree.esb.sdk.soap.invoke.pack;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import cn.com.agree.esb.sdk.soap.SoapUtil;

public class BuildRequestSoapMessage {
	
	private static Map<String, Object> buildRequestMessag1() {
		Map<String,Object> parameters = new LinkedHashMap<String, Object>();
		
		/*
		 * 报文头
		 */
		Map<String,Object> reqInfo = new LinkedHashMap<String, Object>();
		reqInfo.put("IttrDt", "2016-04-05");
		reqInfo.put("IttrStmInd", "Core");
		reqInfo.put("IttrChlInd ", "010100 ");
		reqInfo.put("GloSeqNum ", "GT001201604050000000000000000100");
		reqInfo.put("I18nInd", "CH");
		reqInfo.put("ReqStmInd ", "Core");
		reqInfo.put("ReqStmDt ", "2016-04-05");
		reqInfo.put("ReqStmTm", "18:50:20");
		reqInfo.put("ReqSeqNum", "GT001201604050000000000000000100");
		reqInfo.put("LegOrgId ", "9999");
		reqInfo.put("MAC ", "");
		reqInfo.put("BckInd ", "");
		reqInfo.put("BckId", "");
		
		/*
		 * 报文体
		 */
		Map<String,Object> request = new LinkedHashMap<String, Object>();
		request.put("TxnOrgNum", "3052001");
		request.put("InstChinName", "");
		request.put("TlrNomber", "WYGY");
		request.put("StaffName", "");
		request.put("PltfmTxnSrc", "17");
		request.put("AcctNum", "6231320000000002921");
		
		parameters.put("ReqInfo", reqInfo);
		parameters.put("Request", request);
		return parameters;
	}
	
	public static void main(String[] args) {
		String requestSystemId = "EBNK";
		String providerSystemId = "CORE";
		String serviceNameEn = "DEQ00001";
		String operationNameEn = "A01";
		String encoding = "utf-8";   //根据系统情况自定义设置
		Map<String, Object> parameters = buildRequestMessag1();
		String messageId = null;
		byte[] result = null;
		try {
			result = SoapUtil.buildRequestSoapMessage(requestSystemId, providerSystemId, 
					messageId, serviceNameEn, operationNameEn, parameters, encoding);
			
			System.out.println(new String(result, encoding));
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
