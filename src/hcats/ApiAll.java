package hcats;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/*
 * API function
 */

//1.1 hisGetBasicData
class hisGetBasicData extends ApiBase {

	public hisGetBasicData(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		errorcode = CsHislibrary.Istance.hisGetBasicData(buf.get(0),length.get(0));
	}
	
}
//1.2 hisGetRegisterBasic
class hisGetRegisterBasic extends ApiBase {

	public hisGetRegisterBasic(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		errorcode = CsHislibrary.Istance.hisGetRegisterBasic(buf.get(0),length.get(0));
	}
	
}
//1.3 hisGetRegisterPrevent
class hisGetRegisterPrevent extends ApiBase {

	public hisGetRegisterPrevent(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		errorcode = CsHislibrary.Istance.hisGetRegisterPrevent(buf.get(0),length.get(0));
	}
	
}
//1.4 hisGetRegisterPregnant
class hisGetRegisterPregnant extends ApiBase {

	public hisGetRegisterPregnant(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		errorcode = CsHislibrary.Istance.hisGetRegisterPregnant(buf.get(0),length.get(0));
	}
	
}
//1.5 hisGetTreatmentNoNeedHPC
class hisGetTreatmentNoNeedHPC extends ApiBase {

	public hisGetTreatmentNoNeedHPC(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		errorcode = CsHislibrary.Istance.hisGetTreatmentNoNeedHPC(buf.get(0),length.get(0));
	}
	
}
//1.6 hisGetCumulativeData
class hisGetCumulativeData extends ApiBase {

	public hisGetCumulativeData(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		errorcode = CsHislibrary.Istance.hisGetCumulativeData(buf.get(0),length.get(0));
	}
	
}
//1.7 hisGetCumulativeFee
class hisGetCumulativeFee extends ApiBase {

	public hisGetCumulativeFee(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		errorcode = CsHislibrary.Istance.hisGetCumulativeFee(buf.get(0),length.get(0));
	}
	
}
//1.8 hisGetTreatmentNeedHPC
class hisGetTreatmentNeedHPC extends ApiBase {

	public hisGetTreatmentNeedHPC(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		ICD_10_CM_format = true;
		errorcode = CsHislibrary.Istance.hisGetTreatmentNeedHPC(buf.get(0),length.get(0));
	}
	
}

//1.9 hisGetSeqNumber
class hisGetSeqNumber extends ApiBase {
	public String cTreatItem;

	public hisGetSeqNumber(String API) {
		super(API);
	}

	@Override
	public void run() {
		byte[] c;
		c = "01".getBytes();
		if (getcTreatItem() != null)
			c = getcTreatItem().getBytes();
		byte[] cBabyTreat = " ".getBytes();
		errorcode = CsHislibrary.Istance.hisGetSeqNumber(c, cBabyTreat, buf.get(0), length.get(0));
	}

	public void setcTreatItem(String s) {
		cTreatItem = s;
	}

	public String getcTreatItem() {
		return cTreatItem;
	}
}
//1.10 hisReadPrescription
class hisReadPrescription extends ApiBase {

	public hisReadPrescription(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		errorcode = CsHislibrary.Istance.hisReadPrescription(buf.get(0),length.get(0),buf.get(1),length.get(1),buf.get(2),length.get(2),buf.get(3),length.get(3));
	}
	
}
//1.11 hisGetInoculateData
class hisGetInoculateData extends ApiBase {

	public hisGetInoculateData(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		errorcode = CsHislibrary.Istance.hisGetInoculateData(buf.get(0),length.get(0));
	}
	
}
//1.12 hisGetOrganDonate
class hisGetOrganDonate extends ApiBase {

	public hisGetOrganDonate(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		errorcode = CsHislibrary.Istance.hisGetOrganDonate(buf.get(0),length.get(0));
	}
	
}
//1.13 hisGetEmergentTel
class hisGetEmergentTel extends ApiBase {

	public hisGetEmergentTel(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		errorcode = CsHislibrary.Istance.hisGetEmergentTel(buf.get(0),length.get(0));
	}
	
}
//1.14 hisGetLastSeqNum
class hisGetLastSeqNum extends ApiBase {

	public hisGetLastSeqNum(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		errorcode = CsHislibrary.Istance.hisGetLastSeqNum(buf.get(0),length.get(0));
	}
	
}
//1.15 hisGetCardStatus
class hisGetCardStatus extends ApiBase {

	Map<Integer,String> table_1;
	Map<Integer,String> table_2;
	Map<Integer,String> table_3;
		
	public hisGetCardStatus(String API)
	{
		super(API);
	}
	@Override
	public void setBuffer()
	{
		DataBase.getDatabase().clear_value(API);
		DataBase.getDatabase().data_list_value = DataBase.getDatabase().data_value.get(API);
	}
	@Override
	public void run() {
		setcardtable();
		for(int i=1;i<=3;i++)
		{
			errorcode = CsHislibrary.Istance.hisGetCardStatus(i);
			String errorText =  status_return(i);
			DataBase.getDatabase().data_list_value.add(errorText);
		}
		errorcode = 0;
	}
	
	public void setcardtable()
	{
		table_1 = new HashMap<Integer,String>();
		table_2 = new HashMap<Integer,String>();
		table_3 = new HashMap<Integer,String>();
		//----------------------------------
		table_1.put(4000,"讀卡機Timeout");
		table_1.put(0,"卡片未置入");
		table_1.put(1,"安全模組尚未與健保局IDC認證");
		table_1.put(2,"安全模組與健保局IDC認證成功");
		table_1.put(9,"所置入非安全模組卡");
		//----------------------------------
		table_2.put(4000,"讀卡機Timeout");
		table_2.put(0,"卡片未置入");
		table_2.put(1,"健保IC卡尚未與安全模組認證");
		table_2.put(2,"健保IC卡與安全模組認證成功");
		table_2.put(3,"健保IC卡與醫事人員卡認證成功");
		table_2.put(4,"健保IC卡PIN認證成功");
		table_2.put(5,"健保IC卡與健保局IDC認證成功");
		table_2.put(9,"所置入非健保IC卡");
		//----------------------------------
		table_3.put(4000,"讀卡機Timeout");
		table_3.put(0,"卡片未置入");
		table_3.put(1,"醫事人員卡尚未與安全模組認證");
		table_3.put(2,"醫事人員卡與安全模組認證成功(PIN尚未認證)");
		table_3.put(3,"醫事人員卡PIN認證成功");
		table_3.put(4,"所置入非醫事人員卡");
	}
	
	public String status_return(int cardtype)
	{
		String text = "";
		switch(cardtype)
		{
			case 1:
				text = table_1.get(errorcode);
				break;
			case 2:
				text = table_2.get(errorcode);
				break;
			case 3:
				text = table_3.get(errorcode);
				break;
			default:
				break;
		}
		return text;
	}
	@Override
	public void dataStore()
	{
		DataBase.getDatabase().data_value.put(API, DataBase.getDatabase().data_list_value);
	}
}

//1.16 hisWriteTreatmentCode
class hisWriteTreatmentCode extends ApiBase {

	public hisWriteTreatmentCode(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		byte[] pDateTime = new byte[14];
		byte[] pPatientID = new byte[11];
		byte[] pPatientBirthDate = new byte[8];
		byte[] pDataWrite = new byte[94];
		byte[] pBufferDocID = new byte[11];

		// get ID and BIrthDate
		new hisGetBasicData("hisGetBasicData");
		if (DataBase.getDatabase().list_errorcode.get("hisGetBasicData") != 0) {
			errorcode = DataBase.getDatabase().list_errorcode.get("hisGetBasicData");
		} else if (!DataBase.getDatabase().data_value.get("hisGetBasicData").isEmpty()) {
			errorcode = DataBase.getDatabase().list_errorcode.get("hisGetBasicData");
			int index = DataBase.getDatabase().data_symbol.get("hisGetBasicData").indexOf("身分證號");
			String tmp_text = DataBase.getDatabase().data_value.get("hisGetBasicData").get(index);
			pPatientID = tmp_text.getBytes();

			index = DataBase.getDatabase().data_symbol.get("hisGetBasicData").indexOf("出生日期");
			tmp_text = DataBase.getDatabase().data_value.get("hisGetBasicData").get(index);
			pPatientBirthDate = tmp_text.getBytes();
		}
		// get pDateTime
		String temp1 = "0";
		String temp2 = "0";
		if (!DataBase.getDatabase().data_value.get("hisGetSeqNumber").isEmpty()) {
			int index = DataBase.getDatabase().data_symbol.get("hisGetSeqNumber").indexOf("就診日期時間");
			if (index == -1) {
				index = 0;
			}
			temp1 = DataBase.getDatabase().data_value.get("hisGetSeqNumber").get(index);
		}
		if (!DataBase.getDatabase().data_value.get("hisGetSeqNumber256").isEmpty()) {
			int index = DataBase.getDatabase().data_symbol.get("hisGetSeqNumber256").indexOf("就診日期時間");
			if (index == -1) {
				index = 0;
			}
			temp2 = DataBase.getDatabase().data_value.get("hisGetSeqNumber256").get(index);
		}
		if (temp1.equals("0") && temp2.equals("0")) {
			hisGetSeqNumber n = new hisGetSeqNumber("hisGetSeqNumber");
			if (n.errorcode == 0) {
			} else if (n.errorcode == 5001) {
				new csUpdateHCContents("csUpdateHCContents");
				n = new hisGetSeqNumber("hisGetSeqNumber");
			}
			if (n.errorcode == 0) {
				int index = DataBase.getDatabase().data_symbol.get("hisGetSeqNumber").indexOf("就診日期時間");
				if (index == -1) {
					index = 0;
				}
				temp1 = DataBase.getDatabase().data_value.get("hisGetSeqNumber").get(index);
			}
		}

		if (Double.parseDouble(temp1) > Double.parseDouble(temp2)) {
			pDateTime = temp1.getBytes();

		} else {
			pDateTime = temp2.getBytes();
		}
		// get pDataWrite
		      pDataWrite = new byte[20];
              String tmp_text = "6";
              byte[] tmp = tmp_text.getBytes(Charset.forName("Big5"));
              System.arraycopy(tmp, 0, pDataWrite, 0, tmp.length);
		//get pBufferDocID
		 pBufferDocID = new byte[11];		
		errorcode = CsHislibrary.Istance.hisWriteTreatmentCode(pDateTime, pPatientID, pPatientBirthDate, pDataWrite,pBufferDocID);
		System.out.print(errorcode);
	}
	
@Override
public void dataStore()
{

}
}

//1.17 hisWriteTreatmentFee
class hisWriteTreatmentFee extends ApiBase {

public hisWriteTreatmentFee(String API)
{
super(API);
}
@Override
public void run() {
	byte[] pDateTime = new byte[14];
	byte[] pPatientID = new byte[11];
	byte[] pPatientBirthDate = new byte[8];
	byte[] pDataWrite = new byte[94];


	// get ID and BIrthDate
	new hisGetBasicData("hisGetBasicData");
	if (DataBase.getDatabase().list_errorcode.get("hisGetBasicData") != 0) {
		errorcode = DataBase.getDatabase().list_errorcode.get("hisGetBasicData");
	} else if (!DataBase.getDatabase().data_value.get("hisGetBasicData").isEmpty()) {
		errorcode = DataBase.getDatabase().list_errorcode.get("hisGetBasicData");
		int index = DataBase.getDatabase().data_symbol.get("hisGetBasicData").indexOf("身分證號");
		String tmp_text = DataBase.getDatabase().data_value.get("hisGetBasicData").get(index);
		pPatientID = tmp_text.getBytes();

		index = DataBase.getDatabase().data_symbol.get("hisGetBasicData").indexOf("出生日期");
		tmp_text = DataBase.getDatabase().data_value.get("hisGetBasicData").get(index);
		pPatientBirthDate = tmp_text.getBytes();
	}
	// get pDateTime
	String temp1 = "0";
	String temp2 = "0";
	if (!DataBase.getDatabase().data_value.get("hisGetSeqNumber").isEmpty()) {
		int index = DataBase.getDatabase().data_symbol.get("hisGetSeqNumber").indexOf("就診日期時間");
		if (index == -1) {
			index = 0;
		}
		temp1 = DataBase.getDatabase().data_value.get("hisGetSeqNumber").get(index);
	}
	if (!DataBase.getDatabase().data_value.get("hisGetSeqNumber256").isEmpty()) {
		int index = DataBase.getDatabase().data_symbol.get("hisGetSeqNumber256").indexOf("就診日期時間");
		if (index == -1) {
			index = 0;
		}
		temp2 = DataBase.getDatabase().data_value.get("hisGetSeqNumber256").get(index);
	}
	if (temp1.equals("0") && temp2.equals("0")) {
		hisGetSeqNumber n = new hisGetSeqNumber("hisGetSeqNumber");
		if (n.errorcode == 0) {
		} else if (n.errorcode == 5001) {
			new csUpdateHCContents("csUpdateHCContents");
			n = new hisGetSeqNumber("hisGetSeqNumber");
		}
		if (n.errorcode == 0) {
			int index = DataBase.getDatabase().data_symbol.get("hisGetSeqNumber").indexOf("就診日期時間");
			if (index == -1) {
				index = 0;
			}
			temp1 = DataBase.getDatabase().data_value.get("hisGetSeqNumber").get(index);
		}
	}

	if (Double.parseDouble(temp1) > Double.parseDouble(temp2)) {
		pDateTime = temp1.getBytes();

	} else {
		pDateTime = temp2.getBytes();
	}
	// get pDataWrite
	      pDataWrite = new byte[20];
          String tmp_text = "69";
          byte[] tmp = tmp_text.getBytes(Charset.forName("Big5"));
          System.arraycopy(tmp, 0, pDataWrite, 0, tmp.length);
	
	errorcode = CsHislibrary.Istance.hisWriteTreatmentFee(pDateTime, pPatientID, pPatientBirthDate, pDataWrite);
	System.out.print(errorcode);
}

@Override
public void dataStore()
{

}
}

//1.20	 hisWriteAllergicMedicines
class hisWriteAllergicMedicines extends ApiBase {

public hisWriteAllergicMedicines(String API)
{
super(API);
}
@Override
public void run() {
      
	byte[] pPatientID = new byte[11];
    byte[] pPatientBirthDate = new byte[8];
    new hisGetBasicData("hisGetBasicData");
    if(DataBase.getDatabase().list_errorcode.get("hisGetBasicData") != 0)
    {
     errorcode = DataBase.getDatabase().list_errorcode.get("hisGetBasicData");
    }
    else
    {
     new hisGetSeqNumber("hisGetSeqNumber");
     if(DataBase.getDatabase().list_errorcode.get("hisGetSeqNumber") != 0)
        {
         errorcode = DataBase.getDatabase().list_errorcode.get("hisGetSeqNumber");
        }
     else
     {            
            int index = DataBase.getDatabase().data_symbol.get("hisGetBasicData").indexOf("身分證號");
            String tmp_text = DataBase.getDatabase().data_value.get("hisGetBasicData").get(index);
            byte[] tmp = tmp_text.getBytes(Charset.forName("Big5"));
            System.arraycopy(tmp, 0, pPatientID, 0, tmp.length);
            
            index = DataBase.getDatabase().data_symbol.get("hisGetBasicData").indexOf("出生日期");
            tmp_text = DataBase.getDatabase().data_value.get("hisGetBasicData").get(index);
            tmp = tmp_text.getBytes(Charset.forName("Big5"));
            System.arraycopy(tmp, 0, pPatientBirthDate, 0, tmp.length);
            
            byte[] pDataWrite = new byte[20];
            tmp_text = "6";
            tmp = tmp_text.getBytes(Charset.forName("Big5"));
            System.arraycopy(tmp, 0, pDataWrite, 0, tmp.length);
            byte[] pBufferDocID = new byte[11];
            errorcode = CsHislibrary.Istance.hisWriteAllergicMedicines(pPatientID,pPatientBirthDate,pDataWrite,pBufferDocID);
            System.out.print(errorcode);
     }
    }
}

@Override
public void dataStore()
{

}
}

//1.22 hisWriteHealthInsurance
class hisWriteHealthInsurance extends ApiBase {

	public hisWriteHealthInsurance(String API) {
		super(API);
	}

	@Override
	public void run() {
		byte[] pPatientID = new byte[11];
		byte[] pPatientBirthDate = new byte[8];
		byte[] pServiceItem = new byte[3];
		byte[] pServiceItemCode = new byte[3];

		hisGetSeqNumber n = new hisGetSeqNumber("hisGetSeqNumber");
		new csUnGetSeqNumber("csUnGetSeqNumber");
		n.setcTreatItem("AC");
		n.run();
		new hisGetBasicData("hisGetBasicData");
		if (DataBase.getDatabase().list_errorcode.get("hisGetBasicData") != 0) {
			errorcode = DataBase.getDatabase().list_errorcode.get("hisGetBasicData");
		} else {
			new hisGetRegisterPrevent("hisGetRegisterPrevent");
			if (DataBase.getDatabase().list_errorcode.get("hisGetRegisterPrevent") != 0) {
				errorcode = DataBase.getDatabase().list_errorcode.get("hisGetRegisterPrevent");
			} else {
				int index;
				String tmp_text = "01";
				byte[] tmp = tmp_text.getBytes(Charset.forName("Big5"));
				System.arraycopy(tmp, 0, pServiceItem, 0, tmp.length);

				tmp_text = "11";
				tmp = tmp_text.getBytes(Charset.forName("Big5"));
				System.arraycopy(tmp, 0, pServiceItemCode, 0, tmp.length);

				index = DataBase.getDatabase().data_symbol.get("hisGetBasicData").indexOf("身分證號");
				tmp_text = DataBase.getDatabase().data_value.get("hisGetBasicData").get(index);
				tmp = tmp_text.getBytes(Charset.forName("Big5"));
				System.arraycopy(tmp, 0, pPatientID, 0, tmp.length);

				index = DataBase.getDatabase().data_symbol.get("hisGetBasicData").indexOf("出生日期");
				tmp_text = DataBase.getDatabase().data_value.get("hisGetBasicData").get(index);
				tmp = tmp_text.getBytes(Charset.forName("Big5"));
				System.arraycopy(tmp, 0, pPatientBirthDate, 0, tmp.length);

				errorcode = CsHislibrary.Istance.hisWriteHealthInsurance(pPatientID, pPatientBirthDate, pServiceItem,
						pServiceItemCode);
				System.out.print(errorcode);
			}
		}
	}

	@Override
	public void dataStore() {

	}
}

//1.27 csVerifyHCPIN
class csVerifyHCPIN extends ApiBase {

	public csVerifyHCPIN(String API) {
		super(API);
	}

	@Override
	public void run() {
		errorcode = CsHislibrary.Istance.csVerifyHCPIN();
	}

	@Override
	public void dataStore() {
		if (errorcode == 0) {
			DataBase.getDatabase().clear_value(API);
			DataBase.getDatabase().data_list_value.add("使用者所輸入之健保IC卡pin值，與健保IC卡之pin值吻合");
			DataBase.getDatabase().data_value.put(API, DataBase.getDatabase().data_list_value);
		}
	}
}

//1.28 csInputHCPIN
class csInputHCPIN extends ApiBase {

	public csInputHCPIN(String API) {
		super(API);
	}

	@Override
	public void run() {
		errorcode = CsHislibrary.Istance.csInputHCPIN();
	}

	@Override
	public void dataStore() {
		if (errorcode == 0) {
			DataBase.getDatabase().clear_value(API);
			DataBase.getDatabase().data_list_value.add("使用者輸入兩次新PIN值，兩次PIN值吻合");
			DataBase.getDatabase().data_value.put(API, DataBase.getDatabase().data_list_value);
		}
	}
}

//1.29 csDisableHCPIN
class csDisableHCPIN extends ApiBase {

	public csDisableHCPIN(String API) {
		super(API);
	}

	@Override
	public void run() {
		errorcode = CsHislibrary.Istance.csDisableHCPIN();
	}

	@Override
	public void dataStore() {
		if (errorcode == 0) {
			DataBase.getDatabase().clear_value(API);
			DataBase.getDatabase().data_list_value.add("HC卡之Pin碼輸入功能已被設為不啟用。");
			DataBase.getDatabase().data_value.put(API, DataBase.getDatabase().data_list_value);
		}
	}
}
//1.30 csUpdateHCContents 
class csUpdateHCContents extends ApiBase {
	
	public csUpdateHCContents(String API) 
	{
		super(API);
	}
	@Override
	public void run() 
	{
		errorcode = CsHislibrary.Istance.csUpdateHCContents();
	}
}
//1.33 hisGetCriticalIllness
class hisGetCriticalIllness extends ApiBase {

	public hisGetCriticalIllness(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		ICD_10_CM_format = true;
		errorcode = CsHislibrary.Istance.hisGetCriticalIllness(buf.get(0),length.get(0));
	}
	
}
//1.34 csGetDateTime
class csGetDateTime extends ApiBase {

	public csGetDateTime(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		errorcode = CsHislibrary.Istance.csGetDateTime(buf.get(0),length.get(0));
	}
	
}
//1.35 csGetCardNo
class csGetCardNo extends ApiBase {

	public csGetCardNo(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		DataBase.getDatabase().clear_value(API);
		for(int i=1;i<=3;i++)
		{
			errorcode = CsHislibrary.Istance.csGetCardNo(i,buf.get(0),length.get(0));
			if(errorcode == 0)
			{
				String cardNum = new String(buf.get(0),0,DataBase.getDatabase().data_byte.get(API).get(i-1));
				DataBase.getDatabase().data_value.get(API).add(cardNum);
			}
			else
			{ 
				String error_message = DataBase.getDatabase().errorcode_table.get(errorcode);
				DataBase.getDatabase().data_value.get(API).add(error_message);
			}
		}
		errorcode = 0;
	}
	@Override
	public void dataStore()
	{
		
	}
}

//1.36 csISSetPIN 
class csISSetPIN extends ApiBase {

	public csISSetPIN(String API) {
		super(API);
	}

	@Override
	public void run() {
		errorcode = CsHislibrary.Istance.csISSetPIN();
	}

	@Override
	public void dataStore() {
		DataBase.getDatabase().clear_value(API);
		if (errorcode == 0) {
			DataBase.getDatabase().data_list_value.add("卡片未設定密碼。");
		} else if (errorcode == 1) {
			DataBase.getDatabase().data_list_value.add("卡片有設定密碼。");
		}
		DataBase.getDatabase().data_value.put(API, DataBase.getDatabase().data_list_value);
	}

}
//1.37 hisGetSeqNumber256
class hisGetSeqNumber256 extends ApiBase {
	
	public hisGetSeqNumber256(String API) 
	{
		super(API);
	}
	@Override
	public void run() 
	{
		byte[] cTreatItem = "01".getBytes();
		byte[] cBabyTreat = " ".getBytes();
		byte[] cTreatAfterCheck = "1".getBytes();
		errorcode = CsHislibrary.Istance.hisGetSeqNumber256(cTreatItem, cBabyTreat, cTreatAfterCheck, buf.get(0), length.get(0));
	}
}
//1.38 hisGetRegisterBasic2
class hisGetRegisterBasic2 extends ApiBase {

	public hisGetRegisterBasic2(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		errorcode = CsHislibrary.Istance.hisGetRegisterBasic2(buf.get(0),length.get(0));
	}
	
}
//1.39 csUnGetSeqNumber
class csUnGetSeqNumber extends ApiBase {

	public csUnGetSeqNumber(String API) 
	{
		super(API);
	}
	@Override
	public void run() 
	{
		String temp1 = "0";
		String temp2 = "0";
		byte[] UnTreatDate = "".getBytes();
		if (!DataBase.getDatabase().data_value.get("hisGetSeqNumber").isEmpty()) 
		{
			int index = DataBase.getDatabase().data_symbol.get("hisGetSeqNumber").indexOf("就診日期時間");
			if (index == -1) 
			{
				index = 0;
			}
			temp1 = DataBase.getDatabase().data_value.get("hisGetSeqNumber").get(index);
		}
		if (!DataBase.getDatabase().data_value.get("hisGetSeqNumber256").isEmpty()) 
		{
			int index = DataBase.getDatabase().data_symbol.get("hisGetSeqNumber256").indexOf("就診日期時間");
			if (index == -1) 
			{
				index = 0;
			}
			temp2 = DataBase.getDatabase().data_value.get("hisGetSeqNumber256").get(index);
		}
		if (Double.parseDouble(temp1) > Double.parseDouble(temp2)) 
		{
			UnTreatDate = temp1.getBytes();
		} 
		else 
		{
			UnTreatDate = temp2.getBytes();
		}
		errorcode = CsHislibrary.Istance.csUnGetSeqNumber(UnTreatDate);
	}
}
//1.41 hisReadPrescriptMain
class hisReadPrescriptMain extends ApiBase {

	public hisReadPrescriptMain(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		int[] tmp_length = {1};
		errorcode = CsHislibrary.Istance.hisReadPrescriptMain(buf.get(0),length.get(0),1,60,tmp_length);
	}
	
}
//1.43 hisReadPrescriptHVE
class hisReadPrescriptHVE extends ApiBase {

	public hisReadPrescriptHVE(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		int[] tmp_length = {1};
		errorcode = CsHislibrary.Istance.hisReadPrescriptHVE(buf.get(0),length.get(0),1,10,tmp_length);
	}
	
}
//1.44 hisReadPrescriptAllergic
class hisReadPrescriptAllergic extends ApiBase {

	public hisReadPrescriptAllergic(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		int[] tmp_length = {1};
		errorcode = CsHislibrary.Istance.hisReadPrescriptAllergic(buf.get(0),length.get(0),1,3,tmp_length);
	}
}
//1.46 hisWriteAllergicNum
class hisWriteAllergicNum extends ApiBase {

	public hisWriteAllergicNum(String API)
	{
		super(API);
	}
	@Override
	public void run() {
        
	  byte[] pPatientID = new byte[11];
      byte[] pPatientBirthDate = new byte[8];
      new hisGetBasicData("hisGetBasicData");
      if(DataBase.getDatabase().list_errorcode.get("hisGetBasicData") != 0)
      {
       errorcode = DataBase.getDatabase().list_errorcode.get("hisGetBasicData");
      }
      else
      {
       new hisGetSeqNumber("hisGetSeqNumber");
       if(DataBase.getDatabase().list_errorcode.get("hisGetSeqNumber") != 0)
          {
           errorcode = DataBase.getDatabase().list_errorcode.get("hisGetSeqNumber");
          }
       else
       {
       
              
              int index = DataBase.getDatabase().data_symbol.get("hisGetBasicData").indexOf("身分證號");
              String tmp_text = DataBase.getDatabase().data_value.get("hisGetBasicData").get(index);
              byte[] tmp = tmp_text.getBytes(Charset.forName("Big5"));
              System.arraycopy(tmp, 0, pPatientID, 0, tmp.length);
              
              index = DataBase.getDatabase().data_symbol.get("hisGetBasicData").indexOf("出生日期");
              tmp_text = DataBase.getDatabase().data_value.get("hisGetBasicData").get(index);
              tmp = tmp_text.getBytes(Charset.forName("Big5"));
              System.arraycopy(tmp, 0, pPatientBirthDate, 0, tmp.length);
              
              byte[] pDataWrite = new byte[20];
              tmp_text = "6";
              tmp = tmp_text.getBytes(Charset.forName("Big5"));
              System.arraycopy(tmp, 0, pDataWrite, 0, tmp.length);
              byte[] pBufferDocID = new byte[11];
              int iNum=1;
              errorcode = CsHislibrary.Istance.hisWriteAllergicNum(pPatientID,pPatientBirthDate,pDataWrite,pBufferDocID,iNum);
              System.out.print(errorcode);
       }
      }
}

@Override
public void dataStore()
{

}
}

//1.47 hisWriteTreatmentData
class hisWriteTreatmentData extends ApiBase {

	public hisWriteTreatmentData(String API) {
		super(API);
	}

	@Override
	public void run() {
		byte[] pDateTime = new byte[14];
		byte[] pPatientID = new byte[11];
		byte[] pPatientBirthDate = new byte[8];
		byte[] pDataWrite = new byte[94];
		byte[] pBufferDocID = new byte[11];

		byte[] Record = new byte[1];
		byte[] MainCode = new byte[7];
		byte[] Code1 = new byte[7];
		byte[] Code2 = new byte[7];
		byte[] Code3 = new byte[7];
		byte[] Code4 = new byte[7];
		byte[] Code5 = new byte[7];
		byte[] Expension = new byte[8];
		byte[] PartExpension = new byte[8];
		byte[] Payment = new byte[8];
		byte[] PartPayment1 = new byte[7];
		byte[] PartPayment2 = new byte[7];

		// get ID and BIrthDate
		new hisGetBasicData("hisGetBasicData");
		if (DataBase.getDatabase().list_errorcode.get("hisGetBasicData") != 0) {
			errorcode = DataBase.getDatabase().list_errorcode.get("hisGetBasicData");
		} else if (!DataBase.getDatabase().data_value.get("hisGetBasicData").isEmpty()) {
			errorcode = DataBase.getDatabase().list_errorcode.get("hisGetBasicData");
			int index = DataBase.getDatabase().data_symbol.get("hisGetBasicData").indexOf("身分證號");
			String tmp_text = DataBase.getDatabase().data_value.get("hisGetBasicData").get(index);
			pPatientID = tmp_text.getBytes();

			index = DataBase.getDatabase().data_symbol.get("hisGetBasicData").indexOf("出生日期");
			tmp_text = DataBase.getDatabase().data_value.get("hisGetBasicData").get(index);
			pPatientBirthDate = tmp_text.getBytes();
		}
		// get pDateTime
		String temp1 = "0";
		String temp2 = "0";
		if (!DataBase.getDatabase().data_value.get("hisGetSeqNumber").isEmpty()) {
			int index = DataBase.getDatabase().data_symbol.get("hisGetSeqNumber").indexOf("就診日期時間");
			if (index == -1) {
				index = 0;
			}
			temp1 = DataBase.getDatabase().data_value.get("hisGetSeqNumber").get(index);
		}
		if (!DataBase.getDatabase().data_value.get("hisGetSeqNumber256").isEmpty()) {
			int index = DataBase.getDatabase().data_symbol.get("hisGetSeqNumber256").indexOf("就診日期時間");
			if (index == -1) {
				index = 0;
			}
			temp2 = DataBase.getDatabase().data_value.get("hisGetSeqNumber256").get(index);
		}
		if (temp1.equals("0") && temp2.equals("0")) {
			hisGetSeqNumber n = new hisGetSeqNumber("hisGetSeqNumber");
			if (n.errorcode == 0) {
			} else if (n.errorcode == 5001) {
				new csUpdateHCContents("csUpdateHCContents");
				n = new hisGetSeqNumber("hisGetSeqNumber");
			}
			if (n.errorcode == 0) {
				int index = DataBase.getDatabase().data_symbol.get("hisGetSeqNumber").indexOf("就診日期時間");
				if (index == -1) {
					index = 0;
				}
				temp1 = DataBase.getDatabase().data_value.get("hisGetSeqNumber").get(index);
			}
		}

		if (Double.parseDouble(temp1) > Double.parseDouble(temp2)) {
			pDateTime = temp1.getBytes();

		} else {
			pDateTime = temp2.getBytes();
		}
		// get pDataWrite
		Record = "3".getBytes();// 1
		MainCode = "A1234".getBytes();// 7
		Code1 = "B1234".getBytes();// 7
		Code2 = "C1234".getBytes();// 7
		Code3 = "D1234".getBytes();// 7
		Code4 = "E1234".getBytes();// 7
		Code5 = "F1234".getBytes();// 7
		Expension = "10000000".getBytes();// 8
		PartExpension = "10000000".getBytes();// 8
		Payment = "20000000".getBytes();// 8
		PartPayment1 = "3000000".getBytes();// 7
		PartPayment2 = "4000000".getBytes();// 7

                                  int templength=0;
		// old  form
		System.arraycopy(Record, 0, pDataWrite, templength, Record.length);
                                   templength+=(Record.length);
		System.arraycopy(MainCode, 0, pDataWrite,templength, MainCode.length);
                                   templength+=(MainCode.length);
		System.arraycopy(Code1, 0, pDataWrite, templength, Code1.length);
                                   templength+=(Code1.length);
		System.arraycopy(Code2, 0, pDataWrite, templength, Code2.length);
                                   templength+=(Code2.length);
		System.arraycopy(Code3, 0, pDataWrite, templength, Code3.length);
                                   templength+=(Code3.length);
		System.arraycopy(Code4, 0, pDataWrite, templength, Code4.length);
                                   templength+=(Code4.length);
		System.arraycopy(Code5, 0, pDataWrite, templength, Code5.length);
                                    templength+=(Code5.length);
		System.arraycopy(Expension, 0, pDataWrite, templength, Expension.length);
                                   templength+=(Expension.length);
		System.arraycopy(PartExpension, 0, pDataWrite, templength, PartExpension.length);
                                   templength+=(PartExpension.length);
		System.arraycopy(Payment, 0, pDataWrite,  templength, Payment.length);
                                    templength+=(Payment.length);
		System.arraycopy(PartPayment1, 0, pDataWrite,  templength, PartPayment1.length);
                                    templength+=(PartPayment1.length);
		System.arraycopy(PartPayment2, 0, pDataWrite,  templength, PartPayment2.length);

		errorcode = CsHislibrary.Istance.hisWriteTreatmentData(pDateTime, pPatientID, pPatientBirthDate, pDataWrite,
				pBufferDocID);
	}

	@Override
	public void dataStore() {

	}

}

//1.48 hisWritePrescriptionSign
class hisWritePrescriptionSign extends ApiBase {

	public hisWritePrescriptionSign(String API) {
		super(API);
	}

	@Override
	public void run() {
		byte[] pDateTime = new byte[14];
		byte[] pPatientID = new byte[11];
		byte[] pPatientBirthDate = new byte[8];
		byte[] pDataWrite = new byte[61];

		byte[] DateTime = new byte[13];
		byte[] MedicalOrderCategory = new byte[1];
		byte[] ItemNumber = new byte[12];
		byte[] Part = new byte[6];
		byte[] Usage = new byte[18];
		byte[] Days = new byte[2];
		byte[] Total = new byte[7];
		byte[] Record = new byte[2];

		// get ID and BIrthDate
		new hisGetBasicData("hisGetBasicData");
		if (DataBase.getDatabase().list_errorcode.get("hisGetBasicData") != 0) {
			errorcode = DataBase.getDatabase().list_errorcode.get("hisGetBasicData");
		} else if (!DataBase.getDatabase().data_value.get("hisGetBasicData").isEmpty()) {
			errorcode = DataBase.getDatabase().list_errorcode.get("hisGetBasicData");
			int index = DataBase.getDatabase().data_symbol.get("hisGetBasicData").indexOf("身分證號");
			String tmp_text = DataBase.getDatabase().data_value.get("hisGetBasicData").get(index);
			pPatientID = tmp_text.getBytes();

			index = DataBase.getDatabase().data_symbol.get("hisGetBasicData").indexOf("出生日期");
			tmp_text = DataBase.getDatabase().data_value.get("hisGetBasicData").get(index);
			pPatientBirthDate = tmp_text.getBytes();
		}
		String temp1 = "0";
		// get pDateTime
		String temp2 = "0";
		if (!DataBase.getDatabase().data_value.get("hisGetSeqNumber").isEmpty()) {
			int index = DataBase.getDatabase().data_symbol.get("hisGetSeqNumber").indexOf("就診日期時間");
			if (index == -1) {
				index = 0;
			}
			temp1 = DataBase.getDatabase().data_value.get("hisGetSeqNumber").get(index);
		}
		if (!DataBase.getDatabase().data_value.get("hisGetSeqNumber256").isEmpty()) {
			int index = DataBase.getDatabase().data_symbol.get("hisGetSeqNumber256").indexOf("就診日期時間");
			if (index == -1) {
				index = 0;
			}
			temp2 = DataBase.getDatabase().data_value.get("hisGetSeqNumber256").get(index);
		}
		if (temp1.equals("0") && temp2.equals("0")) {
			hisGetSeqNumber n = new hisGetSeqNumber("hisGetSeqNumber");
			if (n.errorcode == 0) {
			} else if (n.errorcode == 5001) {
				new csUpdateHCContents("csUpdateHCContents");
				n = new hisGetSeqNumber("hisGetSeqNumber");
			}
			if (n.errorcode == 0) {
				int index = DataBase.getDatabase().data_symbol.get("hisGetSeqNumber").indexOf("就診日期時間");
				if (index == -1) {
					index = 0;
				}
				temp1 = DataBase.getDatabase().data_value.get("hisGetSeqNumber").get(index);
			}
		}
		if (Double.parseDouble(temp1) > Double.parseDouble(temp2)) {
			pDateTime = temp1.getBytes();
			DateTime = temp1.substring(0, 13).getBytes();

		} else {
			pDateTime = temp2.getBytes();
			DateTime = temp2.substring(0, 13).getBytes();
		}
		// get pDataWrite
		MedicalOrderCategory = "1".getBytes();
		ItemNumber = "123456789".getBytes();
                                    Part="hand".getBytes();
                                    Usage="none".getBytes();
                                    Days="14".getBytes();
                                    Total="1000ml".getBytes();
                                    Record="30".getBytes();
                                    
                                    int templength=0;
		System.arraycopy(DateTime, 0, pDataWrite, templength, DateTime.length);
                                   templength+=(DateTime.length);
		System.arraycopy(MedicalOrderCategory, 0, pDataWrite, templength, MedicalOrderCategory.length);
                                  templength+=(MedicalOrderCategory.length);
                                  System.arraycopy( ItemNumber, 0, pDataWrite, templength,  ItemNumber.length);
                                  templength+=( ItemNumber.length);
                                  System.arraycopy( Part, 0, pDataWrite,  templength,  Part.length);
                                  templength+=( Part.length);
                                  System.arraycopy(Usage, 0, pDataWrite,  templength,Usage.length);
                                  templength+=(Usage.length);
                                  System.arraycopy(Days, 0, pDataWrite, templength, Days.length);
                                  templength+=(Days.length);
                                  System.arraycopy(Total, 0, pDataWrite, templength, Total.length);
                                  templength+=(Total.length);
                                  System.arraycopy(Record, 0, pDataWrite, templength, Record.length);

		// buffer
		byte[] buf = new byte[40];
		int[] buf_length = { buf.length };
		errorcode = CsHislibrary.Istance.hisWritePrescriptionSign(pDateTime, pPatientID, pPatientBirthDate, pDataWrite,
				buf, buf_length);
	}

	@Override
	public void dataStore() {

	}
}

//1.49 hisWriteMultiPrescriptSign
class hisWriteMultiPrescriptSign extends ApiBase{

	public hisWriteMultiPrescriptSign(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		byte[] pDateTime = new byte[14];
		byte[] pPatientID = new byte[11];
		byte[] pPatientBirthDate = new byte[8];
		byte[] pDataWrite = new byte[20];


		// get ID and BIrthDate
		new hisGetBasicData("hisGetBasicData");
		if (DataBase.getDatabase().list_errorcode.get("hisGetBasicData") != 0) {
			errorcode = DataBase.getDatabase().list_errorcode.get("hisGetBasicData");
		} else if (!DataBase.getDatabase().data_value.get("hisGetBasicData").isEmpty()) {
			errorcode = DataBase.getDatabase().list_errorcode.get("hisGetBasicData");
			int index = DataBase.getDatabase().data_symbol.get("hisGetBasicData").indexOf("身分證號");
			String tmp_text = DataBase.getDatabase().data_value.get("hisGetBasicData").get(index);
			pPatientID = tmp_text.getBytes();

			index = DataBase.getDatabase().data_symbol.get("hisGetBasicData").indexOf("出生日期");
			tmp_text = DataBase.getDatabase().data_value.get("hisGetBasicData").get(index);
			pPatientBirthDate = tmp_text.getBytes();
		}
		// get pDateTime
		String temp1 = "0";
		String temp2 = "0";
		if (!DataBase.getDatabase().data_value.get("hisGetSeqNumber").isEmpty()) {
			int index = DataBase.getDatabase().data_symbol.get("hisGetSeqNumber").indexOf("就診日期時間");
			if (index == -1) {
				index = 0;
			}
			temp1 = DataBase.getDatabase().data_value.get("hisGetSeqNumber").get(index);
		}
		if (!DataBase.getDatabase().data_value.get("hisGetSeqNumber256").isEmpty()) {
			int index = DataBase.getDatabase().data_symbol.get("hisGetSeqNumber256").indexOf("就診日期時間");
			if (index == -1) {
				index = 0;
			}
			temp2 = DataBase.getDatabase().data_value.get("hisGetSeqNumber256").get(index);
		}
		if (temp1.equals("0") && temp2.equals("0")) {
			hisGetSeqNumber n = new hisGetSeqNumber("hisGetSeqNumber");
			if (n.errorcode == 0) {
			} else if (n.errorcode == 5001) {
				new csUpdateHCContents("csUpdateHCContents");
				n = new hisGetSeqNumber("hisGetSeqNumber");
			}
			if (n.errorcode == 0) {
				int index = DataBase.getDatabase().data_symbol.get("hisGetSeqNumber").indexOf("就診日期時間");
				if (index == -1) {
					index = 0;
				}
				temp1 = DataBase.getDatabase().data_value.get("hisGetSeqNumber").get(index);
			}
		}

		if (Double.parseDouble(temp1) > Double.parseDouble(temp2)) {
			pDateTime = temp1.getBytes();
			pDataWrite= temp1.getBytes();

		} else {
			pDateTime = temp2.getBytes();
			pDataWrite= temp1.getBytes();
		}
		// get pDataWrite	
		int[] iWriteCount = {1};
		 errorcode = CsHislibrary.Istance.hisWriteMultiPrescriptSign(pDateTime,pPatientID,pPatientBirthDate,pDataWrite,iWriteCount,buf.get(0),length.get(0));
		System.out.print(errorcode);
	}

}

//1.50 hisGetCriticalIllnessID
class hisGetCriticalIllnessID extends ApiBase {

	public hisGetCriticalIllnessID(String API)
	{
		super(API);
	}
	@Override
	public void run() {
        byte[] pPatientID = new byte[11];
        byte[] pPatientBirthDate = new byte[8];
        new hisGetBasicData("hisGetBasicData");
        if(DataBase.getDatabase().list_errorcode.get("hisGetBasicData") != 0)
        {
        	errorcode = DataBase.getDatabase().list_errorcode.get("hisGetBasicData");
        }
        else
        {
        	int index = DataBase.getDatabase().data_symbol.get("hisGetBasicData").indexOf("身分證號");
            String tmp_text = DataBase.getDatabase().data_value.get("hisGetBasicData").get(index);
            byte[] tmp = tmp_text.getBytes(Charset.forName("Big5"));
    		System.arraycopy(tmp, 0, pPatientID, 0, tmp.length);
    		index = DataBase.getDatabase().data_symbol.get("hisGetBasicData").indexOf("出生日期");
            tmp_text = DataBase.getDatabase().data_value.get("hisGetBasicData").get(index);
            tmp = tmp_text.getBytes(Charset.forName("Big5"));
    		System.arraycopy(tmp, 0, pPatientBirthDate, 0, tmp.length);
    		ICD_10_CM_format = true;
    		errorcode = CsHislibrary.Istance.hisGetCriticalIllnessID(pPatientID,pPatientBirthDate,buf.get(0),length.get(0));
        }
	}
}
//1.51 csGetVersionEx
class csGetVersionEx extends ApiBase {

	public csGetVersionEx(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		String path = "C:\\lib\\CsHis.dll";
		buf.set(0,path.getBytes(Charset.forName("Big5")));
		errorcode = CsHislibrary.Istance.csGetVersionEx(buf.get(0));
		errorcode = 0;
	}
	@Override
	public void dataStore()
	{
		DataBase.getDatabase().clear_value(API);
		DataBase.getDatabase().data_value.get(API).add(new String(buf.get(0),0,buf.get(0).length));
	}
}

//1.52 csSoftwareReset 
class csSoftwareReset extends ApiBase {

public csSoftwareReset(String API) {
    super(API);
}
    public void setBuffer() {
    DataBase.getDatabase().clear_value(API);
    DataBase.getDatabase().data_list_value = DataBase.getDatabase().data_value.get(API);
}
@Override
public void run() {
    for (int i = 0; i <= 3; i++) {       
        errorcode = CsHislibrary.Istance.csSoftwareReset(i);
        String errorText = status_return(i);
        DataBase.getDatabase().data_list_value.add(errorText);
    }
    errorcode = 0;
}

public String status_return(int errorcode) {
    String text = "";
   if( errorcode!=0) {
    text="未重置成功";
   }else {
    text="重置成功";
   }    
    return text;
}
@Override
public void dataStore() {
    DataBase.getDatabase().data_value.put(API, DataBase.getDatabase().data_list_value);
}
}
//2.1 csVerifySAMDC
class csVerifySAMDC extends ApiBase {

	public csVerifySAMDC(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		errorcode = CsHislibrary.Istance.csVerifySAMDC();
	}
	@Override
	public void dataStore()
	{
		
	}
}
//2.2 csGetHospID
class csGetHospID extends ApiBase {

	public csGetHospID(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		int len = Integer.valueOf(DataBase.getDatabase().data_totalbyte.get(API).get(0));
		short[] tmp_len = {(short) len};
		errorcode = CsHislibrary.Istance.csGetHospID(buf.get(0),tmp_len);
	}
}

//4.1 hpcGetHPCStatus
class hpcGetHPCStatus extends ApiBase {
	public int[] tmp_buf = {1};
	public hpcGetHPCStatus(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		int[] tmp_buf = {1};
		errorcode = CsHislibrary.Istance.hpcGetHPCStatus(1,tmp_buf);
		this.tmp_buf = tmp_buf;
	}
	@Override
	public void dataStore()
	{
		DataBase.getDatabase().clear_value(API);
		if(errorcode == 0)
		{
			String text;
			if(tmp_buf[0]==0)
				text = "醫事人員卡不存在";
			else
				text = "醫事人員卡存在";
			DataBase.getDatabase().data_value.get(API).add(text);
		}
	}
}
//4.2 hpcVerifyHPCPIN
class hpcVerifyHPCPIN extends ApiBase {

	public hpcVerifyHPCPIN(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		errorcode = CsHislibrary.Istance.hpcVerifyHPCPIN();
	}
	@Override
	public void dataStore()
	{
		
	}
}
//4.5 hpcGetHPCSN
class hpcGetHPCSN extends ApiBase {

	public hpcGetHPCSN(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		errorcode = CsHislibrary.Istance.hpcGetHPCSN(buf.get(0),length.get(0));
	}
	@Override
	public void dataStore()
	{
		DataBase.getDatabase().clear_value(API);
		if(errorcode == 0)
		{
			String text = new String(buf.get(0),0,length.get(0)[0]);
			DataBase.getDatabase().data_value.get(API).add(text);
		}
	}
}
//4.6 hpcGetHPCSSN
class hpcGetHPCSSN extends ApiBase {

	public hpcGetHPCSSN(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		errorcode = CsHislibrary.Istance.hpcGetHPCSSN(buf.get(0),length.get(0));
	}
	@Override
	public void dataStore()
	{
		DataBase.getDatabase().clear_value(API);
		if(errorcode == 0)
		{
			String text = new String(buf.get(0),0,length.get(0)[0]);
			DataBase.getDatabase().data_value.get(API).add(text);
		}
	}
}
//4.8 hpcGetHPCENAME
class hpcGetHPCENAME extends ApiBase {

	public hpcGetHPCENAME(String API)
	{
		super(API);
	}
	@Override
	public void run() {
		errorcode = CsHislibrary.Istance.hpcGetHPCENAME(buf.get(0),length.get(0));
	}
	@Override
	public void dataStore()
	{
		DataBase.getDatabase().clear_value(API);
		if(errorcode == 0)
		{
			String text = new String(buf.get(0),0,length.get(0)[0]);
			DataBase.getDatabase().data_value.get(API).add(text);
		}
	}
}