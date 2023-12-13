add_library(BookStore_SDKTest SHARED
        HttpClient.cpp)

target_link_libraries(BookStore_SDKTest PRIVATE
        httplib::httplib
        ssl
        crypto
        )

target_include_directories(BookStore_SDKTest PRIVATE
        include/BookStoreLib)

target_include_directories(BookStore_SDKTest INTERFACE
    include)

# Add include and library paths for OpenSSL
#include_directories(include/openssl)
#link_directories(libs/x86_64)

# Link against OpenSSL libraries
#target_link_libraries(your_target_name ssl crypto)
