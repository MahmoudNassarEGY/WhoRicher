package com.mnassar.WhoRicher;


public class Questions {
    int ID ;
    int ImageID ;
    int NetWorth;
    String CelebName;

    public void setID ( int ID){
        this.ID = ID;
    }

    public void setName(String celebName) {
        CelebName = celebName;

    }
    public void setNetWorth(int NetWorth) {
        this.NetWorth = NetWorth;

    }
    public int GetID() {
        return ID;

    }
    public String getName() {
        return CelebName;

    }
    public int getNetWorth(){
        return NetWorth;
    }
    public int getImageID(){
        return ImageID;
    }

    Questions(int ID , int ImageID , int NetWorth, String CelebName){
        this.ID =ID;
        this.ImageID = ImageID;
        this.NetWorth=NetWorth;
        this.CelebName = CelebName;
    }

}
