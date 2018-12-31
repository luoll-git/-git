package cn.com.agree.esb.sdk.soap;

import java.io.IOException;
import java.util.Map;

import javax.xml.stream.XMLStreamException;


public class ParseTest {
	public static void main(String[] args) throws IOException, XMLStreamException {
//		long start = System.currentTimeMillis();
//		for (int i = 0; i < 10000; i++) {
//			parse1();
//			parse2();
//			parse3();
//			parse4();
//			parse5();
//		}
//		long end = System.currentTimeMillis();
//		System.out.println(end-start);
		
		parse1();
		parse2();
		parse3();
		parse4();
		parse5();
	}
	
	/**
	 * 单层值循环
	 * @throws IOException
	 * @throws XMLStreamException
	 */
	@SuppressWarnings("unchecked")
	public static void parse1() throws IOException, XMLStreamException{
		String soap="<?xml version=\"1.0\" ?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Header xmlns:wsa=\"http://www.w3.org/2005/08/addressing\"><wsa:To>T001</wsa:To><wsa:From><wsa:Address>MBS</wsa:Address></wsa:From><wsa:Action>http://ksrcb.service.com/T001/DEQ00001/A01</wsa:Action><wsa:MessageID>urn:uuid:bb1df038-f089-4b3b-844c-7acfd9c90090</wsa:MessageID></soapenv:Header><soapenv:Body><p0:A01 xmlns:p0=\"http://ksrcb.service.com/T001/DEQ00001/Schema\" xmlns:p1=\"http://ksrcb.service.com/B015/Common/Schema\"><p1:RequestInformation><p1:ittrDt>2016-03-24</p1:ittrDt><p1:ittrStmInd>C003</p1:ittrStmInd><p1:ittrChlInd>020201</p1:ittrChlInd><p1:glbSeqNum>?</p1:glbSeqNum><p1:acceptLan>反对撒风</p1:acceptLan><p1:reqStmInd>?</p1:reqStmInd><p1:reqStmDt>?</p1:reqStmDt><p1:reqStmTm>?</p1:reqStmTm><p1:reqSeqNum>?</p1:reqSeqNum><p1:legorgId>?</p1:legorgId><p1:mac>?</p1:mac><p1:bckInd>?</p1:bckInd><p1:bckId>?</p1:bckId></p1:RequestInformation><p0:Request><p0:TxnOrgNum>1</p0:TxnOrgNum><p0:InstChinName>C003</p0:InstChinName><p0:TlrNomber>020201</p0:TlrNomber><p0:StaffName>fasfa</p0:StaffName><p0:PltfmTxnSrc>f</p0:PltfmTxnSrc><p0:AcctNumList><p0:acctNum>fasfa3</p0:acctNum><p0:acctNum>fasfa2</p0:acctNum><p0:acctNum>fasfa1</p0:acctNum><p0:acctNum>fasfa4</p0:acctNum></p0:AcctNumList></p0:Request></p0:A01></soapenv:Body></soapenv:Envelope>";
		Map<String,Object> result = SoapUtil.parseSoapMessage(soap.getBytes("gbk"), "gbk");
		System.out.println(result);
		Map<String,Object> body = (Map<String, Object>) result.get("Body");
		Map<String,Object> a01 = (Map<String, Object>) body.get("A01");
		String requestSystemId = "MBS";
		String providerSystemId = "T001";
		String serviceNameEn = "DEQ00001";
		String operationNameEn = "A01";
		
		byte[] soapByte = SoapUtil.buildRequestSoapMessage(requestSystemId,providerSystemId,null,serviceNameEn,operationNameEn,a01,"gbk");
		System.out.println(new String(soapByte,"gbk"));
	}
	
	/**
	 * 单层节点循环
	 * @throws XMLStreamException 
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public static void parse2() throws IOException, XMLStreamException{
		//String soap="<?xml version=\"1.0\" ?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Header xmlns:wsa=\"http://www.w3.org/2005/08/addressing\"><wsa:To>T001</wsa:To><wsa:From><wsa:Address>MBS</wsa:Address></wsa:From><wsa:Action>http://ksrcb.service.com/T001/DEQ00001/A01</wsa:Action><wsa:MessageID>urn:uuid:2ed96377-a16c-4acc-bdbb-31edecabfdd2</wsa:MessageID></soapenv:Header><soapenv:Body><p0:A01 xmlns:p0=\"http://ksrcb.service.com/T001/DEQ00001/Schema\" xmlns:p1=\"http://ksrcb.service.com/B015/Common/Schema\"><p1:RequestInformation><p1:ittrDt>2016-03-24</p1:ittrDt><p1:ittrStmInd>C003</p1:ittrStmInd><p1:ittrChlInd>020201</p1:ittrChlInd><p1:glbSeqNum>?</p1:glbSeqNum><p1:acceptLan>?</p1:acceptLan><p1:reqStmInd>?</p1:reqStmInd><p1:reqStmDt>?</p1:reqStmDt><p1:reqStmTm>?</p1:reqStmTm><p1:reqSeqNum>?</p1:reqSeqNum><p1:legorgId>?</p1:legorgId><p1:mac>?</p1:mac><p1:bckInd>?</p1:bckInd><p1:bckId>?</p1:bckId></p1:RequestInformation><p0:Request><p0:TxnOrgNum>1</p0:TxnOrgNum><p0:InstChinName>C003</p0:InstChinName><p0:TlrNomber>020201</p0:TlrNomber><p0:StaffName>fasfa</p0:StaffName><p0:PltfmTxnSrc>f</p0:PltfmTxnSrc><p0:NumList><p0:a>a</p0:a><p0:b>b</p0:b></p0:NumList><p0:NumList><p0:c>c</p0:c><p0:d>d</p0:d></p0:NumList></p0:Request></p0:A01></soapenv:Body></soapenv:Envelope>";
		String soap1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?> <soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:p1=\"http://ksrcb.service.com/T001/DEQ00001/Schema\" xmlns:p0=\"http://ksrcb.service.com/B015/Common/Schema\"><soapenv:Header xmlns:wsa=\"http://www.w3.org/2005/08/addressing\"><wsa:To>T001</wsa:To><wsa:From><wsa:Address>C003</wsa:Address> </wsa:From><wsa:Action>http://ksrcb.service.com/T001/DEQ00001/A01</wsa:Action><wsa:MessageID>urn:uuid:67f0ff71-60e9-4146-ad84-dea13985fb6a</wsa:MessageID></soapenv:Header><soapenv:Body><p1:A01><p0:RequestInformation><p0:IttrDt>20160405</p0:IttrDt><p0:IttrStmInd>C003</p0:IttrStmInd><p0:IttrChlInd>010100</p0:IttrChlInd><p0:GlbSeqNum>GT001201604050000000000000000100</p0:GlbSeqNum><p0:AcceptLan>01</p0:AcceptLan><p0:ReqStmInd>T001</p0:ReqStmInd><p0:ReqStmDt>20160405</p0:ReqStmDt><p0:ReqStmTm>18:50:20</p0:ReqStmTm><p0:ReqSeqNum>GT001201604050000000000000000100</p0:ReqSeqNum><p0:LegorgId>9999</p0:LegorgId><p0:Mac/><p0:BckInd/><p0:BckId/></p0:RequestInformation><p1:Request><p1:TxnOrgNum>3052001</p1:TxnOrgNum><p1:InstChinName/><p1:TlrNomber>WYGY</p1:TlrNomber><p1:StaffName/><p1:PltfmTxnSrc>17</p1:PltfmTxnSrc><p1:AcctNum>6231320000000002921</p1:AcctNum><p1:InfoList><p1:Phone>13912345678</p1:Phone><p1:Name>测试1</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654321</p1:Phone><p1:Name>测试2</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654322</p1:Phone><p1:Name>测试3</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654324</p1:Phone><p1:Name>测试4</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654325</p1:Phone><p1:Name>测试5</p1:Name></p1:InfoList></p1:Request></p1:A01></soapenv:Body></soapenv:Envelope>";
		Map<String,Object> result = SoapUtil.parseSoapMessage(soap1.getBytes(), "utf-8");
		System.out.println(result);
		Map<String,Object> body = (Map<String, Object>) result.get("Body");
		Map<String,Object> a01 = (Map<String, Object>) body.get("A01");
		String requestSystemId = "MBS";
		String providerSystemId = "T001";
		String serviceNameEn = "DEQ00001";
		String operationNameEn = "A01";
		
		byte[] soapByte = SoapUtil.buildRequestSoapMessage(requestSystemId,providerSystemId,null,serviceNameEn,operationNameEn,a01,"utf-8");
		System.out.println(new String(soapByte));
	}
	
	
	/**
	 * 值循环／节点循环两种循环并行存在
	 * @throws IOException
	 * @throws XMLStreamException
	 */
	@SuppressWarnings("unchecked")
	public static void parse3() throws IOException, XMLStreamException{
		String soap="<?xml version=\"1.0\" ?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Header xmlns:wsa=\"http://www.w3.org/2005/08/addressing\"><wsa:To>T001</wsa:To><wsa:From><wsa:Address>MBS</wsa:Address></wsa:From><wsa:Action>http://ksrcb.service.com/T001/DEQ00001/A01</wsa:Action><wsa:MessageID>urn:uuid:bb1df038-f089-4b3b-844c-7acfd9c90090</wsa:MessageID></soapenv:Header><soapenv:Body><p0:A01 xmlns:p0=\"http://ksrcb.service.com/T001/DEQ00001/Schema\" xmlns:p1=\"http://ksrcb.service.com/B015/Common/Schema\"><p1:requestInformation><p1:ittrDt>2016-03-24</p1:ittrDt><p1:ittrStmInd>C003</p1:ittrStmInd><p1:ittrChlInd>020201</p1:ittrChlInd><p1:glbSeqNum>?</p1:glbSeqNum><p1:acceptLan>?</p1:acceptLan><p1:reqStmInd>?</p1:reqStmInd><p1:reqStmDt>?</p1:reqStmDt><p1:reqStmTm>?</p1:reqStmTm><p1:reqSeqNum>?</p1:reqSeqNum><p1:legorgId>?</p1:legorgId><p1:mac>?</p1:mac><p1:bckInd>?</p1:bckInd><p1:bckId>?</p1:bckId></p1:requestInformation><p0:request><p0:TxnOrgNum>1</p0:TxnOrgNum><p0:InstChinName>C003</p0:InstChinName><p0:TlrNomber>020201</p0:TlrNomber><p0:StaffName>fasfa</p0:StaffName><p0:PltfmTxnSrc>f</p0:PltfmTxnSrc><p0:AcctNumList><p0:acctNum>fasfa3</p0:acctNum><p0:acctNum>fasfa2</p0:acctNum><p0:acctNum>fasfa1</p0:acctNum><p0:acctNum>fasfa4</p0:acctNum><p1:InfoList><p1:Phone>13912345678</p1:Phone><p1:Name>测试1</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654321</p1:Phone><p1:Name>测试2</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654322</p1:Phone><p1:Name>测试3</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654325</p1:Phone><p1:Name>测试4</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654325</p1:Phone><p1:Name>测试5</p1:Name></p1:InfoList></p0:AcctNumList></p0:request></p0:A01></soapenv:Body></soapenv:Envelope>";
		Map<String,Object> result = SoapUtil.parseSoapMessage(soap.getBytes(), "utf-8");
		System.out.println(result);
		Map<String,Object> body = (Map<String, Object>) result.get("Body");
		Map<String,Object> a01 = (Map<String, Object>) body.get("A01");
		String requestSystemId = "MBS";
		String providerSystemId = "T001";
		String serviceNameEn = "DEQ00001";
		String operationNameEn = "A01";
		
		byte[] soapByte = SoapUtil.buildRequestSoapMessage(requestSystemId,providerSystemId,null,serviceNameEn,operationNameEn,a01,"utf-8");
		System.out.println(new String(soapByte));
	}
	
	/**
	 * 节点循环嵌套值循环
	 * @throws XMLStreamException 
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public static void parse4() throws IOException, XMLStreamException{
		//String soap="<?xml version=\"1.0\" ?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Header xmlns:wsa=\"http://www.w3.org/2005/08/addressing\"><wsa:To>T001</wsa:To><wsa:From><wsa:Address>MBS</wsa:Address></wsa:From><wsa:Action>http://ksrcb.service.com/T001/DEQ00001/A01</wsa:Action><wsa:MessageID>urn:uuid:2ed96377-a16c-4acc-bdbb-31edecabfdd2</wsa:MessageID></soapenv:Header><soapenv:Body><p0:A01 xmlns:p0=\"http://ksrcb.service.com/T001/DEQ00001/Schema\" xmlns:p1=\"http://ksrcb.service.com/B015/Common/Schema\"><p1:requestInformation><p1:ittrDt>2016-03-24</p1:ittrDt><p1:ittrStmInd>C003</p1:ittrStmInd><p1:ittrChlInd>020201</p1:ittrChlInd><p1:glbSeqNum>?</p1:glbSeqNum><p1:acceptLan>?</p1:acceptLan><p1:reqStmInd>?</p1:reqStmInd><p1:reqStmDt>?</p1:reqStmDt><p1:reqStmTm>?</p1:reqStmTm><p1:reqSeqNum>?</p1:reqSeqNum><p1:legorgId>?</p1:legorgId><p1:mac>?</p1:mac><p1:bckInd>?</p1:bckInd><p1:bckId>?</p1:bckId></p1:requestInformation><p0:request><p0:TxnOrgNum>1</p0:TxnOrgNum><p0:InstChinName>C003</p0:InstChinName><p0:TlrNomber>020201</p0:TlrNomber><p0:StaffName>fasfa</p0:StaffName><p0:PltfmTxnSrc>f</p0:PltfmTxnSrc><p0:NumList><p0:a>a</p0:a><p0:b>b</p0:b></p0:NumList><p0:NumList><p0:c>c</p0:c><p0:d>d</p0:d></p0:NumList></p0:request></p0:A01></soapenv:Body></soapenv:Envelope>";
		String soap1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?> <soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:p1=\"http://ksrcb.service.com/T001/DEQ00001/Schema\" xmlns:p0=\"http://ksrcb.service.com/B015/Common/Schema\"><soapenv:Header xmlns:wsa=\"http://www.w3.org/2005/08/addressing\"><wsa:To>T001</wsa:To><wsa:From><wsa:Address>C003</wsa:Address> </wsa:From><wsa:Action>http://ksrcb.service.com/T001/DEQ00001/A01</wsa:Action><wsa:MessageID>urn:uuid:67f0ff71-60e9-4146-ad84-dea13985fb6a</wsa:MessageID></soapenv:Header><soapenv:Body><p1:A01><p0:RequestInformation><p0:IttrDt>20160405</p0:IttrDt><p0:IttrStmInd>C003</p0:IttrStmInd><p0:IttrChlInd>010100</p0:IttrChlInd><p0:GlbSeqNum>GT001201604050000000000000000100</p0:GlbSeqNum><p0:AcceptLan>01</p0:AcceptLan><p0:ReqStmInd>T001</p0:ReqStmInd><p0:ReqStmDt>20160405</p0:ReqStmDt><p0:ReqStmTm>18:50:20</p0:ReqStmTm><p0:ReqSeqNum>GT001201604050000000000000000100</p0:ReqSeqNum><p0:LegorgId>9999</p0:LegorgId><p0:Mac/><p0:BckInd/><p0:BckId/></p0:RequestInformation><p1:Request><p1:TxnOrgNum>3052001</p1:TxnOrgNum><p1:InstChinName/><p1:TlrNomber>WYGY</p1:TlrNomber><p1:StaffName/><p1:PltfmTxnSrc>17</p1:PltfmTxnSrc><p1:AcctNum>6231320000000002921</p1:AcctNum><p1:InfoList><p1:Phone>13912345678</p1:Phone><p1:Name>测试1</p1:Name><p0:acctNum>fasfa3</p0:acctNum><p0:acctNum>fasfa2</p0:acctNum><p0:acctNum>fasfa1</p0:acctNum><p0:acctNum>fasfa4</p0:acctNum></p1:InfoList><p1:InfoList><p1:Phone>13987654321</p1:Phone><p1:Name>测试2</p1:Name><p0:acctNum>fasfa3</p0:acctNum><p0:acctNum>fasfa2</p0:acctNum><p0:acctNum>fasfa3</p0:acctNum><p0:acctNum>fasfa2</p0:acctNum></p1:InfoList><p1:InfoList><p1:Phone>13987654321</p1:Phone><p1:Name>测试3</p1:Name><p0:acctNum>fasfa3</p0:acctNum><p0:acctNum>fasfa2</p0:acctNum><p0:acctNum>fasfa3</p0:acctNum><p0:acctNum>fasfa2</p0:acctNum></p1:InfoList></p1:Request></p1:A01></soapenv:Body></soapenv:Envelope>";
		
		Map<String,Object> result = SoapUtil.parseSoapMessage(soap1.getBytes(), "utf-8");
		System.out.println(result);
		Map<String,Object> body = (Map<String, Object>) result.get("Body");
		Map<String,Object> a01 = (Map<String, Object>) body.get("A01");
		String requestSystemId = "MBS";
		String providerSystemId = "T001";
		String serviceNameEn = "DEQ00001";
		String operationNameEn = "A01";
		
		byte[] soapByte = SoapUtil.buildRequestSoapMessage(requestSystemId,providerSystemId,null,serviceNameEn,operationNameEn,a01,"utf-8");
		System.out.println(new String(soapByte));
	}
	
	
	/**
	 * 值循环嵌套节点循环
	 * @throws IOException
	 * @throws XMLStreamException
	 */
	@SuppressWarnings("unchecked")
	public static void parse5() throws IOException, XMLStreamException{
		String soap="<?xml version=\"1.0\" ?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Header xmlns:wsa=\"http://www.w3.org/2005/08/addressing\"><wsa:To>T001</wsa:To><wsa:From><wsa:Address>MBS</wsa:Address></wsa:From><wsa:Action>http://ksrcb.service.com/T001/DEQ00001/A01</wsa:Action><wsa:MessageID>urn:uuid:bb1df038-f089-4b3b-844c-7acfd9c90090</wsa:MessageID></soapenv:Header><soapenv:Body><p0:A01 xmlns:p0=\"http://ksrcb.service.com/T001/DEQ00001/Schema\" xmlns:p1=\"http://ksrcb.service.com/B015/Common/Schema\"><p1:requestInformation><p1:ittrDt>2016-03-24</p1:ittrDt><p1:ittrStmInd>C003</p1:ittrStmInd><p1:ittrChlInd>020201</p1:ittrChlInd><p1:glbSeqNum>?</p1:glbSeqNum><p1:acceptLan>?</p1:acceptLan><p1:reqStmInd>?</p1:reqStmInd><p1:reqStmDt>?</p1:reqStmDt><p1:reqStmTm>?</p1:reqStmTm><p1:reqSeqNum>?</p1:reqSeqNum><p1:legorgId>?</p1:legorgId><p1:mac>?</p1:mac><p1:bckInd>?</p1:bckInd><p1:bckId>?</p1:bckId></p1:requestInformation><p0:request><p0:TxnOrgNum>1</p0:TxnOrgNum><p0:InstChinName>C003</p0:InstChinName><p0:TlrNomber>020201</p0:TlrNomber><p0:StaffName>fasfa</p0:StaffName><p0:PltfmTxnSrc>f</p0:PltfmTxnSrc><p0:AcctNumList><p0:acctNum><p1:InfoList><p1:Phone>13912345678</p1:Phone><p1:Name>测试1</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654321</p1:Phone><p1:Name>测试2</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654322</p1:Phone><p1:Name>测试3</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654325</p1:Phone><p1:Name>测试4</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654325</p1:Phone><p1:Name>测试5</p1:Name></p1:InfoList></p0:acctNum><p0:acctNum><p1:InfoList><p1:Phone>13912345678</p1:Phone><p1:Name>测试1</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654321</p1:Phone><p1:Name>测试2</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654322</p1:Phone><p1:Name>测试3</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654325</p1:Phone><p1:Name>测试4</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654325</p1:Phone><p1:Name>测试5</p1:Name></p1:InfoList></p0:acctNum><p0:acctNum><p1:InfoList><p1:Phone>13912345678</p1:Phone><p1:Name>测试1</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654321</p1:Phone><p1:Name>测试2</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654322</p1:Phone><p1:Name>测试3</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654325</p1:Phone><p1:Name>测试4</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654325</p1:Phone><p1:Name>测试5</p1:Name></p1:InfoList></p0:acctNum><p0:acctNum><p1:InfoList><p1:Phone>13912345678</p1:Phone><p1:Name>测试1</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654321</p1:Phone><p1:Name>测试2</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654322</p1:Phone><p1:Name>测试3</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654325</p1:Phone><p1:Name>测试4</p1:Name></p1:InfoList><p1:InfoList><p1:Phone>13987654325</p1:Phone><p1:Name>测试5</p1:Name></p1:InfoList></p0:acctNum></p0:AcctNumList></p0:request></p0:A01></soapenv:Body></soapenv:Envelope>";
		Map<String,Object> result = SoapUtil.parseSoapMessage(soap.getBytes(), "utf-8");
		System.out.println(result);
		Map<String,Object> body = (Map<String, Object>) result.get("Body");
		Map<String,Object> a01 = (Map<String, Object>) body.get("A01");
		String requestSystemId = "MBS";
		String providerSystemId = "T001";
		String serviceNameEn = "DEQ00001";
		String operationNameEn = "A01";
		
		byte[] soapByte = SoapUtil.buildRequestSoapMessage(requestSystemId,providerSystemId,null,serviceNameEn,operationNameEn,a01,"utf-8");
		System.out.println(new String(soapByte));
	}
	
}
