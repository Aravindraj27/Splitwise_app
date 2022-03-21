package com.gar.machinecoding.model.expense;


public class ExpenseMetadata {
    private String name;
    private String ImgUrl;
    private String Notes;

    public ExpenseMetadata(String name, String imgUrl, String notes) {
        this.name = name;
        ImgUrl = imgUrl;
        Notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }
}
