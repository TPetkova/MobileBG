package data;

public enum MainMenuItems {
    HOME("Начало"),
    PUBLISH("Публикуване"),
    SEARCH("Търсене"),
    NEWS("Новини"),
    RENT_A_CAR("Rent-a-car"),
    AVERAGE_PRICES("Средни цени"),
    DEALERS("Дилъри"),
    MY_POSTS("Моите обяви");

    private final String localeBG;

    MainMenuItems(String localeBG) {
        this.localeBG = localeBG;
    }

    public String getLocalized(String locale) {
        return switch (locale) {
            case "BG" -> localeBG;
            default -> "";
        };
    }
}
