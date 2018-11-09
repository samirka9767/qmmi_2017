package domain;

import java.io.Serializable;
import java.util.List;

public class roles implements Serializable {
    private String menuID;
    private String levelID;
    private List<String> buttons;
    private List<String> buttonID;

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public List<String> getButtons() {
        return buttons;
    }

    public void setButtons(List<String> buttons) {
        this.buttons = buttons;
    }

    public String getLevelID() {
        return levelID;
    }

    public void setLevelID(String levelID) {
        this.levelID = levelID;
    }

    public List<String> getButtonID() {
        return buttonID;
    }

    public void setButtonID(List<String> buttonID) {
        this.buttonID = buttonID;
    }
}