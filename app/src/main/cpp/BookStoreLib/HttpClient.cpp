// HttpClient.cpp
#define CPPHTTPLIB_OPENSSL_SUPPORT
#include "httplib.h"
#include "HttpClient.h"

char* HttpClient::sendHttpGet() {
    httplib::Client cli(googleapisURL);
    auto res = cli.Get(path);
    if (res && res->status == 200) {
        const char* body = res->body.c_str();
        char* bodyCopy = strdup(body);
        return bodyCopy;
    } else {
        std::cout << "error code: " << res.error() << std::endl;
    }

    return nullptr;
}

