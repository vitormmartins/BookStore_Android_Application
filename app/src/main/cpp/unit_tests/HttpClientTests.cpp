// Include the header file for HttpClient
#include <gtest/gtest.h>
#include <gmock/gmock-matchers.h>

#include "../BookStoreLib/include/BookStoreLib/HttpClient.h"

class HttpClientTests : public ::testing::Test {
protected:
    void SetUp() override {
        // Optional: Add setup code that will be called before each test
    }

    void TearDown() override {
        // Optional: Add teardown code that will be called after each test
    }
};

TEST_F(HttpClientTests, SendHttpGetSuccess) {
    std::string url = "https://www.googleapis.com/books/v1/volumes?q=ios&maxResults=20&startIndex=0";
    auto actual = HttpClient::sendHttpGet(nullptr);

    ASSERT_NE(actual, nullptr);
}

TEST_F(HttpClientTests, WriteCallbackSuccess) {
    std::string output;
    char contents[] = "Hello World!";
    auto actual = HttpClient::writeCallback(contents, 1, sizeof(contents), &output);

    ASSERT_EQ(actual, sizeof(contents));
    ASSERT_STREQ(output.c_str(), "Hello World!");
}

