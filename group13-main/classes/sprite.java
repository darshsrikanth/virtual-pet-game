package classes;

public class sprite {

    private String curSprite;
    private String spriteType;
    private int spriteState;

    public sprite(Pet curPet){
        spriteType = curPet.getPetType().getAnimalType();
        spriteState = curPet.getState().getStateType();
        curSprite = "/images/" + spriteType + spriteState + ".png";
    }

    public void setState(int stateNum){
        spriteState = stateNum;
    }


    public String getCurSprite(Pet curPet){
        spriteType = curPet.getPetType().getAnimalType();
        spriteState = curPet.getState().getStateType();
        curSprite = "/images/" + spriteType + spriteState + ".png";
        return curSprite;
    }




}
