package com.highrock.storage.bean;


public class StorageMaterial {
    private Integer id;
    private String material_code;
    private Integer material_storage_num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaterial_code() {
        return material_code;
    }

    public void setMaterial_code(String material_code) {
        this.material_code = material_code;
    }

    public Integer getMaterial_storage_num() {
        return material_storage_num;
    }

    public void setMaterial_storage_num(Integer material_storage_num) {
        this.material_storage_num = material_storage_num;
    }
}
