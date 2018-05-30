package com.fufu.disconf;

import java.io.InputStream;

public class FileStream {

    private String fileName;
    private InputStream inputStream;
    private Integer fileCode;

    public Integer getFileCode() {
        return fileCode;
    }

    public void setFileCode(Integer fileCode) {
        this.fileCode = fileCode;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFileName() {

        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}