package hujian.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class List_10_ConcurrentHashMapAndHashMap {
    public static void main(String[] args)
    {
        System.out.println("test ConcurrentHashMap fast-fail");
        ConcurrentHashMap<Integer, String> testConcurrentHashMap = new ConcurrentHashMap<Integer, String>();
        testConcurrentHashMap.put(100, "100");
        testConcurrentHashMap.put(200, "200");
        testConcurrentHashMap.put(300, "300");
        testConcurrentHashMap.put(400, "400");
        testConcurrentHashMap.put(500, "500");
        System.out.println(testConcurrentHashMap.size());
        for (Map.Entry<Integer, String> entry : testConcurrentHashMap.entrySet())
        {
            int key = entry.getKey();
            if (key == 300)
            {
            	System.out.println();
                testConcurrentHashMap.remove(key);
            }
            System.out.println("key=" + entry.getKey());
        }
        System.out.println(testConcurrentHashMap.size());
        for (Map.Entry<Integer, String> entry : testConcurrentHashMap.entrySet())
        {
            System.out.println(entry.getKey() + "-->" + entry.getValue());
        }
    }
}
