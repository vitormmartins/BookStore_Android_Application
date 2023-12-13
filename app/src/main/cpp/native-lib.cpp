// native-lib.cpp
#include <jni.h>
#include <string>
#include "BookStoreLib/include/BookStoreLib/HttpClient.h"

extern "C"
JNIEXPORT jstring JNICALL
Java_com_bookstore_bookstorecsdksibsapplication_MainActivity_getDataFromJNI(JNIEnv *env,
                                                                            jobject thiz) {
    HttpClient httpClient;

    char* responseData = httpClient.sendHttpGet();

    if (responseData) {
        std::string responseString(responseData);

        delete[] responseData;

        return env->NewStringUTF(responseString.c_str());
    } else {
        return env->NewStringUTF("Error in HTTP request");
    }
}