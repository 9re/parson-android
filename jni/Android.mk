LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE          := libparson

LOCAL_C_INCLUDES      += $(LOCAL_PATH)/parson
LOCAL_CFLAGS          := -std=c99
LOCAL_LDFLAGS         := -llog
LOCAL_SRC_FILES       := 	\
	parson/parson.c 	\
	parson-android.c


include $(BUILD_SHARED_LIBRARY)
