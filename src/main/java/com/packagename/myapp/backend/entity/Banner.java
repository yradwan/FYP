package com.packagename.myapp.backend.entity;

import javax.persistence.ManyToOne;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Banner extends AbstractEntity implements Cloneable{

    private String gameID;
    private String userName;
    private String rarity1;
    private int rarity1Value;
    private String rarity2;
    private int rarity2Value;
    private String rarity3;
    private int rarity3Value;
    private String rarity4;
    private int rarity4Value;
    private String rarity5;
    private int rarity5Value;
    private String rarity6;
    private int rarity6Value;
    private String rarity7;
    private int rarity7Value;
    private String rarity8;
    private int rarity8Value;
    private String rarity9;
    private int rarityValue;
    private String rarity10;
    private int rarity10Value;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRarity1() {
        return rarity1;
    }

    public void setRarity1(String rarity1) {
        this.rarity1 = rarity1;
    }

    public int getRarity1Value() {
        return rarity1Value;
    }

    public void setRarity1Value(int rarity1Value) {
        this.rarity1Value = rarity1Value;
    }

    public String getRarity2() {
        return rarity2;
    }

    public void setRarity2(String rarity2) {
        this.rarity2 = rarity2;
    }

    public int getRarity2Value() {
        return rarity2Value;
    }

    public void setRarity2Value(int rarity2Value) {
        this.rarity2Value = rarity2Value;
    }

    public String getRarity3() {
        return rarity3;
    }

    public void setRarity3(String rarity3) {
        this.rarity3 = rarity3;
    }

    public int getRarity3Value() {
        return rarity3Value;
    }

    public void setRarity3Value(int rarity3Value) {
        this.rarity3Value = rarity3Value;
    }

    public String getRarity4() {
        return rarity4;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public void setRarity4(String rarity4) {
        this.rarity4 = rarity4;
    }

    public int getRarity4Value() {
        return rarity4Value;
    }

    public void setRarity4Value(int rarity4Value) {
        this.rarity4Value = rarity4Value;
    }

    public String getRarity5() {
        return rarity5;
    }

    public void setRarity5(String rarity5) {
        this.rarity5 = rarity5;
    }

    public int getRarity5Value() {
        return rarity5Value;
    }

    public void setRarity5Value(int rarity5Value) {
        this.rarity5Value = rarity5Value;
    }

    public String getRarity6() {
        return rarity6;
    }

    public void setRarity6(String rarity6) {
        this.rarity6 = rarity6;
    }

    public int getRarity6Value() {
        return rarity6Value;
    }

    public void setRarity6Value(int rarity6Value) {
        this.rarity6Value = rarity6Value;
    }

    public String getRarity7() {
        return rarity7;
    }

    public void setRarity7(String rarity7) {
        this.rarity7 = rarity7;
    }

    public int getRarity7Value() {
        return rarity7Value;
    }

    public void setRarity7Value(int rarity7Value) {
        this.rarity7Value = rarity7Value;
    }

    public String getRarity8() {
        return rarity8;
    }

    public void setRarity8(String rarity8) {
        this.rarity8 = rarity8;
    }

    public int getRarity8Value() {
        return rarity8Value;
    }

    public void setRarity8Value(int rarity8Value) {
        this.rarity8Value = rarity8Value;
    }

    public String getRarity9() {
        return rarity9;
    }

    public void setRarity9(String rarity9) {
        this.rarity9 = rarity9;
    }

    public int getRarityValue() {
        return rarityValue;
    }

    public void setRarityValue(int rarityValue) {
        this.rarityValue = rarityValue;
    }

    public String getRarity10() {
        return rarity10;
    }

    public void setRarity10(String rarity10) {
        this.rarity10 = rarity10;
    }

    public int getRarity10Value() {
        return rarity10Value;
    }

    public void setRarity10Value(int rarity10Value) {
        this.rarity10Value = rarity10Value;
    }


}
