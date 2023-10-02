package src.backend;

public class Utils {
    public String addZeros(int num) {
        switch (String.valueOf(num).length()) {
            case 1: return "000" + num;
            case 2: return "00" + num;
            case 3: return "0" + num;
            case 4: return "" + num;
        }
        return null;
    }
}
