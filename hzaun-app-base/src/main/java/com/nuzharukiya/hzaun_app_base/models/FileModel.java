package com.nuzharukiya.hzaun_app_base.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nuzha Rukiya on 18/01/27.
 */

public class FileModel implements Serializable {
    private String fileName = "";
    private String fileExtension = "";
    private byte[] fileContents;
    private String fileId = "";
    private String lastModified = "";
    private String fileUrl = "";     // File Path
    private String webViewLink = "";
    private String fileType = "";
    private String attachmentCount = ""; // Content  Count
    private String createdByName = "";
    private String pdf_tempFileName = "";

    private boolean bIsDefault = false;

    public FileModel() {
    }

    public FileModel(String fileId) {
        this.fileId = fileId;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public byte[] getFileContents() {
        return fileContents == null ? new byte[0] : fileContents;
    }

    public void setFileContents(byte[] fileContents) {
        this.fileContents = fileContents;

        if (fileContents!= null) {
            setAttachmentCount(String.valueOf(fileContents.length));
        }
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        SimpleDateFormat dateMonthFormatter = new SimpleDateFormat("dd MMM");
        this.lastModified = dateMonthFormatter.format(lastModified);
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getWebViewLink() {
        return webViewLink;
    }

    public void setWebViewLink(String webViewLink) {
        this.webViewLink = webViewLink;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getAttachmentCount() {
        return attachmentCount;
    }

    public void setAttachmentCount(String attachmentCount) {
        this.attachmentCount = attachmentCount;
    }

    public String getPdf_tempFileName() {
        return pdf_tempFileName;
    }

    public void setPdf_tempFileName(String pdf_tempFileName) {
        this.pdf_tempFileName = pdf_tempFileName;
    }

    public boolean isbDefault() {
        return bIsDefault;
    }

    public void setbIsDefault(boolean bIsDefault) {
        this.bIsDefault = bIsDefault;
    }
}