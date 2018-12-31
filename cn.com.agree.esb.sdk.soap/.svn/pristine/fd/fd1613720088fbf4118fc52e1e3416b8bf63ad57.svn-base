/*
 * Copyright(C) 2015 Agree Tech, All rights reserved.
 * 
 * Created on 2016年4月29日 上午12:43:55 by zhouzx
 */
package cn.com.agree.esb.sdk.soap;

import java.io.IOException;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

/**
 * <DL>
 * <DT><B> 标题. </B></DT>
 * <p>
 * <DD>详细介绍</DD>
 * </DL>
 * <p>
 * 
 * <DL>
 * <DT><B>使用范例</B></DT>
 * <p>
 * <DD>使用范例说明</DD>
 * </DL>
 * <p>
 * @author zhouzx 
 * @email  zhou.zx@cfischina.com
 * @date 2016年4月29日 上午12:43:55
 * @version 1.0
 * @since 1.0
 *
 */
public class ExampleTest {
	public static void main(String[] args) throws IOException, XMLStreamException {
		example4();
	}
	
	public static void example1() throws IOException, XMLStreamException{
		String soap ="<?xml version=\"1.0\" encoding=\"UTF-8\" ?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:p1=\"http://ksrcb.service.com/T001/DEQ00001/Schema\" xmlns:p0=\"http://ksrcb.service.com/B015/Common/Schema\"><soapenv:Header xmlns:wsa=\"http://www.w3.org/2005/08/addressing\"><wsa:To>T001</wsa:To><wsa:From><wsa:Address>C003</wsa:Address></wsa:From><wsa:Action>http://ksrcb.service.com/T001/DEQ00001/A01</wsa:Action><wsa:MessageID>urn:uuid:67f0ff71-60e9-4146-ad84-dea13985fb6a</wsa:MessageID></soapenv:Header><soapenv:Body><p1:A01><p0:RequestInformation><p0:IttrDt>20160405</p0:IttrDt><p0:IttrStmInd>C003</p0:IttrStmInd><p0:IttrChlInd>010100</p0:IttrChlInd><p0:GlbSeqNum>GT001201604050000000000000000100</p0:GlbSeqNum><p0:AcceptLan>01</p0:AcceptLan><p0:ReqStmInd>T001</p0:ReqStmInd><p0:ReqStmDt>20160405</p0:ReqStmDt><p0:ReqStmTm>18:50:20</p0:ReqStmTm><p0:ReqSeqNum>GT001201604050000000000000000100</p0:ReqSeqNum><p0:LegorgId>9999</p0:LegorgId><p0:Mac/><p0:BckInd/><p0:BckId /></p0:RequestInformation><p1:Request><p1:TxnOrgNum>3052001</p1:TxnOrgNum><p1:InstChinName/><p1:TlrNomber>WYGY</p1:TlrNomber><p1:StaffName/><p1:PltfmTxnSrc>17</p1:PltfmTxnSrc><p1:AcctNum>6231320000000002921</p1:AcctNum></p1:Request></p1:A01></soapenv:Body></soapenv:Envelope>";
		Map<String,Object> byteMsg = SoapUtil.parseSoapMessage(soap.getBytes(), "utf-8");
		System.out.println(byteMsg);
	}
	
	public static void example2() throws IOException, XMLStreamException{
		String soap ="<?xml version=\"1.0\" encoding=\"UTF-8\" ?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:p1=\"http://ksrcb.service.com/T001/DEQ00001/Schema\" xmlns:p0=\"http://ksrcb.service.com/B015/Common/Schema\"><soapenv:Header xmlns:wsa=\"http://www.w3.org/2005/08/addressing\"><wsa:To>T001</wsa:To><wsa:From><wsa:Address>C003</wsa:Address></wsa:From><wsa:Action>http://ksrcb.service.com/T001/DEQ00001/A01</wsa:Action><wsa:MessageID>urn:uuid:67f0ff71-60e9-4146-ad84-dea13985fb6a</wsa:MessageID></soapenv:Header><soapenv:Body><p1:A01><p0:RequestInformation><p0:IttrDt>20160405</p0:IttrDt><p0:IttrStmInd>C003</p0:IttrStmInd><p0:IttrChlInd>010100</p0:IttrChlInd><p0:GlbSeqNum>GT001201604050000000000000000100</p0:GlbSeqNum><p0:AcceptLan>01</p0:AcceptLan><p0:ReqStmInd>T001</p0:ReqStmInd><p0:ReqStmDt>20160405</p0:ReqStmDt><p0:ReqStmTm>18:50:20</p0:ReqStmTm><p0:ReqSeqNum>GT001201604050000000000000000100</p0:ReqSeqNum><p0:LegorgId>9999</p0:LegorgId><p0:Mac/><p0:BckInd/><p0:BckId/></p0:RequestInformation><p1:Request><p1:TxnOrgNum>3052001</p1:TxnOrgNum><p1:InstChinName/><p1:TlrNomber>WYGY</p1:TlrNomber><p1:StaffName/><p1:PltfmTxnSrc>17</p1:PltfmTxnSrc><p1:AcctNum>6231320000000002921</p1:AcctNum><p1:InfoList><p1:Phon>13912345678</p1:Phon><p1:Name>测试</p1:Name></p1:InfoList><p1:InfoList><p1:Phon>1391234563</p1:Phon><p1:Name>测试1</p1:Name></p1:InfoList></p1:Request></p1:A01></soapenv:Body></soapenv:Envelope>";
		Map<String,Object> byteMsg = SoapUtil.parseSoapMessage(soap.getBytes(), "utf-8");
		System.out.println(byteMsg);
	}
	
	public static void example3() throws IOException, XMLStreamException{
		String soap ="<?xml version=\"1.0\" encoding=\"UTF-8\" ?> <soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:p1=\"http://ksrcb.service.com/T001/DEQ00001/Schema\" xmlns:p0=\"http://ksrcb.service.com/B015/Common/Schema\"><soapenv:Header xmlns:wsa=\"http://www.w3.org/2005/08/addressing\"><wsa:To>T001</wsa:To><wsa:From><wsa:Address>C003</wsa:Address> </wsa:From><wsa:Action>http://ksrcb.service.com/T001/DEQ00001/A01</wsa:Action> <wsa:MessageID>urn:uuid:67f0ff71-60e9-4146-ad84-dea13985fb6a</wsa:MessageID> </soapenv:Header><soapenv:Body><p1:A01><p0:RequestInformation><p0:IttrDt>20160405</p0:IttrDt><p0:IttrStmInd>C003</p0:IttrStmInd><p0:IttrChlInd>010100</p0:IttrChlInd><p0:GlbSeqNum>GT001201604050000000000000000100</p0:GlbSeqNum> <p0:AcceptLan>01</p0:AcceptLan><p0:ReqStmInd>T001</p0:ReqStmInd><p0:ReqStmDt>20160405</p0:ReqStmDt><p0:ReqStmTm>18:50:20</p0:ReqStmTm> <p0:ReqSeqNum>GT001201604050000000000000000100</p0:ReqSeqNum> <p0:LegorgId>9999</p0:LegorgId><p0:Mac /><p0:BckInd /><p0:BckId /> </p0:RequestInformation><p1:Request><p1:TxnOrgNum>3052001</p1:TxnOrgNum><p1:InstChinName/><p1:TlrNomber>WYGY</p1:TlrNomber><p1:StaffName/><p1:PltfmTxnSrc>17</p1:PltfmTxnSrc><p1:AcctNum>6231320000000002921</p1:AcctNum><p1:AddrList><p1:Addr>地址1</p1:Addr><p1:Addr>地址2</p1:Addr></p1:AddrList></p1:Request></p1:A01></soapenv:Body></soapenv:Envelope>";
		Map<String,Object> byteMsg = SoapUtil.parseSoapMessage(soap.getBytes(), "utf-8");
		System.out.println(byteMsg);
	}
	
	public static void example4() throws IOException, XMLStreamException{
		String soap ="<?xml version=\"1.0\" encoding=\"UTF-8\" ?> <soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:p1=\"http://ksrcb.service.com/T001/DEQ00001/Schema\" xmlns:p0=\"http://ksrcb.service.com/B015/Common/Schema\"><soapenv:Header xmlns:wsa=\"http://www.w3.org/2005/08/addressing\"><wsa:To>T001</wsa:To><wsa:From><wsa:Address>C003</wsa:Address> </wsa:From><wsa:Action>http://ksrcb.service.com/T001/DEQ00001/A01</wsa:Action><wsa:MessageID>urn:uuid:67f0ff71-60e9-4146-ad84-dea13985fb6a</wsa:MessageID></soapenv:Header><soapenv:Body><p1:A01><p0:RequestInformation><p0:IttrDt>20160405</p0:IttrDt><p0:IttrStmInd>C003</p0:IttrStmInd><p0:IttrChlInd>010100</p0:IttrChlInd><p0:GlbSeqNum>GT001201604050000000000000000100</p0:GlbSeqNum><p0:AcceptLan>01</p0:AcceptLan><p0:ReqStmInd>T001</p0:ReqStmInd><p0:ReqStmDt>20160405</p0:ReqStmDt><p0:ReqStmTm>18:50:20</p0:ReqStmTm><p0:ReqSeqNum>GT001201604050000000000000000100</p0:ReqSeqNum><p0:LegorgId>9999</p0:LegorgId><p0:Mac/><p0:BckInd/><p0:BckId/></p0:RequestInformation><p1:Request><p1:TxnOrgNum>3052001</p1:TxnOrgNum><p1:InstChinName/><p1:TlrNomber>WYGY</p1:TlrNomber><p1:StaffName/><p1:PltfmTxnSrc>17</p1:PltfmTxnSrc><p1:AcctNum>6231320000000002921</p1:AcctNum><p1:InfoList><p1:Phon>13912345678</p1:Phon><p1:Name>测试1</p1:Name></p1:InfoList><p1:InfoList><p1:Phon>13987654321</p1:Phon><p1:Name>测试2</p1:Name></p1:InfoList></p1:Request></p1:A01></soapenv:Body></soapenv:Envelope>";
		Map<String,Object> byteMsg = SoapUtil.parseSoapMessage(soap.getBytes(), "utf-8");
		System.out.println(byteMsg);
	}
}
