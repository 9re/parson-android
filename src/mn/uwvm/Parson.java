package mn.uwvm;


public class Parson {
    static {
        System.loadLibrary("parson");
    }
    private static final int JSON_ERROR   = 0;
    private static final int JSON_NULL    = 1;
    private static final int JSON_STRING  = 2;
    private static final int JSON_NUMBER  = 3;
    private static final int JSON_OBJECT  = 4;
    private static final int JSON_ARRAY   = 5;
    private static final int JSON_BOOLEAN = 6;
    
    public static Object parse(String json) {
        return convertCptrToJavaObject(jsonParseString(json));
    }

    static Object convertCptrToJavaObject(long ptr) {
        if (ptr == 0) {
            // NULL
            return null;
        }
        Object value = null;
        int type = jsonValueGetType(ptr);
        switch (type) {
        case JSON_NULL:
            break;
        case JSON_STRING:
            value = jsonValueGetString(ptr);
            break;
        case JSON_NUMBER:
            value = jsonValueGetNumber(ptr);
            break;
        case JSON_OBJECT:
            return new ParsonObject(jsonValueGetObject(ptr));
        case JSON_ARRAY:
            return new ParsonArray(jsonValueGetArray(ptr));
        case JSON_BOOLEAN:
            value = jsonValueGetBoolean(ptr);
            break;
        default:
            break;
        }
        
        jsonValueFree(ptr);
        return value;
    }
    
    native static long jsonParseString(String json);
    
    /* JSON Object */
    native static long jsonObjectGetValue      (long ptr, String name);
    native static String jsonObjectGetString   (long ptr, String name);
    native static long jsonObjectGetObject     (long ptr, String name);
    native static long jsonObjectGetArray      (long ptr, String name);
    native static double jsonObjectGetNumber   (long ptr, String name);
    native static boolean jsonObjectGetBoolean (long ptr, String name);
    
    /* dot get */
    native static long jsonObjectDotgetValue      (long ptr, String name);
    native static String jsonObjectDotgetString   (long ptr, String name);
    native static long jsonObjectDotgetObject     (long ptr, String name);
    native static long jsonObjectDotgetArray      (long ptr, String name);
    native static double jsonObjectDotgetNumber   (long ptr, String name);
    native static boolean jsonObjectDotgetBoolean (long ptr, String name);
    
    native static int jsonObjectGetCount   (long ptr);
    native static String jsonObjectGetName (long ptr, int index);

    native static long jsonArrayGetValue      (long ptr, int index);
    native static String jsonArrayGetString   (long ptr, int index);
    native static long jsonArrayGetObject     (long ptr, int index);
    native static long jsonArrayGetArray      (long ptr, int index);
    native static double jsonArrayGetNumber   (long ptr, int index);
    native static boolean jsonArrayGetBoolean (long ptr, int index);
    native static int jsonArrayGetCount       (long ptr);
    
    native static int jsonValueGetType        (long ptr);
    native static long jsonValueGetObject     (long ptr);
    native static long jsonValueGetArray      (long ptr);
    native static String jsonValueGetString   (long ptr);
    native static double jsonValueGetNumber   (long ptr);
    native static boolean jsonValueGetBoolean (long ptr);
    native static void jsonValueFree          (long ptr);
}
