package com.dovantuan.ex1_md18312.DTO;

public class CatDTO {
    int id;
    String name;

    public String toString(){
        return this.id + "--------" + this.name;
    }

//    public CatDTO(){
//
//    }
//
//    public CatDTO(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
