package mn.uwvm.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

import mn.uwvm.Parson;
import mn.uwvm.ParsonArray;
import mn.uwvm.ParsonObject;
import mn.uwvm.ParsonValue;
import mn.uwvm.R;

import org.apache.commons.io.IOUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ParsonAndroidTest extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    final Tester tester = new Tester();
                    testSuite1(tester);
                    testSuite2(tester);
                    testSuite3(tester);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            TextView textView = (TextView) findViewById(R.id.parson_test);
                            textView.setText(tester.getReport());
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    protected void testSuite1(Tester tester) throws IOException {
        Log.v("parson-test", "test suite 1 start");
        testParseFile(tester, "test_1_1.txt");
        testParseFile(tester, "test_1_2.txt");
        testParseFile(tester, "test_1_3.txt");
    }

    private void testParseFile(Tester tester, String assetsName) throws IOException {
        ParsonValue value = (ParsonValue) Parson.parse(getContentOfFile(assetsName));
        tester.isNotNull("parse " + assetsName, value);
        if (value != null) {
            value.free();
        }
    }
    
    protected void testSuite2(Tester tester) throws IOException {
        Log.v("parson-test", "test suite 2 start");
        String contentOfFile = getContentOfFile("test_2.txt");
        ParsonValue rootValue = (ParsonValue) Parson.parse(contentOfFile);
        tester.isNotNull("root object", rootValue);
        if (rootValue == null) {
            return;
        }
        tester.equals("root object is JSONObject", rootValue instanceof ParsonObject, Boolean.TRUE);
        ParsonObject object = (ParsonObject) rootValue;
        tester.equals("string: lorem ipsum", object.getString("string"), "lorem ipsum");
        tester.equals("utf string: lorem ipsum", object.getString("utf string"), "lorem ipsum");
        tester.equals("utf-8 string: あいうえお", object.getString("utf-8 string"), "あいうえお");
        tester.equals("positive one: 1.0", object.getNumber("positive one"), 1.0);
        tester.equals("negative one: -1.0", object.getNumber("negative one"), -1.0);
        tester.equals("hard to parse number: -0.000314", object.getNumber("hard to parse number"), -0.000314);
        tester.equals("boolean true: true", object.getBoolean("boolean true"), Boolean.TRUE);
        tester.equals("boolean false: false", object.getBoolean("boolean false"), Boolean.FALSE);
        tester.isNull("null: null", object.getValue("null"));
        
        ParsonArray array = object.getArray("string array");
        if (tester.equals(
                "string array not null and count > 1",
                array != null && array.getCount() > 1, Boolean.TRUE)) {
            tester.equals("string array index 0: lorem", array.getString(0), "lorem");
            tester.equals("string array index 1: ipsum", array.getString(1), "ipsum");
        }
        
        tester.isNull("non existent array: null", object.getArray("non existent array"));
        tester.equals("object.nested string: str", object.dotGetString("object.nested string"), "str");
        tester.equals("object.nested true: true", object.dotGetBoolean("object.nested true"), Boolean.TRUE);
        tester.equals("object.nested false: false", object.dotGetBoolean("object.nested false"), Boolean.FALSE);
        tester.isNull("object.nested null: null", object.dotGetValue("object.nested null"));
        tester.equals("object.nested number", object.dotGetNumber("object.nested number"), 123.0);
        tester.isNull("should.be.null: null", object.dotGetValue("should.be.null"));
        tester.isNull("should.be.null.: null", object.dotGetValue("should.be.null."));
        tester.isNull(".: null", object.dotGetValue("."));
        tester.isNull(": null", object.dotGetValue(""));
        
        array = object.dotGetArray("object.nested array");
        if (tester.equals(
                "string array not null and count > 1",
                array != null && array.getCount() > 1, Boolean.TRUE)) {
            tester.equals("string array index 0: lorem", array.getString(0), "lorem");
            tester.equals("string array index 1: ipsum", array.getString(1), "ipsum");
        }
        object.free();
    }

    protected void testSuite3(Tester tester) throws IOException {
        Log.v("parson-test", "test suite 3 start");
        tester.isNull("NULL", Parson.parse(null));
        tester.isNull("\"\"", Parson.parse("")); /* empty string */
        tester.isNull("\"[\"lorem\",]\"", Parson.parse("[\"lorem\",]"));
        tester.isNull("\"[,]\"", Parson.parse("[,]"));
        tester.isNull("\"[,\"", Parson.parse("[,"));
        tester.isNull("\"[\"", Parson.parse("["));
        tester.isNull("\"]\"", Parson.parse("]"));
        tester.isNull("\"{\"a\":0,\"a\":0}\"", Parson.parse("{\"a\":0,\"a\":0}")); /* duplicate keys */
        tester.isNull("\"{:,}\"", Parson.parse("{:,}"));
        tester.isNull("\"{,}\"", Parson.parse("{,}"));
        tester.isNull("\"{,\"", Parson.parse("{,"));
        tester.isNull("\"{:\"", Parson.parse("{:"));
        tester.isNull("\"{\"", Parson.parse("{"));
        tester.isNull("\"}\"", Parson.parse("}"));
        tester.isNull("\"x\"", Parson.parse("x"));
        tester.isNull("\"\"string\"\"", Parson.parse("\"string\""));
        tester.isNull("\"{:\"no name\"}\"", Parson.parse("{:\"no name\"}"));
        tester.isNull("\"[,\"no first value\"]\"", Parson.parse("[,\"no first value\"]"));
        tester.isNull("\"[\"\\u00zz\"]\"", Parson.parse("[\"\\u00zz\"]")); /* invalid utf value */
        tester.isNull("\"[\"\\u00\"]\"", Parson.parse("[\"\\u00\"]")); /* invalid utf value */
        tester.isNull("\"[\"\\u\"]\"", Parson.parse("[\"\\u\"]")); /* invalid utf value */
        tester.isNull("\"[\"\\\"]\"", Parson.parse("[\"\\\"]")); /* control character */
        tester.isNull("\"[\"\"\"]\"", Parson.parse("[\"\"\"]")); /* control character */
        // this test won't pass because \0 is converted to \u0000 and it's utf-8 byte
        // representation is 0xC0,0x80 and hence it's not a control characters.
        //tester.isNull("\"[\"\0\"]\"", Parson.parse("[\"\0\"]")); /* control character */
        tester.isNull("\"[\"\u0007\"]\"", Parson.parse("[\"\u0007\"]")); /* control character */
        tester.isNull("\"[\"\b\"]\"", Parson.parse("[\"\b\"]")); /* control character */
        tester.isNull("\"[\"\t\"]\"", Parson.parse("[\"\t\"]")); /* control character */
        tester.isNull("\"[\"\n\"]\"", Parson.parse("[\"\n\"]")); /* control character */
        tester.isNull("\"[\"\f\"]\"", Parson.parse("[\"\f\"]")); /* control character */
        tester.isNull("\"[\"\r\"]\"", Parson.parse("[\"\r\"]")); /* control character */
        tester.isNull("[[[[[[[[[[[[[[[[[[[[\"hi\"]]]]]]]]]]]]]]]]]]]]", Parson.parse("[[[[[[[[[[[[[[[[[[[[\"hi\"]]]]]]]]]]]]]]]]]]]]")); /* too deep */
        tester.isNull("\"[0x2]\"", Parson.parse("[0x2]"));    /* hex */
        tester.isNull("\"[0X2]\"", Parson.parse("[0X2]"));    /* HEX */
        tester.isNull("\"[07]\"", Parson.parse("[07]"));     /* octals */
        tester.isNull("\"[0070]\"", Parson.parse("[0070]"));
        tester.isNull("\"[07.0]\"", Parson.parse("[07.0]"));
        tester.isNull("\"[-07]\"", Parson.parse("[-07]"));
        tester.isNull("\"[-007]\"", Parson.parse("[-007]"));
        tester.isNull("\"[-07.0]\"", Parson.parse("[-07.0]"));
    }
    
    protected String getContentOfFile(String assetsName) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream open = getAssets().open(assetsName);
        InputStreamReader reader = new InputStreamReader(open, Charset.forName("utf-8"));
        IOUtils.copy(reader, baos);
        return new String(baos.toByteArray());
    }
}
