package mn.uwvm;

public class ParsonValue {
    public final long ptr;
    ParsonValue(long ptr) {
        this.ptr = ptr;
    }
    
    public int getType() { return Parson.jsonValueGetType(ptr); }
    public void free  () { Parson.jsonValueFree(ptr); }
}
