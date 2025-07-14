// File: FakeDB.java
package io.backend.backend_caffeine;

import java.util.HashMap;
import java.util.Map;

public class FakeDB {
    public static final Map<String, String> DATA = new HashMap<>();

    static {
        DATA.put("user-1", "ID-001");
        DATA.put("user-2", "ID-OO2");
        DATA.put("user-3", "ID-OO3");
        DATA.put("user-4", "ID-OO4");
        DATA.put("user-5", "ID-OO5");
    }
}

