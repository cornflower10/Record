package com.record.moudle.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xiejingbao on 2017/9/3.
 */
@Entity
public class LawCase {
    @Id(autoincrement = true)
    private Long id;

    private boolean isPrint;

    private String lawCaseTitle;

    private String docPath;
    private String doc2Htmlpath;
    private String date;

    /**
     * 事故詳情 json字符串
     */
    private String lawCaseInfo;

    private int type ;

    @Generated(hash = 1677463010)
    public LawCase(Long id, boolean isPrint, String lawCaseTitle, String docPath,
            String doc2Htmlpath, String date, String lawCaseInfo, int type) {
        this.id = id;
        this.isPrint = isPrint;
        this.lawCaseTitle = lawCaseTitle;
        this.docPath = docPath;
        this.doc2Htmlpath = doc2Htmlpath;
        this.date = date;
        this.lawCaseInfo = lawCaseInfo;
        this.type = type;
    }

    @Generated(hash = 1106844273)
    public LawCase() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getIsPrint() {
        return this.isPrint;
    }

    public void setIsPrint(boolean isPrint) {
        this.isPrint = isPrint;
    }

    public String getLawCaseTitle() {
        return this.lawCaseTitle;
    }

    public void setLawCaseTitle(String lawCaseTitle) {
        this.lawCaseTitle = lawCaseTitle;
    }

    public String getDocPath() {
        return this.docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public String getDoc2Htmlpath() {
        return this.doc2Htmlpath;
    }

    public void setDoc2Htmlpath(String doc2Htmlpath) {
        this.doc2Htmlpath = doc2Htmlpath;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLawCaseInfo() {
        return this.lawCaseInfo;
    }

    public void setLawCaseInfo(String lawCaseInfo) {
        this.lawCaseInfo = lawCaseInfo;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

   


}
