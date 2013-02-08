package mn.uwvm;

public class ParsonObject extends ParsonValue {
    ParsonObject(long ptr) {
        super(ptr);
    }
    static ParsonObject create(long ptr) {
        if (ptr == 0) {
            return null;
        } else {
            return new ParsonObject(ptr);
        }
    }
    public Object getValue           (String name) { return Parson.convertCptrToJavaObject(Parson.jsonObjectGetValue(ptr, name)); }
    public String getString          (String name) { return Parson.jsonObjectGetString(ptr, name); }
    public ParsonObject getObject    (String name) { return (ParsonObject) ParsonObject.create(Parson.jsonObjectGetObject(ptr, name)); }
    public ParsonArray getArray      (String name) { return (ParsonArray) ParsonArray.create(Parson.jsonObjectGetArray(ptr, name)); }
    public double getNumber          (String name) { return Parson.jsonObjectGetNumber(ptr, name); }
    public boolean getBoolean        (String name) { return Parson.jsonObjectGetBoolean(ptr, name); }
    public Object dotGetValue        (String name) { return Parson.convertCptrToJavaObject(Parson.jsonObjectDotgetValue(ptr, name)); }
    public String dotGetString       (String name) { return Parson.jsonObjectDotgetString(ptr, name); }
    public ParsonObject dotGetObject (String name) { return (ParsonObject) ParsonObject.create(Parson.jsonObjectDotgetObject(ptr, name)); }
    public ParsonArray dotGetArray   (String name) { return (ParsonArray) ParsonArray.create(Parson.jsonObjectDotgetArray(ptr, name)); }
    public double dotGetNumber       (String name) { return Parson.jsonObjectDotgetNumber(ptr, name); }
    public boolean dotGetBoolean     (String name) { return Parson.jsonObjectDotgetBoolean(ptr, name); }
    public int getCount              ()            { return Parson.jsonObjectGetCount(ptr); }
    public String getName            (int index)   { return Parson.jsonObjectGetName(ptr, index); }
}
