#include "parson-android-javah.h"
#include "parson-android.h"
#include <stdio.h>
#include <string.h>
#include <android/log.h>

JNIEXPORT jlong JNICALL Java_mn_uwvm_Parson_jsonParseString
  (JNIEnv *env, jclass klass, jstring json) {
  const char *cstrJson = (*env)->GetStringUTFChars(env, json, JNI_FALSE);
  
  JSON_Value *val = json_parse_string(cstrJson);

  (*env)->ReleaseStringUTFChars(env, json, cstrJson);

  return (jlong) val;
}

JNIEXPORT jlong JNICALL Java_mn_uwvm_Parson_jsonObjectGetValue
  (JNIEnv *env, jclass klass, jlong objPtr, jstring name) {
  JSON_Object *jsonObject = (JSON_Object*) objPtr;
  const char *cName = (*env)->GetStringUTFChars(env, name, JNI_FALSE);
  
  JSON_Value *val = json_object_get_value(jsonObject, cName);

  (*env)->ReleaseStringUTFChars(env, name, cName);

  return (jlong) val;
}

JNIEXPORT jstring JNICALL Java_mn_uwvm_Parson_jsonObjectGetString
  (JNIEnv *env, jclass klass, jlong objPtr, jstring name) {
  JSON_Object *jsonObject = (JSON_Object*) objPtr;
  const char *cName = (*env)->GetStringUTFChars(env, name, JNI_FALSE);
  
  const char *str = json_object_get_string(jsonObject, cName);

  (*env)->ReleaseStringUTFChars(env, name, cName);

  return (*env)->NewStringUTF(env, str);
}

JNIEXPORT jlong JNICALL Java_mn_uwvm_Parson_jsonObjectGetObject
  (JNIEnv *env, jclass klass, jlong objPtr, jstring name) {
  JSON_Object *jsonObject = (JSON_Object*) objPtr;
  const char *cName = (*env)->GetStringUTFChars(env, name, JNI_FALSE);
  
  JSON_Object *obj = json_object_get_object(jsonObject, cName);

  (*env)->ReleaseStringUTFChars(env, name, cName);

  return (jlong) obj;
}
 
JNIEXPORT jlong JNICALL Java_mn_uwvm_Parson_jsonObjectGetArray
  (JNIEnv *env, jclass klass, jlong objPtr, jstring name) {
  JSON_Object *jsonObject = (JSON_Object*) objPtr;
  const char *cName = (*env)->GetStringUTFChars(env, name, JNI_FALSE);

  JSON_Array *arr = json_object_get_array(jsonObject, cName);

  JSON_Value_Type type = json_value_get_type(arr);
  
  (*env)->ReleaseStringUTFChars(env, name, cName);

  return (jlong) arr;
}

JNIEXPORT jdouble JNICALL Java_mn_uwvm_Parson_jsonObjectGetNumber
  (JNIEnv *env, jclass klass, jlong objPtr, jstring name) {
  JSON_Object *jsonObject = (JSON_Object*) objPtr;
  const char *cName = (*env)->GetStringUTFChars(env, name, JNI_FALSE);
  
  double number = json_object_get_number(jsonObject, cName);

  (*env)->ReleaseStringUTFChars(env, name, cName);

  return (jdouble) number;
}

JNIEXPORT jboolean JNICALL Java_mn_uwvm_Parson_jsonObjectGetBoolean
  (JNIEnv *env, jclass klass, jlong objPtr, jstring name) {
  JSON_Object *jsonObject = (JSON_Object*) objPtr;
  const char *cName = (*env)->GetStringUTFChars(env, name, JNI_FALSE);
  
  int val = json_object_get_boolean(jsonObject, cName);

  (*env)->ReleaseStringUTFChars(env, name, cName);

  return val ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jlong JNICALL Java_mn_uwvm_Parson_jsonObjectDotgetValue
  (JNIEnv *env, jclass klass, jlong objPtr, jstring name) {
  JSON_Object *jsonObject = (JSON_Object*) objPtr;
  const char *cName = (*env)->GetStringUTFChars(env, name, JNI_FALSE);
  
  JSON_Value *val = json_object_dotget_value(jsonObject, cName);

  (*env)->ReleaseStringUTFChars(env, name, cName);

  return (jlong) val;
}

JNIEXPORT jstring JNICALL Java_mn_uwvm_Parson_jsonObjectDotgetString
  (JNIEnv *env, jclass klass, jlong objPtr, jstring name) {
  JSON_Object *jsonObject = (JSON_Object*) objPtr;
  const char *cName = (*env)->GetStringUTFChars(env, name, JNI_FALSE);
  
  const char *str = json_object_dotget_string(jsonObject, cName);

  (*env)->ReleaseStringUTFChars(env, name, cName);

  return (*env)->NewStringUTF(env, str);
}

JNIEXPORT jlong JNICALL Java_mn_uwvm_Parson_jsonObjectDotgetObject
  (JNIEnv *env, jclass klass, jlong objPtr, jstring name) {
  JSON_Object *jsonObject = (JSON_Object*) objPtr;
  const char *cName = (*env)->GetStringUTFChars(env, name, JNI_FALSE);
  
  JSON_Object *obj = json_object_dotget_object(jsonObject, cName);

  (*env)->ReleaseStringUTFChars(env, name, cName);

  return (jlong) obj;
}

JNIEXPORT jlong JNICALL Java_mn_uwvm_Parson_jsonObjectDotgetArray
  (JNIEnv *env, jclass klass, jlong objPtr, jstring name) {
  JSON_Object *jsonObject = (JSON_Object*) objPtr;
  const char *cName = (*env)->GetStringUTFChars(env, name, JNI_FALSE);
  
  JSON_Array *arr = json_object_dotget_array(jsonObject, cName);

  (*env)->ReleaseStringUTFChars(env, name, cName);

  return (jlong) arr;
}

JNIEXPORT jdouble JNICALL Java_mn_uwvm_Parson_jsonObjectDotgetNumber
  (JNIEnv *env, jclass klass, jlong objPtr, jstring name) {
  JSON_Object *jsonObject = (JSON_Object*) objPtr;
  const char *cName = (*env)->GetStringUTFChars(env, name, JNI_FALSE);
  
  double number = json_object_dotget_number(jsonObject, cName);

  (*env)->ReleaseStringUTFChars(env, name, cName);

  return (jdouble) number;
}

JNIEXPORT jboolean JNICALL Java_mn_uwvm_Parson_jsonObjectDotgetBoolean
  (JNIEnv *env, jclass klass, jlong objPtr, jstring name) {
  JSON_Object *jsonObject = (JSON_Object*) objPtr;
  const char *cName = (*env)->GetStringUTFChars(env, name, JNI_FALSE);
  
  int val = json_object_dotget_boolean(jsonObject, cName);

  (*env)->ReleaseStringUTFChars(env, name, cName);

  return val ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jint JNICALL Java_mn_uwvm_Parson_jsonObjectGetCount
  (JNIEnv *env, jclass klass, jlong objPtr) {
  JSON_Object *jsonObject = (JSON_Object*) objPtr;

  size_t count = json_object_get_count(jsonObject);
  return (jint) count;
}

JNIEXPORT jstring JNICALL Java_mn_uwvm_Parson_jsonObjectGetName
  (JNIEnv *env, jclass klas, jlong objPtr, jint position) {
  JSON_Object *jsonObject = (JSON_Object*) objPtr;
  
  const char *name = json_object_get_name(jsonObject, position);

  return (*env)->NewStringUTF(env, name);
}

JNIEXPORT jlong JNICALL Java_mn_uwvm_Parson_jsonArrayGetValue
  (JNIEnv *env, jclass klass, jlong arrayPtr, jint index) {
  JSON_Array *jsonArray = (JSON_Array*) arrayPtr;
  JSON_Value *val = json_array_get_value(jsonArray, (size_t)index);
  return (jlong)val;
}

JNIEXPORT jstring JNICALL Java_mn_uwvm_Parson_jsonArrayGetString
  (JNIEnv *env, jclass klass, jlong arrayPtr, jint index) {
  JSON_Array *arr = (JSON_Array*) arrayPtr;
  const char *str = json_array_get_string(arr, (size_t)index);

  return (*env)->NewStringUTF(env, str);
}

JNIEXPORT jlong JNICALL Java_mn_uwvm_Parson_jsonArrayGetObject
  (JNIEnv *env, jclass klass, jlong arrayPtr, jint index) {
  JSON_Array *jsonArray = (JSON_Array*) arrayPtr;
  JSON_Object *obj = json_array_get_object(jsonArray, (size_t)index);
  return (jlong)obj;
}

JNIEXPORT jlong JNICALL Java_mn_uwvm_Parson_jsonArrayGetArray
  (JNIEnv *env, jclass klass, jlong arrayPtr, jint index) {
  JSON_Array *jsonArray = (JSON_Array*) arrayPtr;
  JSON_Array *arr = json_array_get_array(jsonArray, (size_t)index);
  return (jlong)arr;
}

JNIEXPORT jdouble JNICALL Java_mn_uwvm_Parson_jsonArrayGetNumber
  (JNIEnv *env, jclass klass, jlong arrayPtr, jint index) {
  JSON_Array *jsonArray = (JSON_Array*) arrayPtr;
  double number = json_array_get_number(jsonArray, (size_t)index);
  return (jdouble) number;
}

JNIEXPORT jboolean JNICALL Java_mn_uwvm_Parson_jsonArrayGetBoolean
  (JNIEnv *env, jclass klass, jlong arrayPtr, jint index) {
  JSON_Array *jsonArray = (JSON_Array*) arrayPtr;
  int val = json_array_get_boolean(jsonArray, (size_t)index);
  return val ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jint JNICALL Java_mn_uwvm_Parson_jsonArrayGetCount
  (JNIEnv *env, jclass klass, jlong arrayPtr) {
  JSON_Array *jsonArray = (JSON_Array*) arrayPtr;
  size_t size = json_array_get_count(jsonArray);
  return (jint)size;
}

JNIEXPORT jint JNICALL Java_mn_uwvm_Parson_jsonValueGetType
  (JNIEnv *env, jclass klass, jlong ptr) {
  JSON_Value *val = (JSON_Value*) ptr;

  if (!val) {
    return (jint) JSONError;
  }

  jint k = (jint) json_value_get_type(val);
  
  return k;
}

JNIEXPORT jlong JNICALL Java_mn_uwvm_Parson_jsonValueGetObject
  (JNIEnv *env, jclass klass, jlong ptr) {
  return (jlong) json_value_get_object((JSON_Value*)ptr);
}

JNIEXPORT jlong JNICALL Java_mn_uwvm_Parson_jsonValueGetArray
  (JNIEnv *env, jclass klass, jlong ptr) {
  return (jlong) json_value_get_array((JSON_Value*)ptr);
}

JNIEXPORT jstring JNICALL Java_mn_uwvm_Parson_jsonValueGetString
  (JNIEnv *env, jclass klass, jlong ptr) {
  const char *str = json_value_get_string((JSON_Value*)ptr);
  return (*env)->NewStringUTF(env, str);
}

JNIEXPORT jdouble JNICALL Java_mn_uwvm_Parson_jsonValueGetNumber
  (JNIEnv *env, jclass klass, jlong ptr) {
  return (jdouble) json_value_get_number((JSON_Value*)ptr);
}

JNIEXPORT jboolean JNICALL Java_mn_uwvm_Parson_jsonValueGetBoolean
  (JNIEnv *env, jclass klass, jlong ptr) {
  int val = json_value_get_boolean((JSON_Value*)ptr);
  return val ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT void JNICALL Java_mn_uwvm_Parson_jsonValueFree
  (JNIEnv *env, jclass klass, jlong ptr) {
  JSON_Value *val = (JSON_Value*) ptr;
  if (val) {
    json_value_free(val);
  }
}
