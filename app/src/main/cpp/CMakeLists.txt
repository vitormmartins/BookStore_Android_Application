# Sets the minimum CMake version required for this project.
cmake_minimum_required(VERSION 3.22.1)

project("application")


include(FetchContent)

FetchContent_Declare(
        googletest
        GIT_REPOSITORY https://github.com/google/googletest.git
        GIT_TAG b796f7d44681514f58a683a3a71ff17c94edb0c1
)
set(gtest_force_shared_crt ON CACHE BOOL "" FORCE)
FetchContent_MakeAvailable(googletest)

add_link_options(-lz)

add_library(ssl STATIC IMPORTED)
set_target_properties(ssl
        PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/libs/openssl-android/${ANDROID_ABI}/libssl.a)

add_library(crypto STATIC IMPORTED)
set_target_properties(crypto
        PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/libs/openssl-android/${ANDROID_ABI}/libcrypto.a)

add_library(curl STATIC IMPORTED)
set_target_properties(curl
        PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/libs/curl-android/${ANDROID_ABI}/libcurl.a)

message(ANDROID_ABI: ${ANDROID_ABI})

include_directories(${CMAKE_SOURCE_DIR}/include)

enable_testing()

add_subdirectory(BookStoreLib)
add_subdirectory(unit_tests)

add_library(${CMAKE_PROJECT_NAME} SHARED
        native-lib.cpp
        )

target_include_directories(${CMAKE_PROJECT_NAME} PRIVATE ${ZLIB_INCLUDE_DIRS})

target_link_libraries(${CMAKE_PROJECT_NAME}
        BookStore_SDKTest
        libz
        android
        log)
