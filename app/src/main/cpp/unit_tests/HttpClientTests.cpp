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
    auto actual = HttpClient::sendHttpGet();

    ASSERT_NE(actual, nullptr);
}