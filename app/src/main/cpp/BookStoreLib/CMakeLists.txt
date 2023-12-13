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

