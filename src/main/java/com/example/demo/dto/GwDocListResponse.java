package com.example.demo.dto;

import lombok.Data;

@Data
public class GwDocListResponse {
  
  private String docId;
  private String docTitle;
  private String apprCompleteDt;

  private String docDrftId;
  private String docDrftNm;
  private String docDrftDeptCd;
  private String docDrftDeptNm;

  private String docApprId;
  private String docApprNm;
  private String docApprDeptCd;
  private String docApprDeptNm;

  private String attcFileNm;
  private String attcFileId;
}
