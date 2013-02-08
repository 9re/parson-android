package mn.uwvm;

public class ParsonArray extends ParsonValue {
    ParsonArray(long ptr) {
        super(ptr);
    }
    static ParsonArray create(long ptr) {
        if (ptr == 0) {
            return null;
        } else {
            return new ParsonArray(ptr);
        }
    }
    
    public Object getValue        (int index) { return Parson.convertCptrToJavaObject(Parson.jsonArrayGetValue(ptr, index)); }
    public String getString       (int index) { return Parson.jsonArrayGetString(ptr, index); }
    public ParsonObject getObject (int index) { return (ParsonObject) ParsonObject.create(Parson.jsonArrayGetObject(ptr, index)); }
    public ParsonArray getArray   (int index) { return (ParsonArray) ParsonArray.create(Parson.jsonArrayGetArray(ptr, index)); }
    public double getNumber       (int index) { return Parson.jsonArrayGetNumber(ptr, index); }
    public boolean getBoolean     (int index) { return Parson.jsonArrayGetBoolean(ptr, index); }
    public int getCount           ()          { return Parson.jsonArrayGetCount(ptr); }
}
