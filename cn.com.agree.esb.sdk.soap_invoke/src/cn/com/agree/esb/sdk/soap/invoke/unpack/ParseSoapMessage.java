package cn.com.agree.esb.sdk.soap.invoke.unpack;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import cn.com.agree.esb.sdk.soap.SoapUtil;

public class ParseSoapMessage {
	
	@SuppressWarnings({ "unchecked" })
	public static void main(String[] args) {
		String soap="<?xml version='1.0' encoding='utf-8'?><soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/'><soapenv:Header xmlns:wsa='http://www.w3.org/2005/08/addressing'><wsa:To>T001</wsa:To><wsa:From><wsa:Address>C003</wsa:Address></wsa:From><wsa:Action>http://ksrcb.service.com/T001/DEQ00001/A01</wsa:Action><wsa:MessageID>urn:uuid:f6c1dacd-7c2e-46a2-a191-7d26c55adb6c</wsa:MessageID></soapenv:Header><soapenv:Body><p1:A01 xmlns:p1='http://ksrcb.service.com/T001/DEQ00001/Schema' xmlns:p0='http://ksrcb.service.com/B015/Common/Schema'><p0:requestInformation><p0:IttrDt>2016-04-05</p0:IttrDt><p0:IttrStmInd>C003</p0:IttrStmInd><p0:IttrChlInd>010100 </p0:IttrChlInd><p0:GlbSeqNum>GT001201604050000000000000000100</p0:GlbSeqNum><p0:I18nInd>CH</p0:I18nInd><p0:ReqStmInd>214640</p0:ReqStmInd><p0:ReqStmDt>2014-06-18</p0:ReqStmDt><p0:ReqStmTm>18:28:38</p0:ReqStmTm><p0:reqSeqNum>QC003201603240000000000000000001</p0:reqSeqNum><p0:LegOrgID>9999</p0:LegOrgID><p0:MAC>ABC</p0:MAC><p0:BckInd></p0:BckInd><p0:BckId></p0:BckId></p0:requestInformation><p1:request><AcctNum>7066500311120100028744</AcctNum><List><Map><TxDt>2016-04-27</TxDt><TxnRrefNum>121214</TxnRrefNum></Map></List></p1:request></p1:A01></soapenv:Body></soapenv:Envelope>";
		Map<String, Object> result;
		try {
			result = SoapUtil.parseSoapMessage(soap.getBytes(), "utf-8");
			
			Map<String,Object> body = (Map<String, Object>) result.get("Body");
			Map<String,Object> a01 = (Map<String, Object>) body.get("A01");
			Map<String, Object> request = (Map<String, Object>) a01.get("request");
			
			Map<String, Object> list = (Map<String, Object>) request.get("List");
			/*
			 * "Map" 标签为循环标签，需要调用静态方法resolveLoop获取相应的List集合
			 */
			List<Map<String, Object>> allMap = SoapUtil.resolveLoop(list, "Map");
			System.out.println(allMap);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

}
