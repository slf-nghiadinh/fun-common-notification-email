package com.sunlife.vn.constant;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Constant {

    public static final String ATTACH_FILE_TEMP_PROP = "attachFile";
    public static final String DOCUMENT_TITLE = "documentTitle";
    public static final String MIME_TYPE_TEMP_PROP = "mimeType";
    public static final String EXTENSION = "ext";

    public static final String EVENT_DOCUMENT_MAPPING_NOT_FOUND = "PLEASE CHECK EVENT_DOCUMENT_MAPPING_NOT_FOUND";
    public static final String EVENT_DOCUMENT_TYPE_PROPERTIES_NOT_FOUND = "PLEASE CHECK_DOC_TYPE_ID_PROPERTIES_NOT_FOUND";
    public static final String GET_FILE_NET_MAPPING_ERROR = "PLEASE FILENET MAPPING CONFIG";

    public static final String DOCUMENT_CLASS = "docClass";
    public static final String FOLDER_COPY = "folderCopy";

    public static final String ACTION_CD_COMPLETE = "CMPLT_PDRVR";
    public static final String ALERT_PASSWORD = "ALERT_PASSWORD";

    public static final String INVOICE_DATA_TAG = "invoiceData";

    public static final String ENGINE_JASPER = "jasper";
    public static final String ENGINE_DOCX4J = "docx4j";

    public static final String ENGINE_EXCEL = "excel";
    public static final String ENGINE_VELOCITY = "velocity";
    
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "hh:mm:ss-00:00";
    public static final String DATE_TIME_MILLISECOND_FORMAT = "yyyy-MM-dd hh:mm:ss.SSS";
    public static final String TARGET_NAMSESPACE = "http://webservices.elink.solcorp.com";
    public static final String TARGET_NAMSESPACE_SCHEMA = "http://schema/webservices.elink.solcorp.com";

    public static final String APPL_UPLOAD_STEP = "UPLD_STEP";
    public static final String APPL_UPLOAD_STEP_CLIENT_EXISTING = "CL-0";
    public static final String APPL_UPLOAD_STEP_CREATE_CLIENT = "CL-1";
    public static final String APPL_UPLOAD_STEP_CREATE_ADDRESS = "CL-2";
    public static final String APPL_UPLOAD_STEP_CREATE_CURRENT_ADDRESS = "CL-6";
    public static final String APPL_UPLOAD_STEP_UPDATE_PHONE = "CL-3";
    public static final String APPL_UPLOAD_STEP_UPLOAD_INCOME = "CL-4";
    public static final String APPL_UPLOAD_STEP_UPDATE_VN_FONT = "CL-5";
    public static final String APPL_STAT_FAIL = "F";
    public static final String APPL_STAT_FILENET_FAIL = "F_UAF";
    public static final String APPL_STAT_FILENET_SUCCESS = "S_UAF";
    public static final String APPL_STAT_SUCCESS = "S";
    public static final String APPL_STAT = "APPL_STAT";
    public static final String APPL_UPLOAD_STEP_CHECK_PREPARE_INFO = "PO-0";
    public static final String APPL_UPLOAD_STEP_CREATE_POLICY = "PO-1";
    public static final String APPL_UPLOAD_STEP_COVERAGE_UPDATE = "PO-2";
    public static final String APPL_UPLOAD_STEP_COVERAGE_CREATE = "PO-3";
    public static final String APPL_UPLOAD_STEP_ALLOCATE_POLICY = "PO-4";
    public static final String APPL_UPLOAD_STEP_UPLOAD_FAMILY_HISTORY = "PO-5";
    public static final String APPL_UPLOAD_STEP_UPLOAD_PHYSICIAN_PERSONAL = "PO-6";
    public static final String APPL_UPLOAD_STEP_UPLOAD_EXISTING_POLICY = "PO-7";
    public static final String APPL_UPLOAD_STEP_APPLICATION_ENTRY_PERSONAL = "PO-8";
    public static final String APPL_UPLOAD_STEP_BENEFICIARY_CREATE = "PO-9";
    public static final String APPL_UPLOAD_STEP_UPDATE_POLICY = "PO-10";
    public static final String APPL_UPLOAD_STEP_APPLY_PREMIUM = "PO-11";
    public static final String APPL_UPLOAD_STEP_CLEAN_CASE = "PO-12";
    public static final String CLIENT_QUESTION = "QSTN_SEC";
    public static final String AGENT_QUESTION = "AGC_SEC";
    public static final String QUESTION_POLICY_EXISTING = "EXST_POL";
    public static final String QUESTION_UNDER_WRITING = "UW_QSTN";
    public static final String QUESTION_AGENT_REPORT = "AGC_RPT";
    public static final String QUESTION_THIRD_PARTY = "3RD_PT";
    public static final String CLIENT_TYPE_COMPANY = "C";
    public static final String MAILLING_ADDRESS = "M";
    public static final String REGISTER_ADDRESS = "RA";
    public static final String BUSINESS_ADDRESS = "EA";

    public static final String POLICY_OWNER_INDICATOR_YES = "Y";
    public static final String CLIENT_TYPE_PERSONAL = "P";
    public static final String CURRENT_ADDRESS = "CA";
    public static final String IS_CLIENT_OWNER_INDICATOR = "Y";
    public static final long CLIENT_OWNER_SEQUENCE_MAIN_LI = 1L;
    public static final String YES_INDICATOR = "Y";
    public static final String NO_INDICATOR = "N";
    public static final String UNKNOWN_INDICATOR = "*";

    public static final String FIRST_ALLOCATION_STEP = "allocation1";
    public static final String SECOND_ALLOCATION_STEP = "allocation2";
    public static final int ACTIVE_FLAG = 1;
    public static final String DLVR_TYP_MAIL_CD = "MAIL";
    public static final String DLVR_TYP_FILE_NET_CD = "FILE_NET";
    public static final String DLVR_TYP_SMS_CD = "SMS";
    
    
    public static final String KIT_DELIVERY_MAIL_PREFIX = "kit-delivery-";
    public static final String DEFAULT_MATCHES_ALL = "(.*)";

    public static enum EVENTSTATUS {
        COMPLETE, INPROGRESS, FAIL, DEQUEUE
    }

    public static enum PARAMSOURCE {
        evt, act
    }

    public static enum EMAILCONFIG {
        mailType, customerEmail, to, cc, bcc, subject, body, attachments, images, password, templateName, encoding, fromAddress, fromName,hasAttachments
    }
    
    public static enum SMSSTATUS {
        SUCCESS, FAILED, IN_PROGRESS, RESCHEDULED;

    }

	public static enum MAILSTATUS {
		INITIAL, SUCCESS, FAILED, IN_PROGRESS, RESCHEDULED;

	}

    public static enum LETTERCONFIG{
        POL_ID("pol_id"),CLIENT_ID("cli_id"),LETTER_CODE("letter_code"),
        CUSTOMER_EMAIL("email_txt"),SIGN("SIGN"),HEADER("HEADER"),
        FOOTER("FOOTER"),PAY_INTRO("PAY_INTRO"),SUCSESS("SUCSESS"),FAIL("FAIL"),
        VALUE_CHECK("Y"),FILEPATH("filePath"),FILE("files"),PAYINTRO("payintro"),
        POL_NUMBER("phon_txt"),DOC_GEN("docGenConfig");

        public final String value;

        private LETTERCONFIG(String value){this.value = value;}
    }

    public static enum SMSPREFIXCODE {
        VN("84"), CN("86");

        public final String value;

        private SMSPREFIXCODE(String value) {
            this.value = value;
        }
    }

    public static enum DOCUMENTEXPORT {
        fileName, filePath, userData, metaData, data, rootPath, fileNameNoExtension
    }

    public static enum DOCUMENTEXPORTCONFIG {
        name_fortmat, file_info_list, data, rootPath
    }

    public static enum DOCUMENTUPLOAD {
        files, fileNetMapping, documentMap
    }

    public static enum DOCUMENTSIGNED {
        docType, signedFilePath, docTypeId, rootPath, nextEventType, fileName, outputName, filePath
    }

    public static enum DATASOURCE {
        driverClassName, url, userName, password, poolSize, idleTimeOut, minIdle, maxLifetime, leakDetectionThreshold, connectionTimeout
    }

    public static enum EVENTREQUEST {
        eventType, objectType, system, masterId, data, base64
    }

    public static enum INGENIUM {
        trxn_uuid, ing_pmt_trxn_id, pol_id, cus_nm, cus_addr, cus_email, inv_amt, cus_tax_cd, inv_trxn_typ, inv_own_typ, pol_stat, cli_po_id, pd_to_dt, acct_hist_prces_dt, NOR, EDP, inv_typ,
        inv_chnl_cd, prem_typ_cd
    }

    public static enum PROVIDERRESPONSE {
        status, errorcode, description
    }

    public static enum SMSREQUEST {
        Authorization, from, to, text, unicode, smsId, contentId
    }

    public static enum TEMPLATEMETADATA {
        template_path, params
    }

    public static enum MAILSENDERCONFIG {
        host, port, protocol, auth, starttls, debug, ssl
    }

    public static enum INVOICETYPE {
        NOR, NTU, EDP
    }

    public static enum INVOICEDATANODE {
        clientNumber, paidToDate, customerEmail, amdPmtContent, noRelatedInvErr, totNtuEqualZeroErr, totNtuLessThanZeroErr
    }

    public static enum EXTERNALCALLBACK {
        nextEventType
    }

    public static enum INV_CHNL_CD {
        EDIST("EDIST"), OTHER("OTHER");

        public final String value;

        private INV_CHNL_CD(String value) {
            this.value = value;
        }
    }

    public static enum EDIST_PRODUCTCODE {
        CANCER("PRCCDI"), PA("PRCCPA");

        public final String value;

        private EDIST_PRODUCTCODE(String value) {
            this.value = value;
        }
    }

    public static enum EDIST_PRODUCTNAME {
        CANCER("SUN - SỐNG VỮNG VÀNG - PHIÊN BẢN ĐẶC BIỆT"), PA("SUN - BẠN ĐỒNG HÀNH"),;

        public final String value;

        private EDIST_PRODUCTNAME(String value) {
            this.value = value;
        }
    }

    public static final Map<String, String> GENDERMAP = new HashMap<String, String>();
    static {
        GENDERMAP.put("F", "Nữ");
        GENDERMAP.put("M", "Nam");
    }

    public static final String CHNL_CD_EMAIL = "EMAIL";
    public static final String CHNL_CD_SMS = "SMS";
    public static final String EVNT_STAT_CD_SUCCESS = "1";
    public static final String EVNT_STAT_CD_FAIL = "0";

    public static enum SLVNINFO {
        slvnAddress, slvnPhone, slvnFax, slvnTax, slvnSite, slvnBarcode, inquiryLink, paymentContent
    }

    public static enum HANDLERSTATUS {
        ACTIVE(1), INACTIVE(0);

        public final int value;

        private HANDLERSTATUS(int value) {
            this.value = value;
        }
    }

    public static enum FACEAMT {
        ONE_MILLION(new BigDecimal(100000000)), TWO_MILLION(new BigDecimal(200000000)), THREE_MILLION(new BigDecimal(300000000));

        public final BigDecimal value;

        private FACEAMT(BigDecimal value) {
            this.value = value;
        }
    }

    public static enum APPLJSON {
        POLICYKITJSON("policyKitJson"), SOURCEJSON("sourceJson"), INSURANCEPURPOSECODE_O("O"), PREMIUMSOURCECODE_O("O"), YES("Y"), NO("N"), ZERO("0"), ONE("1"), EVENTIND_D("D"),
        OCCUPATIONCODE_BARB("BARB"), HEIGHT_QUES("QSTN_09_01"), WEIGHT_QUES("QSTN_09_02"), CANCER_QUES("QSTN_01_01"), PROGRAMCODE_OD("OD"), PLACEOFSIGNINGCODE_HA("HA"),
        ERR_INVALID_PRD_CD("Invalid Product Code"), ADDRESS_TYPE_CURRENT("CA"), ADDRESS_TYPE_RESIDENCE("RA"), LOCATION_VN("VN"), DEFAULT_BEN_DOB("1900-01-01T00:00:00.000Z"),;

        public final String value;

        private APPLJSON(String value) {
            this.value = value;
        }
    }
    public static enum BILLING_NOTICE{
        POL_ID("pol_id"), SMS_TYP("sms_typ"),PTD_DT("ptd_dt"),
        SMS_AMT("sms_amt"),POL_NAME("pol_name"),TO("to"), AGT_CD("source");
        public final String value;
        private BILLING_NOTICE(String value) {
            this.value = value;
        }
    }

    public static enum AUTODEBIT_API_TYPE {
        CHCK_STAT("CHCK_STAT"), REG_AUT_DEBIT("REG_AUT_DEBIT"), UREG_AUT_DEBIT("UREG_AUT_DEBIT"), AUT_DEBIT_TRXN("AUT_DEBIT_TRXN"), PRM_DUE_TRXN("PRM_DUE_TRXN");

        public final String value;

        private AUTODEBIT_API_TYPE(String value) {
            this.value = value;
        }
    }

    public static enum AUTODEBIT_API_STATUS {
        SUC("SUC"), IPG(""), EFR("EFR"), EFS("EFS"), OTH("OTH");

        public final String value;

        private AUTODEBIT_API_STATUS(String value) {
            this.value = value;
        }
    }

    public static enum AUTODEBIT_FROM_CODE {
        SLVN("SLVN"), TCB("TCB"), VPB("VPB"), VCB("VCB");

        public final String value;

        private AUTODEBIT_FROM_CODE(String value) {
            this.value = value;
        }
    }

    public static enum EDIST_EMAIL {
        EACK_LINK("eAckLink"), EACK_KEY("ackKey"), PRODUCTNAME("productName"), EMAIL_INFO("emailInfo");

        public final String value;

        private EDIST_EMAIL(String value) {
            this.value = value;
        }
    }

    public static enum AUTODEBIT {
        AUTODEBIT("AUTODEBIT"), EVENTSERVICE("EVENTSERVICE"), PRODUCTNAME("productName"), YES("Y"), NO("N"), DM_POL_DUE_MSTR_ID("dm_pol_due_mstr_id"), POL_ID("pol_id"),
        ODS_CDM_POL_MSTR_ID("ods_cdm_pol_mstr_id"), CDM_POL_MSTR_ID("cdm_pol_mstr_id"), POL_OWN_CD("pol_own_cd"), POL_OWN_IDENT("pol_own_ident"), RQST_FRM_CD("rqst_frm_cd"), API_TYP_CD("api_typ_cd"),
        POL_OWN_NM("pol_own_nm"), POL_STAT_CD("pol_stat_cd"), POL_STAT_NM_VN("pol_stat_nm_vn"), POL_STAT_NM_EN("pol_stat_nm_en"), BIL_MO_CD("bil_mo_cd"), BIL_MODE_NM_EN("bil_mode_nm_en"),
        BIL_MODE_NM_VN("bil_mode_nm_vn"), POL_MODE_PREM_AMT("pol_mode_prem_amt"), PAY_TO_DT("pay_to_dt"), AUT_DEBI_FLG("aut_debi_flg"), AUT_DEBI_BNK_CD("aut_debi_bnk_cd"),
        LST_AUT_RQST_DT("lst_aut_rqst_dt"), MOD_PREM_AMT("mod_prem_amt"), SRC_IND("src_ind"), VALID_FLG("valid_flg"), VALID_FRM_DT("valid_frm_dt"), VALID_TO_DT("valid_to_dt"), IS_PRC("is_prc"),
        CREAT_DT("creat_dt"), CREAT_BY("creat_by"), UPDT_DT("updt_dt"), UPDT_BY("updt_by"), REF_CDM_POL_MSTR_ID("ref_cdm_pol_mstr_id"), ORG_TRXN_NUM("org_trxn_num"), API_RQST_DT("api_rqst_dt"),
        API_RESP_DT("api_resp_dt"), API_RQST_MSG("api_rqst_msg"), API_RESP_MSG("api_resp_msg"), API_STAT_CD("api_stat_cd"), ERR_CD("err_cd"), RSLT_MSG("rslt_msg"), REMARK("remark"),
        POL_AUT_DEBIT_CD("pol_aut_debit_cd"), EVNT_STAT_FLG("evnt_stat_flg"), PMT_DATE("pmt_date"), APT_TRXN_LOG_ID("apt_trxn_log_id"), PREM_DUE_DT("prem_due_dt"), PREM_DUE_AMT("prem_due_amt"),
        BANKTRANSFERDESC("BankTransferDesc"), XREF("Xref"), TRANSACTIONDATE("TransactionDate"), AUT_DEBIT_DT("aut_debit_dt"), SLV_DESC("SLV_DESC"), COLLECTION_REF_NO("CollectionRefNo");

        public final String value;

        private AUTODEBIT(String value) {
            this.value = value;
        }
    }

    public static enum TPB_API_STATUS {
        SUCCESS("0"), FAILED("1");

        public final String value;

        private TPB_API_STATUS(String value) {
            this.value = value;
        }
    }

    public static enum PATHFINDER {
        TEMPLATE_CODE("templateCode");

        public final String value;

        private PATHFINDER(String value) {
            this.value = value;
        }
    }

    public static enum PATHFINDERRESPONSE {
        RETURN_CODE("returnCode"), CLIENT_ID("clientId"), ERROR_MSG("errorMessage");

        public final String value;

        private PATHFINDERRESPONSE(String value) {
            this.value = value;
        }
    }

    public static enum PATHFINDERRESPONSEDEF {
        OK("OK");

        public final String value;

        private PATHFINDERRESPONSEDEF(String value) {
            this.value = value;
        }
    }

    public static enum INVOICE_TYPE {
        INDIVIDUAL("ind-invoice"), GROUP("group-invoice"), AD_HOC("adhoc-invoice"), POLICY("policy");

        public final String value;

        private INVOICE_TYPE(String value) {
            this.value = value;
        }
    }

    public static enum MAIL_TYPE {
        ALERT_INVOICE("alert_invoice"), ALERT_OTHER_INVOICE("alert_other_invoice"), ALERT_POLICY("alert_policy"), NOTIFY_CODE_EMAIL("NOTIFY_CODE_EMAIL");

        public final String value;

        private MAIL_TYPE(String value) {
            this.value = value;
        }
    }

    public static enum INVOICE_CHANNEL_CODE {
        OTHER("OTHER"), EDIST("EDIST");

        public final String value;

        private INVOICE_CHANNEL_CODE(String value) {
            this.value = value;
        }
    }

    public static enum EPOL_STATUS {
        INPROGRESS("INPROGRESS"), COMPLETED("COMPLETED"), CREATE("CREATE"), 
        ;

        public final String value;

        private EPOL_STATUS(String value) {
            this.value = value;
        }
    }

    public static enum SERVER_INFO {
        SFTP, FTP, userName, host, port, password, remotePath, serverType, sftp, connectionTimeout
    }

    public static enum TBL_APPL {
        appl_id, stat;
    }

    public static enum DOCUMENT_GENERATE {
        DOC_TYPE("docType"), TEMPLATE_PATH("templatePath"), FILE_PATH("filePath"), NAME_FORMAT("nameFormat"), ENGINE("engine"), 
        ACT_SIGN("actSign"), ACT_UPLOAD("actUpload"), ACT_EMAIL("actEmail"),  ACT_NOTI("actNoti"), ACT_SMS("actSms"), 
        FILES("files"), DOC_CONFIG("docConfig"), EXTENSION("ext"), ROOT_NODE("rootNode"), 
        EMAIL_TYPE("mailType"), SMS_TYPE("smsType"), NOTI_TYPE("notiType"), TO("to"), SMS_TO("smsTo"), USER_ID("userId");

        public final String value;

        private DOCUMENT_GENERATE(String value) {
            this.value = value;
        }
    }

    public static enum DOCUMENT_COLLECTOR {
        ROOT_PATH("rootPath"), DOC_TYPE("docType"), DOC_TYP_META("metadata"), DOC_GEN_CONFIG("docGenConfig"), CONFIG_CODE("configCode"),
        FILE_COPY_CONFIG("fileCopyConfig"), FILE_NET_CONFIG("fileNetConfig"), DOC_INSTANCE_ID("docInstanceId"),
        DEFAULT_NODE("CobolData");
        public final String value;

        private DOCUMENT_COLLECTOR(String value) {
            this.value = value;
        }
    }

    public static enum COPYFILE {
        ROOT_PATH("rootPath"), FILE_NAME("fileName"), NAME_FORMAT("nameFormat"), 
        CURRENT_DATE("currentDate"), FILE_EXT("fileExt"), EVENT_ID("eventId"), MASTER_ID("masterId"), SUB_DIR("subDir"), ABSOLUTE_PATH("absolutePath"),
        SRC_FILE_PATH("srcFilePath"), DES_FILE_PATH("desFilePath");

        public final String value;

        private COPYFILE(String value) {
            this.value = value;
        }
	}
	
	public static int FLAG_Y = 1;
	public static int FLAG_N = 0;
	
	public static enum PAPERLESS {
		PAPERLESS("PAPERLESS"), BATCHDATE("batchdate"), METADATA("metadata"), LIST_DATA("list_data"), DATA("data"), COBOL_TYPE("cobol_type"), DOC_TYPE("doc_type"),  EVT_TYP("evt_typ"),ACTIVE_FLAG_YES("1"), ACTIVE_FLAG_NO("0"),
		BATCH_DATE("batch_date"), POLICYNO("policy_no"), PRODUCTCODE("product_code"), PRINT_FLAG_YES("1"), PRINT_FLAG_NO("0"),
		EPOL_KIT_ONL_ID("epol_kit_onl_id"), REF_BTCH_INF_ID("ref_btch_inf_id"), POL_ID("pol_id"), POLID("polid"), SEQ_NUM("seq_num"), CO_ID("co_id"), DOC_ID("doc_id"),
		PROD_CD("prod_cd"), PRNT_FLG("prnt_flg"), REF_EPOL_ONL_STAT("ref_epol_onl_stat"), 
		REF_EPOL_KIT_DETL("ref_epol_kit_detl"), DOC_TYP_CODE("doc_typ_code"), EVT_DATA_ID("evt_data_id"), KIT_INST_ID("kit_inst_id"),
		KIT_TYPE_ID("kit_type_id"), PLAN_ID("plan_id"), PRODUCT_CODE("product_code"), FILES_NODE("files"), DOC_TYP_NODE("docType"),
		E_KIT_VALU("E_KIT"), FILE_NAME_NODE("fileName"), FILE_PATH_NODE("filePath"), P_VERSION_PREFIX("P_VER_"), RIDERLIST("riderList"), APP_NO("app_form_id"),
		POLICY_ID("policy_id"), AGENT("agent"), COMMENT("comment"), EVT_DATA_STAT("eventDataStat"), CIWOP("ciwop"), NOT_SHR_INF("not_shr_inf"), ERR_MSG("ERR_MSG"), DOC_TYP_CD_LS("doc_typ_cd_ls");

		public final String value;

		private PAPERLESS(String value) {
			this.value = value;
		}
	}
	
	public static enum PAPERLESS_STATUS {
		INIT("INIT"), COLLECT("COLLECT"),  FAILED("FAILED"),  COMPLETED("COMPLETED")
		;

		public final String value;

		private PAPERLESS_STATUS(String value) {
			this.value = value;
		}
	}

    public static enum COMMON_FLOW {
        NEXT_HANDLER("nextHandler");
        ;

        public final String value;

        private COMMON_FLOW(String value) {
            this.value = value;
        }
    }

    public static enum FILENET {
        ID("ID"), DOCUMENT_TITLE("DocumentTitle"), EMAIL_TYPE("mailType"), SMS_TYPE("smsType");

        public final String value;

        private FILENET(String value) {
            this.value = value;
        }
    }

	public static enum NBU_SUBMIT_TYPE {
		SPEEDY, SETTLE_MANUALLY;

		public static NBU_SUBMIT_TYPE getSubmitType(String submitType){
			switch (submitType){
				case "SPEEDY": {
					return NBU_SUBMIT_TYPE.SPEEDY;
				}
				case "SETTLE_MANUALLY": {
					return NBU_SUBMIT_TYPE.SETTLE_MANUALLY;
				}
				default:{
					throw new RuntimeException("Inputted Submit type is invalid. Please check decision table.");
				}
			}
		}
	}

    public static enum DOC_INSTANCE_STAT {
        FAILED(3), COMPLETED(2);

        public final long value;

        private DOC_INSTANCE_STAT(long value) {
            this.value = value;
        }
    }
    
    public static enum BIZ_DATA {
        POLICY_NO("policyNo");
        
        public final String value;

        private BIZ_DATA(String value) {
            this.value = value;
        }
    }
    
    public static enum EVENT_RESULT {
        RESULT("result");
        
        public final String value;

        private EVENT_RESULT(String value) {
            this.value = value;
        }
    }

    public static enum SEX_CODE {
    	MALE("M"),
    	FEMALE("F"),
    	COMPANY("C"),

    	;
        
        public final String value;

        private SEX_CODE(String value) {
            this.value = value;
        }
    }

	public static enum XPATH {
		CLIENT_SEX_CODE("/CobolData/L4925-DATA-INFO/L4925-OWNER-INFO/L4925-OW-SEX-CD"), POLICY_OWNER_NM("/CobolData/L4925-DATA-INFO/L4925-OWNER-INFO/L4925-OW-NM"),
		POLICY_ID("/CobolData/L4925-DATA-INFO/L4925-POL-INFO/L4925-POL-ID"), BASIC_PLAN_NM("/CobolData/L4925-DATA-INFO/L4925-CVG-BASIC/L4925-CVG-BASIC-PLAN-NM"),
		POLICY_OWNER_EMAIL("/CobolData/L4925-DATA-INFO/L4925-OWNER-INFO/L4925-OW-EMAIL-ID"), ISS_EFF_DT("/CobolData/L4925-DATA-INFO/L4925-POL-INFO/L4925-POL-ISS-EFF-DT"),
		CHNL("/CobolData/L4925-DATA-INFO/L4925-AG-INFO/L4925-AG-CHNL-NM"), HARD_COPY_KIT_FLAG("/CobolData/L4925-DATA-INFO/L4925-POL-INFO/L4925-ZPOL-RECV-POL-KIT"),
		FIRST_NAME("/CobolData/L4925-DATA-INFO/L4925-OW-NM-INFO/L4925-OW-SUR-NM"), LAST_NAME("/CobolData/L4925-DATA-INFO/L4925-OW-NM-INFO/L4925-OW-GIV-NM"),
		MIDDLE_NAME("/CobolData/L4925-DATA-INFO/L4925-OW-NM-INFO/L4925-OW-MID-NM"),
		APP_NO("/CobolData/L4925-DATA-INFO/L4925-POL-INFO/L4925-POL-APP-FORM-ID"),
		AGENT_CODE("/CobolData/L4925-DATA-INFO/L4925-AG-INFO/L4925-AG-ID"),
        INSURED_NAME("/CobolData/L4925-DATA-INFO/L4925-MAIN-LI/L4925-MAIN-LI-NM");

		public final String value;

		private XPATH(String value) {
			this.value = value;
		}

	}

	public static enum FOLDER_CLEANUP {
		CONF_TYPE("configType"), FOLDER_PATH("folderPath"), SCHEDULE("schedule"), FILTER("filter"), DELETE_SUB("deleteSub"), TIME_TO_DELETE("timeToDelete");

		public final String value;

		private FOLDER_CLEANUP(String value) {

			this.value = value;
		}
	}

    public static enum EVENT_DATA_STAT {
        INIT(1),
        DOC_COLLECT(2),
        DOC_COLLECTED(3),
        MERGED(4),
        SIGN(5),
        DELIVERED(6),
        PRINT(7),
        COPY(8),
        COMPLETED(9),
        SEND_MAIL(10),
        UPLOAD_FILE_NET(11),
        FAILED(0);

        public final int value;

        private EVENT_DATA_STAT(int value) {
            this.value = value;
        }
    }
    
    public static enum ANNUAL_LETTER {
    	TYPE_EE("typeEE"), TYPE_FE("typeFE"), TYPE_UL("typeUL"), TYPE_ILP("typeILP"), TYPE_PA_TERM("typePaTerm"), POLID("polId"), CUS_EMAIL("cusEmail"), 
    	CUS_PHONE("cusPhone"), EXPIRY_DATE("expiryDate"), CLIENT_NAME("clientName"), CLIENT_ID("clientId"), CURRENT_DATE("currentDate")
    	;

		public final String value;

		private ANNUAL_LETTER(String value) {

			this.value = value;
		}
	}
    
    public static enum REMOVE_CLIENT {
    	mailType("REMOVE_CLIENT_EMAIL"), smsType("REMOVE_CLIENT_SMS");

        public final String value;

        private REMOVE_CLIENT(String value) {
            this.value = value;
        }
    }
    
    public static enum ADVISOR_SMS {
    	smsType("ADVISOR_WELCOME");

        public final String value;

        private ADVISOR_SMS(String value) {
            this.value = value;
        }
    }
    
    public static final String NOTI_TYPE_PUSH = "1";

    public static final String GRANT_TYPE ="grant_type";
    public static final String CLIENT_CRED ="client_credentials";
    public static final String SCOPE="scope";

    public static class NotificationStatus {
        public static final int DEFAULT = 0;
        public static final int NEW = 1;
        public static final int READ = 2;
        public static final int DELIVERED = 3;
        public static final int PENDING = 4;
        public static final int ERROR = 5;
    }

    public static class Group {
        public static final String ALL = "ALL";
        public static final String AD_ALL = "AD_ALL";
        public static final String FA_ALL = "FA_ALL";
        public static final String GAD_ALL = "GAD_ALL";

    }
    public static enum CRM_LITE {
        CRM_LITE_CASES("crmLiteCases"), CASE_IDS("caseIds"), POL_ID("policyNo"),
        REF_SUBMIT_HIST_STAT("refSubmitHistStat"), ERROR("error"), DOC_ID("docId"),
        TRXN_ID("trxnId"),SUBMITDATE("submitDate"),PROVINCECODE("provinceCd"),WARD("ward"),DISTRICT("district"),ADDRESSLN1("addressLn1"),ADDRESSLN2("addressLn2"),WARDCODE("wardCd"),TRANSACTIONTYPENAME("transactionTypeName")
        ;

        public final String value;

        private CRM_LITE(String value) {

            this.value = value;
        }
    }
}
