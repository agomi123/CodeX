package com.example.codek;

public class FileName {
    String filename,filedate;

    private FileName() {
    }

    public String getFiledate() {
        return filedate;
    }

    public void setFiledate(String filedate) {
        this.filedate = filedate;
    }

    public FileName(String filename,String dater) {
        this.filename = filename;
        this.filedate=dater;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
