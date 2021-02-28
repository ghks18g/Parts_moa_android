package jong.sku.study;

public class CommonSpinner {
    private String key;
    private String value;

    public CommonSpinner(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey(){
        return key;
    }

    public String getValue() {return value;}

    @Override
    public String toString(){
        return value;
    }

}
