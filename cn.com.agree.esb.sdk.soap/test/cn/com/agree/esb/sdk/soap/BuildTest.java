package cn.com.agree.esb.sdk.soap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

public class BuildTest {
	public static void main(String[] args) throws Exception {
//		long start = System.currentTimeMillis();
//		for (int i = 0; i < 10000; i++) {
//			loopTest1();
//			loopTest2();
//		}
//		long end = System.currentTimeMillis();
//		System.out.println(end-start);
		
		loopTest1();
	}
	
	public static void loopTest1() throws XMLStreamException, IOException{
		String requestSystemId = "MBS";
		String providerSystemId = "T001";
		String serviceNameEn = "DEQ00001";
		String operationNameEn = "A01";
		Map<String,Object> parameters = new LinkedHashMap<String, Object>();
		Map<String,Object> requestInformation = new LinkedHashMap<String, Object>();
		requestInformation.put("ittrDt", "2016-03-24");
		requestInformation.put("ittrStmInd", "C003");
		requestInformation.put("ittrChlInd", "020201");
		requestInformation.put("glbSeqNum", "?");
		requestInformation.put("acceptLan", "?");
		requestInformation.put("reqStmInd", "?");
		requestInformation.put("reqStmDt", "?");
		requestInformation.put("reqStmTm", "?");
		requestInformation.put("reqSeqNum", "?");
		requestInformation.put("legorgId", "?");
		requestInformation.put("mac", "?");
		requestInformation.put("bckInd", "?");
		requestInformation.put("bckId", "?");
		
		
		Map<String,Object> request = new LinkedHashMap<String, Object>();
		request.put("TxnOrgNum", "1");
		request.put("InstChinName", "C003");
		request.put("TlrNomber", "020201");
		request.put("StaffName", "fasfa");
		request.put("PltfmTxnSrc", "f");
		
		List<Object> acctNum = new ArrayList<Object>();
		acctNum.add("fasfa3");
		acctNum.add("fasfa2");
		acctNum.add("fasfa1");
		acctNum.add("fasfa4");
		Map<String,Object> acctNumList = new LinkedHashMap<String, Object>();
		acctNumList.put("acctNum", acctNum);
		request.put("AcctNumList", acctNumList);
		
		parameters.put("RequestInformation", requestInformation);
		parameters.put("Request", request);
		
		System.out.println("test11:"+parameters);
		
		byte[] soap = SoapUtil.buildRequestSoapMessage(requestSystemId,providerSystemId,null,serviceNameEn,operationNameEn,parameters,"GB18030");
		System.out.println("test11:"+new String(soap));
	}
	
	public static void loopTest2() throws XMLStreamException, IOException{
		String requestSystemId = "MBS";
		String providerSystemId = "T001";
		String serviceNameEn = "DEQ00001";
		String operationNameEn = "A01";
		Map<String,Object> parameters = new LinkedHashMap<String, Object>();
		Map<String,Object> requestInformation = new LinkedHashMap<String, Object>();
		requestInformation.put("ittrDt", "2016-03-24");
		requestInformation.put("ittrStmInd", "C003");
		requestInformation.put("ittrChlInd", "020201");
		requestInformation.put("glbSeqNum", "?");
		requestInformation.put("acceptLan", "?");
		requestInformation.put("reqStmInd", "?");
		requestInformation.put("reqStmDt", "?");
		requestInformation.put("reqStmTm", "?");
		requestInformation.put("reqSeqNum", "?");
		requestInformation.put("legorgId", "?");
		requestInformation.put("mac", "?");
		requestInformation.put("bckInd", "?");
		requestInformation.put("bckId", "?");
		
		
		Map<String,Object> request = new LinkedHashMap<String, Object>();
		request.put("TxnOrgNum", "1");
		request.put("InstChinName", "C003");
		request.put("TlrNomber", "020201");
		request.put("StaffName", "fasfa");
		request.put("PltfmTxnSrc", "f");
		
		Map<String,Object> a = new LinkedHashMap<String, Object>();
		a.put("a", "a");
		a.put("b", "b");
		
		Map<String,Object> b = new LinkedHashMap<String, Object>();
		b.put("c", "c");
		b.put("d", "d");
		
		List<Map<String,Object>> numList = new ArrayList<Map<String,Object>>();
		numList.add(a);
		numList.add(b);
		
		request.put("NumList", numList);
		
		parameters.put("RequestInformation", requestInformation);
		parameters.put("Request", request);
		
		System.out.println("test22:"+parameters);
		
		byte[] soap = SoapUtil.buildRequestSoapMessage(requestSystemId,providerSystemId,null,serviceNameEn,operationNameEn,parameters,"utf-8");
		System.out.println("test2:"+new String(soap));
	}
	
	public static void loopTest3() throws XMLStreamException, IOException{
		String requestSystemId = "MBS";
		String providerSystemId = "T001";
		String serviceNameEn = "DEQ00001";
		String operationNameEn = "A01";
		Map<String,Object> parameters = new LinkedHashMap<String, Object>();
		Map<String,Object> requestInformation = new LinkedHashMap<String, Object>();
		requestInformation.put("ittrDt", "2016-03-24");
		requestInformation.put("ittrStmInd", "C003");
		requestInformation.put("ittrChlInd", "020201");
		requestInformation.put("glbSeqNum", "?");
		requestInformation.put("acceptLan", "?");
		requestInformation.put("reqStmInd", "?");
		requestInformation.put("reqStmDt", "?");
		requestInformation.put("reqStmTm", "?");
		requestInformation.put("reqSeqNum", "?");
		requestInformation.put("legorgId", "?");
		requestInformation.put("mac", "?");
		requestInformation.put("bckInd", "?");
		requestInformation.put("bckId", "?");
		
		
		Map<String,Object> request = new LinkedHashMap<String, Object>();
		request.put("TxnOrgNum", "1");
		request.put("InstChinName", "C003");
		request.put("TlrNomber", "020201");
		request.put("StaffName", "fasfa");
		request.put("PltfmTxnSrc", "中国");
		
//		List<Object> acctNum = new ArrayList<Object>();
//		acctNum.add("fasfa3");
//		acctNum.add("fasfa2");
//		acctNum.add("fasfa1");
//		acctNum.add("fasfa4");
		Map<String,Object> acctNumList = new LinkedHashMap<String, Object>();
		acctNumList.put("acctNum", "11");
		acctNumList.put("acctNum1", "22");
		request.put("AcctNumList", acctNumList);
		
		parameters.put("RequestInformation", requestInformation);
		parameters.put("Request", request);
		
		//System.out.println("test11:"+parameters);
		
		byte[] soap = SoapUtil.buildRequestSoapMessage(requestSystemId,providerSystemId,null,serviceNameEn,operationNameEn,parameters,"utf-8");
		System.out.println("test11:"+new String(soap));
	}
	
}
