package getfluxed.minemagicka.api.nature;

public class TreeSap {

    private final String name;
    private final int colour;

    public TreeSap(String name, int colour) {
        this.name = name;
        this.colour = colour;
    }

    public String getName() {
        return name;
    }

    public int getColour() {
        return colour;
    }

}
