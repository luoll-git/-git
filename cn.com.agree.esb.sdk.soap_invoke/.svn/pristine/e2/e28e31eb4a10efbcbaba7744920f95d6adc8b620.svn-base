package cn.com.agree.esb.sdk.soap.invoke.pack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import cn.com.agree.esb.sdk.soap.SoapUtil;

public class BuildResponseSoapMessage {
	
	private static Map<String, Object> buildResponseMessag() {
		Map<String,Object> parameters = new LinkedHashMap<String, Object>();
		
		/*
		 * 报文头
		 */
		Map<String,Object> rspInfo = new LinkedHashMap<String, Object>();
		rspInfo.put("IttrDt", "2016-03-24");
		rspInfo.put("IttrStmInd", "C003");
		rspInfo.put("IttrChlInd", "020201");
		rspInfo.put("GloSeqNum", "");
		rspInfo.put("AcceptLan", "?");
		rspInfo.put("ReqStmInd", "?");
		rspInfo.put("ReqStmDt", "?");
		rspInfo.put("ReqStmTm", "?");
		rspInfo.put("ReqSeqNum", "?");
		rspInfo.put("LegOrgId", "?");
		rspInfo.put("Mac", "?");
		rspInfo.put("BckInd", "?");
		rspInfo.put("BckId", "?");
		
		/*
		 * 报文体
		 */
		Map<String,Object> response = new LinkedHashMap<String, Object>();
		response.put("TxnOrgNum", "1");
		response.put("InstChinName", "C003");
		response.put("TlrNomber", "020201");
		response.put("StaffName", "测试人员 ");
		response.put("PltfmTxnSrc", "f");
		
		List<Object> acctNum = new ArrayList<Object>();
		acctNum.add("fasfa3");
		acctNum.add("fasfa2");
		acctNum.add("fasfa1");
		acctNum.add("fasfa4");
		Map<String,Object> acctNumList = new LinkedHashMap<String, Object>();
		acctNumList.put("acctNum", acctNum);
		response.put("AcctNumList", acctNumList);
		
		parameters.put("RspInfo", rspInfo);
		parameters.put("Response", response);
		
		return parameters;
	}
	
	public static void main(String[] args) {
		String requestSystemId = "MBS";
		String providerSystemId = "T001";
		
		/*
		 * 此处reqMessageId不为空，应填写实际请求报文的messageId
		 */
		String reqMessageId = "urn:uuid:a42b103a-8400-44a1-b93b-da1509fb3af4";
		
		/*
		 * messageId可以为空
		 */
		String messageId = "urn:uuid:a42b103a-8400-44a1-b93b-da1509fb3as3";
		String serviceNameEn = "DEQ00001";
		String operationNameEn = "A01";
		String encoding = "GB18030";//根据系统情况自定义设置
		Map<String, Object> parameters = buildResponseMessag();
		byte[] result = null;
		try {
			result = SoapUtil.buildResponseSoapMessage(requestSystemId, providerSystemId, 
					reqMessageId, messageId, serviceNameEn, operationNameEn, parameters, encoding);
			String resultString = new String(result, encoding);
			System.out.println(resultString);
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
