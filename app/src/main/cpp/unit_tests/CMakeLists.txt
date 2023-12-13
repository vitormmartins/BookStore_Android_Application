add_executable(HttpClientTests HttpClientTests.cpp)

target_link_libraries(HttpClientTests PRIVATE
        BookStore_SDKTest
        GTest::gtest_main
        GTest::gmock)

include(GoogleTest)
gtest_discover_tests(HttpClientTests)
