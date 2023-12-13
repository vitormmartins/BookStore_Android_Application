add_library(BookStore_SDKTest SHARED
        HttpClient.cpp)

target_include_directories(BookStore_SDKTest PRIVATE
        include/BookStoreLib
        include/openssl
        )

target_include_directories(BookStore_SDKTest INTERFACE
        include)

target_link_libraries(BookStore_SDKTest PRIVATE
        httplib::httplib
        ssl
        crypto
        )
