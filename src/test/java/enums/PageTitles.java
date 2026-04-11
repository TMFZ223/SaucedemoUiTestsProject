package enums;

public enum PageTitles {
    Products("Products"),
    Cart("Your Cart");

    private final String displayName;

    PageTitles(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
