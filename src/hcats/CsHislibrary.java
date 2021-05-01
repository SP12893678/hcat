package hcats;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.sun.jna.Library;
import com.sun.jna.Native;
/**
 *
 * @author offic
 */
public interface CsHislibrary extends Library{
    CsHislibrary Istance = (CsHislibrary)Native.load("CsHis",CsHislibrary.class);
    /*1------------------------------------------------------------------------------------------------*/
    /*1-10----------------------------------------------------------------*/
    public int hisGetBasicData(byte[] pBuffer,int[] iBufferLen ); 
    public int hisGetRegisterBasic(byte[] pBuffer,int[] iBufferLen);
    public int hisGetRegisterPrevent(byte[] pBuffer,int[] iBufferLen);
    public int hisGetRegisterPregnant(byte[] pBuffer,int[] iBufferLen);
    public int hisGetTreatmentNoNeedHPC(byte[] pBuffer,int[] iBufferLen);
    public int hisGetCumulativeData(byte[] pBuffer,int[] iBufferLen);
    public int hisGetCumulativeFee(byte[] pBuffer,int[] iBufferLen);
    public int hisGetTreatmentNeedHPC(byte[] pBuffer,int[] iBufferLen);
    public int hisGetSeqNumber(byte[] cTreatItem, byte[] cBabyTreat, byte[] pBuffer, int[] iBufferLen);
    public int hisReadPrescription( byte[] pOutpatientPrescription, int[] iBufferLenOutpatient, 
            byte[] pLongTermPrescription, int[] iBufferLenLongTerm, byte[] pImportantTreatmentCode, 
            int[] iBufferLenImportant, byte[] pIrritationDrug, int[] iBufferLenIrritation);
    /*11-20---------------------------------------------------------------*/
    public int hisGetInoculateData(byte[] pBuffer,int[] iBufferLen); 
    public int hisGetOrganDonate(byte[] pBuffer,int[] iBufferLen); 
    public int hisGetEmergentTel(byte[] pBuffer,int[] iBufferLen); 
    public int hisGetLastSeqNum(byte[] pBuffer,int[] iBufferLen); 
    public int hisGetCardStatus(int CardType); 
    public int hisWriteTreatmentCode(byte[] pDateTime,byte[] pPatientID,byte[] pPatientBirthDate, 
            byte[] pDataWrite,byte[] pBufferDocID); 
    public int hisWriteTreatmentFee(byte[] pDateTime, byte[] pPatientID,byte[] pPatientBirthDate,
            byte[] pDataWrite); 
    public int hisWritePrescription(byte[] DateTime,byte[] pPatientID,byte[] pPatientBirthDate,
            byte[] pDataWrite); 
    public int hisWriteNewBorn(byte[] pPatientID, byte[] pPatientBirthDate,byte[] pNewBornDate,
            byte[] pNoOfDelivered); 
    public int hisWriteAllergicMedicines(byte[] pPatientID,byte[] pPatientBirthDate,byte[] pDataWrite,
            byte[] pBufferDocID); 
    /*21-30---------------------------------------------------------------*/
    public int hisWriteOrganDonate(byte[] pPatientID,byte[] pPatientBirthDate,byte[] pOrganeDonate); 
    public int hisWriteHealthInsurance( byte[] pPatientID,byte[] pPatientBirthDate,byte[] pServiceItem,
            byte[] pServiceItemCode); 
    public int hisWriteEmergentTel(byte[] pPatientID,byte[] pPatientBirthDate,byte[] pEmergentTel); 
    public int hisWritePredeliveryCheckup(byte[] pPatientID,byte[] pPatientBirthDate,byte[] pCheckupCode); 
    public int hisDeletePredeliveryData(byte[] pPatientID,byte[] pPatientBirthDate); 
    public int hisWriteInoculateData(byte[] pPatientID,byte[] pPatientBirthDate,byte[] pItem,
            byte[] pPackageNumber); 
    public int csVerifyHCPIN(); 
    public int csInputHCPIN(); 
    public int csDisableHCPIN(); 
    public int csUpdateHCContents(); 
    /*31-40---------------------------------------------------------------*/
    public int csOpenCom(int Comport);
    public int csCloseCom();
    public int hisGetCriticalIllness(byte[] pBuffer,int[] iBufferLen);
    public int csGetDateTime(byte[] pBuffer,int[] iBufferLen);
    public int csGetCardNo(int CardType,byte[] pBuffer,int[] iBufferLen);
    public int csISSetPIN();
    public int hisGetSeqNumber256(byte[] cTreatItem,byte[] cBabyTreat,byte[] cTreatAfterCheck,
            byte[] pBuffer,int[] iBufferLen);
    public int hisGetRegisterBasic2(byte[] pBuffer,int[] iBufferLen);
    public int csUnGetSeqNumber(byte[] pUnTreatDate);
    public int csUpdateHCNoReset();
    /*41-52---------------------------------------------------------------*/
    public int hisReadPrescriptMain(byte[] pOutpatientPrescription,int[]iBufferLenOutpatient,
            int iStartPos,int iEndPos,int[]iRecCount);
    public int hisReadPrescriptLongTerm(byte[] pLongTermPrescription,int[]iBufferLenLongTerm,
            int iStartPos,int iEndPos,int[]iRecCount);
    public int hisReadPrescriptHVE(byte[] pImportantTreatmentCode,int[]iBufferLenImportant,
            int iStartPos,int iEndPos,int[]iRecCount);
    public int hisReadPrescriptAllergic(byte[] pIrritationDrug,int[]iBufferLenIrritation,
            int iStartPos,int iEndPos,int[]iRecCount);
    public int hisWriteMultiPrescript(byte[] pDateTime,byte[] pPatientID,byte[] pPatientBirthDate,
            byte[] pDataWrite,int[]iWriteCount);
    public int hisWriteAllergicNum(byte[] pPatientID,byte[] pPatientBirthDate,byte[] pDataWrite,
            byte[] pBufferDocID,int iNum);
    public int hisWriteTreatmentData(byte[] pDateTime,byte[] pPatientID,byte[] pPatientBirthDate, 
            byte[] pDataWrite,byte[] pBufferDocID);
    public int hisWritePrescriptionSign(byte[] pDateTime,byte[] pPatientID,byte[] pPatientBirthDate,
            byte[] pDataWrite,byte[] pBuffer,int[]iBufferLen);
    public int hisWriteMultiPrescriptSign(byte[] pDateTime,byte[] pPatientID,byte[] pPatientBirthDate,
            byte[] pDataWrite,int[]iWriteCount,byte[] pBuffer,int[]iBufferLen);
    public int hisGetCriticalIllnessID(byte[] pPatientID,byte[] pPatientBirthDate,byte[] pBuffer,
            int[]iBufferLen);
    public int csGetVersionEx(byte[] pPath);
    public int csSoftwareReset(int Type);
    /*2------------------------------------------------------------------------------------------------*/
    public int csVerifySAMDC();
    public int csGetHospID (byte[] pBuffer,short[] iBufferLen);
    /*3------------------------------------------------------------------------------------------------*/
    public int csUploadData(byte[] pUploadFileName,byte[] pFileSize,byte[] pNumber,byte[] pBuffer,int[] iBufferLen);
    public int csUploadDataPrec(byte[] pUploadFileName,byte[] pFileSize,byte[] pNumber,byte[] pPrecNumber,byte[] pBuffer,int[] iBufferLen);
    // public int csDownloadData(byte[] pSAMID,byte[] pHospID,byte[] pNumber,byte[] pSendDate,byte[] pRecvDate,byte[] pServerRandom ,Pointer pDownloadFileName);
    /*4------------------------------------------------------------------------------------------------*/
    public int hpcGetHPCStatus(int Req,int[] Status);
    public int hpcVerifyHPCPIN();
    public int hpcInputHPCPIN();
    public int hpcUnlockHPC();
    public int hpcGetHPCSN(byte[] SN,int[] Len_SN);
    public int hpcGetHPCSSN(byte[] SSN,int[] Len_SSN);
    public int hpcGetHPCCNAME(byte[] CNAME,int[] Len_CNAME);
    public int hpcGetHPCENAME(byte[] ENAME,int[] Len_ENAME);
    /*5------------------------------------------------------------------------------------------------*/
    public int hisGetICD10EnC(byte[] IN,byte[] OUT);
    public int hisGetICD10DeC(byte[] IN,byte[] OUT);
}
