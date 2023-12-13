# Sets the minimum CMake version required for this project.
cmake_minimum_required(VERSION 3.22.1)

project("application")

add_subdirectory(BookStoreLib)

include(FetchContent)

FetchContent_Declare(
        httplib
        URL
        https://github.com/yhirose/cpp-httplib/archive/refs/tags/v0.14.2.tar.gz
)
FetchContent_MakeAvailable(httplib)

add_library(${CMAKE_PROJECT_NAME} SHARED
        native-lib.cpp
        )

target_link_libraries(${CMAKE_PROJECT_NAME}
        android
        log)

add_library(ssl STATIC IMPORTED)
set_target_properties(ssl
        PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/libs/${ANDROID_ABI}/libssl.a)

add_library(crypto STATIC IMPORTED)
set_target_properties(crypto
        PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/libs/${ANDROID_ABI}/libcrypto.a)

message(ANDROID_ABI: ${ANDROID_ABI})