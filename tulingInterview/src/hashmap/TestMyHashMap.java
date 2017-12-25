package hashmap;

public class TestMyHashMap {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<String,Integer>();

        Integer wuk = map.put("WUK", 30);
        Integer wuk1 = map.put("WUK", 31);
        System.out.println(wuk1);
    }
}
